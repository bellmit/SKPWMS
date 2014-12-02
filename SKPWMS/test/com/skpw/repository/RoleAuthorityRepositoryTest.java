package com.skpw.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TSysAuthorityRole;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RoleAuthorityRepositoryTest {
	
	@Resource
	private RoleAuthorityRepository roleAuthorityRepository;

	@Test
	public void testDeleteAuthorityRole() {
		
		String roleid="402848194617910e0146179642b10001";
		
		List<TSysAuthorityRole> list=roleAuthorityRepository.findAuthorityByroleid(roleid);
		
		System.out.println(list.size());
		
	}

}
