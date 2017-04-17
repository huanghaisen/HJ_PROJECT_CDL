package com.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

/**
 * 自定义权限拦截器
 * @author daniel
 *
 */
@Component("anyRoles")
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;
		
		if (rolesArray.length>1) {
			if (subject.hasRole(rolesArray[0])) {
				for (int i = 1; i < rolesArray.length; i++) {
					if (subject.hasRole(rolesArray[i])) {
						return true;
					}
				}
			}
		}
		return false;
	}
	

}
