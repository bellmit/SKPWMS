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

import org.springframework.security.authentication.AuthenticationServiceException;

import com.skpw.core.util.ReadPropertity;

public class ActionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String referer  = httpServletRequest.getHeader("referer");
		if(referer != null && !referer.equals("") && referer != ""){
		boolean flag = false;
		String refstr = ReadPropertity.getProperty("sys-config.properties", "refererurl");
		String[] arr_ref = {"https://localhost","http://localhost"};
		if(refstr != null && !refstr.trim().equals("")) {
			arr_ref = refstr.split(",");
		}
		//String[] arr_ref = {"http://192.168.2.38","https://192.168.2.38","http://113.106.85.106","https://113.106.85.106","http://192.168.200.159","https://192.168.200.159","https://127.0.0.1","http://127.0.0.1","https://localhost","http://localhost"};
		  for(int i=0; i< arr_ref.length;i++){
			  if(referer.startsWith(arr_ref[i])){
				  flag = true;
				  break;
			  }
		  }
		  if(!flag){
			  httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
		  }
		  
		}

//		String clientSessionId = httpServletRequest.getParameter("ssid");
//		String serverSessionId = httpServletRequest.getSession().getId();
//		if (serverSessionId.equals(clientSessionId)) {
//			chain.doFilter(request, response);
//		} else {
//			httpServletResponse.sendRedirect(httpServletRequest
//					.getContextPath() + "/login.jsp");
//		}
		chain.doFilter(httpServletRequest, httpServletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
