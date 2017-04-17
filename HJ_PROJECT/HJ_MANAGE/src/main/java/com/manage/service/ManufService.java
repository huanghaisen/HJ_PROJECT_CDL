package com.manage.service;

import java.util.List;
import java.util.Map;

import com.model.TSysManuf;

public interface ManufService {

		
	 /**
	    * 分页查询
	    * @param start 第几页
	    * @param length 一页几行
	    * @return
	    */
	   public List<TSysManuf> searchPage(Map<String,Object> map,Long start,Long length);
	   
	   /**
	    * @param type(1配件厂商,2生产厂商,3是维保厂商)
	    * @return
	    */
	   public List<TSysManuf> searchPage(Integer type);
	   
	   /**
	    * 获取条件查询到的条目数
	    * @param map
	    * @return
	    */
	   public Long getSearchSize(Map<String,Object> map);
	   
	   /**
	    * 获取产商信息
	    * @param mid
	    * @return
	    */
	   public TSysManuf findById(Long mid);
	   
	   /**
	    * 以id删除软删除
	    * @param userId 用户id
	    */
	   public void del(Long mid);
	   
	   /**
	    * 以id删除硬删除
	    * @param userId 用户id
	    */
	   public void del(TSysManuf model);
	   
	   
	   /**
		 * 增加或更新
		 * @param user 用户实体类
		 * @return
		 */
	   public TSysManuf addOrUpdate(TSysManuf model);

	   /**
	    * 获取所有的厂商用户
	    * @return
	    */
	public List<TSysManuf> maufCombox();
	   
}
