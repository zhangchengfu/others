package com.shiro.demo.hash.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * @author AmVilCres
 * @desc  密码 重试次数限制
 * @date 2018年10月7日
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher{

	 private Ehcache passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher() {
		CacheManager cacheManager = 
				CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}
	 
	 @Override
	 public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		 String username = (String) token.getPrincipal();
		 // retry count++
		 Element element = passwordRetryCache.get(username);
		 if(element == null) {
			 element = new Element(username, new AtomicInteger(0));
			 passwordRetryCache.put(element);
		 }
		 AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
		 if(retryCount.incrementAndGet() > 5) {
			 // if retry count > 5 throw
			 throw new ExcessiveAttemptsException();
		 }
		 boolean matches = super.doCredentialsMatch(token, info);
		 if(matches) {
			 // 匹配成功，清除cache中的记录
			 passwordRetryCache.remove(username);
		 }
		 
		 return matches;
	 }

}
