package com.security.command;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.framework.util.Md5Encoder;
import com.framework.util.StringUtil;
import com.framework.util.TimeUtils;
import com.framework.view.PermissionView;
import com.framework.view.SessionInfo;
import com.framework.view.UserInfoView;
import com.framework.view.UsernameToken;
import com.model.TSysManuf;
import com.model.TSysRole;
import com.model.TSysUser;
import com.security.service.SecurityService;
/**
 * 数据过滤验证
 * @author daniel
 *
 */
@Component("authenticationRealm")
public class AuthenticationRealm extends AuthorizingRealm {
	
	@Resource(name="securityServiceImpl")
	SecurityService securityService;

	/** 
     * 为当前登录的Subject授予角色和权限 
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取登录候的用户名
		String currentUsername = (String)super.getAvailablePrincipal(principals);
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		//缓存获取用户数据
		Subject currentUser = SecurityUtils.getSubject();
		 Session session = currentUser.getSession();
		 UserInfoView<TSysRole> userInfo=(UserInfoView<TSysRole>)session.getAttribute(SessionInfo.USER_INFO);
		 
//		 System.out.println(userInfo.getNickName()+"<==用户权限配置==>"+userInfo.getrList().size());
//		 for (TSysRole role : userInfo.getrList()) {
//			 System.out.println(role.getId()+"<==权限细项==>"+role.getRoleName());
//		 }
		 
		if (userInfo!=null) {
			simpleAuthorInfo.addRole(userInfo.getSence());
			TSysRole role=userInfo.getRole();//获取用户角色
			simpleAuthorInfo.addRole(role.getRoleName());
//			 simpleAuthorInfo.addStringPermission("admin:manage");
			 List<PermissionView> list=securityService.getPermissionList(role.getId());
			 if (list!=null || list.size()>0) {
				 for (PermissionView permissionView : list) {
//						System.out.println("<====Permission====>"+permissionView.getPermissionToString());
						 simpleAuthorInfo.addObjectPermission(permissionView);
					}
			}
			
			 return simpleAuthorInfo;
		}
		
//		UserInfoView userInfo=(UserInfoView)currentUser.getSession().getAttribute(SessionInfo.USER_INFO);
//		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//		List<String> roleList = new ArrayList<String>();  
//      List<String> permissionList = new ArrayList<String>();  
//		if (userInfo!=null) {
//			for (Object object : userInfo.getrList()) {
//				SysRole role=(SysRole)object;
//				roleList.add(role.getRoleName());
//				List<SysPermission> list=securityService.getPermissionList(role.getId());
//				for (SysPermission sysRolePermission : list) {
////					permissionList.add(e)
//				}
//			}
//		}
		
		//实际中可能会像上面注释的那样从数据库取得  
//        if(null!=currentUsername && "admin".equals(currentUsername)){  
//            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色    
//            simpleAuthorInfo.addRole("admin");
//            //添加权限  
//            simpleAuthorInfo.addStringPermission("admin:manage");  
//            System.out.println("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");  
//            return simpleAuthorInfo;  
//        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址  
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置  
        return null;  
	}

	/** 
     * 验证当前登录的Subject 
     * @see  经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
		UsernameToken usertoken = (UsernameToken)authcToken;
        String username = usertoken.getUsername();//用户名
		String password = new String(usertoken.getPassword());//密码
		Integer userFrom=usertoken.getUserFrom();//来源
		String token = usertoken.getToken();//用户验证码
		String captcha = usertoken.getCaptcha();//验证码
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
        TSysUser user=securityService.getUserName(username);
        if(user!=null){
        	String MD5PASSWORD = Md5Encoder.getMd5Msg(username+password+StringUtil.getSimpleDateFormat(user.getCreateTime()));//登陆提交上来的用户名和密码
//        	System.out.println("登陆密码："+MD5PASSWORD +"==========原始密码："+user.getPassword());
        	if(MD5PASSWORD.equals(user.getPassword())){
        	List<TSysRole> rlist=securityService.getUserRoleList(user.getId());//获取用户角色
			UserInfoView<TSysRole> userInfo=new UserInfoView<TSysRole>();
			userInfo.setUid(user.getId());
			userInfo.setToken(user.getToken());
			userInfo.setUserFrom(userFrom);
			userInfo.setNickName(user.getNickName());
			userInfo.setSex(user.getSex());
			userInfo.setEmail(user.getEmail());
			userInfo.setPhone(user.getPhone());
			userInfo.setBirthday(user.getBirthDay());
			userInfo.setLoginTime(new Date());
			userInfo.setLastLoginTime(user.getLastLoginTime()==null ? TimeUtils.getDate(new Date()):user.getLastLoginTime().toString());
			userInfo.setUserType(user.getState());
			userInfo.setOrgId(user.getOrgId());
			if (user.getManufId()!=null && user.getManufId()!=0) {
				userInfo.setManufId(user.getManufId());
				TSysManuf model=this.securityService.getManuf(userInfo.getManufId());
				userInfo.setManufname(model==null?"":model.getManufName());
				if (model.getManufLeader()!=null && !model.getManufLeader().equals(0) && user.getId().equals(model.getManufLeader())) {
					userInfo.setManufLeader(1l);
				}else{
					userInfo.setManufLeader(0l);
				}
			}else{
				userInfo.setManufId(0l);
				userInfo.setManufname("");
				userInfo.setManufLeader(0l);
			}
			if (rlist!=null && rlist.size()>0) {
				userInfo.setSence(rlist.get(0).getRoleSence());
				userInfo.setRole(rlist.get(0));
				userInfo.setrList(rlist);
	        	setSession(SessionInfo.USER_INFO, userInfo);
	        	rlist=null;
	        	userInfo=null;
	            return new SimpleAuthenticationInfo(user.getLoginName(), password, this.getName());  
			}else{
				return null;
			}
        }else {
        	return null;  
		}
        }else{
        	return null;
        }
        
	}
	
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */  
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
//            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    } 

}
