package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.manage.service.PermissionService;
import com.model.TSysPermission;
import com.model.TSysRoleMenu;
/**
 * 用户功能权限实现类
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService{

	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;
	/**
	 * 新增或修改功能权限
	 */
	@Override
	public TSysPermission addOrUpdate(TSysPermission permssion) {
		TSysPermission model=(TSysPermission)this.baseDao.saveOrupdate(permssion);
		return model;
	}
	/**
	 * 通过ID查找功能权限
	 */
	@Override
	public TSysPermission findById(Long id) {
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		TSysPermission model=(TSysPermission)this.baseDao.getSingleByHsql("from TSysPermission c where c.id=?", params);
		return model;
	}
	/**
	 * 查找所有的功能权限
	 */
	@Override
	public List<TSysPermission> findAll() {
		List<TSysPermission> list=this.baseDao.listByHql("from TSysPermission c where 1=1", null);
		return list;
	}

	/**
	 * 通过ID删除功能权限
	 */
	@Override
	public void del(Long id) {
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		TSysPermission model=(TSysPermission)this.baseDao.getSingleByHsql("from TSysPermission c where c.id=?", params);
		this.baseDao.remove(model);
		
	}
	/**
	 * 通过角色ID和功能ID新增角色功能权限
	 */
	@Override
	public TSysPermission RoleAddPermission(Long roleId, Long PermissionId) {
		TSysPermission model = null;
		try {
			model = new TSysPermission(roleId,PermissionId);
			this.baseDao.saveOrupdate(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 通过角色ID和功能ID删除角色功能权限
	 */
	@Override
	public void RoleDelPermission(Long roleId, Long PermissionId) {
		TSysPermission sysPermission = getPermissionByRmIdAndFunId(roleId,PermissionId);
		if(sysPermission != null){
			this.baseDao.remove(sysPermission);		
		}
	}

	/**
	 * 此方法无用，请勿使用
	 */
	@Override
	public List<TSysPermission> getAllByRoleId(Long roleId) {
		List<Object> params=new ArrayList<Object>();
		params.add(roleId);
	    List<TSysPermission> list=this.baseDao.listByHql("from TSysPermission c where c.roleId=?", params);
		return list;
	}
	/**
	 * 通过角色ID和菜单ID获取角色菜单权限
	 */
	@Override
	public TSysRoleMenu getRoleMenu(Long roleId, Long menuId) {
		List<Object> params=new ArrayList<Object>();
		params.add(roleId);
		params.add(menuId);
		TSysRoleMenu roleMenu = (TSysRoleMenu) this.baseDao.getSingleByHsql("from TSysRoleMenu c where c.roleId=? and c.menuId=?", params);
		return roleMenu;
	}
	/**
	 * 通过角色菜单ID获取功能权限
	 */
	@Override
	public List<TSysPermission> getFunByRMId(Long RMId) {
		List<Object> params=new ArrayList<Object>();
		params.add(RMId);
		List<TSysPermission> list = this.baseDao.listByHql("from TSysPermission c where c.rmId=?", params);
		return list;
	}
	/**
	 * 通过角色菜单ID编辑菜单功能操作权限
	 */
	@Override
	public List<TSysPermission> roleUpdatePermission(Long rmId, Long[] funIds) {
		List<TSysPermission> refreshSysPermission = new ArrayList<TSysPermission>();
		List<TSysPermission> sysPermission = this.getFunByRMId(rmId);
		List<Long> ids = new ArrayList<Long>();
		for(Long funId:funIds){
			TSysPermission permission = getPermissionByRmIdAndFunId(rmId, funId);
			if(permission == null){
				permission = new TSysPermission(rmId, funId);
				refreshSysPermission.add(this.addOrUpdate(permission));
			}else{
				ids.add(permission.getId());
			}
		}
		for(int i =0;i<sysPermission.size();i++){
			if(!ids.contains(sysPermission.get(i).getId())){
				this.baseDao.remove(sysPermission.get(i));
			}
		}
		sysPermission = null;
		ids = null;
		return refreshSysPermission;
	}
	/**
	 * 通过角色菜单ID和菜单ID查找操作权限
	 * @param rmId
	 * @param funId
	 * @return
	 */
	private TSysPermission getPermissionByRmIdAndFunId(Long rmId,Long funId){
		TSysPermission sysPermission = null;
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer hsql=new StringBuffer("from TSysPermission c where 1=1");
			if(rmId != null && funId != null){
				params.add(rmId);
				params.add(funId);
				hsql.append(" and c.rmId=?");
				hsql.append(" and c.funcId=?");
			}
			sysPermission = (TSysPermission) this.baseDao.getSingleByHsql(hsql.toString(), params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sysPermission;
	}
	@Override
	public void delPermissionByRoleId(Long rmId) {
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer hsql=new StringBuffer("delete TSysPermission c where 1=1");
			if(rmId != null){
				params.add(rmId);
				hsql.append(" and c.rmId=?");
				this.baseDao.execHsql(hsql.toString(), params);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
