package com.security.service;

/**
 * 权限业务处理
 * @author daniel
 *
 */
public interface AuthService {
	
	/**
	 * 加载过滤信息配置
	 */
	public String loadFilterChainDefinitions ();
	
	
	/**
     * 重新构建权限过滤器
     * 一般在修改了用户角色、用户等信息时，需要再次调用该方法
     */
    public void reCreateFilterChains ();
	
}
