package com.security.command;

import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

public class ChainApiSectionMetaSource implements FactoryBean<Section> {
	
	private String filterChainDefinitions;  

	 /**
     * 默认premission字符串
     */
    public static final String PREMISSION_STRING="perms[\"{0}\"]";

    public Section getObject() throws BeansException {

        //获取所有Resource
//        List<Resource> list = resourceDao.getAll();
        Ini ini = new Ini();
        //加载默认的url
        ini.load(filterChainDefinitions);
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        
        for (Map.Entry<String, String> entry : section.entrySet()) {
        	System.out.println(entry.getKey()+"<====section========>"+entry.getValue());
		}
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
