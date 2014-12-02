package com.skpw.securitymanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;

import com.skpw.repository.AuthorityRepository;
import com.skpw.repository.MenuItemRepository;
import com.skpw.repository.ResourceDao;

@Service("customSecurityMetadataSource")
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Map<RequestMatcher, Collection<ConfigAttribute>> requestMap = null;
	
	@PostConstruct
	public void loadResourceDefine() {
		List<String> _authorityIds = authorityRepository.getIds();
		requestMap = new HashMap<RequestMatcher, Collection<ConfigAttribute>>();
		for (String _authorityId : _authorityIds) {
			ConfigAttribute _configAttribute = new SecurityConfig(_authorityId);
			List<String> _resourceValues =  resourceDao.getValues(_authorityId);
			for (String _resourceValue : _resourceValues) {
				RequestMatcher requestMatcher = new AntPathRequestMatcher(_resourceValue);
				if (requestMap.containsKey(requestMatcher)) {
					Collection<ConfigAttribute> _value = requestMap.get(_resourceValue);
					_value.add(_configAttribute);
					requestMap.put(requestMatcher, _value);
				} else {
					Collection<ConfigAttribute> _values = new ArrayList<ConfigAttribute>();
					_values.add(_configAttribute);
					requestMap.put(requestMatcher, _values);
				}
			}
		}
		
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		System.out.println(object.toString());
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	@Resource
	private AuthorityRepository authorityRepository;
	
	@Resource
	private MenuItemRepository  menuItemRepository;
	
	@Resource
	private ResourceDao  resourceDao;
	
}
