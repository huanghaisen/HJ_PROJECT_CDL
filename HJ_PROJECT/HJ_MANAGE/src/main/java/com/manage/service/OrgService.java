package com.manage.service;

import java.util.List;

import com.model.TSysOrg;

/**
 * 机构管理
 * @author daniel
 *
 */
public interface OrgService {

	/**
	 * 查询机构数据
	 * @param ident
	 * @param endident
	 * @return
	 */
	public List<TSysOrg> search(Integer ident,Integer endident);
	
	/**
	 * 下来框使用查询
	 * @param ident 
	 * @param endident
	 * @param level 获取到最大层级
	 * @return
	 */
	public List<TSysOrg> cbxsearch(Integer ident,Integer endident,Integer level);
	
	/**
	 * 检查数据接口
	 * @param name
	 * @param val
	 * @return
	 */
	public boolean chkOrg(String name,Object val);
	
   
   /**
    * 以id删除用户
    * @param userId 用户id
    */
   public void del(Long id);
   
   /**
    * 新增或者修改
    * @param model
    */
   public void addOrUpdate(TSysOrg model);
   
   /**
    * 查询单个机构实体
    * @param id
    * @return
    */
   public TSysOrg find(Long id);
   
   
   /**
    * 刷新机构缓存
    */
   public void RefreshAll();

   /**
    * 机构坐标移动
    * @param cmd	指令(0往前移动,1往后移动)
    * @param id		当前节点ID
    * @param nid	下个节点ID
    */
   public void updatePosition(Integer cmd, Long id, Long nid);
   /**
    * 通过父类ID查找子机构
    * @param parentId
    * @return
    */
   public List<TSysOrg> findByParentId(Long parentId);
   /**
    * 通过机构ID更新经纬度
    * @param orgId
    * @param mapX
    * @param mapY
    * @return
    */
   public TSysOrg updateById(Long orgId,Float mapX,Float mapY);
}
