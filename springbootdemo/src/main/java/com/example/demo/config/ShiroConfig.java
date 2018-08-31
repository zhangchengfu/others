package com.example.demo.config;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.shiro.UserAuthcRealm;
import com.example.demo.shiro.filter.LoginFilter;
import com.example.demo.shiro.filter.PermissionFilter;
import com.example.demo.shiro.filter.RoleFilter;
import com.example.demo.utils.INI4j;

@Configuration
public class ShiroConfig {
	
	// 注意/r/n前不能有空格
	private static final String CRLF = "\r\n";
		
	//@ConditionalOnMissingBean(name="hashedCredMatcher")
	@Bean(name="hashedCredMatcher")
	public HashedCredentialsMatcher createHashCredentialsMatcher() {
		HashedCredentialsMatcher hcm = new HashedCredentialsMatcher("MD5");
		hcm.setHashIterations(1);
		hcm.setStoredCredentialsHexEncoded(true);
		return hcm;
	}
	
	@Bean(name="userAuthc")
	public UserAuthcRealm getUserAuthcRealm() {
		UserAuthcRealm uar = new UserAuthcRealm();
		uar.setCredentialsMatcher(createHashCredentialsMatcher());
		return uar;
	}
	
	@Bean(name="rememberCookies")
	public SimpleCookie createSimpleCookies() {
		SimpleCookie sc = new SimpleCookie("rememberMe");
		sc.setHttpOnly(true);
		sc.setMaxAge(5);
		return sc;
	}
	
	@Bean(name="cookieRemmberMananger")
	public CookieRememberMeManager createCookieRemmberMananger() {
		CookieRememberMeManager crm = new CookieRememberMeManager();
		crm.setCookie(createSimpleCookies());
		return crm;
	}
	
	/*public MemorySessionDAO createSessionDAO() {
		MemorySessionDAO sessionDao = new MemorySessionDAO();
		return null;
	}*/
	
	@Bean(name="cacheManager")
	public EhCacheManager createEhcacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		String path = "classpath:ehcache.xml";
		cacheManager.setCacheManagerConfigFile(path);
		return cacheManager;
	}
	
	@Bean(name="securityMananger")
	public DefaultWebSecurityManager createSecurityManager() {
		DefaultWebSecurityManager securityMananger = new DefaultWebSecurityManager();
		securityMananger.setRealm(getUserAuthcRealm());
		securityMananger.setCacheManager(createEhcacheManager());
		securityMananger.setRememberMeManager(createCookieRemmberMananger());
		return securityMananger;
	}

	/**
	 * 自定义的shiro相关的Filter在这里做统一处理
	 * */
	public Map<String, Filter> createFilterChainMap() {
		Map<String, Filter> filters = new LinkedHashMap<>();
		LoginFilter loginFtr = new LoginFilter();
		PermissionFilter permsFilter = new PermissionFilter();
		RoleFilter roleFtr = new RoleFilter();
		filters.put("loginFtr", loginFtr);
		filters.put("permsFtr", permsFilter);
		filters.put("roleFtr", roleFtr);
		return filters;
	}
	
	/**
	 * 读取ini文获得固定权限
	 * */
	public String loadFilterChainDefinitions() {
		StringBuffer sb = new StringBuffer();
			sb.append(getFixedAuthRule());//固定权限，采用读取配置文件
		return sb.toString();
	}
	
	/**
	 * 从配额文件获取固定权限验证规则串
	 */
	private String getFixedAuthRule(){
		String fileName = "shiro_base_auth.ini"; //位于根目录
		ClassPathResource cp = new ClassPathResource(fileName);
		INI4j ini = null;
		try {
			ini = new INI4j(cp.getFile());
		} catch (IOException e) {
			System.out.println("加载文件出错");
		}
		String section = "base_auth";
		Set<String> keys = ini.get(section).keySet();
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			String value = ini.get(section, key);
			sb.append(key).append(" = ")
			.append(value).append(CRLF);
		}		
		return sb.toString();
	}
	
	 @Bean(name="shiroFilter")
	 public ShiroFilterFactoryBean CreateShiroSecurityFilterFactory() {
		 ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		 shiroFilter.setSecurityManager(createSecurityManager());
		 shiroFilter.setLoginUrl("/g/login");
		 shiroFilter.setSuccessUrl("/g/success");
		 shiroFilter.setUnauthorizedUrl("/g/unauthorized");;
		 shiroFilter.setFilters(createFilterChainMap());
         //注意过滤器配置顺序 不能颠倒
  /*     filterChainDefinitionMap.put("/static/**", "anon");
         filterChainDefinitionMap.put("/templates/**", "anon");
         filterChainDefinitionMap.put("/g/unLogin", "anon");
         filterChainDefinitionMap.put("/g/login", "loginFtr");
         filterChainDefinitionMap.put("/g/logout", "authc");
         filterChainDefinitionMap.put("/g/unauthorized", "anon");
         filterChainDefinitionMap.put("/**", "permsFilter");*/
         shiroFilter.setFilterChainDefinitions(loadFilterChainDefinitions());
		 return shiroFilter;
	 }
	
}
