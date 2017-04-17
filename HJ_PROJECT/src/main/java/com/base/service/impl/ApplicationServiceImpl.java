package com.base.service.impl;

import com.base.service.ApplicationService;
import com.command.ApplicationConfig;
import com.command.ApplicationHolder;
import com.framework.base.dao.impl.BaseDaoImpl;
import com.framework.view.*;
import com.model.*;
import com.base.service.CacheService;
import com.utils.MenuApiUtils;
import com.utils.MenuUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统级别业务
 * @author daniel
 *
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	public final static Long TIMEMINUTES=30l;//设置过期
	
	@Resource(name = "baseDaoImpl")
	private BaseDaoImpl baseDao;
	
	@Resource(name="cacheServiceImpl")
	CacheService cacheService;
	
	/**
	 * 从缓存里去查找用户信息
	 * @param token 用户验证串 
	 * @return
	 */
	public UserInfoView getSessionUser(String token){
		if (token!=null && !token.equals("")) {
			return (UserInfoView)cacheService.cacheGetObj(ApplicationConfig.USER_SESSION, token, TIMEMINUTES);
		}
		return null;
	}
	
	/**
	 * 写入用户session缓存
	 * @param obj
	 * @param time
	 * @return
	 */
	public boolean setSessionUser(UserInfoView obj){
		boolean flag=false;
		if (obj.getToken()!=null) {
			flag=this.cacheService.cacheSetObj(ApplicationConfig.USER_SESSION, obj.getToken(), obj, TIMEMINUTES);
		}
		return flag;
	}
	
	/**
	 * 删除用户session缓存
	 * @param obj
	 * @param time
	 * @return
	 */
	public boolean delSessionUser(String token){
		boolean flag=false;
		if (token!=null && "".equals(token)) {
			this.cacheService.deleteCache(ApplicationConfig.USER_SESSION, token);
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 初始组件
	 */
	@Override
	public void initComponent(ApplicationContext ctx){
		//获取组件
		List<TSysComponent> list=this.baseDao.listByHql("from TSysComponent c", null);
		if (list.size()>0){
			for (TSysComponent sysComponent : list) {
				SysCacheInfo.syscomp.put(sysComponent.getCode(), new ArrayList<String>());
			}
		}
		
		//先抽取容器里注册的组件
		String[] str=ctx.getBeanDefinitionNames();
		for (int i = 0; i < str.length; i++) {
//			System.out.println(ctx.getBean(str[i]).getClass().getPackage().getName()+"<===11111===>"+str[i]);
			String[] clsPackage=ctx.getBean(str[i]).getClass().getPackage().getName().split("\\.");
			if (clsPackage.length>2){
				if ("action".equals(clsPackage[2]) || "service".equals(clsPackage[2])) {
					if (SysCacheInfo.syscomp.containsKey(clsPackage[1])) {
						SysCacheInfo.syscomp.get(clsPackage[1]).add(str[i]);
					}else{
						if (SysCacheInfo.sysdestroycomp.containsKey(clsPackage[1])) {
							SysCacheInfo.sysdestroycomp.get(clsPackage[1]).add(str[i]);
						}else{
							List<String> destroyList=new ArrayList<String>();
							destroyList.add(str[i]);
							SysCacheInfo.sysdestroycomp.put(clsPackage[1], destroyList);
							destroyList=null;
						}
					}
				}
			}
		}
		
		//处理注销不可用插件
		for (Map.Entry<String,List<String>> entry : SysCacheInfo.sysdestroycomp.entrySet()) {
			for (String  destroycomp : entry.getValue()) {
				ApplicationHolder.destroyBean(destroycomp);
			}
		}
		SysCacheInfo.sysdestroycomp.clear();//清空
	}

	/**
	 * 缓存所有的机构
	 */
	@Override
	public void initorg() {
		StringBuffer hql=new StringBuffer("from TSysOrg c where c.isDeleted=false order by c.depath asc");
		List<TSysOrg> list=this.baseDao.listByHql(hql.toString(), null);
		Map<String,Object> orgMap=new HashMap<String, Object>();
		orgMap.put("datas", list);//缓存机构数据
		int depth=0;
		for (TSysOrg sysOrg : list) {
			if (sysOrg.getDepath()>depth) {
				depth=sysOrg.getDepath();
			}
		}
		orgMap.put("depth", depth);//添加深度
		//处理子集分层
		for (int i = 1; i <=depth; i++) {
			Map<Long,List<Long>> cmap=new HashMap<Long,List<Long>>();
			for (TSysOrg sysOrg : list) {
				if (sysOrg.getDepath().equals(i)) {
					List<Long> pList=new ArrayList<Long>();
					//判断是否存在
					if (cmap.containsKey(sysOrg.getParentOrgId())) {
						List nList=cmap.get(sysOrg.getParentOrgId());
						nList.add(sysOrg.getId());
						cmap.put(sysOrg.getParentOrgId(), nList);
						nList=null;
					}else{
						pList.add(sysOrg.getId());
						cmap.put(sysOrg.getParentOrgId(), pList);
						pList=null;
					}
				}
			}
			orgMap.put(i+"", cmap);
			cmap=null;
		}
		cacheService.deleteCacheAll(ApplicationConfig.ORGCACHE);//清除缓存
		cacheService.cacheSetAll(ApplicationConfig.ORGCACHE,orgMap);
		orgMap=null;
	}
	

	/**
	 * 获取机构数据
	 * @param cmd	指令(depth 获取数据层级深度,children 获取子集(数据对象TreeView),lastdepth 获取当前机构下子营业厅(数据对象List<TreeView>),parent获取当前机构指定父机构级别数据,childrenNode获取子机构数据)
	 * @param id 当前用户机构id
	 * @return
	 */
	@Override
	public Object getOrg(String cmd,Long id,Integer level) {
		Object Obj=null;
		switch (cmd.toLowerCase()) {
		case "depth":
			Obj=cacheService.cacheGetObj(ApplicationConfig.ORGCACHE,cmd);
			break;
		case "children"://获取子集节点(带营业厅数据)
			Obj=findOrgView(id);
			break;
		case "childrennode"://获取子集节点(子集节点数据)
			Obj=findOrgViewNode(id);
			break;
		case "parent"://获取父集层级
			Obj=findOrgParentView(level,id);
			break;
		case "lastdepth"://获取用户当前机构子营业厅
			Obj=LastOrgView(null,id);
			break;
		default://获取当前机构实体对象
			Obj=findOrg(id);
			break;
		}
		return Obj;
	}
	
	//获取机构数据子类
	private TreeView findOrgView(Long id){
		TSysOrg org=findOrg(id);
		if (org!=null) {
			TreeView treeView=new TreeView(org.getId(),org.getParentOrgId(),org.getOrgName(),org.getDepath(),org.getIdent(),org.getEndIdent(),org.getOrderNo(),org.getOrgType());
			Map<Long,List<Long>> cmap=(Map<Long, List<Long>>) cacheService.cacheGetObj(ApplicationConfig.ORGCACHE, (org.getDepath()+1)+"");
				if (cmap!=null && cmap.containsKey(id)) {
					for (Long pid : cmap.get(org.getId())) {
						TSysOrg corg=findOrg(pid);
						TreeView ctreeView=new TreeView(corg.getId(),corg.getParentOrgId(),corg.getOrgName(),corg.getDepath(),corg.getIdent(),corg.getEndIdent(),corg.getOrderNo(),corg.getOrgType());
						if (chkTreeView((corg.getDepath()+1)+"", corg.getId())) {
							ctreeView=findOrgView(corg.getId());
						}
						treeView.getChildren().add(ctreeView);
						corg=null;
						ctreeView=null;
					}
				}
				org=null;
			return treeView;
		}else{
			return null;
		}
	}
	
	//获取机构数据子类
		private TreeView findOrgViewNode(Long id){
			TSysOrg org=findOrg(id);
			if (org!=null) {
				TreeView treeView=new TreeView(org.getId(),org.getParentOrgId(),org.getOrgName(),org.getDepath(),org.getIdent(),org.getEndIdent(),org.getOrderNo(),org.getOrgType());
				Map<Long,List<Long>> cmap=(Map<Long, List<Long>>) cacheService.cacheGetObj(ApplicationConfig.ORGCACHE, (org.getDepath()+1)+"");
					if (cmap!=null && cmap.containsKey(id)) {
						for (Long pid : cmap.get(org.getId())) {
							TSysOrg corg=findOrg(pid);
							TreeView ctreeView=new TreeView(corg.getId(),corg.getParentOrgId(),corg.getOrgName(),corg.getDepath(),corg.getIdent(),corg.getEndIdent(),corg.getOrderNo(),corg.getOrgType());
							treeView.getChildren().add(ctreeView);
							corg=null;
							ctreeView=null;
						}
					}
					org=null;
				return treeView;
			}else{
				return null;
			}
		}
	
	
	//获取机构层级数据父类
	private TreeView findOrgParentView(Integer level,Long id){
		TSysOrg org=findOrg(id);
		if (org!=null) {
			TreeView treeView=null;
			if (level<org.getDepath()) {
				for (int i = org.getDepath(); i >= level; i--) {
					if (org.getDepath().equals(level)) {
						treeView=new TreeView(org.getId(),org.getParentOrgId(),org.getOrgName(),org.getDepath(),org.getIdent(),org.getEndIdent(),org.getOrderNo(),org.getOrgType());
					}else{
						org=findOrg(org.getParentOrgId());
					}
				}
			}else{
				treeView=new TreeView(org.getId(),org.getParentOrgId(),org.getOrgName(),org.getDepath(),org.getIdent(),org.getEndIdent(),org.getOrderNo(),org.getOrgType());
			}
			org=null;
			return treeView;
		}else{
			return null;
		}
	}
	
	//获取机构下所有营业厅数据
	private List<TreeView> LastOrgView(List<TreeView> listTree,Long id){
		TSysOrg org=findOrg(id);//获取机构
		if (listTree==null) {
			listTree=new ArrayList<TreeView>();//子营业厅数据
		}
		if (org!=null) {//机构不为空
			if (org.getDepath()<4) {
				if (org.getOrgType()>0) {
					TreeView ctreeView=new TreeView(org.getId(),org.getParentOrgId(),org.getOrgName(),org.getDepath(),org.getIdent(),org.getEndIdent(),org.getOrderNo(),org.getOrgType());
					listTree.add(ctreeView);
					ctreeView=null;
				}
				Map<Long,List<Long>> cmap=(Map<Long, List<Long>>) cacheService.cacheGetObj(ApplicationConfig.ORGCACHE, (org.getDepath()+1)+"");
				if (cmap!=null && cmap.containsKey(id)) {
					for (Long pid : cmap.get(org.getId())) {
						LastOrgView(listTree,pid);
					}
				}
				cmap=null;
			}else{
				TreeView ctreeView=new TreeView(org.getId(),org.getParentOrgId(),org.getOrgName(),org.getDepath(),org.getIdent(),org.getEndIdent(),org.getOrderNo(),org.getOrgType());
				listTree.add(ctreeView);
				ctreeView=null;
			}
		}
		return listTree;
	}
	
	//获取单个机构数据对象
	private TSysOrg findOrg(Long id){
		
		List<TSysOrg> list=(List<TSysOrg>) cacheService.cacheGetObj(ApplicationConfig.ORGCACHE, "datas");
		for (TSysOrg sysOrg : list) {
			if(id.equals(sysOrg.getId())){
				return sysOrg;
			}
		}
		return null;
	}
	
	//检查是否还有子类
	private boolean chkTreeView(String depth,Long id){
		boolean fg=false;
		Map<Long,List<Long>> cmap=(Map<Long, List<Long>>) cacheService.cacheGetObj(ApplicationConfig.ORGCACHE, depth);
		if (cmap!=null && cmap.containsKey(id)) {
			if (cmap.get(id)!=null && cmap.get(id).size()>0) {
				fg= true;
			}else{
				fg= false;
			}
		}else{
			fg= false;
		}
		cmap=null;
		return fg;
	}
	
	
	
	//处理菜单缓存新业务(新)
	@Override
	public boolean initmenu(String sence){
		cacheService.deleteCacheAll(ApplicationConfig.MENUCACHE+sence);//清空
		Map<String,Object> menuMap=new HashMap<String, Object>();
		//获取菜单对打深度
		StringBuffer depathhql=new StringBuffer("select max(c.depath) from t_sys_menu c where c.menu_sence='").append(sence).append("'");
		Long depath=Long.valueOf(this.baseDao.getSingleBySql(depathhql.toString(), null)==null?"0":this.baseDao.getSingleBySql(depathhql.toString(), null).toString());
		if(depath>0){
			menuMap.put("depath", depath);//写入层级深度
			StringBuffer menuhql=new StringBuffer("from TSysMenu c where c.menuSence='").append(sence).append("' order by c.menuOrder asc");
			List<TSysMenu> menuList=this.baseDao.listByHql(menuhql.toString(), null);
			menuMap.put("datas", menuList);//写入层级深度
			
			Map<Integer,List<Long>> menuLevelMap=new HashMap<Integer,List<Long>>();
			for (int i = 1; i <= depath; i++) {
				List<Long> idList=new ArrayList<Long>();
				for (TSysMenu tSysMenu : menuList) {
					if (tSysMenu.getDepath()==i) {
						idList.add(tSysMenu.getId());
					}
				}
				menuLevelMap.put(i, idList);
				idList=null;
			}
			menuMap.put("level", menuLevelMap);//写入层级深度
			if (menuList.size()>0) {
				//处理菜单业务对象
				cacheService.deleteCacheAll(ApplicationConfig.MENUCACHE+sence);//清空
				cacheService.cacheSetAll(ApplicationConfig.MENUCACHE+sence, menuMap);
			}
			
			depathhql=null;
			menuhql=null;
			menuMap=null;
			menuList=null;
			menuLevelMap=null;
			return true;
		}else{
			return false;
		}
	}
	
	
	//获取菜单的入口
	@Override
	public List<MenuView> getmenu(UserInfoView<TSysRole> user) {
		Long depath=(Long) cacheService.cacheGetObj(ApplicationConfig.MENUCACHE+user.getSence(),"depath");//获取菜单深度
		List<TSysMenu> menuList=(List<TSysMenu>) cacheService.cacheGetObj(ApplicationConfig.MENUCACHE+user.getSence(),"datas");//获取菜单模型数据
		List<MenuView> mListNew=new ArrayList<MenuView>();
		if (user.getrList()!=null && user.getrList().size()>0) {
			StringBuffer hql=new StringBuffer("select c.menu_id from t_sys_role_menu c where c.role_id in(");
			List<Long> params=new ArrayList<Long>();
			for (int i = 0; i < user.getrList().size(); i++) {
				if (i>0) {
					hql.append(",?");
				}else{
					hql.append("?");
				}
				params.add(user.getrList().get(i).getId());
			}
			hql.append(")");
			List<Object> rmlist=this.baseDao.listBySQL(hql.toString(), params);
			rmlist=new ArrayList<Object>(new HashSet<Object>(rmlist));//去重数据
			//填充用菜单信息
			//获取菜单层级
			Map<Integer,List<Long>> menuLevelMap=(Map<Integer, List<Long>>) cacheService.cacheGetObj(ApplicationConfig.MENUCACHE+user.getSence(),"level");//货菜单分层数据
			Map<Long,TSysMenu> MenuMap=new HashMap<Long,TSysMenu>();
			for (Object mobj : rmlist) {
				Long mid=Long.valueOf(mobj.toString());
				for (TSysMenu menu : menuList) {
					if (mid.equals(menu.getId())) {
						MenuMap.put(menu.getId(), menu);
					}
				}
			}
			mListNew=MenuUtils.menupak(depath.intValue(),menuLevelMap,MenuMap);//处理封装
			hql=null;
			depath=null;
			menuList=null;
			menuLevelMap=null;
			MenuMap=null;
			rmlist=null;
			params=null;
		}
		
		return mListNew;
	}
	
	//获取菜单的入口
		@Override
		public List<MenuViewApi> getapimenu(UserInfoView<TSysRole> user) {
			Long depath=(Long) cacheService.cacheGetObj(ApplicationConfig.MENUCACHE+user.getSence(),"depath");//获取菜单深度
			List<TSysMenu> menuList=(List<TSysMenu>) cacheService.cacheGetObj(ApplicationConfig.MENUCACHE+user.getSence(),"datas");//获取菜单模型数据
			List<MenuViewApi> mListNew=new ArrayList<MenuViewApi>();
			if (user.getrList()!=null && user.getrList().size()>0) {
				StringBuffer hql=new StringBuffer("select c.menu_id from t_sys_role_menu c where c.role_id in(");
				List<Long> params=new ArrayList<Long>();
				for (int i = 0; i < user.getrList().size(); i++) {
					if (i>0) {
						hql.append(",?");
					}else{
						hql.append("?");
					}
					params.add(user.getrList().get(i).getId());
				}
				hql.append(")");
				List<Object> rmlist=this.baseDao.listBySQL(hql.toString(), params);
				rmlist=new ArrayList<Object>(new HashSet<Object>(rmlist));//去重数据
				//填充用菜单信息
				//获取菜单层级
				Map<Integer,List<Long>> menuLevelMap=(Map<Integer, List<Long>>) cacheService.cacheGetObj(ApplicationConfig.MENUCACHE+user.getSence(),"level");//货菜单分层数据
				Map<Long,TSysMenu> MenuMap=new HashMap<Long,TSysMenu>();
				for (Object mobj : rmlist) {
					Long mid=Long.valueOf(mobj.toString());
					for (TSysMenu menu : menuList) {
						if (mid.equals(menu.getId())) {
							MenuMap.put(menu.getId(), menu);
						}
					}
				}
				mListNew=MenuApiUtils.menupak(depath.intValue(),menuLevelMap,MenuMap);//处理封装
				hql=null;
				depath=null;
				menuList=null;
				menuLevelMap=null;
				MenuMap=null;
				rmlist=null;
				params=null;
			}
			
			return mListNew;
		}
		
		//获取相关菜单按钮
		@Override
		public List<TSysFunc> getbtn(UserInfoView user,String url){
			StringBuffer hql=new StringBuffer("select new com.model.TSysFunc(f.id,f.funcName,f.funcSign,f.funcCode,f.funcIcon,f.funcBg) from TSysRoleMenu rm,TSysPermission p,TSysFunc f,TSysMenu m,TSysRole r");
			hql.append(" where r.id=rm.roleId and rm.menuId=m.id and p.rmId=rm.id and f.id=p.funcId and  m.isVisible=0 and m.menuIsopen=0 ");
			hql.append(" and m.menuUrl='").append(url).append("'");
			hql.append(" and r.id=").append(user.getRole().getId());
			hql.append(" order by p.id asc");
			List<TSysFunc> list=this.baseDao.listByHql(hql.toString(), null);
			return list;
		}
		
		//日志缓存
		@Override
		public void userlog(Object obj,String keyPrefix){
			cacheService.cacheSetSeqObj(keyPrefix, obj);
			
		}
		
		//处理日志
		@Override
		public void userlogtask(){
			Long num=cacheService.getListLength(ApplicationConfig.USER_LOG);
			int i=0;
			if (num>0) {
				boolean loop=true;
				do {
					Object obj=cacheService.cacheGetSeqObj(ApplicationConfig.USER_LOG);
					if (obj!=null) {
						this.baseDao.savePo(obj);
					}else{
						loop=false;
					}
				} while (loop);
			}
		} 
		
		//处理日志
		@Override
		public void userActionlogtask(){
			Long num=cacheService.getListLength(ApplicationConfig.USER_ACTION_LOG);
			if (num>0) {
				boolean loop=true;
				do {
					Object obj=cacheService.cacheGetSeqObj(ApplicationConfig.USER_ACTION_LOG);
					if (obj!=null) {
						this.baseDao.savePo(obj);
					}else{
						loop=false;
					}
				} while (loop);
			}
		} 
		
}
