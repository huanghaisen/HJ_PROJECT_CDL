package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.manage.service.UserRoleService;
import com.model.TSysUserRole;

@Service
public class SysUserRoleServiceImpl implements UserRoleService{

	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao; 
	
	@Override
	public List<TSysUserRole> save(Long id,Long[] roleIds) {
		List<TSysUserRole> refreshRoles = new ArrayList<TSysUserRole>();
		List<TSysUserRole> roles = this.findByUserId(id);
		for(Long roleId:roleIds){
			roles.remove(new TSysUserRole(id, roleId));
			refreshRoles.add(this.addorupdate(new TSysUserRole(id, roleId)));
		}
		for(int i=0;i<roles.size();i++){
			this.del(roles.get(i));
		}
		
		return refreshRoles;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TSysUserRole> findByUserId(Long id) {
		List<Object> params=new ArrayList<Object>();
		if(id!=null){
			params.add(id);
		}
		StringBuffer hsql=new StringBuffer("from TSysUserRole c where 1=1");
		if(id!=null){
			hsql.append(" and c.userId=?");
		}
		List<TSysUserRole> role=this.baseDao.listByHql(hsql.toString(), params);
		return role;
	}

	@Override
	public void del(TSysUserRole role) {
		this.baseDao.remove(role);
		
	}

	@Override
	public TSysUserRole addorupdate(TSysUserRole role) {
		return (TSysUserRole) this.baseDao.saveOrupdate(role);
	}


}
