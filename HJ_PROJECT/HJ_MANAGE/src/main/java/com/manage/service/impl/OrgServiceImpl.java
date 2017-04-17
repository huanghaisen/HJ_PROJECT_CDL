package com.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.manage.service.OrgService;
import com.model.TSysOrg;
import com.base.service.ApplicationService;

/**
 * 机构管理
 * @author daniel
 *
 */
@Service
public class OrgServiceImpl implements OrgService {
	
	static final Logger log=Logger.getLogger(OrgServiceImpl.class);
	
	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;
	
	@Resource(name="applicationServiceImpl")
	ApplicationService applicationService;

	@Override
	public List<TSysOrg> search(Integer ident,Integer endident) {
		StringBuffer hql=new StringBuffer("from TSysOrg c where 1=1 ");
		if (ident!=null && endident!=null) {
			hql.append(" and c.ident>").append(ident).append(" and c.ident<=").append(endident);
		}
		hql.append(" order by c.orderNo asc");
		List<TSysOrg> list=this.baseDao.listByHql(hql.toString(), null);
		hql=null;
		return list;
	}
	
	@Override
	public List<TSysOrg> cbxsearch(Integer ident,Integer endident,Integer level){
		StringBuffer hql=new StringBuffer("from TSysOrg c where 1=1 ");
		if (ident!=null && endident!=null) {
			hql.append(" and c.ident>=").append(ident).append(" and c.ident<=").append(endident);
		}
		if (level!=null && level!=0) {
			hql.append(" and c.depath<=").append(level);
		}
		hql.append(" order by c.orderNo asc");
		List<TSysOrg> list=this.baseDao.listByHql(hql.toString(), null);
		hql=null;
		return list;
	}

	@Override
	public void del(Long id) {
		 List<Object> params=new ArrayList<Object>();
		 params.add(id);
		 try{
			 TSysOrg model=(TSysOrg) this.baseDao.getSingleByHsql(" from TSysOrg c where c.id=?", params);
             //必须硬删并更新标识，不然树层级有问题,会报错
		     this.baseDao.remove(model);
			 updateIdent(1, TSysOrg.class, model.getIdent(),model.getEndIdent());//修改树结构
	        params=null;
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}

	@Override
	public void addOrUpdate(TSysOrg model) {
		try{
			//新增数据逻辑
			if (model.getId()==null) {
				//获取父级节点对象
				TSysOrg parentModel =this.find(model.getParentOrgId());//父级对象
				model.setIdent(parentModel.getEndIdent()+1);
				model.setEndIdent(parentModel.getEndIdent()+1);
				parentModel.setEndIdent(parentModel.getEndIdent()+1);
				//修改数据
				updateIdent(0,TSysOrg.class,parentModel.getIdent(),parentModel.getEndIdent());
				this.baseDao.saveOrupdate(parentModel);//保存父级对象
				parentModel=null;
			}
			this.baseDao.saveOrupdate(model);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("机构保存失败！");
		}
	}

	@Override
	public void RefreshAll() {
		applicationService.initorg();
	}

	@Override
	public TSysOrg find(Long id) {
		String hql="from TSysOrg c where c.id="+id;
		TSysOrg org=(TSysOrg) this.baseDao.getSingleByHsql(hql, null);
		return org;
	}
	
	
	@Override
	public void updatePosition(Integer cmd,Long id,Long nid){
		String hsql=" update TSysOrg c set ";
		if (cmd==0) {//往前移动
			this.baseDao.execHsql(hsql+"c.orderNo=orderNo-1 where c.id="+id, null);
			this.baseDao.execHsql(hsql+"c.orderNo=orderNo+1 where c.id="+nid, null);
		}else if (cmd==1) {//往后移动
			this.baseDao.execHsql(hsql+"c.orderNo=orderNo+1 where c.id="+id, null);
			this.baseDao.execHsql(hsql+"c.orderNo=orderNo-1 where c.id="+nid, null);
		}else{
			//储存在的指令
			log.warn("数据处理异常，指令cmd["+cmd+"]");
		}
		hsql=null;
	}


	@Override
	public boolean chkOrg(String name, Object val) {
		StringBuffer hql=new StringBuffer(" from TSysOrg c where 1=1");
		if (val instanceof Long || val instanceof Integer || val instanceof Float || val instanceof Double) {//判断是数值类型
			hql.append(" and c.").append(name).append("=").append(val.toString());
		}else if(val instanceof String){//判断字符串类型
			hql.append(" and c.").append(name).append("='").append(val.toString()).append("'");
		}else if (val instanceof Date) {//判断时间类型
			hql.append(" and c.").append(name).append("=").append(val.toString());
		}
		Long count=this.baseDao.getTotalSize(hql.toString(), null);
		
		if (count>0) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改业务规则模型
	 * @param cmd	(0是增加,1是删除)
	 * @param cls	实体Bean
	 * @param ident	标签
	 */
	private void updateIdent(Integer cmd,Class cls,Integer ident,Integer endIdent){
		if (cmd==0) {
			StringBuffer identhql =new StringBuffer("update ").append(cls.getName()).append(" c set c.ident=c.ident+1 where c.ident>=").append(endIdent);
			this.baseDao.execHsql(identhql.toString(), null);
			identhql=null;
			StringBuffer endidenthql =new StringBuffer("update ").append(cls.getName()).append(" c set c.endIdent=c.endIdent+1 where c.endIdent>=").append(endIdent);
			this.baseDao.execHsql(endidenthql.toString(), null);
			endidenthql=null;
		}else if (cmd==1) {
			StringBuffer identhql =new StringBuffer("update ").append(cls.getName()).append(" c set c.ident=c.ident-1 where c.ident>").append(ident);
			this.baseDao.execHsql(identhql.toString(), null);
			identhql=null;
			StringBuffer endidenthql =new StringBuffer("update ").append(cls.getName()).append(" c set c.endIdent=c.endIdent-1 where c.endIdent>=").append(ident);
			this.baseDao.execHsql(endidenthql.toString(), null);
			endidenthql=null;
		}else{
			//储存在的指令
			log.warn("数据处理异常，指令cmd["+cmd+"] class["+cls.getName()+"] ident["+ident+"]");
		}
	}

	@Override
	public List<TSysOrg> findByParentId(Long parentId) {
		List<Object> params=new ArrayList<Object>();
		List<TSysOrg> list = null;
		StringBuffer hsql=new StringBuffer("from TSysOrg c where 1=1 and c.isDeleted=0 ");
		if(parentId != null){
			 params.add(parentId);
			 hsql.append(" and c.parentOrgId=?");
		}
		try{
			list = this.baseDao.listByHql(hsql.toString(), params);
			params=null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TSysOrg updateById(Long orgId, Float mapX, Float mapY) {
		TSysOrg sysOrg = null;
		if(orgId==null || "".equals(orgId) || mapX==null || "".equals(mapX) || mapY==null || "".equals(mapY)){
			
		}else{
			try {
				TSysOrg org = this.find(orgId);
				org.setMapX(mapX);
				org.setMapY(mapY);
				sysOrg = (TSysOrg) this.baseDao.saveOrupdate(org);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sysOrg;
	}

}
