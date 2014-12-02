package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TRtFacilityServiceImplTest {
	
	@Resource
	private TRtFacilityService  tRtFacilityService;

	@Test
	public void testFindFaciltityByControlid() {
		int fctrlid=49;
		
//		TRtFacility t=tRtFacilityService.findOne("10b771c694e448f5a575ef5e570fecc6");
//		
//		System.out.println(t.getFcreatTime());
		List list=tRtFacilityService.findFaciltityByControlid(fctrlid);
		
		System.out.println(list.size());
	}

}
