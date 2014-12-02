package com.skpw.securitymanager;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

public class Cache {

	private static Ehcache passwordRetryCache = CacheManager.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml")).getCache("passwordRetryCache");
	
	public static Ehcache getPasswordRetryCache() {
		return passwordRetryCache;
	}
}
