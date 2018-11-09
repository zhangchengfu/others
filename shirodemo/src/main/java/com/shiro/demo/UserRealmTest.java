package com.shiro.demo;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月17日
 */
public class UserRealmTest extends BaseTest {
	@Test
    public void testLoginSuccess() {
      login("classpath:shiro-multirealms.ini", u1.getUsername(), password);
      Assert.assertTrue(subject().isAuthenticated());
  }
}
