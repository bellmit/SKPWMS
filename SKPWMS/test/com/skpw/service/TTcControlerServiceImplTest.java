package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TTcControlerServiceImplTest {

	@Resource
	private TTcControlerService tTcControlerService;
	

	@Test
	public void testFindtccontrollerById() {
		String id = "951e54ef28614faf8f9081afcd8cbccd";
		List list= tTcControlerService.findByFenterId1(id);

		System.out.println(list);
	}

}
