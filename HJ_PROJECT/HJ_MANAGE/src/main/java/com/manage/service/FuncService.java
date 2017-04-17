package com.manage.service;

import java.util.List;

import com.model.TSysFunc;
/**
 * 功能接口
 * @author wudj,zhaoyang
 *
 */
public interface FuncService {

	public TSysFunc addOrUpdate(TSysFunc func);
	   
	  /**
	   * 根据功能id找到功能
	   * @param menuCode
	   * @return
	   */
	   public TSysFunc findById(Long id);
	   
	  /**
	   * 找到库里所有功能
	   * @return
	   */
	   public List<TSysFunc> findAll();
	  
	  /**
	   * 根据功能id删除对应功能
	   * @param menuCode
	   */
	   public void del(Long id);
	   
}
