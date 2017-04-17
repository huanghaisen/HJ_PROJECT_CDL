package com.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.base.dao.impl.BaseDaoImpl;
import com.manage.service.FuncService;
import com.model.TSysFunc;

@Service
public class SysFuncServiceImpl implements FuncService{

	@Resource(name="baseDaoImpl")
	private BaseDaoImpl baseDao;
	
	@Override
	public TSysFunc addOrUpdate(TSysFunc func) {
		TSysFunc model=null;
		if(func!=null){
			try{
		model=(TSysFunc)this.baseDao.saveOrupdate(func);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return model;
	}

	@Override
	public TSysFunc findById(Long id) {
		List<Object> params=new ArrayList<Object>();
		TSysFunc model=null;
		if(id!=null){
		params.add(id);
		try{
			model=(TSysFunc)this.baseDao.getSingleByHsql("from TSysFunc c where c.id=?", params);
		}catch(Exception e){
			e.printStackTrace();
		}
		}else{
			System.out.println("取不到  TSysFunc");
		}		
		return model;
	}

	@Override
	public List<TSysFunc> findAll() {
		List<TSysFunc> list=null;
		try{
		list=this.baseDao.listByHql("from TSysFunc c where 1=1 order by c.id asc", null);
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void del(Long id) {
		List<Object> params=new ArrayList<Object>();
		TSysFunc model=null;
		if(id!=null){
		params.add(id);
		try{
			model=(TSysFunc)this.baseDao.getSingleByHsql("from TSysFunc c where c.id=?", params);
			this.baseDao.remove(model);
		}catch(Exception e){
			e.printStackTrace();
		}
		}else{
			System.out.println("取不到  TSysFunc,删除失败");
		}	
		
	}

}
