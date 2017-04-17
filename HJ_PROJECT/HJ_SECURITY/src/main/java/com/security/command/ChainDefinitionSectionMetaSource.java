package com.security.command;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

import com.framework.util.StringUtil;
import com.model.TSysRole;
import com.security.service.SecurityService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {
	
	private String filterChainDefinitions;
	
	@Resource(name="securityServiceImpl")
	SecurityService securityService;
	 /**
     * 默认premission字符串
     */
    public static final String ANYROLES_STRING="anyRoles[{0}]";

    public Section getObject() throws BeansException {

        Ini ini = new Ini();
        ini.load(filterChainDefinitions);
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        
//        for (Map.Entry<String, String> entry : section.entrySet()) {
//        	System.out.println(entry.getKey()+"<====section========>"+entry.getValue());
//		}
        
       List<TSysRole> List=securityService.getRoles("soa");
       for (Map.Entry<String, String> entry : section.entrySet()) {
    	   for (TSysRole tSysRole : List) {
	           	String key=entry.getKey();
	           	StringBuffer value=new StringBuffer(entry.getValue());
	           	if (StringUtil.search(key, tSysRole.getRoleSence())) {
	           		if (value.toString().lastIndexOf(']')!=-1) {
	           			value.insert(value.toString().lastIndexOf(']'),","+tSysRole.getRoleName());
					}else{
						value.append(",anyRoles[").append(tSysRole.getRoleSence()).append(",")
						.append(tSysRole.getRoleName())
						.append("]");
					}
	           		section.put(key, value.toString());
	           		value=null;
				}
	   		}
       }
       List=null;
     //获取所有Resource
//     List<Resource> list = resourceDao.getAll();
        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
        //里面的键就是链接URL,值就是存在什么条件才能访问该链接
//        for (Iterator<Resource> it = list.iterator(); it.hasNext();) {
//
//            Resource resource = it.next();
//            //如果不为空值添加到section中
//            if(StringUtils.isNotEmpty(resource.getValue()) && StringUtils.isNotEmpty(resource.getPermission())) {
//                section.put(resource.getValue(),  MessageFormat.format(PREMISSION_STRING,resource.getPermission()));
//            }

//        }
        return section;
    }

    /**
     * 通过filterChainDefinitions对默认的url过滤定义
     * 
     * @param filterChainDefinitions 默认的url过滤定义
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public Class<?> getObjectType() {
        return this.getClass();
    }

    public boolean isSingleton() {
        return false;
    }


}
