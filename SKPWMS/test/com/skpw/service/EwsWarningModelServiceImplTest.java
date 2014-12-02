package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class EwsWarningModelServiceImplTest {
	
	@Resource
	private EwsWarningModelService ewsWarningModelService;
	@Test
	public void testShowEwsWarningModel() {
		
		List list=ewsWarningModelService.showEwsWarningModel();
		
		System.out.println(list.size());

	}

}
