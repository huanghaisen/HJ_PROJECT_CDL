package com.command;

import com.framework.util.SystemUtil;
import com.framework.view.Session;
import com.framework.view.UserInfoView;
import com.model.TSysRole;
import com.base.service.ApplicationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求拦截适配器(控制权限拦截)
 * @author daniel
 *
 */
public class HandlerApiAdapter extends HandlerInterceptorAdapter {
	
	 private String mappingURL;//利用正则映射到需要拦截的路径
	 public final static String SYSTITLE=SystemUtil.getPro("SYS.TITLE");
	 
	 @Resource(name="applicationServiceImpl")
	 ApplicationService applicationServics;


	 @Override
	//在执行action里面的处理逻辑之前执行
	//安全控制等处理
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		 String path=request.getContextPath();
//		 String url=StringUtils.remove(request.getRequestURI(), request.getContextPath());
		 
			
			return true;
		
	}
	 
	@Override
	//在执行action里面的逻辑后返回视图之前执行
	//修改ModelAndView
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		UserInfoView<TSysRole> userInfo=(UserInfoView)request.getSession().getAttribute(Session.USER_INFO);
		String url=StringUtils.remove(request.getRequestURI(), request.getContextPath());
		System.out.println(userInfo.getNickName()+"<=============>"+url);
		if (modelAndView!=null) {
//			System.out.println("=============postHandle=========&&&&&=======>"+handler.getClass().getSimpleName());
//			for (Map.Entry<String, Object> entry : modelAndView.getModel().entrySet()) {
//				System.out.println(entry.getKey()+"<========Map======>"+entry.getValue());
//			}
//			System.out.println("=============postHandle=========#####=======");
			if (handler instanceof HandlerMethod) {
				
			}
		}
		super.postHandle(request, response, handler, modelAndView);
	}
	
	

	@Override
	//在action返回视图后执行
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("=============afterCompletion================");
		super.afterCompletion(request, response, handler, ex);
	}

}
