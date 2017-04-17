package com.manage.action;

import java.io.IOException;
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
import com.manage.service.FuncService;
import com.model.TSysFunc;
@Controller
@RequestMapping("/soa/func")
public class SysFuncController extends BaseViewController{

	    @Resource(name="sysFuncServiceImpl")
	    private FuncService funcService;
		
		Logger log=Logger.getLogger(SysFuncController.class);
	    
	    @RequestMapping(value="query",method={RequestMethod.POST,RequestMethod.GET})
	    public ModelAndView query(HttpServletRequest request,HttpServletResponse response)throws IOException{
	    	return new ModelAndView("#");//暂定
	    }
		
	    @RequestMapping(value="add",method={RequestMethod.POST,RequestMethod.GET})
	    public ModelAndView addfunc(HttpServletRequest request,HttpServletResponse response){
	    	
	    	return new ModelAndView("#");//暂定
	    }
	    
	    @RequestMapping(value="update",method={RequestMethod.POST,RequestMethod.GET})
	    public ModelAndView updatefunc(HttpServletRequest request,HttpServletResponse response){
	    	
	    	return new ModelAndView("#");//暂定
	    }
	    
	    @RequestMapping(value="saveorupdate",method={RequestMethod.POST,RequestMethod.GET})
	    @ResponseBody
	    public TSysFunc save(HttpServletRequest request,HttpServletResponse response,TSysFunc func){
	    	
	    	return funcService.addOrUpdate(func);
	    }
	    
	    @RequestMapping(value="find",method={RequestMethod.POST,RequestMethod.GET})
	    @ResponseBody
	    public TSysFunc find(HttpServletRequest request,HttpServletResponse response,Long id)throws IOException{
	    	TSysFunc model=null;
	    	model=funcService.findById(id);
	    	return model;
	    }
	    
	    @RequestMapping(value="list",method={RequestMethod.POST,RequestMethod.GET})
	    @ResponseBody
	    public List<TSysFunc> list(HttpServletRequest request,HttpServletResponse response)throws IOException{
	    	List<TSysFunc> list = funcService.findAll();
	    	return list;
	    }
	    
	    
	    @RequestMapping(value="del",method={RequestMethod.POST,RequestMethod.GET})
	    @ResponseBody
	    public Object del(HttpServletRequest request,HttpServletResponse response){
	    	Map<String,Object> result=new HashMap<String,Object>();
	    	Long id=Long.parseLong(request.getParameter("id"));
	    	if(id!=null){
	    		try{
	    			System.out.println("-------------------id: "+id);
	    			funcService.del(id);
	    			result.put("msg", "删除成功");
	    			result.put("status", 0);
	    		}catch(Exception e){
	    			e.printStackTrace();
	    			result.put("msg", "删除失败");
	    			result.put("status", 1);
	    		}
	    	}
	    	return result;
	    }
	}


