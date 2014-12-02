package com.skpw.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lowagie.text.List;
import com.skpw.bean.TSysUserInfo;
import com.skpw.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UserServiceImplTest {
	
	
	

	@Resource
	private UserService userService;

	@Resource
	private LogService logService;

	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRepository userRepository;

	// @Test
	// public void findRoleByUserId(){
	//
	// String userid="402884eb467fd42101467fd4cfa70000";
	// List list=userService.findRoleByUserId(userid);
	// List<TSysRole> roleList=new ArrayList();
	// for(int i=0;i<list.size();i++) {
	// TSysRole role=new TSysRole();
	// Object [] objs=(Object[]) list.get(i);
	// role.setId((String)objs[0]);
	// role.setRolename((String)objs[1]);
	//
	// roleList.add(role);
	//
	// }
	//
	// for(TSysRole role:roleList){
	//
	// System.out.println(role.getId()+" "+role.getRolename());
	// }
	// }

	// @Test
	// public void testShowUserInfo() {
	//
	// }
	//
	// @Test
	// public void testShowUserListByCondition() {
	// long count = userService.count();
	// Page page = new Page(1, 10, count);
	// Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
	// page.getPageSize());
	// TSysUserInfo user = new TSysUserInfo();
	// user.setUsername("hh");
	// List<TSysUserInfo> list = userService.showUserListByCondition(
	// UserSpecs.queryCondition(user), pageRequest).getContent();
	// // List<TSysUserInfo> list= userService.showUserListByCondition(null,
	// // null).getContent();
	//
	// System.out.print(list.size());
	// }

	 @Test
	 public void testSaveUser() {
		 
		 java.util.List<TSysUserInfo> list=userService.showUserInfo();
		 
		 System.out.println(list.size());
	
//		String list= userService.findorgunitByUserId("402884ef49126fdf0149127409290001");
//		
//		System.out.println(list);
		
	 }

	// @Test
	// public void testDeleteUser() {
	//
	// String id = "4028481945ff07cb0145ff07d3aa0000";
	//
	// userService.deleteUser(id);
	// String message = "用户" + "aa" + "删除成功";
	// logService.addLog(new
	// TSysLog(Globals.Log_Leavel_INFO,Globals.Log_Type_DEL, message));
	//
	// }
	//
//	@Test
//	public void testDeleteUserRole() {
//
//		TSysUserInfo userinfo=userRepository.findbyUsername("user");
//		
//		System.out.println(userinfo.getPassword());
//
//	}
	//
	// @Test
	// public void testInitUpdateUser() {
	//
	// }
	//
	// @Test
	// public void testCount() {
	//
	// }

	// @Test
	// public void testLogin() {
	//
	// TSysUserInfo flag = userService.login("hh", "123");
	//
	// System.out.println(flag.getId() + " " + flag.getUsername());
	//
	// }

}
