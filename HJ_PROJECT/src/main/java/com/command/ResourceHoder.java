package com.command;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取工程下资源文件
 * @author daniel
 *
 */

@Service
public class ResourceHoder implements ResourceLoaderAware {
	
	private ResourceLoader resourceLoader;
	
	
	public InputStream getFileInputStream(String fileName) throws IOException{		
		Resource resource=null;
		if (resourceLoader!=null) {
			resource=resourceLoader.getResource("classpath:"+fileName);
			return resource.getInputStream();
		}else{
			return null;
		}
		
		
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader=resourceLoader;

	}

}
