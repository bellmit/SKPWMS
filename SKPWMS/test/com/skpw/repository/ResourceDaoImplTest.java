package com.skpw.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ResourceDaoImplTest {

	@Resource
	private ResourceDao resourceDao;

	// @Test
	// public void testGetResourceByRoleID() {
	// String roleid = "40284819461cea7a01461ceb0f5d0000";
	// List list = resourceDao.getResourceByRoleID(roleid);
	//
	// System.out.println(list.size());
	// }

	@Test
	public void testGetValues() {
		//
		// String authorityid = "4028480d46fae6040146fae6b9740000";
		// List<String> list = resourceDao.getValues(authorityid);
		//
		// System.out.println(list.size());

		String username = "user";
		List list = resourceDao.getAuthorityid(username);
		System.out.println(list.size());
	}

}
