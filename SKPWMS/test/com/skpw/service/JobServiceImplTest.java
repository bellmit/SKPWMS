package com.skpw.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TSysjob;
import com.skpw.repository.JobRepository;
import com.skpw.repository.OrgUnitRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JobServiceImplTest {

	@Resource
	private JobRepository jobRepository;

	@Resource
	private OrgUnitRepository orgUnitRepository;

	// @Test
	// public void testShowJobInfo() {
	//
	// }
	//
//	 @Test
//	 public void testShowJobListByCondition() {
//		 
//		 
//		List<TSysjob> list=jobRepository.findAll();
//		
//		System.out.println(list.size());
//	
//	 }
	
	@Test
	public void testSaveJob() {
//		String orgid = "40284819462d225501462d225aa00000";
//		String jobid="4028481946368a760146368a7c030000";
		TSysjob tSysjob = new TSysjob();
		tSysjob.setJobCode("02");
		tSysjob.setJobname("软件开发");
	//	tSysjob.setParentJob(jobRepository.findOne(jobid));
		//tSysjob.setDepid("123");
		//tSysjob.setOrgUnit(orgUnitRepository.findOne(orgid));
		jobRepository.save(tSysjob);

	}
	//
	// @Test
	// public void testDeleteJob() {
	//
	// }
	//
	// @Test
	// public void testUpdateJob() {
	//
	// }
	//
	// @Test
	// public void testInitUpdateJob() {
	//
	// }
	//
	// @Test
	// public void testCount() {
	//
	// }

}
