package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MonthReportServiceImplTest {
	
	@Resource
	private MonthReportService monthReportService;

	@Test
	public void testGetXData() {
		int ttcid=33;
		
		String time="2014-11";
		List list=monthReportService.statisticComplexMonthReport(ttcid, time,"cod");
		
		System.out.println(list.size());
		
	}

}
