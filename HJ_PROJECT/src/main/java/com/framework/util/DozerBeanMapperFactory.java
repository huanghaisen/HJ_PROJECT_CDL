package com.framework.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 在Bean间复制数据
 * 更多信息请访问 http://dozer.sourceforge.net/
 *
 */
public class DozerBeanMapperFactory {
	
	private static final Log log = LogFactory.getLog(DozerBeanMapperFactory.class);
	private static DozerBeanMapper mapp = null;;
	
	public static DozerBeanMapper getMapper(){
		if(mapp==null){
			mapp = new DozerBeanMapper();
			List mappingFiles = new ArrayList();
			mappingFiles.add("dozerBeanMapping.xml");
			mapp.setMappingFiles(mappingFiles);
			log.debug("Dozer init successfully!");
		}
		return mapp;
	} 
}
