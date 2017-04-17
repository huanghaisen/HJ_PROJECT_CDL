package com.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.command.ApplicationConfig;
import com.framework.base.dao.impl.BaseDaoImpl;
import com.framework.view.PermissionView;
import com.framework.view.UserInfoView;
import com.model.TSysManuf;
import com.model.TSysRole;
import com.model.TSysUser;
import com.model.TSysUserRole;
import com.security.service.SecurityService;
import com.base.service.CacheService;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;
	
	@Resource(name="cacheServiceImpl")
	CacheService cacheService;
	
	
	@Override
	public TSysUser getUserName(String name) {
		TSysUser user=null;
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		try{
		user=(TSysUser) this.baseDao.getSingleByHsql("from TSysUser c where c.loginName=?", params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public TSysUser getUser(String token) {
		List<Object> params=new ArrayList<Object>();
		params.add(token);
		TSysUser user=(TSysUser) this.baseDao.getSingleByHsql("from TSysUser c where c.token=? ", params);
		params=null;
		return user;
	}

	@Override
	public TSysUser getUser(Long uid) {
		List<Object> params=new ArrayList<Object>();
		params.add(uid);
		TSysUser user=(TSysUser) this.baseDao.getSingleByHsql("from TSysUser c where c.id=?", params);
		params=null;
		return user;
	}

	@Override
	public TSysUser getUserModel(String name) {
		List<Object> params=new ArrayList<Object>();
		params.add(name);
		TSysUser user=(TSysUser) this.baseDao.getSingleByHsql("from TSysUser c where c.loginName=? ", params);
		params=null;
		return user;
	}

	@Override
	public List<TSysRole> getUserRoleList(Long uid) {
		List<TSysUserRole> list=this.baseDao.listByHql("from TSysUserRole c where c.userId="+uid, null);
		List<TSysRole> rList=null;
		if (list.size()>0) {
			rList=new ArrayList<TSysRole>();
			for (TSysUserRole model : list) {
				TSysRole role=(TSysRole)this.baseDao.getSingleByHsql("from TSysRole c where c.id="+model.getRoleId(), null);
				rList.add(role);
				role=null;
			}
		}
		list=null;
		return rList;
	}
	
	@Override
	public List<PermissionView> getPermissionList(Long rid){
		StringBuffer bf=new StringBuffer("select new com.framework.view.PermissionView(r.roleName,m.menuUrl,f.funcCode) from TSysRoleMenu rm,TSysPermission p,TSysFunc f,TSysMenu m,TSysRole r");
		bf.append(" where r.id=rm.roleId and rm.menuId=m.id and p.rmId=rm.id and f.id=p.funcId and  m.isVisible=0 and m.menuIsopen=0 ");
		bf.append(" and r.id=").append(rid);
		List<PermissionView> list=this.baseDao.listByHql(bf.toString(), null);
		bf=null;
		return list;
	}

	@Override
	public TSysManuf getManuf(Long id) {
		TSysManuf model=null;
		List<TSysManuf> manufList=this.baseDao.listByHql("from TSysManuf c where c.id="+id, null);
		if (manufList.size()>0) {
			model=manufList.get(0);
		}
		return model;
	}
	
	@Override
	public List<TSysRole> getRoles(String sence){
		if (sence==null || "".equals(sence)) {
			sence="soa";
		}
		
		StringBuffer hql=new StringBuffer("from TSysRole c where 1=1");
		hql.append(" and c.roleSence='").append(sence).append("'");
		List<TSysRole> list=this.baseDao.listByHql(hql.toString(), null);
		return list;
	}
	
	@Override
	public boolean setUserInfo(UserInfoView user){
		cacheService.cacheSetObj(ApplicationConfig.USER_SESSION, user.getToken(), user);
		return true;
	}
	
}
