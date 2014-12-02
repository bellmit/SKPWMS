package com.skpw.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Ehcache;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TSysLog;
import com.skpw.bean.TSysUserInfo;
import com.skpw.common.AjaxJson;
import com.skpw.common.Globals;
import com.skpw.common.PasswordUtil;
import com.skpw.core.util.oConvertUtils;
import com.skpw.securitymanager.Cache;
import com.skpw.service.LogService;
import com.skpw.service.UserDetailsService;
import com.skpw.service.UserService;

@Controller
public class LoginController {

	@Resource
	private UserService userService;

	@Resource
	private LogService logService;

	@Resource
	private UserDetailsService userDetailsService;

	private Ehcache passwordRetryCache = Cache.getPasswordRetryCache();

	@RequestMapping("/sys/login")
	public void login(String username, String password,
			HttpServletResponse res, HttpServletRequest req) {

		TSysUserInfo userInfo = userService.login(username, password);

		try {
			if (userInfo != null) {

				req.getSession().setAttribute("userinfo", userInfo);
				res.getWriter().print("true");
			} else {
				res.getWriter().print("false");
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@RequestMapping("/sys/loginOut")
	public String loginOut(HttpServletResponse res, HttpServletRequest req,
			HttpServletResponse reponse) {
		reponse.setHeader("Pragma", "No-cache");
		reponse.setHeader("Cache-Control", "no-cache");
		reponse.setDateHeader("Expires", 0);
		req.getSession(true).invalidate();
		return "../login";

	}

	/**
	 * 修改密码
	 * 
	 * @param res
	 * @param id
	 * @param pw
	 */
	@RequestMapping("/sys/editPassword")
	@ResponseBody
	public AjaxJson editPassword(HttpServletRequest request, String userid) {
		AjaxJson j = new AjaxJson();
		TSysUserInfo user = userService.initUpdateUser(userid);
		String password = oConvertUtils.getString(request
				.getParameter("password"));
		String newpassword = oConvertUtils.getString(request
				.getParameter("newpassword"));
		String pString = PasswordUtil.encrypt(user.getUsername(), password,
				PasswordUtil.getStaticSalt());

		if (!pString.equals(user.getPassword())) {
			j.setMsg("原密码不正确");
			j.setSuccess(false);
		} else {
			try {
				user.setPassword(PasswordUtil.encrypt(user.getUsername(),
						newpassword, PasswordUtil.getStaticSalt()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			userService.editPassword(user);
			j.setMsg("修改成功");

		}
		return j;
	}

	@RequestMapping("/sys_index")
	public String login1(HttpServletRequest req, Model model,
			HttpServletResponse response) {

		Object userSecurity = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String username = "";
		String password = "";
		req.getSession().invalidate();
		if (userSecurity instanceof UserDetails) {
			username = ((UserDetails) userSecurity).getUsername();

		} else {

			username = userSecurity.toString();
		}

		TSysUserInfo userInfo = userService.findUserinfoByUsername(username);

		if (userInfo != null) {
			String message = "用户" + userInfo.getUsername() + "登陆成功";
			logService.addLog(new TSysLog(Globals.Log_Leavel_INFO,
					Globals.Log_Type_LOGIN, message));
			req.getSession().setAttribute("userinfo", userInfo);
		}

		passwordRetryCache.remove(username);

		return "../index";

	}

	@RequestMapping("/loginFail")
	public String loginFail(Model model) {		

		model.addAttribute("msg", "用户名或密码错误");
		
		return "../login";
	}

}
