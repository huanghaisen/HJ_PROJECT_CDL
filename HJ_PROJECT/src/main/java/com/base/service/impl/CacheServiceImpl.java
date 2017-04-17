package com.base.service.impl;

import com.framework.base.domain.Entity;
import com.base.service.CacheService;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class CacheServiceImpl<E extends Entity, V> implements CacheService<E> {
	
	@Resource(name="redisTemplate")
	private RedisTemplate redisTemplate;
	
	@Override
	public boolean cacheSetList(final String keyPrefix, final List<E> objList) {
		return (Boolean) redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	ops.multi();
		        	HashOperations<Serializable, Serializable, Serializable> hashOper=ops.opsForHash();
		        	for (E obj : objList) {
		    			hashOper.put(keyPrefix, obj.getId().toString(), obj);
		    		}
		        	ops.exec();
					return true;	
				} catch (Exception e) {
//					e.printStackTrace();
					return false ;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public boolean cacheSetAll(final String keyPrefix, final Map<String, Object> map) {
		return (Boolean) redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	ops.multi();
		        	HashOperations<Serializable, Serializable, Object> hashOper=ops.opsForHash();
		        	hashOper.putAll(keyPrefix, map);
		        	ops.exec();
					return true;	
				} catch (Exception e) {
//					e.printStackTrace();
					return false ;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public Boolean cacheSetEntity(final String keyPrefix, final E obj){
		return (Boolean)redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	HashOperations<Serializable, Serializable, Serializable> hashOper=ops.opsForHash();
		        	hashOper.put(keyPrefix, obj.getId().toString(), obj);
		        	return true;
				} catch (Exception e) {
//					e.printStackTrace();
					return false;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public Boolean cacheSetObj(final String keyPrefix,final String key, final Object obj){
		return (Boolean)redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	HashOperations<Serializable, Serializable, Object> hashOper=ops.opsForHash();
		        	hashOper.put(keyPrefix, key, obj);
		        	return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public Boolean cacheSetObj(final String keyPrefix,final String key, final Object obj,final Long time){
		return (Boolean)redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	HashOperations<Serializable, Serializable, Object> hashOper=ops.opsForHash();
		        	hashOper.put(keyPrefix, key, obj);
		        	ops.expire(key, time, TimeUnit.MINUTES);
		        	return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public Object cacheGetObj(final String keyPrefix,final String key) {
		return (Object) redisTemplate.execute(new SessionCallback<Object>() {
		      @Override
		      public Object execute(RedisOperations ops){			        
		        try {
//		        	HashOperations<String, String, Object> hashOper=ops.boundHashOps(keyPrefix);
		        	BoundHashOperations<Serializable, Serializable, Object> hashOper=ops.boundHashOps(keyPrefix);
//		        	hashOper.e
		        	return hashOper.get(key);
		        	
//		        	return (List<E>) hashOper.values(keyPrefix);
				} catch (Exception e) {
					return null;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public Object cacheGetObj(final String keyPrefix,final String key,final Long time) {
		return (Object) redisTemplate.execute(new SessionCallback<Object>() {
		      @Override
		      public Object execute(RedisOperations ops){			        
		        try {
//		        	HashOperations<String, String, Object> hashOper=ops.boundHashOps(keyPrefix);
		        	BoundHashOperations<Serializable, Serializable, Object> hashOper=ops.boundHashOps(keyPrefix);
		        	hashOper.expire(time,TimeUnit.MINUTES);
		        	return hashOper.get(key);
		        	
//		        	return (List<E>) hashOper.values(keyPrefix);
				} catch (Exception e) {
					return null;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public List<Object> cacheGetListObj(final String keyPrefix) {
		return (List<Object>) redisTemplate.execute(new SessionCallback<List<E>>() {
		      @Override
		      public List<Object> execute(RedisOperations ops){			        
		        try {
		        	HashOperations<Serializable, Serializable, Object> hashOper=ops.opsForHash();
		        	return (List<Object>) hashOper.values(keyPrefix);
				} catch (Exception e) {
					return null;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public List<E> cacheGetListEntity(final String keyPrefix) {
		return (List<E>) redisTemplate.execute(new SessionCallback<List<E>>() {
		      @Override
		      public List<E> execute(RedisOperations ops){			        
		        try {
		        	HashOperations<Serializable, Serializable, E> hashOper=ops.opsForHash();
		        	return (List<E>) hashOper.values(keyPrefix);
				} catch (Exception e) {
					return null;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public E cacheGetEntity(final String keyPrefix,final String key) {
		return (E) redisTemplate.execute(new SessionCallback<E>() {
		      @Override
		      public E execute(RedisOperations ops){			        
		        try {
		        	HashOperations<Serializable, Serializable, Object> hashOper=ops.opsForHash();
		        	return (E) hashOper.get(keyPrefix, key);
				} catch (Exception e) {
//					e.printStackTrace();
					return null;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public boolean cacheSetSeq(final String keyPrefix,final E obj) {
		return (Boolean) redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	ListOperations<String, E> listOper=ops.opsForList();
		        	if(listOper.leftPush(keyPrefix, obj)!=0){
		        		return true;
	        		}else{
	        			return false;
	        		}	
				} catch (Exception e) {
					return false ;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public boolean cacheSetSeqObj(final String keyPrefix,final Object obj) {
		return (Boolean) redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	ListOperations<Serializable, Object> listOper=ops.opsForList();
		        	if(listOper.leftPush(keyPrefix, obj)!=0){
		        		return true;
	        		}else{
	        			return false;
	        		}	
				} catch (Exception e) {
					e.printStackTrace();
					return false ;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public E cacheGetSeq(final String keyPrefix) {
		return (E) redisTemplate.execute(new SessionCallback<E>() {
		      @Override
		      public E execute(RedisOperations ops){			        
		        try {
		        	ListOperations<Serializable, E> listOper=ops.opsForList();
		        	return (E) listOper.rightPop(keyPrefix);
				} catch (Exception e) {
					return null;
				}
		      }
		    });
	}
	
	@Override
	public Object cacheGetSeqObj(final String keyPrefix) {
		return  redisTemplate.execute(new SessionCallback<Object>() {
		      @Override
		      public Object execute(RedisOperations ops){			        
		        try {
		        	ListOperations<Serializable, Object> listOper=ops.opsForList();
		        	return  listOper.rightPop(keyPrefix);
				} catch (Exception e) {
					return null;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public List<E> range(final String keyPrefix, final int start, final int end){
		return (List<E>) redisTemplate.execute(new SessionCallback<List<E>>() {
		      @Override
		      public List<E> execute(RedisOperations ops){			        
		        try {
		        	ListOperations<Serializable, E> listOper=ops.opsForList();
		        	return (List<E>) listOper.range(keyPrefix, start, end);
				} catch (Exception e) {
					return null;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public Long getHashLength(final String keyPrefix){
		return (Long) redisTemplate.execute(new SessionCallback<Long>() {
		      @Override
		      public Long execute(RedisOperations ops){			        
		        try {
					return ops.opsForHash().size(keyPrefix);
				} catch (Exception e) {
					return 0l ;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
		
	}
	
	@Override
	public Long getListLength(final String keyPrefix){
		return (Long) redisTemplate.execute(new SessionCallback<Long>() {
		      @Override
		      public Long execute(RedisOperations ops){			        
		        try {
					return ops.opsForList().size(keyPrefix);
				} catch (Exception e) {
					return 0l ;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
		
	}
	
	@Override
	public void deleteCache(final String keyPrefix,final String key){
		redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	HashOperations<Serializable, Serializable, Object> hashOper=ops.opsForHash();
		        	hashOper.delete(keyPrefix, key);
		        	return true;
				} catch (Exception e) {
					return false;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}

	@Override
	public void deleteCacheAll(final String keyPrefix) {
		redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	ops.delete(keyPrefix);
		        	return true;
				} catch (Exception e) {
					return false;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	@Override
	public Boolean cacheSetlong(final String keyPrefix){
		return (Boolean)redisTemplate.execute(new SessionCallback<Boolean>() {
		      @Override
		      public Boolean execute(RedisOperations ops){			        
		        try {
		        	ValueOperations<Serializable, Long> num= ops.opsForValue();
		        	num.set(keyPrefix, 0l);
		        	return true;
				} catch (Exception e) {
//					e.printStackTrace();
					return false;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	
	@Override
	public Long cacheGetLong(final String keyPrefix) {
		return (Long) redisTemplate.execute(new SessionCallback<Long>() {
		      @Override
		      public Long execute(RedisOperations ops){			        
		        try {
		        	ValueOperations<Serializable, Long> num= ops.opsForValue();
		        	num.set(keyPrefix, num.get(keyPrefix).longValue());
		        	return num.get(keyPrefix).longValue();
				} catch (Exception e) {
//					e.printStackTrace();
					return 0l;
				}finally{
					redisTemplate.getConnectionFactory().getConnection().close();
				}
		      }
		    });
	}
	
	
}



