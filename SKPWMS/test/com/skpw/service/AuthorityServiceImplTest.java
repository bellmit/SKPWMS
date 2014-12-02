package com.skpw.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TSysAuthority;
import com.skpw.bean.TSysMenu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AuthorityServiceImplTest {
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MenuItemService  menuItemService;

//	@Test
//	public void testShowAuthorityInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testShowAuthorityListByCondition() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testSaveAuthority() {
		
		String menuid="40284819462cfdcb01462cfdd0510000";
		
		TSysMenu menu=menuItemService.initUpdateTSysMenu(menuid);
		TSysAuthority authority=new TSysAuthority();
		authority.setAuthorityName("用户管理");
		authority.setSysMenu(menu);
		
		authorityService.saveAuthority(authority);
		System.out.println("HELLO");
	}
//
//	@Test
//	public void testDeleteAuthority() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateAuthority() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindAuthorityById() {
//		fail("Not yet implemented");
//	}

}
