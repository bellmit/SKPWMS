package com.skpw.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AuthorityRepositoryTest {
	
	@Resource
	private AuthorityRepository authorityRepository;

	@Test
	public void testGetIds() {
		  List<String> list=authorityRepository.getIds();
		  for (String id : list) {
			
			  System.out.println(id);
		}
		  System.out.println(list.size());
	}

}
