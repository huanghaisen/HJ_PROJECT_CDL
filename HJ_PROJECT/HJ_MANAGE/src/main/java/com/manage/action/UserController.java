package com.manage.action;

import java.io.IOException;
import java.text.ParseException;
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
import com.framework.page.SysButton;
import com.framework.util.Md5Encoder;
import com.framework.util.StringUtil;
import com.framework.view.KxdParams;
import com.framework.view.ResultDatas;
import com.framework.view.UserInfoView;
import com.manage.service.ManufService;
import com.manage.service.UserService;
import com.model.TSysManuf;
import com.model.TSysRole;
import com.model.TSysUser;
import com.base.service.CacheService;


@Controller
@RequestMapping("/soa/user")
public class UserController extends BaseViewController {
	
	Logger log=Logger.getLogger(UserController.class);
		
	@Resource(name="userServiceImpl")
	UserService userService;
	
	@Resource(name="cacheServiceImpl")
	CacheService<TSysUser> cacheService;
	
	@Resource
	ManufService manufService;
	/**
	 * 主界面跳转
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
		return new ModelAndView("/user/userIndex",params);
	}
	
	/**
	 * 搜索控制器，整合了无参分页和有参分页
	 * 由于DT用空参分页会报错，故保留无参分页service
	 * @param request
	 * @param response
	 * @param params
	 * @param start
	 * @param length
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="datas/search",method={RequestMethod.POST})
	@ResponseBody
	public Object conditionsPaging(HttpServletRequest request,HttpServletResponse response,KxdParams params,Long start,Long length) throws IOException{
		ResultDatas resultDatas=new ResultDatas();
		List<TSysUser> list=null;
		Long recordsTotal=null;
		Long recordsFiltered=null;
		
		if(null==params.getParams()){
			recordsTotal=userService.getTotalSize();
			//filter的数额是显示在DT的条目数
			recordsFiltered=recordsTotal;
			list=userService.searchPage(start, length);			
		}else {
			Map<String,Object> map=params.getParams();
			recordsTotal=userService.getSearchSize(map);
			//filter的数额是显示在DT的条目数
			recordsFiltered=recordsTotal;
			list=userService.conditionsPage(map, start, length);
		}
		if (list.size()>0) {
    		resultDatas.setSuccess(0);
    		resultDatas.setMsg("拿到数据");
    		resultDatas.setStart(start);
    		resultDatas.setLength(length);
    		resultDatas.setRecordsFiltered(recordsFiltered);
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
	 * 通过条件查询用户信息列表
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="datas/userSelect",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object userSelect(HttpServletRequest request,HttpServletResponse response,Integer cmd,Long oid) throws IOException{
		List<TSysUser> ulist=null;
		if (cmd!=null && oid!=null ) {
			ulist=userService.selectUser(cmd, oid,"soa");
		}
		return ulist;
	}
	
	@RequestMapping(value="datas/manufUserSelect",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object manufUserSelect(HttpServletRequest request,HttpServletResponse response,Long manufId) throws IOException{
		List<TSysUser> ulist=null;
			ulist=userService.manufUserSel(manufId);
		return ulist;
	}
	/**
	 * 以Id找User对象，Edit要用
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="datas/find",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public TSysUser find(HttpServletRequest request,HttpServletResponse response,Long userId) throws IOException{
		TSysUser model=null;
		model=userService.findById(userId);	
		return model;
	}
	

	/**
     * 新增方法(主要做页面跳转到模态框取值)
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="inst",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response){
    	
    	return editUser(request, response, null);//撞到编辑页面
    }
    
    /**
     * 修改方法
     * @param request
     * @param response
     * @param id
     * @return
     */
   @RequestMapping(value="edit",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView editUser(HttpServletRequest request,HttpServletResponse response,Long id){
	   		TSysUser model=null;
	    	if (id!=null) {
	    		model=userService.findById(id);	    		
			}else{
				model=new TSysUser();
			}
	    	
		return new ModelAndView("/user/userEdit","userModel",model);
	}
   
    /**
     * 保存user对象，inst和edit最后都要走到这里，不能删
     * @param request
     * @param response
     * @param model
     * @return
     * @throws IOException
     * @throws ParseException
     */
	@RequestMapping(value="datas/save",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object save(HttpServletRequest request,HttpServletResponse response, TSysUser model) throws IOException, ParseException{
		UserInfoView userInfo=this.getSessionUser(request);
		Map<String,Object> result=new HashMap<String,Object>();
		if (model.getId()!=null) {
			TSysUser user=userService.findById(model.getId());
			user.setOrgId(model.getOrgId());
			user.setNickName(model.getNickName());
			user.setEmail(model.getEmail());
			user.setPhone(model.getPhone());
			user.setSex(model.getSex());
			user.setType(model.getType());
			user.setSence(model.getSence());
			userService.addOrUpdate(user);
			model=null;
			user=null;
		}else{
			Date date = new Date();
			String MD5PASSWORD = Md5Encoder.getMd5Msg(model.getLoginName()+model.getPassword()+StringUtil.getSimpleDateFormat(date));
			model.setPassword(MD5PASSWORD);
			model.setBirthDay(new Date());
			model.setCreateTime(new Date());
			model.setToken(Md5Encoder.getMd5Msg(model.getLoginName()+model.getPassword()+model.getCreateTime().toString()+System.currentTimeMillis()));
			model.setTokenLimit(System.currentTimeMillis()+1000000);
			userService.addOrUpdate(model);
			result.put("user", model);
			result.put("msg", "新增成功");
			result.put("status",0);
			model=null;
		}
		System.out.println(result);
		return result;
	}

	
	@RequestMapping(value="datas/del",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Object del(HttpServletRequest request,HttpServletResponse response,Long id) throws IOException{
		Map<String,Object> result=new HashMap<String,Object>();
		try{
		userService.del(id);
		result.put("msg", "删除成功");
		result.put("status", 0);
		}catch (Exception e){
			e.printStackTrace();
			result.put("msg", "删除失败");
			result.put("status", 1);
		}
		return result;
	}
  

	@RequestMapping(value="datas/getButtonsMap",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
    public Object getButtonsMap(HttpServletRequest request,HttpServletResponse response,String params){
    //String user=params;
	String user="admin"	;  //模拟，先写死
		 Map<String,Object> inst=new HashMap<String,Object>();
	 	 inst.put("name","新增");
	 	 inst.put("value","inst");
	 	 inst.put("type","列表域");
	 	 inst.put("className","btn btn-primary margin btn-sm");
	 	 
	 	Map<String,Object> edit=new HashMap<String,Object>();
	 	edit.put("name","修改");
	 	edit.put("value","edit");
	 	edit.put("type","列表域");
	 	edit.put("className","btn btn-primary margin btn-sm");
		 
		 Map<String,Object> del=new HashMap<String,Object>();
		 del.put("name","删除");
		 del.put("value","del");
		 del.put("type","列表域");
		 del.put("className","btn btn-warning margin btn-sm");
	 	 
	 	Map<String,Object> search=new HashMap<String,Object>();
	 	search.put("name","查询");
	 	search.put("value","search");
	 	search.put("type","列表域");
	 	search.put("className","btn btn-info margin btn-sm");
		 
		 Map<String,Object> save=new HashMap<String,Object>();
		 save.put("name","刷新");
		 save.put("value","save");
		 save.put("type","列表域");
		 save.put("className","btn btn-success margin btn-sm");		 
	 	  
	     Map<String,Object> sub=new HashMap<String,Object>();
	     sub.put("name","提交");
	     sub.put("value","sub");
	     sub.put("type","列表域");
	     sub.put("className","btn btn-success margin btn-sm");	
			 
		 Map<String,Object> chk=new HashMap<String,Object>();
		 chk.put("name","审核");
		 chk.put("value","chk");
		 chk.put("type","列表域");
		 chk.put("className","btn btn-primary margin btn-sm");			 
			 
		Map<String,Object> exp=new HashMap<String,Object>();
		exp.put("name","导出");
		exp.put("value","exp");
		exp.put("type","列表域");
		exp.put("className","btn btn-primary margin btn-sm");
		Map<String,Object> print=new HashMap<String,Object>();
		print.put("name","打印");
		print.put("value","print");
		print.put("type","列表域 表单域");
		print.put("className","btn btn-primary margin btn-sm");

			 
	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	if("admin".equals(user)){
 	  list.add(inst);
 	  list.add(del);
 	  list.add(edit);
 	  list.add(search);
 	  list.add(save);
 	  
	}
	 

	  return list;
  }

	@RequestMapping(value="datas/getButtonsData",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
    public Object getButtonsData(HttpServletRequest request,HttpServletResponse response,String params){
    //String user=params;
	String user="admin"	;  //模拟，先写死
    SysButton inst=new SysButton();
    inst.setName("新增");
    inst.setValue("inst");
    inst.setType("列表域");
    inst.setClassName("btn btn-primary margin btn-sm");
    SysButton del=new SysButton();
    del.setName("删除");
    del.setValue("del");
    del.setType("列表域");
    del.setClassName("btn btn-warning margin btn-sm");
    SysButton edit=new SysButton();
    edit.setName("修改");
    edit.setValue("edit");
    edit.setType("列表域");
    edit.setClassName("btn btn-primary margin btn-sm");
    SysButton search=new SysButton();
    search.setName("查询");
    search.setValue("search");
    search.setType("列表域");
    search.setClassName("btn btn-info margin btn-sm");
    SysButton save=new SysButton();
    save.setName("刷新");
    save.setValue("save");
    save.setType("列表域");
    save.setClassName("btn btn-primary margin btn-sm");


	List<SysButton> list=new ArrayList<SysButton>();
	if("admin".equals(user)){
 	  list.add(inst);
 	  list.add(del);
 	  list.add(edit);
 	  list.add(search);
 	  list.add(save);
 	  
	}
	  return list;
  }
	/**
	 * 获取厂商信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value ="datas/maufCombox", method = { RequestMethod.POST })
	@ResponseBody
	public Object maufCombox(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<TSysManuf> list = manufService.maufCombox();
		return list;
	}
}