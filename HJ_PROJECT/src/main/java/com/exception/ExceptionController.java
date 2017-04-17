package com.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理控制器
 * @author daniel
 *
 */
@Controller
@RequestMapping("/error")
public class ExceptionController {
	
	@RequestMapping(value="uncaughtException",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object error(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> api=new HashMap<String, Object>();
		Map<String,Object> head=new HashMap<String,Object>();
		Map<String,Object> body=new HashMap<String,Object>();
		head.put("code", 1);
		head.put("msg", "没有对应请求对象");
		api.put("head", head);
		api.put("body", body);
		head=null;
		body=null;
		return api;
	}

}
