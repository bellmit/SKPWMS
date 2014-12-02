package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysAuthority;
import com.skpw.bean.TSysRole;

public interface AuthorityService {
	
	// 显示所有权限
	public List<TSysAuthority> showAuthorityInfo();

	// 分页显示所有权限
	public Page<TSysAuthority> showAuthorityListByCondition(Specification<TSysAuthority> spec,
			Pageable pager);

	// 添加权限
	public void saveAuthority(TSysAuthority authority);

	// 删除权限
	public void deleteAuthority(String authorityid);

	// 修改权限
	public void updateAuthority(TSysAuthority authority);

	// 修改初始化
	public TSysAuthority findAuthorityById(String authorityid);

	// 权限记录
	public long count(Specification<TSysAuthority> spec);
}
