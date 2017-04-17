package com.security.command;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RoleAdapter{
	
    
	@Pointcut("execution(* com.*.action.*.*(..))")
    public void sleeppoint(){}
	
	@Before("sleeppoint()")
	public void beforeMethod(JoinPoint point) {
		
		Subject currentUser = SecurityUtils.getSubject();
//		point.getSignature().getDeclaringTypeName()//获取请求类
//		point.getSignature().getName();//获取请求方法名
//		System.out.println(currentUser.getPrincipal().toString()+"<=====>"+point.getSignature().getDeclaringTypeName());
		
    }
    
    @AfterReturning("sleeppoint()")
    public void afterMethod(JoinPoint point){
    }
}
