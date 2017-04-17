package com.manage.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.controller.BaseViewController;
import com.framework.util.StringUtil;
import com.framework.view.MenuView;
import com.framework.view.ResultDatas;
import com.framework.view.UserInfoView;
import com.manage.service.UserService;
import com.model.TSysRole;
import com.model.TSysUser;
import com.base.service.ApplicationService;
import com.base.service.CacheService;


/**
 * 系统控制处理器
 * @author daniel
 *
 */

@Controller
@RequestMapping("/soa/manage")
public class KxdSysController extends BaseViewController{
	
	@Resource(name="cacheServiceImpl")
	CacheService<MenuView> cacheService;
	
	@Resource(name="applicationServiceImpl")
	ApplicationService applicationService;
	
	@Resource(name="userServiceImpl")
	UserService userService;
	
	/**
	 * 获取用户菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="datas/menu",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object menuLoad(HttpServletRequest request,HttpServletResponse response,String sence){
		UserInfoView<TSysRole> user=getSessionUser(request);//获取用户信息
		if (StringUtil.isEmpty(sence)) {
			sence=user.getSence();
		}
		ResultDatas resultDatas=new ResultDatas();
		if (user!=null) {
			List<MenuView> mlist=applicationService.getmenu(user);
			Collections.sort(mlist);
			resultDatas.setSuccess(0);
			resultDatas.setMsg("");
			resultDatas.setData(mlist);
		}else{
			resultDatas.setSuccess(9);
			resultDatas.setData("");
			resultDatas.setMsg("用户未登录");
		}
		return resultDatas;
	}
	
	
	
	@RequestMapping(value="datas/menurefresh",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object memw(HttpServletRequest request,HttpServletResponse response,String sence) throws IOException{
		UserInfoView<TSysRole> user=getSessionUser(request);
		ResultDatas resultDatas=new ResultDatas();
		if (StringUtil.isEmpty(sence)) {
			sence=user.getSence();
		}
		if (user!=null) {
			applicationService.initmenu(sence);
			resultDatas.setSuccess(0);
			resultDatas.setMsg("");
		}else{
			resultDatas.setSuccess(9);
			resultDatas.setData(null);
			resultDatas.setMsg("用户未登录");
		}
		return resultDatas;
	}
	
	
	/**
	 * 修改当前用户信息  wyq
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value="update",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object update(HttpServletRequest request,HttpServletResponse response,TSysUser user) throws IOException, ParseException{
        Map<String,Object> result=new HashMap<String,Object>();
        UserInfoView<TSysRole> userInfo=getSessionUser(request);
        TSysUser model=null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = request.getParameter("birthday");
		Date birthDay=sdf.parse(strDate);
    	if (userInfo.getUid()!=null) {
    		
    			try{
        			model=userService.findById(userInfo.getUid());
            		model.setNickName(user.getNickName());
            		model.setSex(user.getSex());
            		model.setEmail(user.getEmail());
            		model.setPhone(user.getPhone());
            		model.setBirthDay(birthDay);
            		userService.addOrUpdate(model);
        			result.put("msg", "修改成功!");
        			result.put("status", 0);
        		}catch(Exception e){
        			e.printStackTrace();
        			result.put("msg", "修改失败!");
        			result.put("status", 1);
        		}
		}else{
			result.put("msg", "用户未登录!");
			result.put("status",2);
		}
		return result;
	}
	
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param usedpwd
	 * @param newpwd
	 * @param verifypwd
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value="password/update",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object updatePwd(HttpServletRequest request,HttpServletResponse response,String usedpwd,String newpwd,String verifypwd) throws IOException, ParseException{
        Map<String,Object> result=new HashMap<String,Object>();
        UserInfoView<TSysRole> userInfo=getSessionUser(request);
        TSysUser model=null;
    	if (userInfo.getUid()!=null) {
    		try{
    			model=userService.findById(userInfo.getUid());
    			if(!usedpwd.equals(model.getPassword())){
    				result.put("msg", "当前密码有误!");
            		result.put("status", 1);
    			}else if(StringUtil.isEmpty(newpwd) && StringUtil.isEmpty(verifypwd)){
    				result.put("msg", "不能为空!");
            		result.put("status", 1);
    				
    			}else if(!newpwd.equals(verifypwd)){
    				result.put("msg", "两次输入密码不一致!");
            		result.put("status", 1);
    			}else{
    				model.setPassword(newpwd);
    				userService.addOrUpdate(model);
            		result.put("msg", "修改成功!");
            		result.put("status", 0);
    			}
            	
        	}catch(Exception e){
        		e.printStackTrace();
        		result.put("msg", "修改失败!");
        		result.put("status", 1);
        	}
		}else{
			result.put("msg", "用户未登录!");
			result.put("status",2);
		}
		return result;
	}

}
