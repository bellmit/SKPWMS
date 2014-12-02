package com.skpw.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TPsOutPermit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OutPermitServiceImplTest {

	@Autowired
	private TPsOutPermitService tPsOutPermitService;

	@Test
	public void test1() {
		TPsOutPermit tPsOutPermit= tPsOutPermitService.findByEnterpriseId("b22901c3a5394af99c064edb57677d9e");
		System.out.println("ok");
	}

}
