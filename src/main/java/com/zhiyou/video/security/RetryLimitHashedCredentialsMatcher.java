package com.zhiyou.video.security;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	  private Cache<String, AtomicInteger> passwordRetryCache;

	    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
	        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	    }

	    
	    @Override
	    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
	    	UsernamePasswordToken principal = (UsernamePasswordToken) token;
	        //retry count + 1
	    	//AtomicInteger 提供一个原子性的++操作，是线程安全的。 Integer是线程不安全的。
	        AtomicInteger retryCount = passwordRetryCache.get(principal.getUsername());
	        if(retryCount == null) {
	            retryCount = new AtomicInteger(0);
	            
	        }
	        System.out.println(retryCount.get());
	        if(retryCount.incrementAndGet() > 2) {
	        	
	            throw new ExcessiveAttemptsException();
	        }
	        System.out.println(retryCount.get());
	        passwordRetryCache.put(principal.getUsername(), retryCount);

	        boolean matches = super.doCredentialsMatch(token, info);
	        if(matches) {
	            //clear retry count
	            passwordRetryCache.remove(principal.getUsername());
	        }
	        return matches;
	    }
	   

}
