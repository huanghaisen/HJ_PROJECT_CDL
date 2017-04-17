package com.manage.service;
/**
 * 用户角色接口
 * 
 */
import java.util.List;

import com.model.TSysUserRole;

public interface UserRoleService {
	
	/**
	 * 保存用户角色
	 * @param role
	 * @return
	 */
   public List<TSysUserRole> save(Long id,Long[] roleIds);
   
   /**
    * 获取用户拥有的所有角色
    * @param id 角色id
    * @return
    */
   public List<TSysUserRole> findByUserId(Long id);
   
   /**
    * 删除用户的指定角色
    * @param role 
    * @return
    */
   public void del(TSysUserRole role);
   
   /**
    * 增加或修改角色
    * @param role
    * @return
    */
   public TSysUserRole addorupdate(TSysUserRole role);
   
   
}

   
