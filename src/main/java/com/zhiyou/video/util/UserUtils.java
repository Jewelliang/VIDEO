package com.zhiyou.video.util;
import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import com.zhiyou.video.mapper.SysPermissionMapper;
import com.zhiyou.video.mapper.SysRoleMapper;
import com.zhiyou.video.mapper.SysUserMapper;
import com.zhiyou.video.model.Resource;
import com.zhiyou.video.model.Role;
import com.zhiyou.video.model.SysUser;
import com.zhiyou.video.security.SystemAuthorizingRealm.Principal;
public class UserUtils {
	private static SysUserMapper userMapper = SpringContextHolder.getBean(SysUserMapper.class);
	private static SysRoleMapper roleMapper = SpringContextHolder.getBean(SysRoleMapper.class);
	private static SysPermissionMapper permissionMapper = SpringContextHolder.getBean(SysPermissionMapper.class);
	public static final String CACHE_AUTH_INFO = "authInfo";
	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_LOGIN_NAME_ = "ln";
	public static final String ROLE_CACHE = "roleCache";
	public static final String ROLE_CACHE_ID_ = "role_id_";
	public static final String PERMISSION_CACHE = "permissionCache";
	public static final String PERMISSION_CACHE_ID_ = "permission_id_";

	/**
	 * 根据登录名获取用户
	 * 
	 * @param loginName
	 * @return 取不到返回null
	 */
	public static SysUser getByLoginName(String loginName) {
		SysUser user = (SysUser) CacheUtils.get(USER_CACHE, USER_CACHE_LOGIN_NAME_ + loginName);
		if (user == null) {
			user = userMapper.getUserByName(loginName);
			if (user == null) {
				return null;
			}
			CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
			CacheUtils.put(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername(), user);
		}
		return user;
	}

	/**
	 * 获取当前用户对应的角色列表
	 * 
	 * @param ids
	 * @param userId
	 * @return
	 */
	public static List<Role> getRolesByIds(String ids, Long userId) {
		List<Role> roles = (List<Role>) CacheUtils.get(ROLE_CACHE, ROLE_CACHE_ID_ + userId);
		if (roles == null || roles.size() == 0) {

			String[] roleIds = ids.split(",");

			List<Integer> rids = new ArrayList<>();

			for (int i = 0; i < roleIds.length; i++) {
				rids.add(Integer.valueOf(roleIds[i]));
			}
			roles = roleMapper.getRolesByIds(rids);
			CacheUtils.put(ROLE_CACHE, ROLE_CACHE_ID_ + userId, roles);
		}
		return roles;
	}

	/**
	 * 获取当前用户的权限列表
	 * 
	 * @param ids
	 * @param userId
	 * @return
	 */
	public static List<Resource> getPermissionByIds(String ids, Long userId) {
		List<Resource> permissions = (List<Resource>) CacheUtils.get(PERMISSION_CACHE, PERMISSION_CACHE_ID_ + userId);
		if (permissions == null || permissions.size() == 0) {

			String[] permissionIds = ids.split(",");

			List<Integer> rids = new ArrayList<>();

			for (int i = 0; i < permissionIds.length; i++) {
				rids.add(Integer.valueOf(permissionIds[i]));
			}
			permissions = permissionMapper.getResourcesByIds(rids);
			CacheUtils.put(PERMISSION_CACHE, PERMISSION_CACHE_ID_ + userId, permissions);
		}
		return permissions;
	}

	
	/**
	 * 清除当前用户缓存
	 */
	public static void clearCache(){
		removeCache(CACHE_AUTH_INFO);
		
		UserUtils.clearCache(getUser());
	}
	
	
	/**
	 * 获取当前用户
	 * @return 取不到返回 new User()
	 */
	public static SysUser getUser(){
		Principal principal = getPrincipal();
		if (principal!=null){
			SysUser user = getByLoginName(principal.getName());
			if (user != null){
				return user;
			}
			return new SysUser();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new SysUser();
	}

	
	/**
	 * 清除指定用户缓存
	 * @param user
	 */
	public static void clearCache(SysUser user){
		/*
		 * 
		 * 假设
		 * 1 用户角色更新之前需要需要先清除相关缓存
		 * 2 可以对用户进行编辑，用户名更改之前需要注意什么？
		 */
		
		CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getUsername());
		CacheUtils.remove(ROLE_CACHE,  ROLE_CACHE_ID_ +  user.getId());
		CacheUtils.remove(PERMISSION_CACHE, PERMISSION_CACHE_ID_ +user.getId());
	}
	
	
	/**
	 * 获取授权主要对象
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
			// subject.logout();
		} catch (InvalidSessionException e) {

		}
		return null;
	}

	// ============== User Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		// Object obj = getCacheMap().get(key);
		Object obj = getSession().getAttribute(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		// getCacheMap().put(key, value);
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		// getCacheMap().remove(key);
		getSession().removeAttribute(key);
	}

}
