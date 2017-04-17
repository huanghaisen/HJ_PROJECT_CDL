package com.manage.service;

import java.util.List;

import com.framework.view.MenuView;
import com.framework.view.UserInfoView;

public interface KxdSysService {
	
	public List<MenuView> searchMenu(UserInfoView user);

}
