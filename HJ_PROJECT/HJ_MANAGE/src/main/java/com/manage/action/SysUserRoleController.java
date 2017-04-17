package com.manage.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.controller.BaseViewController;
import com.framework.view.ResultDatas;
import com.manage.service.UserRoleService;
import com.model.TSysUserRole;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/soa/userRole")
public class SysUserRoleController extends BaseViewController {
	@Resource(name="sysUserRoleServiceImpl")
    private UserRoleService userRoleService;
	
	Logger log=Logger.getLogger(SysUserRoleController.class);
	
	/**
     * 查询单个实体
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value="/datas/show",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object find(HttpServletRequest request,HttpServletResponse response,Long id){
    	ResultDatas resultDatas=new ResultDatas();
		List<TSysUserRole> list=userRoleService.findByUserId(id);
    	if (list.size()>0) {
    		resultDatas.setSuccess(0);
    		resultDatas.setMsg("");
    		resultDatas.setData(list);
		}else{
			resultDatas.setSuccess(1);
    		resultDatas.setData("");
    		resultDatas.setMsg("没有数据");
		}
	return resultDatas;
    }
    
    @RequestMapping(value="/datas/update",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<TSysUserRole> userAddRole(HttpServletRequest request,HttpServletResponse response,Long userId,Long[] roleIds){
    	System.out.println("userId="+userId+"-----roleIds.length="+roleIds.length);
    	if(userId!=null&&roleIds!=null){
    		return userRoleService.save(userId,roleIds);
    	}
    	return null;
    	   			
    }
}
