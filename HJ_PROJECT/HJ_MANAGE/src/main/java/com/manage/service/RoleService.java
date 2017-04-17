package com.manage.service;
/**
 * 角色接口
 * @author:wudj
 */
import java.util.List;

import com.model.TSysMenu;
import com.model.TSysRole;
import com.model.TSysRoleMenu;
import com.model.TSysUserRole;

public interface RoleService {
	
	/**
	 * 增加或修改角色
	 * @param role
	 * @return
	 */
   public TSysRole addorupdate(TSysRole role);
   
   /**
    * 获取单个角色
    * @param id 角色id
    * @return
    */
   public TSysRole findById(Long id);
   
   /**
    * 获取全部角色  （分场景）
    * @return
    */
   public List<TSysRole> findAll(String sence);
   
   /**
    * 删除角色
    * @param id 角色id
    */
   public void del(Long id);
   
   /**
    * 添加用户角色
    * @param userid
    * @param roleid
    */
   public TSysUserRole UserAddRole(Long userId,Long roleId);
   
   /**
    * 删除用户角色
    * @param userid
    * @param roleid
    */
   public void UserDelRole(Long userId,Long roleId);  
   
   /**
    * 通过角色Id查询所有菜单权限
    * @param roleId
    * @return
    */
   public List<TSysRoleMenu> roleFindMenu(Long roleId);
   
   /**
    * 更新角色下的菜单权限
    * @param roleId
    * @param menuIds
    * @return
    */
   public List<TSysRoleMenu> roleUpdateMenu(Long roleId,Long[] menuIds);
   
   /**
    * 删除角色下的菜单
    * @param roleMenu
    */
   public void roleDelMenu(TSysRoleMenu roleMenu);
   
   /**
    * 增加或修改角色角色下的菜单
    * @param roleMenu
    * @return
    */
   public TSysRoleMenu roleSaveOrUpdateMenu(TSysRoleMenu roleMenu);
   
   /**
    * 获取用户场景下的菜单
    * @param role
    * @return
    */
   public List<TSysMenu> roleSenceMenu(TSysRole role);
   /**
    * 统计角色下是否有用户，以便用于删除
    * @param roleId
    * @return
    */
   public int countUserByRoleId(Long roleId);
   /**
    * 通过角色菜单删除权限
    * @param rmId
    */
   public void delPermissionByRMId(Long rmId);
}

   
