package com.framework.base.controller;

import com.framework.util.Utils;
import com.framework.view.ResultFiles;
import com.framework.view.Session;
import com.framework.view.UserInfoView;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BaseViewController{
	
	public String controllertip="";
	
	@ModelAttribute
	private void start(){
//		System.out.println(controllertip+"<==========start============>"+this.getClass().getName());
	}
	
	@PostConstruct
	private void init(){
//		System.out.println(this.getClass().getPackage().getName()+"<==========init===========>"+this.getClass().getName()+"<====>"+this.getClass().getSimpleName());
		String[] str=this.getClass().getPackage().getName().split("\\.");
		if (str.length>2) {
			this.controllertip=str[1];
		}
		str=null;
	}
	
	@PreDestroy
	private void destroy(){
		System.out.println(this.getClass().getPackage().getName()+"<==========destroy===========>"+this.getClass().getName()+"<====>"+this.getClass().getSimpleName());
		
	}
	
	/**
	 * 把用户对象存储在Session
	 * @param request
	 * @param user
	 */
	protected void setSessionUser(HttpServletRequest request,UserInfoView user){
		request.getSession().setAttribute(Session.USER_INFO, user);
	}
	
	/**
	 * 获取保存在Session的用户对象
	 * @param request
	 * @return
	 */
	protected UserInfoView getSessionUser(HttpServletRequest request){
		return (UserInfoView)request.getSession().getAttribute(Session.USER_INFO);
	}
	
	
	/**
	 * 把用户对象从Session中删除
	 * @param request
	 */
	protected void removeSessionUser(HttpServletRequest request){
		request.getSession().removeAttribute(Session.USER_INFO);
	}
	
	protected String getip(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获取用户来源设备
	 * @param request
	 * @return
	 */
	protected String getBrowser(HttpServletRequest request) {
		String browserType="";
		String agent =request.getHeader("user-agent");
		 if(agent.contains("Android")) {
			browserType="android";
		 } else if(agent.contains("iPhone")) {
			 browserType="iPhone";
		 }  else if(agent.contains("iPad")) {
			 browserType="iPad";
		 }  else {
			 browserType="other";
		 }
		return browserType;
	}
	
	public Map<String,Object> sysUploadFile(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
    	Map<String,Object> result=new HashMap<String,Object>();
    	List<ResultFiles> rlist=new ArrayList<ResultFiles>();
    	Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
        	//创建一个通用的多部分解析器  
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
            //判断 request 是否有文件上传,即多部分请求  
            if(multipartResolver.isMultipart(request)){  
                //转换成多部分request    
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
                List<MultipartFile> files = multiRequest.getFiles("file"); 
                for (MultipartFile file : files) {
                	if (file!=null && !"".equals(file.getOriginalFilename())) {
                    	ResultFiles rfile=new ResultFiles();
                    	//取得当前上传文件的文件名称  
                        String myFileName = file.getOriginalFilename();
                        //原始文件名
                        String ORIGINAL_FILE_NAME = myFileName.substring(0,myFileName.lastIndexOf("."));
                        //文件后缀
                        String FILE_SUFFIX=myFileName.substring(myFileName.lastIndexOf(".")+1);
                        //最终文件名
                        String FINAL_FILE_CODE = UUID.randomUUID().toString();
                        //上传后的文件
                        String FILE_NAME = FINAL_FILE_CODE+"."+FILE_SUFFIX;
                        //文件大小
                        Long FILE_SIZE = file.getSize();
                        if(file != null){  
                            //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                            if(myFileName.trim() !=""){
                                //重命名上传后的文件名  
                                String fileName = FILE_NAME;  
                                //定义上传路径  
                                String rooturl="/local/webroot/hxsp_app/upload"+File.separator;
                                String url="";//返回虚拟路径
                                String path="";//存储真实路径
                                

                              path=request.getSession().getServletContext().getRealPath("upload");//原始服务路径
                              System.out.println("==========================>>>path:"+path);
                                Utils.addFile(path);
                                try {
                                	File localFile = new File(path+File.separator+fileName);
                                    file.transferTo(localFile);
                                    //返回状态 
                                    rfile.setSize(FILE_SIZE/1000);
                                    rfile.setName(FILE_NAME);
                                    rfile.setPath(path+File.separator + fileName);
                                    rfile.setUrl(url);
                                    rfile.setSuffix(FILE_SUFFIX);
                                    rfile.setMsg("成功");
                                    rfile.setStatus(0l);
                                    localFile=null;
    							} catch (Exception e) {
                                    rfile.setSuffix(FILE_SUFFIX);
                                    rfile.setMsg("文件["+ORIGINAL_FILE_NAME+"]上传失败");
                                    rfile.setStatus(1l);
    							}
                                rooturl=null;
                                url=null;
                                path=null;
                            }  
                        }  
                        rlist.add(rfile);
                        result.put("files", rlist);
                        result.put("msg", "");
            			result.put("status", 0);
                        rfile=null;
                        rlist=null;
					}
                	file=null;
				}
                files=null;
            }  
        }else{
        	result.put("msg", "未登录或者没有权限!");
			result.put("status", 1);
        }
        return result;
    } 
}
