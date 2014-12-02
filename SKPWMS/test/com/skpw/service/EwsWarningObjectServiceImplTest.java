package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class EwsWarningObjectServiceImplTest {
	
	@Resource
	private EwsWarningObjectService ewsWarningObjectService;
	@Test
	public void testShowEwsWarningObjectModel() {
		List list=ewsWarningObjectService.showEwsWarningObjectModel();
		System.out.println(list.size());
				
	}

}
