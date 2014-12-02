package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.TSysAuthorityRole;
import com.skpw.repository.RoleAuthorityRepository;
@Service("roleAuthorityService")
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

	@Resource
	private RoleAuthorityRepository roleAuthorityRepository;

	public void saveRoleAuthority(TSysAuthorityRole authorityRole) {
		
		roleAuthorityRepository.save(authorityRole);

	}

	public void delRoleAuthority(TSysAuthorityRole authorityRole) {
		roleAuthorityRepository.delete(authorityRole.getId());
	}

	
	public void deleteAuthorityRole(String roleid, String authorityid) {
		
		roleAuthorityRepository.deleteAuthorityRole(roleid, authorityid);
		
	}

	
	public List<TSysAuthorityRole> findAuthorityByroleid(String roleid) {
		
		return  roleAuthorityRepository.findAuthorityByroleid(roleid);
		
	}
	
}