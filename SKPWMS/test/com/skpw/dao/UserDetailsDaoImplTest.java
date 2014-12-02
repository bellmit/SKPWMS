package com.skpw.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserDetailsDaoImplTest {
	
	@Resource
	private UserDetailsDao userDetailsDao;

//	@Test
//	public void testUpdateFailAttempts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testResetFailAttempts() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testGetUserAttempts() {
		
		String username="cc";
		userDetailsDao.resetFailAttempts(username);
		
		System.out.println(userDetailsDao.getUserAttempts(username).getAttempts());
		
//		userDetailsDao.resetFailAttempts(username);
		
	}
	
//	@Test
//	public void testIsUserExists(){
//		String username="user";
//		boolean flag=userDetailsDao.isUserExists(username);
//		System.out.println(flag);
//		System.out.println("Hello");
//	}

}
