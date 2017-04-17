package com.base.service;

import com.framework.view.MenuView;
import com.framework.view.MenuViewApi;
import com.framework.view.UserInfoView;
import com.model.TSysFunc;
import com.model.TSysRole;
import org.springframework.context.ApplicationContext;

import java.util.List;

public interface ApplicationService {
	
	
	/**
	 * 从缓存里去查找用户信息
	 * @param token 用户验证串 
	 * @return
	 */
	public UserInfoView getSessionUser (String token);
	
	/**
	 * 写入用户session缓存
	 * @param obj
	 * @return
	 */
	public boolean setSessionUser (UserInfoView obj);
	
	/**
	 * 删除用户session缓存
	 * @param token
	 * @return
	 */
	public boolean delSessionUser (String token);
	
	/**
	 * 初始组件
	 * @param ctx
	 */
	public void initComponent (ApplicationContext ctx);
	
	/**
	 * 机构数据缓存
	 */
	public void initorg ();
	
	/**
	 * 获取机构数据
	 * @param cmd	指令(depth 获取数据层级深度,children 获取子集,lastdepth 获取当前机构下子营业厅)
	 * @param id 当前用户机构id
	 * @return
	 */
	public Object getOrg (String cmd, Long id, Integer level);
	
	/**
	 * 初始菜单
	 * @param sence
	 * @return
	 */
	public boolean initmenu (String sence);
	
	/**
	 * 获取用户菜单(平台)
	 * @return
	 */
	public List<MenuView> getmenu (UserInfoView<TSysRole> user);
	
	/**
	 * 获取用户菜单(移动端)
	 * @return
	 */
	public List<MenuViewApi> getapimenu (UserInfoView<TSysRole> user);
	
   /**
    * 获取相关菜单按钮
    * @param user
    * @param url
    * @return
    */
	public List<TSysFunc> getbtn (UserInfoView user, String url);
	
	/**
	 * 日志记录接口
	 * @param obj 缓存对象
	 * @param keyPrefix	缓存空间
	 */
	public void userlog (Object obj, String keyPrefix);
	
	/**
	 * 处理登录日志写库
	 */
	public void userlogtask ();
	
	/**
	 * 处理用户操作日志
	 */
	public void userActionlogtask ();
	
}
