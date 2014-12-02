package com.skpw.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TSysuserOrgUnit;
import com.skpw.repository.UserOrgunitRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserOrgunitServiceImplTest {

	@Resource
	private UserOrgunitRepository userOrgunitRepository;

//	@Test
//	public void testFindAuthorityByuserid() {
//
//		String userid = "402884ea491ca7f001491caaf8890001";
//			
//		java.util.List<TSysuserOrgUnit> list=userOrgunitRepository.findUserOrgunitByuserid(userid);
//		
//		System.out.println(list.size());
//	}

//	@Test
//	public void testSaveUserOrgUnit() {
//		
//	}

	@Test
	public void testDelUserOrgUnit() {
		
		String  userorgunitid="402884ea491cbee101491cc0d9d20003";
		
		userOrgunitRepository.delete(userorgunitid);
//		
		
	}

}
