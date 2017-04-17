package com.base.service;

import com.framework.base.domain.Entity;

import java.util.List;
import java.util.Map;


/**
 * MEMCACHE处理接口
 * @author daniel
 *
 */
public interface CacheService<E extends Entity> {
	
	
	/**
	 * 写入数据
	 * @param keyPrefix 缓存前缀
	 * @param key 缓存键值
	 * @param List<Entity> 缓存模型
	 * @return 缓存状态
	 */
	public boolean cacheSetList (String keyPrefix, List<E> objList);
	
	/**
	 * 写入数据(批量)
	 * @param keyPrefix 缓存前缀
	 * @param key 缓存键值
	 * @param Map<String,Object> 缓存模型
	 * @return 缓存状态
	 */
	public boolean cacheSetAll (final String keyPrefix, final Map<String,Object> map);

	/**
	 * 获取容器类数据
	 * @param keyPrefix 容器键
	 * @return
	 */
	public List<E> cacheGetListEntity (String keyPrefix);
	
	/**
	 * 获取容器类数据
	 * @param keyPrefix 容器键
	 * @return
	 */
	public List<Object> cacheGetListObj (String keyPrefix);
	
	/**
	 * 获取单个数据模型
	 * @param keyPrefix 容器键
	 * @param key
	 * @return
	 */
	public E cacheGetEntity (String keyPrefix, String key);
	
	/**
	 * 写入数据模型
	 * @param keyPrefix 容器键
	 * @param obj
	 */
	public Boolean cacheSetEntity (String keyPrefix, E obj);
	
	/**
	 * 写入数据模型
	 * @param keyPrefix 容器键
	 * @param obj
	 */
	public Boolean cacheSetObj (String keyPrefix, String key, Object obj);
	
	/**
	 * 写入数据带过期时间
	 * @param keyPrefix
	 * @param key
	 * @param obj
	 * @param time 分钟
	 * @return
	 */
	public Boolean cacheSetObj (String keyPrefix, String key, Object obj, Long time);
	
	/**
	 * 写入载
	 * @param keyPrefix 容器键
	 * @param obj
	 * @return
	 */
	public boolean cacheSetSeq (String keyPrefix, E obj);
	
	/**
	 * 写入载
	 * @param keyPrefix 容器键
	 * @param obj
	 * @return
	 */
	public boolean cacheSetSeqObj (String keyPrefix, Object obj);
	
	/**
	 * 获取载
	 * @param keyPrefix 容器键
	 * @param obj
	 * @return
	 */
	public E cacheGetSeq (String keyPrefix);
	
	
	/**
	 * 队列方式抽取缓存
	 * @param keyPrefix
	 * @return
	 */
	public Object cacheGetSeqObj (final String keyPrefix);
	
	/**
	 * 范围检索
	 * @param keyPrefix
	 * @param start
	 * @param end
	 * @return
	 */
	public List<E> range (String keyPrefix, int start, int end);
	
	/**
	 * Hash缓存长度
	 * @param keyPrefix
	 * @return
	 */
	public Long getHashLength (String keyPrefix);
	
	/**
	 * List缓存长度
	 * @param keyPrefix
	 * @return
	 */
	public Long getListLength (final String keyPrefix);
	
	/**
	 * 删除缓存
	 * @param keyPrefix
	 * @param key 
	 */
	public void deleteCache (String keyPrefix, String key);
	
	/**
	 * 清楚缓存容器
	 * @param keyPrefix
	 */
	public void deleteCacheAll (String keyPrefix);
	
	
	/**
	 * 获取单个缓存对象
	 * @param keyPrefix
	 * @param key
	 * @return
	 */
	public Object cacheGetObj (String keyPrefix, String key);

	/**
	 * 获取单个缓存对象
	 * @param keyPrefix
	 * @param key
	 * @return
	 */
	public Object cacheGetObj (String keyPrefix, String key, Long time);
	
	/**
	 * 创建序列缓存对象
	 * @param keyPrefix
	 * @return
	 */
	public Boolean cacheSetlong (String keyPrefix);

	/**
	 * 获取序列
	 * @param keyPrefix
	 * @return
	 */
	public Long cacheGetLong (String keyPrefix);

}
