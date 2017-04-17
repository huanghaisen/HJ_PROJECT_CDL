package com.security.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.security.service.AuthService;

public class AuthServiceImpl implements AuthService {
	
    private static final String CRLF= "\r\n";
    private static final String LAST_AUTH_STR= "/** =authc\r\n";
    
    @Resource
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    
    @Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;

	@Override
	public String loadFilterChainDefinitions() {
		
		System.out.println("============>########%^&%&%^%^^*");
		StringBuffer sb = new StringBuffer("");
	       sb.append(getFixedAuthRule())
	       .append(getDynaAuthRule())
	       .append(getRestfulOperationAuthRule())
	       .append(LAST_AUTH_STR);
	       return sb.toString();
	}
	
	
	

	@Override
	public void reCreateFilterChains() {
		AbstractShiroFilter shiroFilter = null;
	       try{
	           shiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
	       } catch(Exception e) {
	    	   
	       }
	       
	       PathMatchingFilterChainResolver filterChainResolver =(PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver();
	       DefaultFilterChainManager manager =(DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
	 
	       //清空老的权限控制
	       manager.getFilterChains().clear();
	 
	       shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
	       shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());
	       //重新构建生成
	       Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
	        for(Map.Entry<String, String> entry :chains.entrySet()) {
	            String url = entry.getKey();
	            String chainDefinition =entry.getValue().trim().replace(" ", "");
	            manager.createChain(url,chainDefinition);
	        }
	       
	    }
	
	/**
	 * 得到固定权限验证规则串
	 * @return
	 */
	private String getFixedAuthRule(){
		
		return "";
	}
	
	/**
	 * 根据角色，得到动态权限规则
	 * @return
	 */
	private String getDynaAuthRule(){
		
		return "";
	}
	
	/**
	 * 生成restful风格功能权限规则
	 * @return
	 */
	private String getRestfulOperationAuthRule(){
		
		
		return "";
	}
}
