package com.manage.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.framework.view.MenuView;
import com.framework.view.ResultDatas;
import com.framework.view.UserInfoView;
import com.manage.service.MenuService;
import com.manage.service.RoleService;
import com.model.TSysMenu;
import com.model.TSysRole;
import com.model.TSysRoleMenu;
import com.model.TSysUserRole;
import com.base.service.CacheService;

@Controller
@RequestMapping("/soa/role")
public class RoleController extends BaseViewController{

    @Resource(name="roleServiceImpl")
    private RoleService roleService;
    
    @Resource(name="sysMenuServiceImpl")
    MenuService menuService;
    
	@Resource(name="cacheServiceImpl")
	CacheService<MenuView> cacheService;
	
	//private List<MenuView> menuLists;
	Logger log=Logger.getLogger(RoleController.class);
	
    
    @RequestMapping(value="index",method={RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request,HttpServletResponse response)throws IOException{
    	UserInfoView<TSysRole> userInfo=getSessionUser(request);
    	
    	return new ModelAndView("/manage/roleindex","user",userInfo);//暂定
    }
	
    /**
     * 新增方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="inst",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView addRole(HttpServletRequest request,HttpServletResponse response){   	
    	return updateRole(request, response, null);//撞到编辑页面
    }
    
    /**
     * 编辑页面动作
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value="edit",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView updateRole(HttpServletRequest request,HttpServletResponse response,Long id){
    	UserInfoView<TSysRole> userInfo=getSessionUser(request);
    	TSysRole model=null;
    	if (id!=null) {
    		model=roleService.findById(id);
		}else{
			model = new TSysRole();
			model.setCreateUser(userInfo.getUid());
		}
    	return new ModelAndView("/manage/roleedit","model",model);
    }
    /**
     * 
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value="editRoleMenu",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object editRoleMenu(HttpServletRequest request,HttpServletResponse response,Long id){
    	//menuLists = new ArrayList<MenuView>();
    	List<TSysMenu> list= new ArrayList<TSysMenu>();
    	System.out.println("editRoleMenu:"+id);
    	if (id!=null) {
    		//TSysRole role = roleService.findById(id);
    		//System.out.println("角色的场景："+role.getRoleSence());
    		//List<MenuView> mlists=cacheService.cacheGetListEntity(ApplicationConfig.MENUCACHE+role.getRoleSence());  		
    		List<TSysRoleMenu> roleMenu =roleService.roleFindMenu(id);
    		//List<MenuView> mlist = iterationMenu(mlists);
    		//System.out.println(mlist.size());
    		//for(int i=0;i<mlist.size();i++){
			for (int j = 0; j < roleMenu.size(); j++) {
				// System.out.println(mlist.get(i).getId()+"--editRoleMenu---"+roleMenu.get(j).getMenuId()+"--"+(mlist.get(i).getId().equals(roleMenu.get(j).getMenuId())));
				/*
				 * if(mlist.get(i).getId().equals(roleMenu.get(j).getMenuId())){
				 * list.add(mlist.get(i)); }
				 */
				TSysMenu menu = menuService.findNodeMenuById(roleMenu.get(j).getMenuId());
				if(menu != null){
					list.add(menu);
					menu = null;
				}
			}
			roleMenu = null;
    		//}
    	}
    	ResultDatas resultDatas=new ResultDatas();
    	if (list!=null && list.size()>0) {
    		resultDatas.setSuccess(0);
    		resultDatas.setMsg("");
    		resultDatas.setData(list);
		}else{
			resultDatas.setSuccess(1);
    		resultDatas.setData("");
    		resultDatas.setMsg("没有数据");
		}
    	System.out.println(list.size());
    	list = null;
	return resultDatas;
    }
    
    
    /**
     * 数据保存
     * @param request
     * @param response
     * @param role
     * @return
     */
    @RequestMapping(value="datas/save",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public TSysRole save(HttpServletRequest request,HttpServletResponse response,TSysRole role){
    	if(role.getId()==null){
    		role.setCreateTime(new Date());
    		role.setRoleLevel(1L);
    	}
    	return roleService.addorupdate(role);
    }
    
    /**
     * 查询单个实体
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value="show",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView find(HttpServletRequest request,HttpServletResponse response,Long id){
    	TSysRole model=null;
    	model=roleService.findById(id);
    	return new ModelAndView("/manage/roleedit","model",model);
    }
    
    /**
     * 查询页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="datas/search",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object list(HttpServletRequest request,HttpServletResponse response,String sence){
    	ResultDatas resultDatas=new ResultDatas();
    	String str;
    	   if(sence==null){
    		   str="soa";
    	   }else{
    		   str=sence;
    	   }
    		List<TSysRole> list=roleService.findAll(str);
        	if (list.size()>0) {
        		resultDatas.setSuccess(0);
        		resultDatas.setMsg("");
        		resultDatas.setData(list);
    		}else{
    			resultDatas.setSuccess(1);
        		resultDatas.setData("");
        		resultDatas.setMsg("没有数据");
    		}
        	sence=null;
        	str=null;
    	return resultDatas;
    }
    
    /**
     * 删除数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="datas/del",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object del(HttpServletRequest request,HttpServletResponse response,String ids){
    	Map<String,Object> result=new HashMap<String,Object>();
    	Long id=Long.parseLong(request.getParameter("id"));
    	if(id!=null){
    		int userNum = this.countUserByRoleId(id);
    		System.out.println("改角色下的用户数量："+userNum);
    		if(userNum>0){
    			result.put("msg", "该角色下有用户关联，不容许删除！");
    			result.put("status", 1);
    		}else{
	    		try{
	    			System.out.println("-------------------id: "+id);
	    			roleService.del(id);
	    			List<TSysRoleMenu> roleFindMenu = roleService.roleFindMenu(id);
	    			for(TSysRoleMenu roleMenu:roleFindMenu){
	    				roleService.delPermissionByRMId(roleMenu.getId());
	    				roleService.roleDelMenu(roleMenu);
	    			}
	    			
	    			result.put("msg", "删除成功");
	    			result.put("status", 0);
	    		}catch(Exception e){
	    			e.printStackTrace();
	    			result.put("msg", "删除失败");
	    			result.put("status", 1);
	    		}
    		}
    	}
    	return result;
    }
   
    
    @RequestMapping(value="userAddRole",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public TSysUserRole userAddRole(HttpServletRequest request,HttpServletResponse response,Long userId,Long roleId){
    	TSysUserRole model=new TSysUserRole();
    	if(userId!=null&&roleId!=null){	
    	 model=roleService.UserAddRole(userId, roleId); 
    	}
    	return model;    			
    }
    
    @RequestMapping(value="userDelRole",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void userDelRole(HttpServletRequest request,HttpServletResponse response,Long userId,Long roleId){
    	if(userId!=null&&roleId!=null){	
    	roleService.UserDelRole(userId, roleId);	
    	}else{
    		System.out.println("udr失败");
    	}
    }
    
    @RequestMapping(value="datas/roleFindMenu",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object roleFindMenu(HttpServletRequest request,HttpServletResponse response,Long roleId){
    	System.out.println(roleId);
    	ResultDatas resultDatas=new ResultDatas();
    	if(roleId!=null){	
    		List<TSysRoleMenu> list =roleService.roleFindMenu(roleId);
    		if (list.size()>0) {
        		resultDatas.setSuccess(0);
        		resultDatas.setMsg("");
        		resultDatas.setData(list);
    		}else{
    			resultDatas.setSuccess(1);
        		resultDatas.setData("");
        		resultDatas.setMsg("没有数据");
    		}
    	}else{
    		System.out.println("udr失败");
    	}
    	return resultDatas;
    }
    
    @RequestMapping(value="datas/roleUpdateMenu",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<TSysRoleMenu> roleUpdateMenu(HttpServletRequest request,HttpServletResponse response,Long roleId,Long[] menuIds){
    	if(roleId!=null&&menuIds!=null){	
    		return roleService.roleUpdateMenu(roleId, menuIds);
    	}else{
    		System.out.println("udr失败");
    		return null;
    	}
    }
    
    @RequestMapping(value="datas/roleSenceMenu",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object roleSenceMenu(HttpServletRequest request,HttpServletResponse response,Long roleId){
    	ResultDatas resultDatas=new ResultDatas();
    	if(roleId!=null){	
    		TSysRole role = roleService.findById(roleId);
    		List<TSysMenu> list = roleService.roleSenceMenu(role);
    		System.out.println("角色场景："+role.getRoleSence()+",list="+list.size());
    		if (list.size()>0) {
        		resultDatas.setSuccess(0);
        		resultDatas.setMsg("");
        		resultDatas.setData(list);
    		}else{
    			resultDatas.setSuccess(1);
        		resultDatas.setData("");
        		resultDatas.setMsg("没有数据");
    		}
    	}else{
    		System.out.println("udr失败");
    	}
    	return resultDatas;
    }
    /*
     * //旧的从缓存获取数据的方法
    private List<MenuView> iterationMenu(List<MenuView> list){
    	for(int i =0;i<list.size();i++){
    		menuLists.add(list.get(i));
    		if(list.get(i).getHaschild()>0){
    			iterationMenu(list.get(i).getChildren());
    		}
    	}
    	return menuLists;
    }*/
    
    private int countUserByRoleId(Long roleId){
    	return roleService.countUserByRoleId(roleId);
    }
}
