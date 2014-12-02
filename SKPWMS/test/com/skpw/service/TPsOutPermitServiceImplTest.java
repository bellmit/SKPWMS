package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TPsOutPermit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TPsOutPermitServiceImplTest {

	@Autowired
	private TPsOutSPollService tPsOutSPollService;

	@Test
	public void test1() {
		List<Map<String, String>> maps=tPsOutSPollService.distinct("aac90719f24e40399eaa7b5241a1c77f");
		System.out.println("ok");
	}

}
