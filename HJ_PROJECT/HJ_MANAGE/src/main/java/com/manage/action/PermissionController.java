package com.manage.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framework.base.controller.BaseViewController;
import com.framework.view.ResultDatas;
import com.manage.service.PermissionService;
import com.model.TSysPermission;
import com.model.TSysRoleMenu;

@Controller
@RequestMapping("/soa/permission")
public class PermissionController extends BaseViewController{
   
	@Resource(name="permissionServiceImpl")
	private PermissionService pmService;
	
	Logger log=Logger.getLogger(RoleController.class);
	
	/**
	 * 跳转页面控制  add update delete query
	 */
    @RequestMapping(value="add",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView addRole(HttpServletRequest request,HttpServletResponse response){
    	
    	return new ModelAndView("#");//暂定
    }
    
    @RequestMapping(value="update",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView updateRole(HttpServletRequest request,HttpServletResponse response){
    	
    	return new ModelAndView("#");//暂定
    }
    
    @RequestMapping(value="delete",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView deleteRole(HttpServletRequest request,HttpServletResponse response){
    	
    	return new ModelAndView("#");//暂定
    }
    
    @RequestMapping(value="query",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView query(HttpServletRequest request,HttpServletResponse response)throws IOException{
    	return new ModelAndView("/permission/permissionIndex");//暂定
    }
    
    /**
     * service控制
     */
    @RequestMapping(value="saveOrUpdate",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public TSysPermission saveOrUpdate(HttpServletRequest request,HttpServletResponse response,TSysPermission permission)throws IOException{
    	TSysPermission pm=pmService.addOrUpdate(permission);
    	return pm;
    }
    
    @RequestMapping(value="list",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<TSysPermission> list(HttpServletRequest request,HttpServletResponse response){
    	List<TSysPermission> list=pmService.findAll();
    	return list;
    }
    
    @RequestMapping(value="del",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void del(HttpServletRequest request,HttpServletResponse response,Long id){
    	pmService.del(id);
    }
    
    @RequestMapping(value="findById",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public TSysPermission findById(HttpServletRequest request,HttpServletResponse response,Long id){
    	TSysPermission permission=pmService.findById(id);
    	return permission;
    }
    
    /**
     * 角色关联权限控制
     */
    @RequestMapping(value="roleAddPm",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public TSysPermission RoleAddPermission(HttpServletRequest request,HttpServletResponse response,Long roleId,Long PermissionId){
    	TSysPermission RP=pmService.RoleAddPermission(roleId, PermissionId);
		return RP;
    	
    }
	
    @RequestMapping(value="roleDelPm",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
	public void RoleDelPermission(HttpServletRequest request,HttpServletResponse response,Long roleId,Long PermissionId){
		pmService.RoleDelPermission(roleId, PermissionId);
	}
	/**
	 * 此方法无效，请勿使用
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 */
    @RequestMapping(value="getAllPmByRoleId",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
	public List<TSysPermission> getAllByRoleId(HttpServletRequest request,HttpServletResponse response,Long roleId){
		List<TSysPermission> list=pmService.getAllByRoleId(roleId);
    	return list;		
	}
    
    @RequestMapping(value="getAllPmByRMId",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object getRMByR2MId(HttpServletRequest request,HttpServletResponse response,Long roleId,Long menuId){
    	ResultDatas resultDatas=new ResultDatas();
    	List<TSysPermission> list= new ArrayList<TSysPermission>();
    	System.out.println("roleId="+roleId+",menuId="+menuId);
    	if(roleId!=null && menuId!=null){
    		TSysRoleMenu roleMenu = this.getRoleMenu(roleId, menuId);
    		list = pmService.getFunByRMId(roleMenu.getId());
    		resultDatas.setMsg(roleMenu.getId().toString());
    	}
    	if (list.size()>0) {
    		resultDatas.setSuccess(0);
    		resultDatas.setData(list);
		}else{
			resultDatas.setSuccess(1);
    		resultDatas.setData("");
		}
    	return resultDatas;		
    }
    
    @RequestMapping(value="roleUpdatePermission",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<TSysPermission> roleUpdatePermission(HttpServletRequest request,HttpServletResponse response,Long rmId,Long[] funIds){
    	List<TSysPermission> list= new ArrayList<TSysPermission>();
    	System.out.println("rmId="+rmId+",funIds="+funIds.length);
    	if(rmId!=null && funIds!=null){
    		list = pmService.roleUpdatePermission(rmId, funIds);
    	}
    	return list;		
    }
    
    private TSysRoleMenu getRoleMenu(Long roleId,Long menuId){
    	return pmService.getRoleMenu(roleId,menuId);
    }
}
