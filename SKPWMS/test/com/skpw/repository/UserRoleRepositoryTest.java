package com.skpw.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TSysRole;
import com.skpw.bean.TSysUserInfo;
import com.skpw.bean.TSysUserrole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserRoleRepositoryTest {

	@Resource
	private UserRoleRepository userRoleRepository;
	@Resource
	private UserRepository userRepository;
	@Resource
	private  RoleRepository roleRepository;

	@Test
	public void testFindRoleUserByUserid() {

	

		List<TSysUserrole> list = userRoleRepository
				.findRoleUserByUserid("402884ef49126fdf0149127409290001");

		System.out.println(list.size());
	}

}
