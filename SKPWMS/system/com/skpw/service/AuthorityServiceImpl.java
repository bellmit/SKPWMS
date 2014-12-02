package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysAuthority;
import com.skpw.repository.AuthorityRepository;

@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

	@Resource
	private AuthorityRepository authorityRepository;

	public List<TSysAuthority> showAuthorityInfo() {

		return authorityRepository.findAll();
	}

	public Page<TSysAuthority> showAuthorityListByCondition(
			Specification<TSysAuthority> spec, Pageable pager) {

		return authorityRepository.findAll(spec, pager);
	}

	public void saveAuthority(TSysAuthority authority) {
		authorityRepository.save(authority);
	}

	public void deleteAuthority(String authorityid) {

		authorityRepository.delete(authorityid);
	}

	public void updateAuthority(TSysAuthority authority) {
		authorityRepository.save(authority);
	}

	public long count(Specification<TSysAuthority> spec) {

		return authorityRepository.count(spec);
	}

	public TSysAuthority findAuthorityById(String authorityid) {

		return authorityRepository.findOne(authorityid);
	}

}
