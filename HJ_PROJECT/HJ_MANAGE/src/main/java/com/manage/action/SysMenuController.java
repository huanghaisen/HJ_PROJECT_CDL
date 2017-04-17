package com.manage.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
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

import com.command.ApplicationConfig;
import com.framework.base.controller.BaseViewController;
import com.framework.util.StringUtil;
import com.framework.view.MenuView;
import com.framework.view.ResultDatas;
import com.framework.view.UserInfoView;
import com.manage.service.MenuService;
import com.model.TSysMenu;
import com.model.TSysRole;
import com.base.service.CacheService;
import com.view.ManageMenuView;

@Controller
@RequestMapping("/soa/menu")
public class SysMenuController extends BaseViewController{

    @Resource(name="sysMenuServiceImpl")
    MenuService menuService;
    
    @Resource(name="cacheServiceImpl")
	CacheService<MenuView> cacheService;
    
	
	Logger log=Logger.getLogger(SysMenuController.class);
	
	/**
	 * 跳转菜单页面 wyq
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
    @RequestMapping(value="index",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView query(HttpServletRequest request,HttpServletResponse response)throws IOException{
    	UserInfoView<TSysRole> userInfo=getSessionUser(request);
		return new ModelAndView("/menu/menuIndex","user",userInfo);
    }
    
    @RequestMapping(value="index2",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView newIndex(HttpServletRequest request,HttpServletResponse response)throws IOException{
    	UserInfoView<TSysRole> userInfo=getSessionUser(request);
		return new ModelAndView("/menu/menuIndex2","user",userInfo);
    }
	
    @RequestMapping(value="add",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView addmenu(HttpServletRequest request,HttpServletResponse response){
    	
    	return new ModelAndView("#");//暂定
    }
    
    @RequestMapping(value="update",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView updatemenu(HttpServletRequest request,HttpServletResponse response){
    	
    	return new ModelAndView("#");//暂定
    }
    

    
    @RequestMapping(value="list",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public List<TSysMenu> list(HttpServletRequest request,HttpServletResponse response,String sence)throws IOException{
    	
    	if(sence==null){
    		return menuService.findAll("soa");
    	}else{
    		return menuService.findAll(sence);
    	}
    	
    }
    
    
    /**
     *  获取用户菜单(新版菜单用)
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    /**
     *  获取用户菜单(新版菜单用)
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    
    @RequestMapping(value="datas/search2",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object menuLoad2(HttpServletRequest request,HttpServletResponse response,String sence)throws IOException{
    	UserInfoView<TSysRole> user=getSessionUser(request);
		ResultDatas resultDatas=new ResultDatas();
		if(sence==null){
			sence="soa";
		}
		if (user!=null) {
			List<TSysMenu> mlist= menuService.findAll(sence);
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

    
    
    
    
    /**
	 * 获取用户菜单
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="datas/search",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object menuLoad(HttpServletRequest request,HttpServletResponse response,String sence){
		UserInfoView<TSysRole> user=getSessionUser(request);
		if (StringUtil.isEmpty(sence)) {
			sence=user.getSence();
		}
		ResultDatas resultDatas=new ResultDatas();
		if (user!=null) {
			List<MenuView> mlist=cacheService.cacheGetListEntity(ApplicationConfig.MENUCACHE+sence);
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
    
    
    /**
     * 弹出新增页面方法 wyq
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="inst",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView addRole(HttpServletRequest request,HttpServletResponse response){
    	ManageMenuView menu = new ManageMenuView();
    	menu.setParentId(Integer.parseInt(request.getParameter("parentId")));
    	menu.setTier(Integer.parseInt(request.getParameter("tier")));
    	menu.setCode(Long.parseLong(request.getParameter("code")));
    	menu.setSence(request.getParameter("sence"));
		return new ModelAndView("/menu/menuInst","menu",menu);
    }
 
    /**
     * 弹出修改页面方法 wyq
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value="edit",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView editMenu(HttpServletRequest request,HttpServletResponse response,Long id){
	   		TSysMenu model=null;
	   		if (id!=null) {
	    		model=menuService.findById(id);
			}else{
				model=new TSysMenu();
			}
	    	
		return new ModelAndView("/menu/menuUpdate","menu",model);
	}
    
    
    /**
     * 添加菜单项 wyq
     * @param request
     * @param response
     * @param menu
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @RequestMapping(value="datas/save",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object save(HttpServletRequest request,HttpServletResponse response, TSysMenu menu, String tier) throws IOException, ParseException{
		 Map<String,Object> result=new HashMap<String,Object>();
		if (menu.getId()!=null) {		
			System.out.println("------修改菜单-------");
			try{
				TSysMenu model=menuService.findById(menu.getId());
				model.setMenuName(menu.getMenuName());
				model.setMenuUrl(menu.getMenuUrl());
				model.setMenuIsopen(menu.getMenuIsopen());
				model.setIsVisible(menu.getIsVisible());
				menuService.addOrUpdate(model);
				result.put("menu", model);
				result.put("msg", "修改成功!");
				result.put("status",0);
				menu=null;
				model=null;
				}catch (Exception e){
					e.printStackTrace();
					result.put("msg","修改失败!");
					result.put("status",1);
				}finally{
					menu=null;
				}	
		}else{
			System.out.println("------添加菜单------");
			Integer num=this.menuService.getNodeCount(menu.getParentId(),menu.getMenuSence());
			TSysMenu pmodel=this.menuService.findById(menu.getParentId());
			int level=0;
			Long code=0l;
			if(pmodel!=null){
				level=pmodel.getDepath()+1;
			}else{
				level=1;
			}
			if(level==1){
				code = new Long((num+1)*100000);
			}else if(level==2){
				code = (num+1)*100+menu.getMenuCode();
			}else if(level==3){
				code = (num+1)+menu.getMenuCode();
			}
			try {
					menu.setMenuCode(code);
					menu.setMenuType("normal");
					menu.setMenuIcon("fa-laptop");
					menu.setMenuOrder(num+1);
					menu.setIsLeaf(1);
					menu.setStatus("");
					menu.setMclass("");
					menu.setDepath(level);
					menuService.addOrUpdate(menu);
					result.put("menu", menu);
					result.put("msg", "添加成功!");
					result.put("status",0);
					
					System.out.println("添加菜单==序号："+menu.getMenuOrder()+"====编号："+code+"======菜单级别："+menu.getDepath());
					
				} catch (Exception e) {
					e.printStackTrace();
					result.put("msg", "添加失败!");
					result.put("status", 1);
				}
		}
		return result;
		
	}
 
    
    
    
    /**
	 * 删除菜单 wyq
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="datas/del",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object del(HttpServletRequest request,HttpServletResponse response){
    	Map<String,Object> result=new HashMap<String,Object>();
    	Long id=Long.parseLong(request.getParameter("id"));//当前节点ID
    	int pid = Integer.parseInt(request.getParameter("pid"));//父节点ID
    	int order = Integer.parseInt(request.getParameter("order"));//当前节点序号
    	String tier=request.getParameter("tier");//当前节点层级
    	Long code = Long.parseLong(request.getParameter("code"));//但前节点编号
    	String sence = request.getParameter("sence");//菜单分级
    	
    	System.out.println("id:"+id+",pid:"+pid+",order:"+order+",tier:"+tier+",code:"+code+",sence:"+sence);
    	if(id!=null){
    		try{
    			int count = menuService.getNodeCount(id,sence);//判断是否有子节点
    			if(code!=100000){
    				if(count<=0){
        				menuService.del(id);
            			menuService.updateOrder(pid,order,sence);//修改序号
            			menuService.updateEode(pid, tier,code,sence);//修改编号
            			result.put("msg", "删除成功!");
            			result.put("status", 0);
        			}
        			else{
        				result.put("msg", "存在子节点，无法删除!");
            			result.put("status", 1);
        			}
    			}else{
    				result.put("msg", "此处为最高节点，无法删除!");
        			result.put("status", 1);
    			}
    			
    			
    		}catch(Exception e){
    			e.printStackTrace();
    			result.put("msg", "删除失败!");
    			result.put("status", 1);
    		}
    	}
    	return result;
    }
    
    
    /**
	 * 删除菜单 wudj(新菜单)
	 * @param request
	 * @param response
	 * @return
	 */
    @RequestMapping(value="datas/del2",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object del2(HttpServletRequest request,HttpServletResponse response,Long id,Integer pid,Integer order,String tier,Long code,String sence ){
    	Map<String,Object> result=new HashMap<String,Object>();
    	//id当前节点ID
    	//pid父节点ID
    	//order当前节点序号
    	//tier当前节点层级
    	//code但前节点编号
    	//sence菜单分级
    	
    	System.out.println("id:"+id+",pid:"+pid+",order:"+order+",tier:"+tier+",code:"+code+",sence:"+sence);
    	if(id!=null){
    		try{
    			int count = menuService.getNodeCount(id,sence);//判断是否有子节点
    			System.out.println("count:"+count);
    				
        				menuService.del(id);
            			menuService.updateOrder(pid,order,sence);//修改序号
            			menuService.updateEode(pid, tier,code,sence);//修改编号
            			result.put("msg", "删除成功!");
            			result.put("status", 0);
        		
        		
    			
    			
    		}catch(Exception e){
    			e.printStackTrace();
    			result.put("msg", "删除失败!");
    			result.put("status", 1);
    		}
    	}
    	return result;
    }
    
    
    /**
     * 菜单升序 wyq
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="asc",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object asc(HttpServletRequest request,HttpServletResponse response){
    	Map<String,Object> result=new HashMap<String,Object>();
    	int id = Integer.parseInt(request.getParameter("id"));//当前ID
    	int parent_id = Integer.parseInt(request.getParameter("parent_id"));//父ID
    	int order = Integer.parseInt(request.getParameter("order"));//当前序号
    	String sence = request.getParameter("sence");//菜单分级
    	System.out.println(id+"：升序=============");
    		try{
    			System.out.println("-------------------id: "+id);
    			menuService.asc(id, parent_id, order,sence);
    			result.put("msg", "升序成功!");
    			result.put("status", 0);
    		}catch(Exception e){
    			e.printStackTrace();
    			result.put("msg", "升序失败!");
    			result.put("status", 1);
    		}
    	return result;
    }
    
    /**
     * 菜单降序 wyq
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="desc",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object desc(HttpServletRequest request,HttpServletResponse response){
    	Map<String,Object> result=new HashMap<String,Object>();
    	int id = Integer.parseInt(request.getParameter("id"));//当前ID
    	int parent_id = Integer.parseInt(request.getParameter("parent_id"));//父ID
    	int order = Integer.parseInt(request.getParameter("order"));//当前序号
    	String sence = request.getParameter("sence");//菜单分级
    	System.out.println(id+"：降序=============");
    		try{
    			System.out.println("-------------------id: "+id);
    			menuService.desc(id, parent_id, order,sence);
    			result.put("msg", "降序成功!");
    			result.put("status", 0);
    		}catch(Exception e){
    			e.printStackTrace();
    			result.put("msg", "降序失败!");
    			result.put("status", 1);
    		}
    	return result;
    }
    
    /**
     * 写入菜单缓存
     */
    @RequestMapping(value="writeCache",method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public Object writeCache(HttpServletRequest request,HttpServletResponse response,String sence){
    	ResultDatas resultDatas=new ResultDatas();
    	try{
			menuService.writeCache(sence);
			resultDatas.setMsg("写入成功!");
			resultDatas.setSuccess(0);
		}catch(Exception e){
			e.printStackTrace();
			resultDatas.setMsg("写入失败!");
			resultDatas.setSuccess(1);
		}
    	return resultDatas;
    }
}
