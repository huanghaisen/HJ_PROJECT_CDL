package com.manage.action;

import java.io.IOException;
import java.text.ParseException;
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
import org.springframework.web.servlet.ModelAndView;

import com.framework.base.controller.BaseViewController;
import com.framework.view.KxdParams;
import com.framework.view.ResultDatas;
import com.framework.view.UserInfoView;
import com.manage.service.ManufService;
import com.model.TSysManuf;
import com.model.TSysRole;


@Controller
@RequestMapping("/soa/manuf")
public class ManufController extends BaseViewController {
	
	@Resource
	ManufService manufService;
	
	/**
	 * 厂商首页
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="index",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView query(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> params=new HashMap<String, Object>();
		UserInfoView<TSysRole> userInfo=getSessionUser(request);
		params.put("user", userInfo);
		return new ModelAndView("/manuf/index",params);
	}
	
	/**
	 * 分页搜索
	 * @param request
	 * @param response
	 * @param params
	 * @param start
	 * @param length
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="datas/search",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object conditionsPaging(HttpServletRequest request,HttpServletResponse response,KxdParams params,Long start,Long length) throws IOException{
		ResultDatas resultDatas=new ResultDatas();
		List<TSysManuf> list=null;
		Map<String,Object> map=params.getParams();
		Long recordsTotal=manufService.getSearchSize(map);
		list=manufService.searchPage(map, start, length);
		if (list.size()>0) {
    		resultDatas.setSuccess(0);
    		resultDatas.setStart(start);
    		resultDatas.setLength(length);
    		resultDatas.setRecordsFiltered(recordsTotal);
    		resultDatas.setRecordsTotal(recordsTotal);
    		resultDatas.setData(list);
		}else{
			resultDatas.setSuccess(1);
    		resultDatas.setData("");
    		resultDatas.setMsg("没有数据");
		}
    	list=null;
    	return resultDatas;
	}
	
	/**
	 * 分页搜索
	 * @param request
	 * @param response
	 * @param params
	 * @param start
	 * @param length
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="datas/cbxsearch",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object cbxsearch(HttpServletRequest request,HttpServletResponse response,Integer type) throws IOException{
		ResultDatas resultDatas=new ResultDatas();
		List<TSysManuf> list=null;
		list=manufService.searchPage(type);
		if (list.size()>0) {
    		resultDatas.setSuccess(0);
    		resultDatas.setRecordsFiltered(0);
    		resultDatas.setRecordsTotal(0);
    		resultDatas.setData(list);
		}else{
			resultDatas.setSuccess(1);
    		resultDatas.setData("");
    		resultDatas.setMsg("没有数据");
		}
    	list=null;
    	return resultDatas;
	}
	
	/**
	 * 获取单条数据对象
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="datas/find",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object find(HttpServletRequest request,HttpServletResponse response,Long id) throws IOException{
		TSysManuf model=null;
		model=manufService.findById(id);	
		return model;
	}
	
	 /**
     * 新增方法
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="inst",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response){
    	
    	
    	return edit(request, response, null);//撞到编辑页面
    }
	
	/**
     * 修改方法
     * @param request
     * @param response
     * @param id
     * @return
     */
   @RequestMapping(value="edit",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView edit(HttpServletRequest request,HttpServletResponse response,Long id){
	   	TSysManuf model=null;
    	if (id!=null) {
    		model=manufService.findById(id);
    		
		}else{
			model=new TSysManuf();
		}
		return new ModelAndView("/manuf/edit","model",model);
	}
   
   /**
    * 保存数据对象
    * @param request
    * @param response
    * @param model
    * @return
    * @throws IOException
    * @throws ParseException
    */
   @RequestMapping(value="datas/save",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object save(HttpServletRequest request,HttpServletResponse response, TSysManuf model) throws IOException, ParseException{
	     UserInfoView<TSysRole> user=getSessionUser(request);
		 Map<String,Object> result=new HashMap<String,Object>();
		if (model!=null) {			
			try{
				if (model.getId()!=null) {
					TSysManuf manuf=manufService.findById(model.getId());
					model.setLastModifiedTime(new Date());
					model.setModifier(user.getUid());
					manuf.copy(model);
					result.put("msg", "更新成功");
					manufService.addOrUpdate(manuf);//修改
					manuf=null;
				}else{
					if(model.getManufType()==null){
						model.setManufType(1L);
					}
					model.setIsDeleted(false);
					model.setCreator(user.getUid());
					model.setModifier(user.getUid());
					model.setCreateTime(new Date());
					model.setLastModifiedTime(new Date());
					manufService.addOrUpdate(model);//新增
					result.put("msg", "添加成功");
				}
				result.put("status",0);
				}catch (Exception e){
					e.printStackTrace();
					result.put("msg", "更新失败");
					result.put("status", 1);
				}finally{
					model=null;
				}	
		}
		return result;
		
	}
   
   /**
    * 保存数据对象
    * @param request
    * @param response
    * @param model
    * @return
    * @throws IOException
    * @throws ParseException
    */
   @RequestMapping(value="datas/del",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object del(HttpServletRequest request,HttpServletResponse response, TSysManuf model) throws IOException, ParseException{
		 Map<String,Object> result=new HashMap<String,Object>();
		if (model!=null) {			
			try{
				//Long mid=model.getId();
				//this.manufService.del(mid);//软删除
				this.manufService.del(model);
				result.put("msg", "删除成功");				
				result.put("status",0);
				}catch (Exception e){
					e.printStackTrace();
					result.put("msg", "删除失败");
					result.put("status", 1);
				}finally{
					model=null;
				}	
		}
		return result;
		
	}
   
	
}
