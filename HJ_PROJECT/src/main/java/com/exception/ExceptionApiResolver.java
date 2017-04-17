package com.exception;


import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionApiResolver implements HandlerExceptionResolver {

	private static final Logger log=Logger.getLogger(ExceptionApiResolver.class);
	@Override
	public ModelAndView resolveException(
			HttpServletRequest arg0, HttpServletResponse response, Object arg2,
			Exception ex) {
		PrintWriter writer=null;
		JSONObject errorjson=new JSONObject();
		JSONObject head=new JSONObject();
		JSONObject body=new JSONObject();
		try {
			writer = response.getWriter();
			head.put("code", 1);
			head.put("msg", ex.getMessage());
			errorjson.put("head", head);
			errorjson.put("body", body);
		} catch (IOException e) {
			
		}finally{
			writer.write(errorjson.toString());
			writer.flush();
		}
		return new ModelAndView();
	}

	

}
