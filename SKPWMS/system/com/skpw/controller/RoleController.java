package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TSysAuthority;
import com.skpw.bean.TSysAuthorityRole;
import com.skpw.bean.TSysLog;
import com.skpw.bean.TSysRole;
import com.skpw.common.Globals;
import com.skpw.common.Page;
import com.skpw.common.StringUtil;
import com.skpw.service.AuthorityService;
import com.skpw.service.LogService;
import com.skpw.service.RoleAuthorityService;
import com.skpw.service.RoleService;
import com.skpw.service.RoleSpecs;

@Controller
public class RoleController {
	@Resource
	private LogService logService;

	@Resource
	private RoleService roleService;

	@Resource
	private AuthorityService authorityService;

	@Resource
	private RoleAuthorityService roleAuthorityService;

	// 跳转到角色显示页面
	@RequestMapping("/role/initRoleToList")
	public String initRoleToList() {

		return "sys/role_list";
	}

	// 显示所有
	@RequestMapping("/role/showRole")
	@ResponseBody
	public List showRole(Model model) {
		List<TSysRole> roles = roleService.showRoleInfo();

		return roles;
	}

	// 分页条件显示所有
	@RequestMapping("/role/showrolebyCondition")
	@ResponseBody
	public Map showrolebyCondition(Model model, TSysRole role,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(20);
		}
		long count = roleService.count(RoleSpecs.queryCondition(role));
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TSysRole> roles = roleService.showRoleListByCondition(
				RoleSpecs.queryCondition(role), pageRequest).getContent();

		map.put("total", count);
		map.put("rows", roles);
		return map;
	}

	// 添加初始化
	@RequestMapping("/role/initAddRole")
	public String initAddRole(Model model) {

		return "sys/role_add";
	}

	// 添加

	@RequestMapping("/role/addRole")
	@ResponseBody
	public String addRole(Model model, TSysRole role) {

		String result = "";
		try {

			if (StringUtil.isNotEmpty(role.getId())) {

				String message = "角色" + role.getRolename() + "更新成功";
				logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
						Globals.Log_Type_UPDATE, message));
				roleService.saveRole(role);
			} else {

				roleService.saveRole(role);
				String message = "角色" + role.getRolename() + "添加成功";
				logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
						Globals.Log_Type_INSERT, message));
			}
			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除
	@RequestMapping("/role/delRole")
	@ResponseBody
	public Map<String, Boolean> delRole(Model model, String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;

		try {

			TSysRole sysRole = roleService.findRoleById(id);

			List<TSysAuthorityRole> authorityRoles = roleAuthorityService
					.findAuthorityByroleid(id);

			if (authorityRoles.size() > 0) {

				delRoleAuthority(id);
				roleService.deleteRole(id);
				String message = "角色" + sysRole.getRolename() + "删除成功";
				logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
						Globals.Log_Type_DEL, message));
			} else {

				roleService.deleteRole(id);
				String message = "角色" + sysRole.getRolename() + "删除成功";
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

	// 跳转到权限列表弹出框
	public String jumpToAuthority() {

		return "";
	}

	// 角色绑定权限
	@RequestMapping("/role/addRoleAuthority")
	@ResponseBody
	public String addRoleAuthority(HttpServletRequest request) {
		String message = "";
		String roleid = request.getParameter("roleid");
		String authorityidStr = request.getParameter("authorityids");

		List<TSysAuthorityRole> list = roleAuthorityService
				.findAuthorityByroleid(roleid);

		if (list.size() > 0) {

			delRoleAuthority(roleid); // 删除角色权限关系
			saveRoleAuthority(roleService.findRoleById(roleid), authorityidStr);

		} else {

			saveRoleAuthority(roleService.findRoleById(roleid), authorityidStr);

		}

		message = "1";

		return message;

	}

	// 保存角色权限
	public void saveRoleAuthority(TSysRole role, String authorityids) {

		String[] authoritystr = authorityids.split(",");

		for (int i = 0; i < authoritystr.length; i++) {
			TSysAuthorityRole authorityRole = new TSysAuthorityRole();
			authorityRole.setRole(role);
			authorityRole.setAuthority(authorityService
					.findAuthorityById(authoritystr[i]));
			roleAuthorityService.saveRoleAuthority(authorityRole);
		}

	}

	// 删除角色权限
	public void delRoleAuthority(String roleid) {

		List<TSysAuthorityRole> list = roleAuthorityService
				.findAuthorityByroleid(roleid);

		if (list.size() > 0) {

			for (TSysAuthorityRole tSysAuthorityRole : list) {

				roleAuthorityService.delRoleAuthority(tSysAuthorityRole);
			}

		}

	}

	@RequestMapping("/role/findAuthorityByroleId")
	public @ResponseBody List<TSysAuthority> findAuthorityByroleId(String rid) {
		return roleService.findAuthorityByroleId(rid);
	}
}
