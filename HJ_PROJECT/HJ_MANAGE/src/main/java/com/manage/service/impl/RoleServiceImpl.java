package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.manage.service.PermissionService;
import com.manage.service.RoleService;
import com.model.TSysMenu;
import com.model.TSysRole;
import com.model.TSysRoleMenu;
import com.model.TSysUserRole;
/**
 * 系统角色和角色菜单实现类
 *
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;
	
	@Resource(name="permissionServiceImpl")
	private PermissionService permissionService;
	/**
	 * 新增或修改角色
	 */
	@Override
	public TSysRole addorupdate(TSysRole role) {
		TSysRole model=(TSysRole)this.baseDao.saveOrupdate(role);
		return model;
	}
	/**
	 * 通过ID查找角色
	 */
	@Override
	public TSysRole findById(Long id) {
		List<Object> params=new ArrayList<Object>();
		if(id!=null){
			params.add(id);
		}
		StringBuffer hsql=new StringBuffer("from TSysRole c where 1=1");
		if(id!=null){
			hsql.append(" and c.id=?");
		}
		TSysRole role=(TSysRole)this.baseDao.getSingleByHsql(hsql.toString(), params);
		return role;
	}
	/**
	 * 通过场景查找所有的角色
	 */
	@Override
	public List<TSysRole> findAll(String sence) {
		
		List<TSysRole> list=this.baseDao.listByHql("from TSysRole c where c.roleSence='"+sence+"' order by c.id desc", null);
		System.out.println("from TSysRole c where c.roleSence='"+sence+"' order by c.id desc");
		return list;
	}

	/**
	 * 通过ID删除角色
	 */
	@Override
	public void del(Long id) {
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		TSysRole role=(TSysRole)this.baseDao.getSingleByHsql("from TSysRole c where c.id=?", params);
		this.baseDao.remove(role);
	}
	/**
	 * 通过用户ID和角色ID新增用户角色
	 */
	@Override
	public TSysUserRole UserAddRole(Long userId, Long roleId) {
		TSysUserRole realation=new TSysUserRole(userId,roleId);
		TSysUserRole model=(TSysUserRole)this.baseDao.saveOrupdate(realation);
		return model;
	}
	/**
	 * 通过用户ID和角色ID删除用户角色
	 */
	@Override
	public void UserDelRole(Long userId, Long roleId) {
		TSysUserRole sysUserRole = getUserRoleByUidAndRid(userId,roleId);
		if(sysUserRole != null){
			this.baseDao.remove(sysUserRole);		
		}
	}
	/**
	 * 通过角色ID查询该角色下的所有角色菜单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TSysRoleMenu> roleFindMenu(Long roleId) {
		List<Object> params=new ArrayList<Object>();
		if(roleId!=null){
			params.add(roleId);
		}
		StringBuffer hsql=new StringBuffer("from TSysRoleMenu c where 1=1");
		if(roleId!=null){
			hsql.append(" and c.roleId=?");
		}
		List<TSysRoleMenu> role=this.baseDao.listByHql(hsql.toString(), params);
		return role;
	}
	/**
	 * 通过角色ID和菜单IDs，编辑用户角色菜单
	 */
	@Override
	public List<TSysRoleMenu> roleUpdateMenu(Long roleId, Long[] menuIds) {
		List<TSysRoleMenu> refreshRoleMenu = new ArrayList<TSysRoleMenu>();
		List<TSysRoleMenu> roleMenu = this.roleFindMenu(roleId);
		List<Long> ids = new ArrayList<Long>();
		for(Long menuId:menuIds){
			TSysRoleMenu sysRoleMenu = getIdByRIdAndMId(roleId,menuId);
			if(sysRoleMenu == null){
				sysRoleMenu = new TSysRoleMenu(roleId, menuId);
				refreshRoleMenu.add(this.roleSaveOrUpdateMenu(sysRoleMenu));
			}else{
				ids.add(sysRoleMenu.getId());
			}
		}
		for(int i=0;i<roleMenu.size();i++){
			if(!ids.contains(roleMenu.get(i).getId())){
				this.roleDelMenu(roleMenu.get(i));
				permissionService.delPermissionByRoleId(roleMenu.get(i).getId());
			}
		}
		ids = null;
		roleMenu = null;
		return refreshRoleMenu;
	}

	/**
	 * 通过角色菜单对象删除角色菜单
	 */
	@Override
	public void roleDelMenu(TSysRoleMenu roleMenu) {
		this.baseDao.remove(roleMenu);
	}
	/**
	 * 新增或修改角色菜单
	 */
	@Override
	public TSysRoleMenu roleSaveOrUpdateMenu(TSysRoleMenu roleMenu) {
		return (TSysRoleMenu) this.baseDao.saveOrupdate(roleMenu);
	}
	/**
	 * 通过角色获取该角色下的所有菜单
	 */
	@Override
	public List<TSysMenu> roleSenceMenu(TSysRole role) {
		List<Object> params=new ArrayList<Object>();
		if(role!=null){
			params.add(role.getRoleSence());
		}
		StringBuffer hsql=new StringBuffer("from TSysMenu c where 1=1 and c.isVisible=0 and c.isLeaf=1");
		if(role!=null){
			hsql.append(" and c.menuSence=?");
		}
		List<TSysMenu> roles=this.baseDao.listByHql(hsql.toString(), params);
		return roles;
	}
	/**
	 * 通过角色ID统计该角色下的用户数量
	 */
	@Override
	public int countUserByRoleId(Long roleId) {
		List<Object> params=new ArrayList<Object>();
		StringBuffer hsql=new StringBuffer("from TSysUserRole c where 1=1");
		if(roleId!=null){
			params.add(roleId);
			hsql.append(" and c.roleId=?");
		}
		List<TSysUserRole> userRoles=this.baseDao.listByHql(hsql.toString(), params);
		return userRoles.size();
	}
	/**
	 * 通过角色菜单ID删除菜单功能权限
	 */
	@Override
	public void delPermissionByRMId(Long rmId) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hsql=new StringBuffer("delete TSysPermission c where 1=1");
		if(rmId!=null){
			params.add(rmId);
			hsql.append(" and c.rmId=?");
			this.baseDao.execHsql(hsql.toString(), params);
		}
		
	}
	/**
	 * 通过角色ID和菜单ID查找TSysRoleMenu对象
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public TSysRoleMenu getIdByRIdAndMId(Long roleId,Long menuId){
		TSysRoleMenu role = null;
		try {
			List<Object> params = new ArrayList<Object>();
			StringBuffer hsql=new StringBuffer("from TSysRoleMenu c where 1=1");
			if(roleId != null && menuId != null){
				params.add(roleId);
				params.add(menuId);
				hsql.append(" and c.roleId=?");
				hsql.append(" and c.menuId=?");
			}
			role = (TSysRoleMenu) this.baseDao.getSingleByHsql(hsql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	
	private TSysUserRole getUserRoleByUidAndRid(Long userId, Long roleId){
		TSysUserRole sysUserRole = null;
		try{
			List<Object> params = new ArrayList<Object>();
			StringBuffer hsql=new StringBuffer("from TSysRoleMenu c where 1=1");
			if(userId != null && roleId != null){
				params.add(userId);
				params.add(roleId);
				hsql.append(" and c.userId=?");
				hsql.append(" and c.roleId=?");
			}
			sysUserRole = (TSysUserRole) this.baseDao.getSingleByHsql(hsql.toString(), params);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sysUserRole;
	}
}
