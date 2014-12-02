package com.skpw.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.SMAdjust;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SMAdjustServiceImplTest {

	@Resource
	private SMAdjustService smAdjustService;

	@Test
	public void testShowSMAdjustInfo() {
		
		int pollutant=65;
		int monthid=201411;
		
		SMAdjust smAdjust=smAdjustService.isExist(pollutant, monthid);
		
		System.out.println(smAdjust+"___________________________");
	}

	// @Test
	// public void testShowSMAdjustListByCondition() {
	// fail("Not yet implemented");
	// }
	//
//	 @Test
//	 public void testSaveSMAdjust() {
//		 
//		 
//		 
//	 }
	//
	// @Test
	// public void testDeleteSMAdjust() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testCount() {
	// fail("Not yet implemented");
	// }

}
