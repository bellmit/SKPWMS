package com.skpw.securitymanager;

 import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.skpw.bean.TSysUserInfo;
import com.skpw.common.PasswordUtil;
import com.skpw.core.util.ReadPropertity;
import com.skpw.service.UserService;

public class MyUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	@Resource
	private UserService userService;

	public static final String USERNAME = "j_username";
	public static final String PASSWORD = "j_password";

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
//		  String clientSessionId = request.getParameter("ssid");
//	      String serverSessionId = request.getSession().getId();
//	      if (!serverSessionId.equals(clientSessionId)) {
//	    	 
//				 throw new AuthenticationServiceException("用户名或者密码错误！");
//	      }
	      
	      String referer  = request.getHeader("referer");
			if(referer != null && !referer.equals("") && referer != ""){
			boolean flag = false;
			String refstr = ReadPropertity.getProperty("sys-config.properties", "refererurl");
			String[] arr_ref = {"https://localhost","http://localhost"};
			if(refstr != null && !refstr.trim().equals("")) {
				arr_ref = refstr.split(",");
			}
			  //String[] arr_ref = {"http://113.106.85.106","https://113.106.85.106","http://192.168.200.159","https://192.168.200.159","https://127.0.0.1","http://127.0.0.1","https://localhost","http://localhost"};
			  for(int i=0; i< arr_ref.length;i++){
				  if(referer.startsWith(arr_ref[i])){
					  flag = true;
					  break;
				  }
			  }
			  if(!flag){
				  throw new AuthenticationServiceException("用户名或者密码错误！");
			  }
			}
	      
		request.getSession(true).invalidate();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				if ("jsesion-skpw".equalsIgnoreCase(cookies[i].getName())) {
					cookies[i].setMaxAge(0);
				}
			}
		}
		Cookie cookie = new Cookie("jsesion-skpw", UUID.randomUUID().toString()); // 新建Cookie  
		cookie.setSecure(true);  
		response.addCookie(cookie);
		
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		// 验证用户账号与密码是否对应
		username = username.trim();
		TSysUserInfo users = userService.findUserinfoByUsername(username);
		if (users == null || !users.getPassword().equals(PasswordUtil.encrypt(username, password, PasswordUtil.getStaticSalt()))) {
			/*
			 * 在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段
			 * 这样我们可以在登录失败后，向用户提供相应的信息。 if (forwardToDestination) {
			 * request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } else { HttpSession session =
			 * request.getSession(false);
			 * 
			 * if (session != null || allowSessionCreation) {
			 * request.getSession(
			 * ).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			 * exception); } }
			 */
			throw new AuthenticationServiceException("用户名或者密码错误！");
		}

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, PasswordUtil.encrypt(username, password, PasswordUtil.getStaticSalt()));
		// Place the last username attempted into HttpSession for views

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

}
