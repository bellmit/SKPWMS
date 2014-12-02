package com.skpw.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class WarningLogDaoImplTest {

	@Resource
	private WarningLogDao warningLogDao;

	@Test
	public void testShowWarnLog() {

		List list = warningLogDao.showWarnLog(1, 10,"2014-07-08","2014-07-10");

		System.out.println(list.size());

	}

}
