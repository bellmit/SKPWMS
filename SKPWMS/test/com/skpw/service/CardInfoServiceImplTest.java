package com.skpw.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.CardInfo;
import com.skpw.bean.ICCardControler;
import com.skpw.bean.TTcControler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CardInfoServiceImplTest {

	@Resource
	private CardInfoService cardInfoService;
	@Resource
	private UserService userService;
	@Resource
	private WryjbxxService wryjbxxService;
	@Resource
	private TTcControlerService tTcControlerService;
	
	@Test
	public  void  testFindCardByID(){
		/*String 	cardid="4028481a46b7edd60146b81c204c0008";
		CardInfo  cardInfo=cardInfoService.findCardById(cardid);
		
		System.out.println(cardInfo);*/
		List<CardInfo> list=cardInfoService.showCardinfo();
		
		System.out.println(list.size());
		for (CardInfo cardInfo : list) {
			if (cardInfo.getEnterprise()!=null) {
				String str=cardInfo.getEnterprise().getFenterId();
				String ttcname=tTcControlerService.getTTController(str);
				System.out.println(ttcname+"_______________");
			}
			
		}
		
		
	}

	/*@Test
	public void testSaveCard() {
		ICCardControler icCardControler = new ICCardControler();

		CardInfo cardInfo = new CardInfo();
		cardInfo.setCardid("20140619001");
		cardInfo.setDate("2014-06-19");
		cardInfo.setFLinkMan("hjy");
		cardInfo.setEnterprise(wryjbxxService
				.findWry("009a96e8b4314dfc96c80ef1e5ea7857"));
		cardInfo.setUserInfo(userService
				.initUpdateUser("4028481945f4d4210145f4d423de0000"));
		TTcControler tTcControler = tTcControlerService
				.findtccontrollerById("1");
		icCardControler.setTcControler(tTcControler);
		icCardControler.setCardInfo(cardInfo);

		cardInfo.setIcCardControler(icCardControler);
		cardInfoService.saveCard(cardInfo);

	}*/

	@Test
	public void testFindByCardPhyNO(){
		/*Object[] cardInfo=cardInfoService.findCardByCardPhyNo("B01");
		
		System.out.println(cardInfo);*/
	}
}
