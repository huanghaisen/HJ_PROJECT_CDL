package com.manage.service;

import java.util.List;

import com.model.TSysPermission;
import com.model.TSysRoleMenu;

/**
 * 权限接口
 * @author wudj
 *
 */
public interface PermissionService {
    
	/**
	 * 新增、修改权限
	 * @return
	 */
	public TSysPermission addOrUpdate(TSysPermission permission);
	
	/**
	 * 用id获取权限
	 * @return
	 */
	public TSysPermission findById(Long id);
	
	/**
	 * 获取库里所有权限列表
	 * @return
	 */
	public List<TSysPermission> findAll();
	
	/**
	 * 删除权限
	 * @return
	 */
	public void del(Long id);
	
	/**
	 * 给角色附加权限
	 * @return
	 */
	public TSysPermission RoleAddPermission(Long roleId,Long PermissionId);
	
	/**
	 * 删除角色已附加的权限
	 */
	public void RoleDelPermission(Long roleId,Long PermissionId);
	
	/**
	 * 获得角色下的所有权限
	 * @return
	 */
	public List<TSysPermission> getAllByRoleId(Long roleId);
	
	/**
	 * 获取角色菜单
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public TSysRoleMenu getRoleMenu(Long roleId,Long menuId);
	
	/**
	 * 通过RMId获取所有的功能
	 * @param RMId
	 * @return
	 */
	public List<TSysPermission> getFunByRMId(Long RMId);
	
	/**
	 * 更新角色权限
	 * @param rmId
	 * @param funIds
	 * @return
	 */
	public List<TSysPermission> roleUpdatePermission(Long rmId,Long[] funIds);
	/**
	 * 通过角色菜单ID删除操作权限
	 * @param roleId
	 */
	public void delPermissionByRoleId(Long rmId);
	
}
