package com.skpw.service;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TSysorgUnit;
import com.skpw.repository.OrgUnitRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class OrgUnitServiceImplTest {
	
	@Resource
	private OrgUnitRepository orgUnitRepository;

//	@Test
//	public void testShowTSysorgUnitInfo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testShowTSysorgUnitListByCondition() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testSaveTSysorgUnit() {
		TSysorgUnit orgUnit=new TSysorgUnit();
		
		orgUnit.setOrgUnitCode("001");
		
		orgUnit.setOrgUnitName("数字环保部");
		
//		orgUnit.setParentOrgUnit(orgUnitRepository.findOne("40284819462d215c01462d2161d90000"));
		orgUnitRepository.save(orgUnit);
		
		
	}

//	@Test
//	public void testDeleteTSysorgUnit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateTSysorgUnit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInitUpdateTSysorgUnit() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCount() {
//		fail("Not yet implemented");
//	}

}
