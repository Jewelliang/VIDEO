package com.zhiyou.video.security.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import redis.clients.jedis.Jedis;
import com.google.common.collect.Sets;
import com.zhiyou.video.util.JedisUtils;
import com.zhiyou.video.util.Servlets;

/**
 * 自定义授权缓存管理类
 */
public class JedisCacheManager implements CacheManager {

	private String cacheKeyPrefix = "shiro_cache_";
	
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return new JedisCache<K, V>(cacheKeyPrefix + name);
	}

	public String getCacheKeyPrefix() {
		return cacheKeyPrefix;
	}

	public void setCacheKeyPrefix(String cacheKeyPrefix) {
		this.cacheKeyPrefix = cacheKeyPrefix;
	}
	
	/**
	 * 自定义授权缓存管理类
	 */
	public class JedisCache<K, V> implements Cache<K, V> {


		private String cacheKeyName = null;

		public JedisCache(String cacheKeyName) {
			this.cacheKeyName = cacheKeyName;
//			if (!JedisUtils.exists(cacheKeyName)){
//				Map<String, Object> map = Maps.newHashMap();
//				JedisUtils.setObjectMap(cacheKeyName, map, 60 * 60 * 24);
//			}
//			logger.debug("Init: cacheKeyName {} ", cacheKeyName);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public V get(K key) throws CacheException {
			if (key == null){
				return null;
			}
			
			V v = null;
			HttpServletRequest request = Servlets.getRequest();
			if (request != null){
				v = (V)request.getAttribute(cacheKeyName);
				if (v != null){
					return v;
				}
			}
			
			V value = null;
			Jedis jedis = null;
			try {
				jedis = JedisUtils.getResource();
				value = (V)JedisUtils.toObject(jedis.hget(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key)));
			} catch (Exception e) {
			} finally {
				JedisUtils.returnResource(jedis);
			}
			
			if (request != null && value != null){
				request.setAttribute(cacheKeyName, value);
			}
			
			return value;
		}

		@Override
		public V put(K key, V value) throws CacheException {
			if (key == null){
				return null;
			}
			
			Jedis jedis = null;
			try {
				jedis = JedisUtils.getResource();
				jedis.hset(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key), JedisUtils.toBytes(value));
			} catch (Exception e) {
			} finally {
				JedisUtils.returnResource(jedis);
			}
			return value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public V remove(K key) throws CacheException {
			V value = null;
			Jedis jedis = null;
			try {
				jedis = JedisUtils.getResource();
				value = (V)JedisUtils.toObject(jedis.hget(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key)));
				jedis.hdel(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key));
			} catch (Exception e) {
			} finally {
				JedisUtils.returnResource(jedis);
			}
			return value;
		}

		@Override
		public void clear() throws CacheException {
			Jedis jedis = null;
			try {
				jedis = JedisUtils.getResource();
				jedis.hdel(JedisUtils.getBytesKey(cacheKeyName));
			} catch (Exception e) {
			} finally {
				JedisUtils.returnResource(jedis);
			}
		}

		@Override
		public int size() {
			int size = 0;
			Jedis jedis = null;
			try {
				jedis = JedisUtils.getResource();
				size = jedis.hlen(JedisUtils.getBytesKey(cacheKeyName)).intValue();
				return size;
			} catch (Exception e) {
			} finally {
				JedisUtils.returnResource(jedis);
			}
			return size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Set<K> keys() {
			Set<K> keys = Sets.newHashSet();
			Jedis jedis = null;
			try {
				jedis = JedisUtils.getResource();
				Set<byte[]> set = jedis.hkeys(JedisUtils.getBytesKey(cacheKeyName));
				for(byte[] key : set){
					Object obj = (K)JedisUtils.getObjectKey(key);
					if (obj != null){
						keys.add((K) obj);
					}
	        	}
				return keys;
			} catch (Exception e) {
			} finally {
				JedisUtils.returnResource(jedis);
			}
			return keys;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Collection<V> values() {
			Collection<V> vals = Collections.emptyList();;
			Jedis jedis = null;
			try {
				jedis = JedisUtils.getResource();
				Collection<byte[]> col = jedis.hvals(JedisUtils.getBytesKey(cacheKeyName));
				for(byte[] val : col){
					Object obj = JedisUtils.toObject(val);
					if (obj != null){
						vals.add((V) obj);
					}
	        	}
				return vals;
			} catch (Exception e) {
			} finally {
				JedisUtils.returnResource(jedis);
			}
			return vals;
		}
	}
}
