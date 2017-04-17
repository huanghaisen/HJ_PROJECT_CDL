package com.manage.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.command.ApplicationConfig;
import com.framework.base.controller.BaseViewController;
import com.framework.view.SessionInfo;
import com.framework.view.UserInfoView;
import com.framework.view.UsernameToken;
import com.manage.service.UserService;
import com.model.TSysRole;
import com.model.TSysUser;
import com.model.TSysUserLog;
import com.base.service.ApplicationService;


/**
 * 用户登录处理控制器
 * @author daniel
 *
 */

@Controller
@RequestMapping("")
public class LoginController extends BaseViewController {
	
	Logger log=Logger.getLogger(LoginController.class);
	final static String adminURL="/soa/echarts/map";
	final static String manageURL="/soa/echarts/map";
	final static String manufURL="/soa/asmp/asmpWork/accept";
	
	@Resource(name="userServiceImpl")
	UserService userService;
	
	@Resource(name="applicationServiceImpl")
	ApplicationService applicationService;
	
	/**
	 * 登录流程
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="login",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView logIn(HttpServletRequest request,HttpServletResponse response,String username,String password,String captcha,Integer userFrom) throws IOException{
		Subject currentUser = SecurityUtils.getSubject();
		Map<String ,Object> pms=new HashMap<String, Object>();
        if (!currentUser.isAuthenticated()) {
    		UsernameToken token=new UsernameToken(username, password, captcha,this.getip(request),userFrom);
    		token.setRememberMe(true);
    		try {
    			currentUser.login(token);
    		} catch ( UnknownAccountException uae ) { //用户名未知...  
//    			uae.printStackTrace();
    			pms.put("msg", "用户名未知！");
    			return index(request, response, pms);
    		} catch ( IncorrectCredentialsException ice ) {//凭据不正确，例如密码不正确 ...  
//    			ice.printStackTrace();
    			pms.put("msg", "凭据不正确！");
    			return index(request, response, pms);
    		} catch ( LockedAccountException lae ) { //用户被锁定，例如管理员把某个用户禁用...  
//    			lae.printStackTrace();
    			pms.put("msg", "用户被锁定，例如管理员把某个用户禁用！");
    			return index(request, response, pms);
    		} catch ( ExcessiveAttemptsException eae ) {//尝试认证次数多余系统指定次数 ...  
//    			eae.printStackTrace();
    			pms.put("msg", "尝试认证次数多余系统指定次数!");
    			return index(request, response, pms);
    		} catch ( AuthenticationException ae ) {  
//    			ae.printStackTrace();
    			pms.put("msg", "其他异常!");
    			return index(request, response, pms);
    		}
        }
        
        if(currentUser.isAuthenticated()){
			UserInfoView userInfo=(UserInfoView)currentUser.getSession().getAttribute(SessionInfo.USER_INFO);
			TSysUserLog userLog=new TSysUserLog();//创建一个用户日志对象
			userLog.setUserId(userInfo.getUid());
			userLog.setType(userInfo.getUserType());
			userLog.setUserfrom(userInfo.getUserFrom());
			userLog.setIp(this.getip(request));
			userLog.setCtime(new Date());
			applicationService.userlog(userLog, ApplicationConfig.USER_LOG);
			Object rlist=userInfo.getrList();
			TSysUser user = userService.findById(userInfo.getUid());
			user.setLastLoginTime(new Date());
			userService.addOrUpdate(user);
			userLog=null;
			if (userInfo.getUserFrom()==1&&rlist!=null) {
				return new ModelAndView("redirect:/"+userInfo.getSence()+"/index");
			}else if (userInfo.getUserFrom()==2&&rlist!=null) {
				return new ModelAndView("redirect:/"+userInfo.getSence()+"/webapp/index");
			}else if (userInfo.getUserFrom()==3&&rlist!=null) {
				return new ModelAndView("redirect:/"+userInfo.getSence()+"/webapp/index");
			}else if (rlist!=null) {
				return new ModelAndView("redirect:/"+userInfo.getSence()+"/index");
			}else{
				return new ModelAndView("redirect:/loginError");
			}
			
		}else{
			pms.put("msg", "用户名不存在或者密码错误！");
			return index(request, response, pms);
		}
		
	}
	
	
	
	
	/**
	 * 退出流程
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="logout",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView logOut(HttpServletRequest request,HttpServletResponse response) throws IOException{
		this.removeSessionUser(request);
		
		 return new ModelAndView("login");
	}
	
	/**
	 * 首页框架
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="soa/index",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView smain(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserInfoView<TSysRole> userInfo=getSessionUser(request);
		Map<String ,Object> pms=new HashMap<String, Object>();
		if (userInfo!=null) {

			if (userInfo.getUserType()==0) {
				pms.put("mainurl", manageURL);
			}else if (userInfo.getUserType()==1) {
				pms.put("mainurl", manufURL);
			}else if (userInfo.getUserType()==2) {
				pms.put("mainurl", adminURL);
			}
			
			pms.put("userInfo", userInfo);
			return new ModelAndView("soaindex",pms);
		}else{
			return new ModelAndView("/login");
		}
	}
	
	/**
	 * 首页框架
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="coa/index",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView cmain(HttpServletRequest request,HttpServletResponse response) throws IOException{
		UserInfoView<TSysRole> userInfo=getSessionUser(request);
//		if (userInfo!=null) {
			return new ModelAndView("coaindex","userInfo",userInfo);
//		}else{
//			return new ModelAndView("/login");
//		}
	}
	
	/**
	 * 退出流程
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response,Map<String ,Object> pms) throws IOException{
		String devicestype=getBrowser(request);
		if (pms.size()<=0) {
			pms.put("msg", "请输入用户和密码登录");
		}
		if (devicestype.equals("other")) {//PC
			pms.put("userFrom", "1");
			return new ModelAndView("/login",pms);
		}else{
			if ("iPhone".equals(devicestype)|| "iPad".equals(devicestype)) {
				pms.put("userFrom", "2");
			}else if ("android".equals(devicestype)) {
				pms.put("userFrom", "3");
			}
			return new ModelAndView("/app/login",pms);
		}
		
	}
	
	/**
	 * 登录错误，重新登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/loginError",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView errorIndex(HttpServletRequest request,HttpServletResponse response,Integer errorType) throws IOException{
		Map<String ,Object> pms=new HashMap<String ,Object>();
		if(errorType==null){
			pms.put("msg", "该用户没有设置角色，请联系管理员设置后再登录");
		}else{
			pms.put("msg", "异常登录");
		}
			return new ModelAndView("/login",pms);
		
		
	}

}
