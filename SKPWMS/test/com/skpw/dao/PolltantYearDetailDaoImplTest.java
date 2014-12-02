package com.skpw.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PolltantYearDetailDaoImplTest {
	
	@Resource
	private PolltantYearDetailDao polltantYearDetailDao;
	
	@Test
	public void testShowPolltantYeayDetail() {
		
		String enterid="2aeca06be3eb49da933cb0f0071005e8";
		List list=polltantYearDetailDao.showPolltantYeayDetail(enterid);
		
		System.out.println(list.size());
	}

}
