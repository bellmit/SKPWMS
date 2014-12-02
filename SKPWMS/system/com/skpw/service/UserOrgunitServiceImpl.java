package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.TSysuserOrgUnit;
import com.skpw.repository.UserOrgunitRepository;

@Service("userOrgunitService")
public class UserOrgunitServiceImpl implements UserOrgunitService {

	@Resource
	private UserOrgunitRepository userOrgunitRepository;

	public List<TSysuserOrgUnit> findUserOrgunitByuserid(String userid) {

		return userOrgunitRepository.findUserOrgunitByuserid(userid);
	}

	public void saveUserOrgUnit(TSysuserOrgUnit tSysuserOrgUnit) {

		userOrgunitRepository.save(tSysuserOrgUnit);
	}

	public void delUserOrgUnit(TSysuserOrgUnit tSysuserOrgUnit) {

		userOrgunitRepository.deleteUserOrgunitByid(tSysuserOrgUnit.getId());
	}

}
