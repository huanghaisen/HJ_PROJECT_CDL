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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framework.base.controller.BaseViewController;
import com.framework.view.ResultDatas;
import com.framework.view.UserInfoView;
import com.manage.service.OrgService;
import com.model.TSysOrg;
import com.model.TSysRole;

/**
 * 机构管理模块
 * @author daniel
 *
 */
@Controller
@RequestMapping("/soa/org")
public class OrgController extends BaseViewController {
	
	Logger log=Logger.getLogger(OrgController.class);
	
	@Resource(name="orgServiceImpl")
	OrgService orgService;

	/**
	 * 主界面跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="index",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView orgquery(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> params=new HashMap<String, Object>();
		UserInfoView<TSysRole> userInfo=getSessionUser(request);
		params.put("user", userInfo);
		return new ModelAndView("/org/index",params);
	}
	
	
		/**
	    * 机构数据查询
	    * @param request
	    * @param response
	    * @return
	    */
	   @RequestMapping(value="datas/search",method={RequestMethod.POST,RequestMethod.GET})
	   @ResponseBody
	   public Object orgsearch(HttpServletRequest request,HttpServletResponse response,Long id){
	   		ResultDatas resultDatas=new ResultDatas();
	   		List<TSysOrg> list=null;
	   		if (id!=null) {
	   			TSysOrg tSysOrg=orgService.find(id);
	   			list=orgService.search(tSysOrg.getIdent(),tSysOrg.getEndIdent());
	   			tSysOrg=null;
	   		}else{
	   			list=orgService.search(null,null);
	   		}
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
	   
	   /**
	    * 机构数据查询
	    * @param request
	    * @param response
	    * @return
	    */
	   @RequestMapping(value="datas/cbxsearch",method={RequestMethod.POST,RequestMethod.GET})
	   @ResponseBody
	   public Object cbxsearch(HttpServletRequest request,HttpServletResponse response,Integer level){
	   		ResultDatas resultDatas=new ResultDatas();
	   		List<TSysOrg> list=null;
	   		UserInfoView<TSysRole> userInfo=getSessionUser(request);
	   		if (level==null || "".equals(level)) {
	   			level=0;
			}
   			TSysOrg tSysOrg=orgService.find(userInfo.getOrgId());
   			list=orgService.cbxsearch(tSysOrg.getIdent(),tSysOrg.getEndIdent(),level);
   			tSysOrg=null;
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
	   
	   /**
	    * 新增记录
	    * @param request
	    * @param response
	    * @param devicePartType
	    * @return
	    * @throws IOException
	    * @throws ParseException
	    */
	   @RequestMapping(value="datas/save",method={RequestMethod.POST,RequestMethod.GET})
		@ResponseBody
		public Object orgsave(HttpServletRequest request,HttpServletResponse response, TSysOrg model) throws IOException, ParseException{
			 Map<String,Object> result=new HashMap<String,Object>();
			 UserInfoView<TSysRole> userInfo=getSessionUser(request);
			if (model!=null) {			
				try{
					if(model.getId()==null){
						//新增用户
						TSysOrg maxTSysOrg=orgService.find(model.getParentOrgId());//获取同级数据是否有多条
						model.setStatus(0);
						model.setModifier(userInfo.getUid());
						model.setLastModifiedTime(new Date());
						model.setOrgFullName(model.getOrgName());
						model.setIsDeleted(true);
						if (maxTSysOrg==null) {//没有
							maxTSysOrg=orgService.find(model.getParentOrgId());
							model.setDepath(maxTSysOrg.getDepath()+1);
							model.setOrderNo(1);
						}else{//有本级子类数据
							model.setDepath(maxTSysOrg.getDepath());
							model.setOrderNo(maxTSysOrg.getOrderNo()+1);
						}
						maxTSysOrg=null;
						result.put("model", model);
						result.put("msg","新增成功!");
						result.put("status",0);
					}else{
						//修改
						TSysOrg nmodel=orgService.find(model.getId());
						nmodel.setOrgName(model.getOrgName());
						nmodel.setOrgType(model.getOrgType());
						nmodel.setOrgCode(model.getOrgCode());
						nmodel.setOrgBriefName(model.getOrgBriefName());
						nmodel.setTelphone(model.getTelphone());
						nmodel.setEmail(model.getEmail());
						nmodel.setAddress(model.getAddress());
						nmodel.setLeaderId(model.getLeaderId());
						model=nmodel;
						result.put("model", model);
						result.put("msg","更新成功!");
						result.put("status",0);
					}
					model.setIsDeleted(false);
					model.setModifier(userInfo.getUid());
					model.setLastModifiedTime(new Date());
					orgService.addOrUpdate(model);
					
					}catch (Exception e){
						e.printStackTrace();
						result.put("msg","保存失败!");
						result.put("status", 1);
					}finally{
						model=null;
					}	
			}
			return result;
		}
	   
	   
	   /**
	    * 排序
	    * @param request
	    * @param response
	    * @param id
	    * @return
	    * @throws IOException
	    */
	   @RequestMapping(value="datas/position",method={RequestMethod.POST,RequestMethod.GET})
		@ResponseBody
		public Object position(HttpServletRequest request,HttpServletResponse response,Integer cmd,Long id,Long nid) throws IOException{
			Map<String,Object> result=new HashMap<String,Object>();
			try{
			orgService.updatePosition(cmd, id, nid);
			result.put("msg", "排序成功！");
			result.put("status", 0);
			}catch (Exception e){
				e.printStackTrace();
				result.put("msg", "排序失败！");
				result.put("status", 1);
			}
			return result;
		}
	   
	   /**
	    * 删除配件
	    * @param request
	    * @param response
	    * @param id
	    * @return
	    * @throws IOException
	    */
	   @RequestMapping(value="datas/del",method={RequestMethod.POST,RequestMethod.GET})
		@ResponseBody
		public Object orgdel(HttpServletRequest request,HttpServletResponse response,Long id) throws IOException{
			Map<String,Object> result=new HashMap<String,Object>();
			try{
			//System.out.println("-------------------执行删除id: "+id);
			orgService.del(id);
			//System.out.println("-------------------ok");
			result.put("msg", "删除成功！");
			result.put("status", 0);
			}catch (Exception e){
				e.printStackTrace();
				result.put("msg", "删除失败！");
				result.put("status", 1);
			}
			return result;
		}
	   
	   /**
	     * 新增配件类型方法
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value="inst",method={RequestMethod.POST,RequestMethod.GET})
	    public ModelAndView orgadd(HttpServletRequest request,HttpServletResponse response,Long id,Long pid){
	    	return orgedit(request, response, id,pid);//转到编辑页面
	    }
	    
	   /**
	    * 修改
	    * @param request
	    * @param response
	    * @param id
	    * @param pid
	    * @return
	    */
	   @RequestMapping(value="edit",method={RequestMethod.POST,RequestMethod.GET})
	    public ModelAndView orgedit(HttpServletRequest request,HttpServletResponse response,Long id,Long pid){
		   TSysOrg model=null;
		    	if (id!=null) {
		    		model=orgService.find(id);
				}else{
					model=new TSysOrg();
					model.setParentOrgId(pid);
				}
			return new ModelAndView("/org/orgedit","model",model);
		}
	
	   @RequestMapping(value="datas/findChildrenById",method={RequestMethod.POST,RequestMethod.GET})
	   @ResponseBody
	   public Object findChildren(HttpServletRequest request,HttpServletResponse response,Long orgid){
	   		ResultDatas resultDatas=new ResultDatas();
	   		List<TSysOrg> list=null;
	   		UserInfoView<TSysRole> userInfo=getSessionUser(request);
	   		if (orgid==null || "".equals(orgid)) {
	   			orgid=userInfo.getOrgId();
			}
   			list=orgService.findByParentId(orgid);
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
	   @RequestMapping(value="datas/updateById",method={RequestMethod.POST,RequestMethod.GET})
	   @ResponseBody
	   public Object updateById(HttpServletRequest request,HttpServletResponse response,Long orgId,Float mapX,Float mapY){
		   Map<String,Object> result=new HashMap<String,Object>();
		   if(orgId==null || "".equals(orgId) || mapX==null || "".equals(mapX) || mapY==null || "".equals(mapY)){
			   result.put("msg", "更新失败！");
			   result.put("status", 1);
		   }else{
			   try{
				   TSysOrg updateOrg = orgService.updateById(orgId, mapX, mapY);
				   if(updateOrg != null){
					   result.put("msg", "更新成功！");
					   result.put("status", 0);
				   }else{
					   result.put("msg", "更新失败！");
					   result.put("status", 1);
				   }
				   
			   }catch (Exception e){
				   e.printStackTrace();
				   result.put("msg", "更新失败！");
				   result.put("status", 1);
			   }
		   }
			return result;
	   }
}
