package com.command;

import com.framework.util.SystemUtil;
import com.framework.view.PermissionView;
import com.framework.view.Session;
import com.framework.view.UserInfoView;
import com.model.TSysFunc;
import com.model.TSysRole;
import com.model.TSysUserActionLog;
import com.base.service.ApplicationService;
import com.utils.PermissionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 请求拦截适配器(控制权限拦截)
 * @author daniel
 *
 */
public class HandlerAdapter extends HandlerInterceptorAdapter {
	
	 private String mappingURL;//利用正则映射到需要拦截的路径
	 public final static String SYSTITLE=SystemUtil.getPro("SYS.TITLE");
	 public final static Long SYSOID=Long.valueOf(SystemUtil.getPro("SYS.OID")==""?"0":SystemUtil.getPro("SYS.OID"));
	 
	 @Resource(name="applicationServiceImpl")
	 ApplicationService applicationServics;


	 @Override
	//在执行action里面的处理逻辑之前执行
	//安全控制等处理
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 String path=request.getContextPath();
		 String url=StringUtils.remove(request.getRequestURI(), request.getContextPath());
		 Subject currentUser = SecurityUtils.getSubject();
		 String func="";
			int len=url.lastIndexOf("datas");
			if (len!=-1) {
				func=StringUtils.substring(url,len+6);
			}
			url=PermissionUtils.strToSence(url);
		 
//		 System.out.println("=======111111====>"+handler.getClass().getSimpleName());
		 //-----------------------------TODO(控制权限范围扫描)-----------
		 if (handler instanceof HandlerMethod) {
			 HandlerMethod handlerMethod = (HandlerMethod) handler;
//			 System.out.println(currentUser.getPrincipal().toString()+"=============preHandle================>"+url);
			 
			 PermissionView permission=new PermissionView(currentUser.getPrincipal().toString(),url,func);
			 /*if (currentUser.isPermitted(permission)) {
//				System.out.println("===111====");
			}else{
//				System.out.println("===222====");
			}*/
			 return true;
		 }else{
			 response.sendRedirect(request.getContextPath()+"/login");
			 return false;
		 }
		
	}
	 
	@Override
	//在执行action里面的逻辑后返回视图之前执行
	//修改ModelAndView
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		UserInfoView<TSysRole> userInfo=(UserInfoView)request.getSession().getAttribute(Session.USER_INFO);
		String url=StringUtils.remove(request.getRequestURI(), request.getContextPath());
//		System.out.println(userInfo.getNickName()+"<=============>"+url);
		TSysUserActionLog userActionLog = new TSysUserActionLog();
		//业务操作缓存
		action(userActionLog, userInfo, url, request);
		
		if (modelAndView!=null) {
//			System.out.println("=============postHandle=========&&&&&=======>"+handler.getClass().getSimpleName());
//			for (Map.Entry<String, Object> entry : modelAndView.getModel().entrySet()) {
//				System.out.println(entry.getKey()+"<========Map======>"+entry.getValue());
//			}
//			System.out.println("=============postHandle=========#####=======");
			if (handler instanceof HandlerMethod) {
				if (modelAndView.getModel().containsKey("user")) {
//					System.out.println("=============postHandle=========&&&&&=======>"+url);
					UserInfoView<TSysRole> user=(UserInfoView<TSysRole>) modelAndView.getModel().get("user");
					List<TSysFunc> list=applicationServics.getbtn(user, url);
					modelAndView.getModel().put("btnlist", list);
				}
				modelAndView.getModel().put("sysorg", SYSOID);
				modelAndView.getModel().put("systitle", SYSTITLE);
			}
		}
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
	//筛选方法
	public void action(TSysUserActionLog userActionLog,UserInfoView<TSysRole> userInfo,String url,HttpServletRequest request){
		String[] str = url.split("/");
		String action = str[str.length-1];
		if(action.equals("inst")){
			addUserLog(userActionLog, userInfo,url,request);
		}
		if(action.equals("del")){
			addUserLog(userActionLog, userInfo,url,request);
		}
		if(action.equals("save")){
			addUserLog(userActionLog, userInfo,url,request);
		}
		if(action.equals("edit")){
			addUserLog(userActionLog, userInfo,url,request);
		}
		if (action.equals("check")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("checkInfo")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("checkStep")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("acceptStep")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("settle")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("settleParts")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("settleStep")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("revoke")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("edit")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("changeStatus")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("revoke")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("addWork")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if (action.equals("submit")) {
			addUserLog(userActionLog, userInfo, url, request);
		}
		if(action.equals("supplies")){
			addUserLog(userActionLog, userInfo, url, request);
		}
		if(action.equals("status")){
			addUserLog(userActionLog, userInfo, url, request);
		}
		if(action.equals("update")){
			addUserLog(userActionLog, userInfo, url, request);
		}
		if(action.equals("delete")){
			addUserLog(userActionLog, userInfo, url, request);
		}
		
	}
	
	
	//用户操作日志写入缓存
	public void addUserLog(TSysUserActionLog userActionLog,UserInfoView<TSysRole> userInfo,String url,HttpServletRequest request){
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		userActionLog.setUserid(userInfo.getUid());
		userActionLog.setUsername(userInfo.getNickName());
		userActionLog.setType(userInfo.getUserType());
		userActionLog.setAction(url);
		userActionLog.setIp(ip);
		userActionLog.setCtime(new Date());
		applicationServics.userlog(userActionLog, ApplicationConfig.USER_ACTION_LOG);
		
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
