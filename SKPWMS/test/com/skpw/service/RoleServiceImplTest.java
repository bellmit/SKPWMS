package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RoleServiceImplTest {

	@Resource
	private RoleService roleService;

	@Test
	public void testFindAuthorityByroleId() {

		String roleid = "402848194617910e0146179642b10002";
		
		List list=roleService.findAuthorityByroleId(roleid);
		
		System.out.println(list.size());
	}

}
