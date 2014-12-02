package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysAuthority;
import com.skpw.bean.TSysRole;
import com.skpw.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleRepository roleRepository;

	public List<TSysRole> showRoleInfo() {

		return roleRepository.findAll();
	}

	public Page<TSysRole> showRoleListByCondition(Specification<TSysRole> spec,
			Pageable pager) {

		return roleRepository.findAll(spec, pager);
	}

	public void saveRole(TSysRole role) {

		roleRepository.save(role);
	}

	public void deleteRole(String roleid) {
		
		roleRepository.delete(roleid);

	}

	public void updateRole(TSysRole role) {
		
		roleRepository.save(role);
	}

	public TSysRole findRoleById(String roleid){

		return roleRepository.findOne(roleid);
	}

	public long count(Specification<TSysRole> spec) {

		return roleRepository.count(spec);
	}

	
	public List<TSysAuthority> findAuthorityByroleId(String roleid) {
		
		List list=roleRepository.findAuthorityByroleId(roleid);
		List<TSysAuthority> authorityList = new ArrayList<TSysAuthority>();
		for (int i = 0; i < list.size(); i++) {
			TSysAuthority authority = new TSysAuthority();
			Object[] objs = (Object[]) list.get(i);
			authority.setId((String) objs[0]);
			authority.setAuthorityName((String) objs[1]);
			
			authorityList.add(authority);
		}
		
		return authorityList;
	}

}
