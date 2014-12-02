package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TICRechargeDetailsBak;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ICRechargeDetailsServiceImplTest {

	// @Test
	// public void testSaveRechargeDetails() {
	// fail("Not yet implemented");
	// }
	@Resource
	private ICRechargeDetailsService icRechargeDetailsService;

	@Test
	public void testShowRechargeDetails() {
		List<TICRechargeDetailsBak> list = icRechargeDetailsService.showRechargeDetails();

		System.out.println(list.size());
	}

	// @Test
	// public void
	// testShowRechargeDetailsSpecificationOfTICRechargeDetailsPageable() {
	// fail("Not yet implemented");
	// }

}
