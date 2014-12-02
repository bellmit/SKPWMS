package com.skpw.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TBasSewageLicence;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SewageLicenseServiceImplTest {
	
	/*@Resource
	private  WryjbxxService wryjbxxService;*/
	
	@Resource
	private SewageLicenceService sewageLicenseService;

	// @Test
	// public void testShowTBasSewageLicence() {
	//
	// }
	//
	// @Test
	// public void testShowSewageLicenceListByCondition() {
	//
	// }
	//
	@Test
	public void testSaveSewageLicence() {
		
		TBasSewageLicence tBasSewageLicence=new TBasSewageLicence();
		tBasSewageLicence.setStatus(1);
//		TBasEnterprise tBasEnterprise=
//		tBasSewageLicence.settBasEnterprise(wryjbxxService.f);
		tBasSewageLicence.setWrybh("aa5");
		tBasSewageLicence.setWrw("aa2");
		tBasSewageLicence.setXkzbh("step1234");
		tBasSewageLicence.setYxpfl(23.2f);
		tBasSewageLicence.setYxpfld(53.62f);
		
		sewageLicenseService.saveSewageLicence(tBasSewageLicence);
		

	}
	//
	// @Test
	// public void testDeleteSewageLicence() {
	//
	// }
	//
	// @Test
	// public void testUpdateSewageLicence() {
	//
	// }
	//
	// @Test
	// public void testInitUpdateSewageLicence() {
	//
	// }
	//
	// @Test
	// public void testCount() {
	//
	// }
	//
	// @Test
	// public void testDeleteList() {
	//
	// }

}
