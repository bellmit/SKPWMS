package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TSysLog;
import com.skpw.bean.TSysUserInfo;
import com.skpw.bean.TSysUserrole;
import com.skpw.bean.TSysorgUnit;
import com.skpw.bean.TSysuserOrgUnit;
import com.skpw.common.AjaxJson;
import com.skpw.common.Globals;
import com.skpw.common.Page;
import com.skpw.common.PasswordUtil;
import com.skpw.common.StringUtil;
import com.skpw.service.LogService;
import com.skpw.service.OrgUnitService;
import com.skpw.service.RoleService;
import com.skpw.service.RoleUserService;
import com.skpw.service.UserOrgunitService;
import com.skpw.service.UserService;
import com.skpw.service.UserSpecs;
import com.skpw.service.WryjbxxService;

@Controller
public class UserController {

	@Resource
	private UserService userService;

	@Resource
	private LogService logService;

	@Resource
	private RoleService roleService;

	@Resource
	private OrgUnitService orgUnitService;

	@Resource
	private WryjbxxService wryjbxxService;

	@Resource
	private RoleUserService roleUserService;
	
	@Resource
	private  UserOrgunitService  userOrgunitService;

	@RequestMapping("/user/initUserToList1")
	public String initUserToList() {

		return "sys/user_list";
	}

	// 显示所有
	@RequestMapping("/user/showUser")
	@ResponseBody
	public List showUser(Model model) {

		List<TSysUserInfo> list = userService.showUserInfo();

		return list;
	}

	// 分页条件显示所有用户信息列表
	@RequestMapping("/user/showUserByCondition")
	@ResponseBody
	public Object showUserbyCondition(Model model, TSysUserInfo user,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = userService.count(UserSpecs.queryCondition(user));
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TSysUserInfo> userInfos = userService.showUserListByCondition(
				UserSpecs.queryCondition(user), pageRequest).getContent();
		
		JSONArray jsonArray = new JSONArray();
		for (TSysUserInfo userInfo : userInfos) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", userInfo.getId());
			jsonObject.put("username", userInfo.getUsername());
			jsonObject.put("usercount", userInfo.getUsercount());
			jsonObject.put("password", userInfo.getPassword());
			jsonObject.put("workUnit", userInfo.getWorkUnit());
			jsonObject.put("phone", userInfo.getPhone());
			jsonObject.put("email", userInfo.getEmail());
			jsonObject.put("roleStr", userService.getRoleStr(userInfo.getId()));
			jsonArray.add(jsonObject);

		}

		map.put("total", count);
		map.put("rows", jsonArray);

