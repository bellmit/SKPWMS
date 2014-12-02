package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skpw.bean.TSysMenu;
import com.skpw.repository.MenuItemRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MenuItemServiceImplTest {
	
	@Resource
	private MenuItemRepository menuItemRepository;

//	@Test
//	public void testShowTSysMenuInfo() {
//		
//	}
//
//	@Test
//	public void testShowTSysMenuListByCondition() {
//		
//	}
//
//	@Test
//	public void testSaveTSysMenu() {
//		
////		TSysMenu parmenu=new TSysMenu();
////		parmenu.setId("刷卡排污");
//	
//		TSysMenu menu=new TSysMenu();
//		
//		menu.setMenuCode("004");
//		menu.setMenuName("權限管理");
//		
//		menu.setParentMenu(menuItemRepository.findOne("40284819462cfdcb01462cfdd0510000"));
//		
//		menu.setUrl("user/initUserToList");
//		
//		menuItemRepository.save(menu);
//		
//	}

//	@Test
//	public void testDeleteTSysMenu() {
//		
//	}
//
//	@Test
//	public void testUpdateTSysMenu() {
//		
//	}
//
//	@Test
//	public void testInitUpdateTSysMenu() {
//		
//	}
//
	@Test
	public void testfindNodesByParentId() {
		String id="402884f146af61e20146af9bf0020003";
		List list=menuItemRepository.findNodesByParentId(id);
		
		System.out.println(list.size());
	}

}
