package com.exception;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionResolver implements HandlerExceptionResolver {

	private static final Logger log=Logger.getLogger(ExceptionResolver.class);
	@Override
	public ModelAndView resolveException(
			HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception ex) {
		//log.error(ex);
		System.out.println(ex.getMessage());
		return new ModelAndView("error/error","error",ex.getMessage());
	}



}