		return map;
	}

	// 添加初始化

	public String initAddUser(Model model) {

		return "user/user_add";
	}

	// 用户录入
	@RequestMapping("/user/saveUser")
	@ResponseBody
	public AjaxJson saveUser(Model model, TSysUserInfo userInfo,
			HttpServletRequest request) {
		String message = "";
		String password = request.getParameter("password");
		String orgunitid = request.getParameter("orgunitid");
		String roleid = request.getParameter("roleid");
		AjaxJson ajaxJson = new AjaxJson();

		if (StringUtil.isNotEmpty(userInfo.getId())) {

			TSysUserInfo user = userService.initUpdateUser(userInfo.getId());
			user.setUsercount(userInfo.getUsercount());
			user.setWorkUnit(userInfo.getWorkUnit());
			user.setEnterprise(userInfo.getEnterprise());
			//删除用户组织的关系
			deleteUserOrgunit(user.getId());
			if (StringUtil.isNotEmpty(orgunitid)) {
				TSysuserOrgUnit userorgUnit = new TSysuserOrgUnit();
				TSysorgUnit orgUnit = orgUnitService
						.initUpdateTSysorgUnit(orgunitid);
				userorgUnit.setUserInfo(userInfo);
				userorgUnit.setOrgUnit(orgUnit);
				userInfo.setUserOrgUnit(userorgUnit);

			}
			user.setUserOrgUnit(userInfo.getUserOrgUnit());
			user.setPhone(userInfo.getPhone());
			user.setEmail(userInfo.getEmail());
			userService.saveUser(user);
			deleteRoleUser(user.getId());//删除用户角色关系
			message = "用户" + user.getUsername() + "更新成功";

			if (StringUtil.isNotEmpty(roleid)) {

				saveRoleUser(user, roleid);
			}
			logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
					Globals.Log_Type_UPDATE, message));
		} else {

			userInfo.setPassword(PasswordUtil.encrypt(userInfo.getUsername(),
					password, PasswordUtil.getStaticSalt()));

			if (StringUtil.isNotEmpty(orgunitid)) {
				TSysuserOrgUnit userorgUnit = new TSysuserOrgUnit();
				TSysorgUnit orgUnit = orgUnitService
						.initUpdateTSysorgUnit(orgunitid);
				userorgUnit.setUserInfo(userInfo);
				userorgUnit.setOrgUnit(orgUnit);
				userInfo.setUserOrgUnit(userorgUnit);
			}
			String userid=userService.saveUser(userInfo).getId();
			
			userInfo.setId(userid);

			message = "用户" + userInfo.getUsername() + "添加成功";
			if (StringUtil.isNotEmpty(roleid)) {

				saveRoleUser(userInfo, roleid);
			}
			logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
					Globals.Log_Type_INSERT, message));

		}

		ajaxJson.setMsg(message);
		return ajaxJson;
	}

	// 删除
	@RequestMapping("/user/delUser")
	@ResponseBody
	public Map<String, Boolean> delUser(String id, HttpServletRequest request) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			TSysUserInfo userInfo = userService.initUpdateUser(id);
			List<TSysUserrole> roleuserlist = roleUserService
					.findRoleUserByUserid(id);

			if (roleuserlist.size() > 0) {

				deleteRoleUser(id);// 删除用户角色的关系
				userService.deleteUser(id);
				String message = "用户" + userInfo.getUsername() + "删除成功";
				logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
						Globals.Log_Type_DEL, message));
			} else {
				userService.deleteUser(id);
				String message = "用户" + userInfo.getUsername() + "删除成功";
				logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
						Globals.Log_Type_DEL, message));

			}
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

	// 修改初始化
	@RequestMapping("/sys/initEditPassword")
	public String initEditPassword(Model model, String id) {

		TSysUserInfo userInfo = userService.initUpdateUser(id);

		model.addAttribute("userinfo", userInfo);
		return "sys/changepassword";
	}

	// 修改
	public String editUser(TSysUserInfo userInfo) {

		userService.updateUser(userInfo);

		return "redirect:user/showUserByCondition";
	}

	// 显示用户信息详情
	@RequestMapping("/user/showUserInfo")
	public String showUserInfo(Model model, String userid) {

		TSysUserInfo user = userService.initUpdateUser(userid);

		model.addAttribute("user", user);

		return "sys/userinfo";
	}

	// 跳转到添加和修改界面界面
	@RequestMapping("/user/initJumpToUserAdd")
	public String initJumpToUserAdd(Model model, TSysUserInfo userInfo) {

		List<TBasEnterprise> enterlist = wryjbxxService.findAll();

		List<TSysorgUnit> orgUnitlist = orgUnitService.showTSysorgUnitInfo();

		model.addAttribute("enterlist", enterlist);
		model.addAttribute("orgUnitlist", orgUnitlist);

		if (StringUtil.isNotEmpty(userInfo.getId())) {

			TSysUserInfo user = userService.initUpdateUser(userInfo.getId());
			String orgunitid = userService
					.findorgunitByUserId(userInfo.getId());
			
			String rolenames=userService.getRoleStr(userInfo.getId());
			String roleids=userService.getRoleidStr(userInfo.getId());
			model.addAttribute("orgunitid", orgunitid);
			model.addAttribute("user", user);
			model.addAttribute("rolenames", rolenames);
			model.addAttribute("roleids", roleids);
			return "sys/user";

		}

		return "sys/user";

	}

	// 用户选择角色跳转页面
	@RequestMapping("/user/selectrole")
	public String roles() {

		return "sys/user_rolelist";
	}
	
	
	//删除用户组织的关系	
	public void  deleteUserOrgunit(String  userid){
		
		List<TSysuserOrgUnit> userOrgUnits=userOrgunitService.findUserOrgunitByuserid(userid);
		if (userOrgUnits.size()>0) {
			
			for (TSysuserOrgUnit tSysuserOrgUnit : userOrgUnits) {
				
				userOrgunitService.delUserOrgUnit(tSysuserOrgUnit);
			}
		}
		
	}

	// 删除用户角色关系
	public void deleteRoleUser(String userid) {

		List<TSysUserrole> roleuserlist = roleUserService
				.findRoleUserByUserid(userid);

		if (roleuserlist.size() > 0) {

			for (TSysUserrole tSysUserrole : roleuserlist) {

				roleUserService.deleteRoleUser(tSysUserrole);
			}
		}

	}

	// 保存用户角色关系
	public void saveRoleUser(TSysUserInfo userInfo, String roleids) {

		String[] roleidStr = roleids.split(",");

		for (int i = 0; i < roleidStr.length; i++) {

			TSysUserrole userrole = new TSysUserrole();

			userrole.setUserInfo(userInfo);

			userrole.setRole(roleService.findRoleById(roleidStr[i]));

			roleUserService.saveRoleUser(userrole);
		}

	}
	
	//通过用户查找角色
	@RequestMapping("/user/findRoleByUserId")
	@ResponseBody
	public   List  findRoleByUserId(String useid){		
		
		return  userService.findRoleByUserId(useid);
	}
}
