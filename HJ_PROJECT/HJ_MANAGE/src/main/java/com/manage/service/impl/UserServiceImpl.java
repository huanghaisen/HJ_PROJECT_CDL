package com.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.framework.util.Md5Encoder;
import com.framework.util.StringUtil;
import com.framework.util.TimeUtils;
import com.manage.service.UserService;
import com.model.TSysUser;

@Service
public class UserServiceImpl implements UserService{

	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;
	
	/**
	 * 搜索SQL加参数条件（抽出来复用）
	 * @param map
	 * @return
	 */
	public String SQLAddParams(Map<String,Object> map){
		StringBuffer bf=new StringBuffer("");
		if (map.containsKey("nickName") && StringUtil.isNotEmpty(map.get("nickName").toString())) {
			bf.append(" and c.nickName like '%").append(map.get("nickName").toString()).append("%'");
		}
		
		if (map.containsKey("loginName") && StringUtil.isNotEmpty(map.get("loginName").toString())) {
			bf.append(" and c.loginName like '%").append(map.get("loginName").toString()).append("%'");
		}
		if (map.containsKey("email") && StringUtil.isNotEmpty(map.get("email").toString())) {
			bf.append(" and c.email like '%").append(map.get("email").toString()).append("%'");
		}
		if (map.containsKey("phone") && StringUtil.isNotEmpty(map.get("phone").toString())) {
			bf.append(" and c.phone like '%").append(map.get("phone").toString()).append("%'");
		}
		if (map.containsKey("createUser") && StringUtil.isNotEmpty(map.get("createUser").toString())) {
			bf.append(" and c.createUser like '%").append(map.get("createUser").toString()).append("%'");
		}
		//System.out.println("bf: "+bf);
		return bf.toString();
	}
    
	@Override
	public TSysUser findById(Long userId) {
		 List<Object> params=new ArrayList<Object>();
	     TSysUser user=null;	     
		    if (userId!=null) {
	            params.add(userId);
	            StringBuffer hql=new StringBuffer(" from TSysUser c where 1=1");
	            hql.append(" and c.id=? ");
	            try{
	            	user=(TSysUser) this.baseDao.getSingleByHsql(hql.toString(), params);
	            }catch(Exception e){
	            	System.out.println("查找用户失败");
	            }
		    }else{
		    	System.out.println("userId为空");
		    }
	        return user;
	}
	
	@Override
	public TSysUser findBySsotoken(String ssoToken) {
		 List<Object> params=new ArrayList<Object>();
	     TSysUser user=null;	     
		    if (ssoToken!=null || !ssoToken.equals("")) {
	            params.add(ssoToken);
	            StringBuffer hql=new StringBuffer(" from TSysUser c where 1=1");
	            hql.append(" and c.ssoToken=? ");
	            try{
	            	user=(TSysUser) this.baseDao.getSingleByHsql(hql.toString(), params);
	            }catch(Exception e){
	            	System.out.println("查找用户失败");
	            }
		    }
	        return user;
	} 

	@Override
	public List<TSysUser> findAll() {
		List<TSysUser> list=null;
		try{
		list=this.baseDao.listByHql("from TSysUser c where 1=1", null);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("查找所有用户失败");
		}
		return list;
	}

	@Override
	public TSysUser addOrUpdate(TSysUser user) {
		TSysUser model=null;
		try{
		 model=(TSysUser) this.baseDao.saveOrupdate(user);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("用户保存失败");
		}
		return model;
	}
	
	public void saveuser(TSysUser user){
		this.baseDao.savePo(user);
	}

