package com.utils;

import com.framework.view.MenuView;
import com.model.TSysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 菜单工具类
 * @author daniel
 *
 */
public class MenuUtils {
	
	
	/**
	 * 新菜单封装业务
	 * @param depath 菜单项最大层级
	 * @param menuLevelMap 菜单分级ID
	 * @param tempList 缓存临时数据
	 * @return
	 */
	public static List<MenuView> menupak(Integer depath,Map<Integer,List<Long>> menuLevelMap,Map<Long,TSysMenu> MenuMap){
		List<MenuView> mList=new ArrayList<MenuView>();//存放菜单模型数据
		//处理第一层包装
		for (Long pid : menuLevelMap.get(1)) {
			if (MenuMap.containsKey(pid)) {
				TSysMenu menu=MenuMap.get(pid);
				MenuView menuView=new MenuView();
				Integer chileNum=getChileNum(menu.getId(),menuLevelMap.get(2l), MenuMap);
				menuView=menupush(menuView, menu, "", chileNum, 1l);
				mList.add(menuPro(1l, menuLevelMap, MenuMap, menuView));
			}
		}

		return mList;
	}
	
	
	private static Integer getChileNum(Long id,List<Long> menuLevelList,Map<Long,TSysMenu> MenuMap){
		Integer fg=0;
		for (Map.Entry<Long,TSysMenu> entry: MenuMap.entrySet()) {
			if (id.equals(entry.getValue().getParentId())) {
				fg=1;
				break;
			}
		}
		
		return fg;
	}
	
	
	/**
	 * 递归方法
	 * @param level	当前层级
	 * @param menuLevelMap	分成id
	 * @param tempList	缓存数据
	 * @param menuView	菜单模型
	 * @return
	 */
	private static MenuView menuPro(Long level,Map<Integer,List<Long>> menuLevelMap,Map<Long,TSysMenu> MenuMap,MenuView menuView){
		level++;
		for (Long pid : menuLevelMap.get(level.intValue())) {
			if (MenuMap.containsKey(pid)) {
				TSysMenu menu=MenuMap.get(pid);
				if (menuView.getId().equals(menu.getParentId())) {
					MenuView cmenuView=new MenuView();
					menuView.getChildren().add(cmenuView);
					Integer chileNum=getChileNum(menu.getId(),menuLevelMap.get(level.intValue()), MenuMap);	
					cmenuView=menupush(cmenuView, menu, menuView.getNavtitle(), chileNum, level);
					if (chileNum>0) {
						menuPro(level, menuLevelMap, MenuMap, cmenuView);
					}
				}
			}
		}
		
		return menuView;
	}
	
	
	/**
	 * 包装菜单
	 * @param menuView 菜单对象
	 * @param menu 菜单数据对象
	 * @param navtitle 父级目录名称
	 * @param ischile 是否有子集
	 * @param level	层级
	 * @return
	 */
	public static MenuView menupush(MenuView menuView,TSysMenu menu,String navtitle,int ischile,Long level){
		menuView.setId(menu.getId());
		menuView.setName(menu.getMenuName());
		menuView.setHaschild(ischile);
		menuView.setLevel(level);
		menuView.setSeq(menu.getMenuOrder());
		if (navtitle!=null && !"".equals(navtitle)) {
			menuView.setNavtitle(navtitle+"_"+menu.getMenuName());
		}else{
			menuView.setNavtitle(menu.getMenuName());
		}
		menuView.setTarget(menu.getMenuTarget()==null?"":menu.getMenuTarget());
		menuView.setIco(menu.getMenuIcon()==null?"":menu.getMenuIcon());
		menuView.setNode(menu.getIsLeaf());
		menuView.setAction(menu.getMenuUrl()==null?"":menu.getMenuUrl());
		menuView.setStatus(menu.getStatus()==null?"":menu.getStatus());
		menuView.setCode(menu.getMenuCode());
		menuView.setMclass(menu.getMclass()==null?"":menu.getMclass());
		menuView.setType(menu.getMenuType()==null?"":menu.getMenuType());
		menuView.setSence(menu.getMenuSence()==null?"":menu.getMenuSence());
		menuView.setIsopen(menu.getMenuIsopen());
		return menuView;
	}
	
}

