package com.framework.base.service;

import java.util.List;


public interface DBService extends Service {

	/**
	 * 保存当前对象
	 * 
	 * @param obj
	 * @return
	 */
	public Object savePo (Object obj);
	
	/**
	 * 更新当前对象
	 * 
	 * @param obj
	 * @return
	 */
	public Object updatePo (Object obj);
	

	/**
	 * 根据HQL语句查询数据库
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	public List listByHql (String hsql, List params);
	
	
	/**
	 * 根据HQL语句查询数据库
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	public List listByHql (String hsql, List params, Long endLine);
	
	/**
	 * 通过hsql查询一个唯一值
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	public Object getSingleByHsql (String hsql, List params);
	
	/**
	 * 通过原生sql查询一个唯一值，比如count(*)。
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getSingleBySql (String sql, List params);
	
	/**
	 * 根据参数执行HQL语句，此方法用于利用HQL语句修改数据库的值
	 * 
	 * @param hsql
	 * @param ls_params
	 * @return
	 */
	public int execHsql (String hsql, List ls_params);
	
	/**
	 * 根据参数执行SQL语句，此方法用于利用SQL语句修改数据库的值
	 * 
	 * @param hsql
	 * @param ls_params
	 * @return
	 */
	public int execSql (String sql, List params);
	
	/**
	 * 分页查询
	 * @param hsql
	 * @param ls_params
	 * @return
	 */
	public List listByHqlBeatch (String hsql, Long first, Long size);
	
	/**
	 * 分页查询
	 * @param sql
	 * @param ls_params
	 * @return
	 */
	public List listBySqlBeatch (String sql, Long first, Long size);
	
	
	/**
	 * 通过HQL获取分页的总记录数totalSize
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	public Long getTotalSize (String hsql, List params);
	
	/**
	 * 通过SQL获取分页的总记录数totalSize
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	public Long getTotalSize (String sql);
}
