package com.command;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 监听类使用
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 **/

@Service
public class ApplicationHolder implements ApplicationContextAware {

	public static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext() {
		ChackApplicationContext();
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ApplicationHolder.applicationContext = applicationContext;
//		System.out.println("=============ApplicationHolder==============>");
//		String[] str=applicationContext.getBeanDefinitionNames();
//		for (int i = 0; i < str.length; i++) {
//			System.out.println(getBean(str[i]).getClass().getPackage().getName()+"<===11111===>"+str[i]);
//		}
//		
	}
	
	
	

	/**
	 * 
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		ChackApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 
	 * 如果有多个Bean符合Class, 取出第一个.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		ChackApplicationContext();
		Map beanMaps = applicationContext.getBeansOfType(clazz);
		if (beanMaps != null && !beanMaps.isEmpty()) {
			return (T) beanMaps.values().iterator().next();
		} else {
			return null;
		}
	}
	
	
	public static boolean destroyBean(String beanname){
		ChackApplicationContext();
		ConfigurableApplicationContext context=(ConfigurableApplicationContext) applicationContext;
		DefaultListableBeanFactory beans=(DefaultListableBeanFactory)context.getBeanFactory();
		if (beans.containsBean(beanname)) {
//			beans.destroyBean(beanName, beanInstance);
			beans.destroySingleton(beanname);
			beans.removeBeanDefinition(beanname);
		}
		return true;
	}

	// 检查容器注入
	private static void ChackApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException(
					"applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}

	}

}
