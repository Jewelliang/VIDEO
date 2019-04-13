package com.zhiyou.video.security.cache;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.zhiyou.video.util.Servlets;

/**
 * 自定义授权缓存管理类
 */

public class SessionCacheManager implements CacheManager {

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return new SessionCache<K, V>(name);
	}

	/**
	 * SESSION缓存管理类
	 */
	public class SessionCache<K, V> implements Cache<K, V> {

		
		private String cacheKeyName = null;

		public SessionCache(String cacheKeyName) {
			this.cacheKeyName = cacheKeyName;
		}
		
		public Session getSession(){
			Session session = null;
			try{
				Subject subject = SecurityUtils.getSubject();
				session = subject.getSession(false);
				if (session == null){
					session = subject.getSession();
				}
			}catch (InvalidSessionException e){
			}catch (UnavailableSecurityManagerException e2){
			}
			return session;
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
			value = (V)getSession().getAttribute(cacheKeyName);
			
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

			getSession().setAttribute(cacheKeyName, value);
			return value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public V remove(K key) throws CacheException {
			
			V value = null;
			value = (V)getSession().removeAttribute(cacheKeyName);
			return value;
		}

		@Override
		public void clear() throws CacheException {
			getSession().removeAttribute(cacheKeyName);
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public Set<K> keys() {
			return Sets.newHashSet();
		}

		@Override
		public Collection<V> values() {
			return Collections.emptyList();
		}
	}
}
