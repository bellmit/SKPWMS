package com.skpw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skpw.bean.TSysUserInfo;

public class LoginFilter implements Filter {

	private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		TSysUserInfo user = (TSysUserInfo) httpServletRequest.getSession(true).getAttribute(
				"userinfo");
		if (!isExcludePages(httpServletRequest.getRequestURI())) {
			if (user == null) {
				httpServletResponse.sendRedirect(httpServletRequest
						.getContextPath() + "/login.jsp");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean isExcludePages(String url) {
		return url.indexOf("login.html") != -1
				|| url.indexOf("sys_index") != -1
				|| url.indexOf("loginFail") != -1
				|| url.indexOf("login.jsp") != -1 || url.endsWith(".css")
				|| url.endsWith(".js") || url.endsWith(".gif")
				|| url.endsWith(".jpg") || url.endsWith(".png");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	public void destroy() {
		this.config = null;
	}
}
