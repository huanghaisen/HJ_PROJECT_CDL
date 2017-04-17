package com.security.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enums.ErrorType;
import com.framework.base.controller.BaseViewController;
/**
 * API外部接口
 * http://127.0.0.1:8080/api/user/
 * @author daniel
 *
 */

@Controller
@RequestMapping("/error")
public class SecuityErrorAction extends BaseViewController {
	
	
	@RequestMapping(value="msg",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object msg(HttpServletRequest request,HttpServletResponse response,int cmd) throws IOException{
		JSONObject msgJson=new JSONObject();
		msgJson.put("error", ErrorType.valueOf(cmd).getValue());
		msgJson.put("msg", ErrorType.valueOf(cmd).toString());
		return msgJson;
	}
	
}
