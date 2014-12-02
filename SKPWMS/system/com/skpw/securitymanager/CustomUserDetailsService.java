package com.skpw.securitymanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysUserInfo;
import com.skpw.common.ContextHolderUtils;
import com.skpw.repository.ResourceDao;
import com.skpw.repository.UserRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	protected static Logger logger = Logger.getLogger("service");
	@Resource
	private UserRepository userRepository;

	@Resource
	private ResourceDao resourceDao;

	private Ehcache passwordRetryCache = Cache.getPasswordRetryCache();

	// 根据用户名获取密码和权限
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		ContextHolderUtils.getRequest().getSession(true);
		UserDetails user = null;

		Element element = passwordRetryCache.get(username);
		if (element == null) {
			element = new Element(username, new AtomicInteger(0));
			passwordRetryCache.put(element);
		}
		AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
		if (retryCount.incrementAndGet() > 3) {
			TSysUserInfo userInfo = userRepository.findbyUsername(username);
			user = new User(userInfo.getUsername(), userInfo.getPassword()
					.toLowerCase(), true, true, true, false,
					getAuthorities(username));
		} else {
			TSysUserInfo userInfo = userRepository.findbyUsername(username);
			user = new User(userInfo.getUsername(), userInfo.getPassword()
					.toLowerCase(), true, true, true, true,
					getAuthorities(username));
		}

		return user;
	}

	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(String username) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// 所有的用户默认拥有ROLE_USER权限
		// logger.debug("Grant ROLE_USER to this user");
		// authList.add(new GrantedAuthorityImpl("ROLE_USER"));
		List list = resourceDao.getAuthorityid(username);
		for (int i = 0; i < list.size(); i++) {
			GrantedAuthority authority = new SimpleGrantedAuthority(list.get(i)
					.toString());
			authList.add(authority);
		}

		return authList;
	}

}
