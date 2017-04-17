package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.framework.util.StringUtil;
import com.framework.view.MenuView;
import com.manage.service.MenuService;
import com.model.TSysMenu;
import com.base.service.ApplicationService;
import com.base.service.CacheService;

	@Service
	public class SysMenuServiceImpl implements MenuService{

		@Resource(name="baseDaoImpl")
		private BaseDaoImpl baseDao;
		
		@Resource(name="cacheServiceImpl")
		CacheService<MenuView> cacheService;
		
		@Resource(name="applicationServiceImpl")
		ApplicationService applicationService;
		
		@Override
		public TSysMenu addOrUpdate(TSysMenu Menu) {
			TSysMenu model=null;
			if(Menu!=null){
				try{
					this.baseDao.execHsql("update TSysMenu c set c.isLeaf=0 where c.id="+Menu.getParentId(), null);
					model=(TSysMenu)this.baseDao.saveOrupdate(Menu);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return model;
		}

		@Override
		public TSysMenu findById(Long id) {
			List<Object> params=new ArrayList<Object>();
			TSysMenu model=null;
			if(id!=null){
			params.add(id);
			try{
				model=(TSysMenu)this.baseDao.getSingleByHsql("from TSysMenu c where c.id=?", params);
			}catch(Exception e){
				e.printStackTrace();
			}
			}else{
				System.out.println("取不到  TSysMenu");
			}		
			return model;
		}

		@Override
		public List<TSysMenu> findAll(String sence) {
			List<TSysMenu> list=null;
			try{
			list=this.baseDao.listByHql("from TSysMenu c where c.menuSence='"+sence+"'", null);
			}catch (Exception e){
				e.printStackTrace();
			}
			return list;
		}

		@Override
		public void del(Long id) {
			List<Object> params=new ArrayList<Object>();
			TSysMenu model=null;
			if(id!=null){
			params.add(id);
			try{
				model=(TSysMenu)this.baseDao.getSingleByHsql("from TSysMenu c where c.id=?", params);
				this.baseDao.remove(model);
			}catch(Exception e){
				e.printStackTrace();
			}
			}else{
				System.out.println("取不到  TSysMenu,删除失败");
			}	
			
		}
		
		/**
		    * 菜单升序
		    * @param id
		    * @param parent_id
		    * @param order
		    */
		@Override
		public void asc(int id, int parent_id, int order,String sence) {
			System.out.println("升序===="+id+"==="+parent_id+"===="+order+"=="+sence);
			try {
				this.baseDao.execHsql("update TSysMenu c set c.menuOrder=c.menuOrder+1 where c.parentId="+parent_id+" and c.menuOrder="+(order-1)+" and c.menuSence='"+sence+"'", null);
				this.baseDao.execHsql("update TSysMenu c set c.menuOrder=c.menuOrder-1 where c.parentId="+parent_id+" and c.id="+id+" and c.menuSence='"+sence+"'",null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		 /**
		    * 菜单降序
		    * @param id
		    * @param parent_id
		    * @param order
		    */
		@Override
		public void desc(int id, int parent_id, int order,String sence) {
			System.out.println("降序===="+id+"==="+parent_id+"===="+order+"=="+sence);
			try {
				this.baseDao.execHsql("update TSysMenu c set c.menuOrder=c.menuOrder-1 where c.parentId="+parent_id+" and c.menuOrder="+(order+1)+" and c.menuSence='"+sence+"'", null);
				this.baseDao.execHsql("update TSysMenu c set c.menuOrder=c.menuOrder+1 where c.parentId="+parent_id+" and c.id="+id+" and c.menuSence='"+sence+"'",null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		

		//修改序号
		@Override
		public void updateOrder(int parent_id, int order,String sence) {
			/*List<Object> params = new ArrayList<Object>();
			params.add(sence);
			params.add(parent_id);
			params.add(order);*/
			try {
				this.baseDao.execHsql("update TSysMenu c set c.menuOrder=c.menuOrder-1 where c.menuSence='"+sence+"' and c.parentId="+parent_id+" and c.menuOrder>"+order,null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		  /**
		    * 修改编号
		    * @param parent_id
		    * @param tier
		    * @param eode
		    */
		@Override
		public void updateEode(int parent_id, String tier, Long code,String sence) {
			System.out.println("修改编号============================"+tier);
			/*List<Object> params = new ArrayList<Object>();
			params.add(sence);
			params.add(parent_id);
			params.add(code);*/
			int rank = Integer.parseInt(tier);//级别
			String Hql = "";
			if(rank==1){
				Hql="update TSysMenu c set c.menuCode=c.menuCode-100000 where c.menuSence='"+sence+"' and c.parentId="+parent_id+" and c.menuCode>"+code;

			}else if(rank==2){
				Hql="update TSysMenu c set c.menuCode=c.menuCode-100 where c.menuSence='"+sence+"' and c.parentId="+parent_id+" and c.menuCode>"+code;

			}else if(rank==3){
				Hql="update TSysMenu c set c.menuCode=c.menuCode-1 where c.menuSence='"+sence+"' and c.parentId="+parent_id+" and c.menuCode>"+code;

			}
			
			if(StringUtil.isNotEmpty(Hql)){
				try {
					this.baseDao.execHsql(Hql,null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}

		  /**
		    * 通过父节点Id获取子节点总数
		    * @param ParentId
		    */
		@Override
		public int getNodeCount(Long ParentId, String menuSence) {
			int num=0;
			try {
				StringBuffer hql=new StringBuffer("select count(*) from t_sys_menu c where c.PARENT_ID=").append(ParentId).append(" and c.menu_sence='").append(menuSence).append("'");
				num=Integer.parseInt((this.baseDao.getSingleBySql(hql.toString(), null).toString()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return num;
		}

		@Override
		public void updatePosition(Integer cmd, Long id, Long nid) {
				String hsql=" update TMenu c set ";
				if (cmd==0) {//往前移动
					this.baseDao.execHsql(hsql+"c.menuOrder=menuOrder-1 where c.id="+id, null);
					this.baseDao.execHsql(hsql+"c.menuOrder=menuOrder+1 where c.id="+nid, null);
				}else if (cmd==1) {//往后移动
					this.baseDao.execHsql(hsql+"c.menuOrder=menuOrder+1 where c.id="+id, null);
					this.baseDao.execHsql(hsql+"c.menuOrder=menuOrder-1 where c.id="+nid, null);
				}else{
					//储存在的指令
					System.out.println("数据处理异常，指令cmd["+cmd+"]");
				}
				hsql=null;
			
			
		}

		@Override
		public void writeCache(String sence) {
			// TODO Auto-generated method stub
			this.applicationService.initmenu(sence);
		}

		@Override
		public TSysMenu findNodeMenuById(Long id) {
			List<Object> params=new ArrayList<Object>();
			TSysMenu model=null;
			if(id!=null){
			params.add(id);
			try{
				model=(TSysMenu)this.baseDao.getSingleByHsql("from TSysMenu c where 1=1 and c.isLeaf=1 and c.isVisible=0 and c.id=?", params);
			}catch(Exception e){
				e.printStackTrace();
			}
			}else{
				System.out.println("取不到  TSysMenu");
			}		
			return model;
		}
		

			
	}
