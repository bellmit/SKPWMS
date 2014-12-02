package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.TSysUserrole;
import com.skpw.repository.UserRoleRepository;

@Service("roleUserService")
public class RoleUserServiceImpl implements RoleUserService {
	
	@Resource
	private UserRoleRepository userRoleRepository;

	public void saveRoleUser(TSysUserrole userrole) {

		userRoleRepository.save(userrole);

	}

	
	public void deleteRoleUser(TSysUserrole userrole) {

		userRoleRepository.delete(userrole.getId());

	}
	
	//通过用户查找用户角色信息
	public List<TSysUserrole> findRoleUserByUserid(String userid) {
		
		return userRoleRepository.findRoleUserByUserid(userid);
	}

}
