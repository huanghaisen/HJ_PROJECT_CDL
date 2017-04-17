package com.manage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.framework.util.StringUtil;
import com.manage.service.ManufService;
import com.model.TSysManuf;

@Service
public class ManufServiceImpl implements ManufService {
	
	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;

	@Override
	public List<TSysManuf> searchPage(Map<String, Object> map, Long start,
			Long length) {
		
		StringBuffer hql=new StringBuffer("from TSysManuf c where c.isDeleted=0 ");
		hql=seclparams(hql, map);
		hql.append(" order by c.id desc");
		List<TSysManuf> list=this.baseDao.listByHqlBeatch(hql.toString(), start, length);
		hql=null;
		return list;
	}
	
	@Override
	public List<TSysManuf> searchPage(Integer type) {
		
		StringBuffer hql=new StringBuffer("from TSysManuf c where c.isDeleted=0 ");
		if (type==null || type.equals(null)) {
			type=2;
		}
		
		hql.append(" and c.manufType=").append(type);
		
		hql.append(" order by c.id desc");
		List<TSysManuf> list=this.baseDao.listByHql(hql.toString(), null);
		hql=null;
		return list;
	}

	@Override
	public Long getSearchSize(Map<String, Object> map) {
		StringBuffer hql=new StringBuffer("select count(c.id) from TSysManuf c where c.isDeleted=0 ");
		hql=seclparams(hql, map);
		return this.baseDao.getTotalSize(hql.toString(), null);
	}
	
	private StringBuffer seclparams(StringBuffer hql,Map<String, Object> map){
		if (map!=null) {
			if (map.containsKey("manufCode") && StringUtil.isNotEmpty(map.get("manufCode").toString())) {
				hql.append(" and c.manufCode='").append(map.get("manufCode").toString()).append("' ");
			}
			if (map.containsKey("manufType") && StringUtil.isNotEmpty(map.get("manufType").toString()) && !map.get("manufType").toString().equals("0")) {
				hql.append(" and c.manufType=").append(map.get("manufType").toString());
			}
			if (map.containsKey("manufName") && StringUtil.isNotEmpty(map.get("manufName").toString())) {
				hql.append(" and c.manufName like '%").append(map.get("manufName").toString()).append("%' ");
			}
			if (map.containsKey("manufBriefName") && StringUtil.isNotEmpty(map.get("manufBriefName").toString())) {
				hql.append(" and c.manufBriefName like '%").append(map.get("manufBriefName").toString()).append("%' ");
			}
		}
		return hql;
	}

	@Override
	public void del(Long mid) {
		TSysManuf model=findById(mid);
		model.setIsDeleted(false);
		this.baseDao.saveOrupdate(model);
		model=null;
	}
	
	@Override
	public void del(TSysManuf model) {
		this.baseDao.remove(model);
	}

	@Override
	public TSysManuf addOrUpdate(TSysManuf model) {
		return (TSysManuf) this.baseDao.saveOrupdate(model);
	}

	@Override
	public TSysManuf findById(Long mid) {
		StringBuffer hql=new StringBuffer("from TSysManuf c where c.id=").append(mid);
		TSysManuf model=(TSysManuf) this.baseDao.getSingleByHsql(hql.toString(), null);
		return model;
	}

	@Override
	public List<TSysManuf> maufCombox() {
		StringBuffer hql=new StringBuffer("from TSysManuf c where c.isDeleted=0 ");
		hql.append(" order by c.id desc");
		List<TSysManuf> list=this.baseDao.listByHql(hql.toString(), null);
		hql=null;
		return list;
	}

}
