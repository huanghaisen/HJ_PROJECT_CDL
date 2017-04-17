package com.server.command;

import java.util.Date;

import javax.annotation.Resource;

import com.base.service.ApplicationService;
import com.base.service.CacheService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.command.ApplicationConfig;
import com.framework.util.TimeUtils;


/**
 * 服务启动监听
 * 
 * @author chirs
 * 
 */

@Service
public class KXDApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	public static Logger loger = Logger.getLogger(KXDApplicationListener.class);
	
	@Resource(name="applicationServiceImpl")
	ApplicationService applicationService;
	
	@Resource(name="cacheServiceImpl")
	CacheService cacheService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent()==null) {
			System.out.println("====系统初始服务=========>"+TimeUtils.getDateTime(new Date()));
//			applicationService.initComponent(event.getApplicationContext());//初始组件
			applicationService.initorg();//处理机构
			ApplicationConfig.SYSTEMBOOT=true;
			
			applicationService.initmenu("soa");//初始菜单数据
//			applicationService.initmenu("moa");//初始菜单数据
//			applicationService.initmenu("coa");//初始菜单数据
			
			/*ApplicationContext applicationContext=event.getApplicationContext();
			RequestMappingHandlerMapping rqp=(RequestMappingHandlerMapping) applicationContext.getBean(RequestMappingHandlerMapping.class);*/
//			
//			String[] str=applicationContext.getBeanDefinitionNames();
//			for (int i = 0; i < str.length; i++) {
//				System.out.println("========2222222222222222========>"+str[i]);
//			}
			
//			for (Map.Entry<RequestMappingInfo,HandlerMethod> entry : rqp.getHandlerMethods().entrySet()) {
////				System.out.println("========1111111111111111========>"+entry.getValue().getMethod().getName());
//				System.out.println(entry.getKey().getPatternsCondition().toString()+"<========检查授权控制器========>"+entry.getValue().getMethod().getName());
////				System.out.println("========2222222222222222===[POST/GET]=====>"+entry.getKey().getMethodsCondition().toString());
////				System.out.println("========3333333333333333========>"+entry.getKey().getPatternsCondition().toString());
//			}
		}
	}

}
