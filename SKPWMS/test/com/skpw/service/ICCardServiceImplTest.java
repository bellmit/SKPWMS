package com.skpw.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.ICCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ICCardServiceImplTest {

	@Resource
	private ICCardService icCardService;

	@Test
	public void testSaveICCard() {

		ICCard icCard = new ICCard();
		icCard.setId("001");
		icCard.setEncryptInfo("00000000001");
		icCard.setEncryptInfo("xxxxx");
		icCard.setStatus(1);
		icCardService.saveICCard(icCard);

	}

}
