package com.manage.service;
/**
 * 用户信息接口
 * @author wudj
 */
import java.util.List;
import java.util.Map;

import com.model.TSysUser;

public interface UserService {
	
	/**
	 * 增加或更新用户
	 * @param user 用户实体类
	 * @return
	 */
   public TSysUser addOrUpdate(TSysUser user);
   
   /**
    * 循环生成大量模拟数据时用
    * @param user
    */
   public void saveuser(TSysUser user);
   
   /**
    * 以id找用户
    * @param userId 用户id
    * @return
    */
   public TSysUser findById(Long userId);
   

   /**
    * 获取所有用户集合
    * @return
    */
   public List<TSysUser> findAll();
   
   
	/**
	 * 搜索SQL加参数条件（抽出来复用）
	 * @param map
	 * @return
	 */
   public String SQLAddParams(Map<String,Object> map);
   /**
    * 获取条件查询到的条目数
    * @param map
    * @return
    */
   public Long getSearchSize(Map<String,Object> map);
   /**
    * 根据元素全局查询(分页)
    * @param o
    * @return
    */
   public List<TSysUser> conditionsPage(Map<String,Object> map, Long start, Long length);
   
   /**
    * 分页查询
    * @param start 第几页
    * @param length 一页几行
    * @return
    */
   public List<TSysUser> searchPage(Long start,Long length);
   
   /**
    * 获取总记录数
    * @return
    */
   public Long getTotalSize();
  
   /**
    * 以id删除用户
    * @param userId 用户id
    */
   public void del(Long userId);
   
   /**
    * 用户登录使用
    * @param name 用户名
    * @param password 密码
    * @return
    */
   public TSysUser userLogin(String name,String password);
   
   /**
    * 外部用户登录
    * @param token 登陆串
    * @return
    */
   public TSysUser userToken(String token);

   /**
    * 获取用户列表
    * @param cmd (0查询机构用户,1查询厂商用户)
    * @param oid 机构ID或者厂商ID(0表示查询全部)
    * @param sence 场景
    * @return
    */
   public List<TSysUser> selectUser(Integer cmd, Long oid,String sence);
   
   /**
    * 获取厂商用户列表
    * @param manufId
    * @return
    */
   public List<TSysUser> manufUserSel(Long manufId);
   
   /**
    * 以用户名获取用户
    */
   public TSysUser findByName(String username);
   
   /**
    * 认证码获取用户
    */
   public TSysUser findBySsotoken(String ssoToken);
   
   /**
    * 发送手机验证码
    */
   public Boolean sendCheckCode(TSysUser user);
   
   public boolean createtoken();
   
   public boolean createpassword();

}
