package com.framework.base.dao.impl;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class BaseDaoImpl {

	@Resource
	protected SessionFactory sessionFactory;
	
	/**
	 * 获取session
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	/**
	 * 关闭session
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public void closeSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session != null) {
			session.close();
		}
	}

	/**
	 * 保存当前对象
	 * 
	 * @param obj
	 * @return
	 */
	public Object savePo(Object obj) {
		try {
			getSession().save(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 更新当前对象
	 * 
	 * @param obj
	 * @return
	 */
	public Object updatePo(Object obj) {
		try {
			getSession().update(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 更新或者新增
	 * 
	 * @param obj
	 * @return
	 */
	public Object saveOrupdate(Object obj) {
		try {
			getSession().saveOrUpdate(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	/**
	 * 根据HQL语句查询数据库
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List listByHql(String hsql, List params) {
		Session session = getSession();
		List list = new ArrayList();
		try {
			Query query = session.createQuery(hsql);
			query.setCacheable(true);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据HQL语句查询数据库
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List listByHql(String hsql, List params, Long endLine) {
		Session session = getSession();
		List list = new ArrayList();
		try {
			Query query = session.createQuery(hsql);
			query.setCacheable(true);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			
			query.setFirstResult(0);
			query.setMaxResults(endLine.intValue());
			
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	/**
	 * 根据SQL语句查询数据库
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List listBySQL(String sql, List params) {
		Session session = getSession();
		List list = new ArrayList();
		try {
			Query query = session.createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * 通过hsql查询一个唯一值
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Object getSingleByHsql(String hsql, List params) {
		Session session = getSession();
		Query query = session.createQuery(hsql);
		query.setCacheable(true);
		Object obj = null;
		if (params == null || params.size() == 0)
			obj = query.uniqueResult();
		else {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
			obj = query.uniqueResult();
		}
		return obj;
	}

	/**
	 * 通过原生sql查询一个唯一值，比如count(*)。
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getSingleBySql(String sql, List params) {
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		Object obj = null;
		if (params == null || params.size() == 0)
			obj = query.uniqueResult();
		else {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
			obj = query.uniqueResult();
		}
		return obj;
	}
	
	/**
	 * 通过原生sql查询一个唯一值，比如count(*)。
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getCountSql(String sql) {
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		Number number = (Number)query.uniqueResult();
		return number.intValue();
	}
	

	/**
	 * 根据参数执行HQL语句，此方法用于利用HQL语句修改数据库的值
	 * 
	 * @param hsql
	 * @param ls_params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int execHsql(String hsql, List ls_params) {
		Session session = getSession();
		int j = 0;
		try {
			Query query = session.createQuery(hsql);
			query.setCacheable(true);
			if (ls_params != null) {
				for (int i = 0; i < ls_params.size(); i++) {
					query.setParameter(i, ls_params.get(i));
				}
			}
			j = query.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return j;
	}

	/**
	 * 根据参数执行SQL语句，此方法用于利用SQL语句修改数据库的值
	 * 
	 * @param hsql
	 * @param ls_params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int execSql(String sql, List params) {
		Session session = getSession();
		int j = 0;
		try {
			Query query = session.createSQLQuery(sql);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			j = query.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return j;
	}

	/**
	 * 分页查询
	 * @param hsql
	 * @param ls_params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List listByHqlBeatch(String hsql, Long first, Long size) {
		Session session = this.getSession();
		List list = new ArrayList();
		try {
			Query query = session.createQuery(hsql);
			query.setCacheable(true);
			query.setFirstResult(first.intValue());
			query.setMaxResults(size.intValue());
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 分页查询
	 * @param sql
	 * @param ls_params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List listBySqlBeatch(String sql, Long first, Long size) {
		Session session = this.getSession();
		List list = new ArrayList();
		try {
			Query query = session.createSQLQuery(sql);
			query.setFirstResult(first.intValue());
			query.setMaxResults(size.intValue());
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 通过HQL获取分页的总记录数totalSize
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long getTotalSize(String hql, List params) {
		Session session = getSession();
		try {
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			return ((Number)query.uniqueResult()).longValue();
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 通过SQL获取分页的总记录数totalSize
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long getTotalSqlSize(String sql,List params) {
		Long total=Long.valueOf(this.getSingleBySql(sql, params).toString());
		return total;
	}
	
	/**
	 * 通过QBC获取分页的总记录数totalSize
	 * 
	 * @param hsql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Long getTotalSize(Class entity,ArrayList<Criterion> term){
		Session session = getSession();
		 Criteria criteria = session.createCriteria(entity).setProjection(Projections.count("id"));
		 if(term!=null){
			 for(int i = 0 ; i< term.size();i++){
				 Criterion criterion = term.get(i);
				 criteria.add(criterion);
			 }
		 }
		 Integer recordCount = (Integer)criteria.uniqueResult();
		 return Long.parseLong(recordCount.toString());
	}
	
	/**
	 * 删除一个对象
	 * @param obj
	 */
	public void remove(Object obj){
		try {
			getSession().delete(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}