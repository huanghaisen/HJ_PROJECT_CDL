package com.security.service;


import java.util.List;

import com.framework.view.PermissionView;
import com.framework.view.UserInfoView;
import com.model.TSysManuf;
import com.model.TSysRole;
import com.model.TSysUser;
/**
 * 验证框架查库逻辑
 * @author daniel
 *
 */
public interface SecurityService {
	
	/**
	 * 用户名密码查询用户
	 * @param name
	 * @param password
	 * @return
	 */
	public TSysUser getUserName (String name);
	
	/**
	 * 使用token 查询用户
	 * @param token
	 * @return
	 */
	public TSysUser getUser (String token);
	
	/**
	 * 用户ID查询用户
	 * @param uid
	 * @return
	 */
	public TSysUser getUser (Long uid);
	
	
	/**
	 * 用户名查询用户对象
	 * @param name
	 * @return
	 */
	public TSysUser getUserModel (String name);
	
	/**
	 * 查询用户角色列表
	 * @param uid
	 * @return
	 */
	public List<TSysRole> getUserRoleList (Long uid);
	
	/**
	 * 查询权限关联的资源信息
	 * @param rid
	 * @return
	 */
	public List<PermissionView> getPermissionList (Long rid);
	
	
	/**
	 *  获取角色数据
	 * @return
	 */
	public List<TSysRole> getRoles (String sence);
	
	/**
	 * 获取厂商对象
	 * @param id
	 * @return
	 */
	public TSysManuf getManuf (Long id);
	
	/**
	 * 写入用户代替缓存
	 * @param user
	 * @return
	 */
	public boolean setUserInfo (UserInfoView user);

}
