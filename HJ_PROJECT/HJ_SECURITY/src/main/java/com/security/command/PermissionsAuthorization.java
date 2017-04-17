package com.security.command;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;


/**
 * 业务控制过滤
 * @author daniel
 *
 */
@Component
public class PermissionsAuthorization extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		System.out.println("22222222222222222222");
		Subject subject = getSubject(request, response);
		return false;
	}
	
	

}
