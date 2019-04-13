package com.zhiyou.video.security;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.zhiyou.video.model.Resource;
import com.zhiyou.video.model.Role;
import com.zhiyou.video.model.SysUser;
import com.zhiyou.video.util.Global;
import com.zhiyou.video.util.UserUtils;


public class SystemAuthorizingRealm extends AuthorizingRealm {
	
	@Autowired
	private SessionDAO sessionDao;
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken authenToken = (com.zhiyou.video.security.UsernamePasswordToken) token;
		String userName = authenToken.getUsername();
		// 通过用户名把数据从数据库取出来
		// 现在缓存中取，取不到再去数据库中取，在数据库中取出后在放到缓存中
		SysUser user = UserUtils.getByLoginName(userName);
		
		if (user != null) {
			
			if (Global.BOOLFALSE.equals(user.getLocked())){
				throw new AuthenticationException("该已帐号禁止登录.");
			}
			// 数据库中的密码密文 数据中的盐  表单的提交的密码明文  你加密的方式 哈希次数 用户名
			// 把前端获取到的 密码 再加上 配置文件配置的加密方式MD5 md5次数 2 盐  userName+user.getSalt()
			return new SimpleAuthenticationInfo(new Principal(user, authenToken.isMobileLogin()), 
					String.valueOf(user.getPassword()), ByteSource.Util.bytes(userName+user.getSalt()), getName());	
		}
		else {
			return null;
		}
		
	}
	
	/**
	 * 获取权限授权信息，如果缓存中存在，则直接从缓存中获取，否则就重新获取， 登录成功后调用
	 */
	@Override
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		
		//Principals：一般指用户名等，唯一表明Subject身份也就是当前用户身份的东西
		if (principals == null) {
            return null;
        }
		Principal principal = (Principal) getAvailablePrincipal(principals);
		System.out.println(principal.id);
		System.out.println(principal.name);
		System.out.println(principal.mobileLogin);
		System.out.println(principal.password);
        AuthorizationInfo info = null;

        // 在缓冲中取出授权信息
        info = (AuthorizationInfo)UserUtils.getCache(UserUtils.CACHE_AUTH_INFO+principal.getName());

        if (info == null) {
        	// 在缓存中没有取到，调用doGetAuthorizationInfo
            info = doGetAuthorizationInfo(principals);
            if (info != null) {
            	UserUtils.putCache(UserUtils.CACHE_AUTH_INFO+principal.getName(), info);
            }
        }

        
        return info;
	}
	

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) getAvailablePrincipal(principals);

		Collection<Session> sessions = sessionDao.getActiveSessions();
				
				//
	/*	
	 * 
	 * 暂时没实现，改天再讲
	 * if (sessions.size() > 0){
			// 如果是登录进来的，则踢出已在线用户
			if (UserUtils.getSubject().isAuthenticated()){
				for (Session session : sessions){
					sessionDao.delete(session);
				}
			}
			// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
			else{
				UserUtils.getSubject().logout();
				throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
			}
		}*/
		
		String loginName = principal.getName();
		
		System.out.println(loginName);
		SysUser user = UserUtils.getByLoginName(loginName);
		if (user != null) {
			
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// 把角色信息查出来
			List<Role> roles = UserUtils.getRolesByIds(user.getRoleIds(),  user.getId());
			// 遍历他所拥有的角色
			for (int i = 0; i < roles.size(); i++) {
				
				Role ro = roles.get(i);
				
				info.addRole(ro.getRole());
				// 通过ResourceIds获取对应的权限信息
				List<Resource> sresources = UserUtils.getPermissionByIds(ro.getResourceIds(), user.getId());
			
				for (int j = 0; j < sresources.size(); j++) {
					
					Resource re = sresources.get(j);
					System.out.println(re.getPermission());
					info.addStringPermission(re.getPermission());
				}
			}
			
			return info;
			
		}
		
				
				
		return null;
	}
	
	
	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private Long id; // 编号
		private String name; // 姓名
		private String password;
		private boolean mobileLogin; // 是否手机登录
		
//		private Map<String, Object> cacheMap;

		public Principal(SysUser user, boolean mobileLogin) {
			this.id = user.getId();
			this.name = user.getUsername();
			this.mobileLogin = mobileLogin;
			this.password = user.getPassword();
			
		}

		public String getName() {
			return name;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}


		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try{
				return (String) UserUtils.getSession().getId();
			}catch (Exception e) {
				return "";
			}
		}
		
	

	}
	

}
