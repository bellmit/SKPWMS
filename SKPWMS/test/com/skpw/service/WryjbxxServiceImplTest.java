package com.skpw.service;

import javax.annotation.Resource;
import com.skpw.bean.TBasEnterprise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class WryjbxxServiceImplTest {
	
	@Resource
	private   WryjbxxService wryjbxxService;
	

	@Test
	public void testSave() {
		
		TBasEnterprise  tBasEnterprise=new TBasEnterprise();
		
		tBasEnterprise.setFenterId("test123");
		
		tBasEnterprise.setFenterName("aa");
		wryjbxxService.save(tBasEnterprise);
		
	}

}