	@Override
	public void del(Long id) {
		 List<Object> params=new ArrayList<Object>();
		 params.add(id);
		 try{
	        TSysUser user=(TSysUser) this.baseDao.getSingleByHsql(" from TSysUser c where c.id=?", params);
	        this.baseDao.remove(user);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	@Override
	public TSysUser userLogin(String name,String password){
		TSysUser user=null;
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		params.add(password);
		try{
		user=(TSysUser) this.baseDao.getSingleByHsql("from TSysUser c where c.loginName=? and c.password=?", params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public TSysUser userToken(String token){
		List<Object> params=new ArrayList<Object>();
		params.add(token);
		TSysUser user=(TSysUser) this.baseDao.getSingleByHsql("from TSysUser c where c.token=? and token is not null", params);
		params=null;
		return user;
	}

	@Override
	public List<TSysUser> searchPage(Long start, Long length) {
		return this.baseDao.listByHqlBeatch("from TSysUser c order by c.id desc", start, length);
	}

	@Override
	public List<TSysUser> conditionsPage(Map<String,Object> map,Long start, Long length) {
		String params=this.SQLAddParams(map);
		StringBuffer hql=new StringBuffer("from TSysUser c where 1=1 ");
		hql=hql.append(params);
		List<TSysUser> list=null;
		try{
		list=this.baseDao.listByHqlBeatch(hql.toString(), start, length);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("条件查找用户失败");
		}
		return list;
	}
	/**
	 * 获取整表到的条目数
	 */
	public Long getTotalSize() {		
		return this.baseDao.getTotalSize("select count(c.id) from TSysUser c ",null);
	}
	
	/**
	 * 获取条件搜索到的条目数
	 */
	public Long getSearchSize(Map<String,Object> map) {
		String params=this.SQLAddParams(map);
		StringBuffer hql=new StringBuffer("select count(c.id) from TSysUser c where 1=1");
		hql.append(params);
		return this.baseDao.getTotalSize(hql.toString(),null);

	}
	
	@Override
	public List<TSysUser> selectUser(Integer cmd,Long oid,String sence){
		List<TSysUser> list=null;
		String hql="from TSysUser c";
		if (cmd==0) {//查询机构用户
			if (oid==null || oid==0) {
				list=this.baseDao.listByHql(hql+" where  c.sence='"+sence+"'", null);
			}else{
				list=this.baseDao.listByHql(hql+" where c.orgId="+oid+" and c.sence='"+sence+"'", null);
			}
		}else if (cmd==1) {//查询厂商用户
			if (oid==null || oid==0) {
				list=this.baseDao.listByHql(hql+" where  c.sence='"+sence+"'", null);
			}else{
				list=this.baseDao.listByHql(hql+" where c.manufId="+oid+" and c.sence='"+sence+"'", null);
			}
			
		}else{//其他查询
			//--------------------------TODO 其他项目----------------
		}
		
		return list;
	}

	@Override
	public List<TSysUser> manufUserSel(Long manufId) {
		List<TSysUser> list=null;
		String hql="from TSysUser c where c.type=1";
		if(manufId!=null&&!manufId.equals("")&&manufId!=0){
			list=this.baseDao.listByHql(hql+"and c.manufId="+manufId, null);
		}else{
			list=this.baseDao.listByHql(hql, null);
		}
		
		return list;
	}

	@Override
	public TSysUser findByName(String loginName) {
		 List<Object> params=new ArrayList<Object>();
	     TSysUser user=null;	     
		    if (loginName!=null) {
	            params.add(loginName);
	            StringBuffer hql=new StringBuffer(" from TSysUser c where 1=1");
	            hql.append(" and c.loginName=? ");
	            try{
	            	user=(TSysUser) this.baseDao.getSingleByHsql(hql.toString(), params);
	            }catch(Exception e){
	            	System.out.println("查找用户失败");
	            }
		    }else{
		    	System.out.println("loginName为空");
		    }
		    return user;
	}

	@Override
	public Boolean sendCheckCode(TSysUser user) {
		String phoneNum=user.getPhone();
		
		return false;
	}
	
	//临时处理
	public boolean createtoken(){
		StringBuffer hql=new StringBuffer("from TSysUser c");
		List<TSysUser> list=this.baseDao.listByHql(hql.toString(), null);
		for (TSysUser tSysUser : list) {
			tSysUser.setToken(Md5Encoder.getMd5Msg(tSysUser.getLoginName()+tSysUser.getPassword()+tSysUser.getNickName()+System.currentTimeMillis()));
			tSysUser.setTokenLimit(TimeUtils.addDate(new Date(), 120).getTime());
			this.baseDao.saveOrupdate(tSysUser);
		}
		return true;
	}
	
	//临时处理
		public boolean createpassword(){
			StringBuffer hql=new StringBuffer("from TSysUser c");
			List<TSysUser> list=this.baseDao.listByHql(hql.toString(), null);
			for (TSysUser tSysUser : list) {
				tSysUser.setPassword(Md5Encoder.getMd5Msg(tSysUser.getLoginName()+"123456"+TimeUtils.format(tSysUser.getCreateTime())));
				this.baseDao.saveOrupdate(tSysUser);
			}
			return true;
		}
	
}
 