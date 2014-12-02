package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OfflineLogServiceImplTest {

	@Resource
	private OfflineLogService offlineLogService;

	@Test
	public void testShowOfflineLog() {

		List list = offlineLogService.showOfflineLog();

		System.out.println(list.size());
	}

	// @Test
	// public void testShowOfflineLogByCondition() {
	//
	// }

}
