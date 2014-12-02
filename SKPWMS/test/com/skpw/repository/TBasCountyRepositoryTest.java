package com.skpw.repository;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lowagie.text.List;
import com.skpw.bean.TBasCounty;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TBasCountyRepositoryTest {

	
	
	@Resource
	private  TBasCountyRepository  countyRepository;
	@Test
	public void testFindCountryByAll() {
		
		java.util.List<TBasCounty> list=countyRepository.findCountryByAll();
		
		
		for (TBasCounty tBasCounty : list) {
			
			System.out.println(tBasCounty.getFcountyName());
		}
		System.out.println(list.size());
		
	}

}
