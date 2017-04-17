package com.command;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 防止重复提交
 * @author daniel
 *
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token. class );
            if (annotation != null ) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession( false ).setAttribute( "token" , UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isSubmit(request)) {
                   	 request.setAttribute("pass", "false");
                        return true ;
                    }
                    request.getSession( false ).removeAttribute( "token" );
                }
            }
//            System.out.println("====HandlerInterceptor==11111111==>"+handlerMethod.getMethod().getName());
            return true ;
        }else{
//        	System.out.println("====HandlerInterceptor==22222222==>"+request.getRequestURI());
        	return super.preHandle(request, response, handler);
        }
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}





	private boolean isSubmit(HttpServletRequest request){
		String serverToken = (String) request.getSession( false ).getAttribute( "token" );
        if (serverToken == null ) {
            return true ;
        }
        String clinetToken = request.getParameter( "token" );
        if (clinetToken == null ) {
            return true ;
        }
        if (!serverToken.equals(clinetToken)) {
            return true ;
        }
        return false ;
	}

}
