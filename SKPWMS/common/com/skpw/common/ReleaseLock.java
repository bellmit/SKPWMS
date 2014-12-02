//package com.skpw.common;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import com.skpw.bean.TSysUserInfo;
//import com.skpw.service.UserDetailsService;
//import com.skpw.service.UserService;
//
////@Component
//public class ReleaseLock {
//
//	@Resource
//	private UserService userService;
//	
//	@Resource
//	private UserDetailsService userDetailsService;
//
////	@Scheduled(cron = "0/30 * * * * ?")
//	public void releaseLock() {
//
//		System.out.println("释放锁" + "_______________"+new Date());
//		
//		List<TSysUserInfo> infos = userService.showUserInfo();
//
//		for (TSysUserInfo tSysUserInfo : infos) {
//
//			TSysUserInfo userInfo = userService
//					.findUserinfoByUsername(tSysUserInfo.getUsername());
//			if (userInfo != null) {
//				
//				if (userInfo.getBlockStatus() == 0) {
//					userInfo.setBlockStatus(1);
//					userService.updateUser(userInfo);
//				}
//
//			}
//
//		}
//
//	}
//
//}
