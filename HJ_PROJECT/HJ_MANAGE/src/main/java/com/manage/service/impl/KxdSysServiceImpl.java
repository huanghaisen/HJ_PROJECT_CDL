package com.manage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.command.ApplicationConfig;
import com.framework.base.dao.impl.BaseDaoImpl;
import com.framework.view.MenuView;
import com.framework.view.UserInfoView;
import com.manage.service.KxdSysService;
import com.model.TSysMenu;
import com.base.service.CacheService;

/**
 * 基础业务类
 * @author daniel
 *
 */
@Service
public class KxdSysServiceImpl implements KxdSysService {
	
	@Resource(name = "baseDaoImpl")
	private BaseDaoImpl baseDao;
	
	@Resource(name="cacheServiceImpl")
	CacheService cacheService;

	/**
	 * 菜单获取
	 */
	@Override
	public List<MenuView> searchMenu(UserInfoView user) {
		List<TSysMenu> mlist=cacheService.cacheGetListEntity(ApplicationConfig.MENUCACHE);
		return null;
	}
	
	
	
	
	

}
