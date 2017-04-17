package com.manage.service;

import java.util.List;

import com.model.TSysMenu;
/**
 * 菜单接口
 * @author wudj
 *
 */
public interface MenuService {
	
		
	  /**
	   * 增加或修改菜单
	   * @param menu
	   * @return
	   */
	   public TSysMenu addOrUpdate(TSysMenu menu);
	   
	  /**
	   * 根据菜单id找到菜单
	   * @param id
	   * @return
	   */
	   public TSysMenu findById(Long id);
	   
	  /**
      * 找到库里所有菜单(特定场景)
	  * @return
	  */
	  public List<TSysMenu> findAll(String sence);
	  
	  /**
	   * 根据菜单id删除对应菜单
	   * @param menuCode
	   */
	   public void del(Long id);
	   
	   /**
	    * 菜单升序
	    * @param id
	    * @param parent_id
	    * @param order
	    */
	   public void asc(int id,int parent_id,int order,String sence);
	   
	   /**
	    * 菜单降序
	    * @param id
	    * @param parent_id
	    * @param order
	    */
	   public void desc(int id,int parent_id,int order,String sence);
	   
	   
	   /**
	    * 修改序号
	    * @param parent_id
	    * @param order
	    */
	   public void updateOrder(int parent_id,int order,String sence);
	   
	   /**
	    * 修改编号
	    * @param parent_id
	    * @param tier
	    * @param eode
	    */
	   public void updateEode(int parent_id,String tier,Long code,String sence);
	   
	   /**
	    * 通过父节点Id获取子节点总数
	    * @param ParentId
	    */
	   public int getNodeCount(Long ParentId, String menuSence);
	   
	   /**
	    * 排序修改
	    * @param cmd(0往前移动,1往后移动)
	    * @param id 当前ID
	    * @param nid 新ID
	    */
	   public void updatePosition(Integer cmd,Long id,Long nid);
	   

	   /**
	    * 写入缓存
	    */
	   public void writeCache(String sence);
	   
	   /**
		 * 根据菜单id找到没有删除的节点菜单
		 * 
		 * @param id
		 * @return
		 */
		public TSysMenu findNodeMenuById(Long id);
}
