package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysRole;
import com.skpw.bean.TSysUserInfo;
import com.skpw.repository.UserRepository;
import com.skpw.repository.UserRoleRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserRoleRepository userRoleRepository;

	public List<TSysUserInfo> showUserInfo() {

		return userRepository.findAll();
	}

	public Page<TSysUserInfo> showUserListByCondition(
			Specification<TSysUserInfo> spec, Pageable pager) {

		return userRepository.findAll(spec, pager);
	}

	public TSysUserInfo saveUser(TSysUserInfo userInfo) {

		return userRepository.saveAndFlush(userInfo);
	}

	public void deleteUser(String userid) {

		userRepository.delete(userid);
	}

	public void updateUser(TSysUserInfo userInfo) {

		userRepository.save(userInfo);
	}

	public TSysUserInfo initUpdateUser(String userid) {

		return userRepository.findOne(userid);
	}

	public long count(Specification<TSysUserInfo> spec) {
		return userRepository.count(spec);
	}

	public TSysUserInfo login(String username, String password) {

		return userRepository.login(username, password);
	}

	public List<TSysRole> findRoleByUserId(String userid) {
		List list = userRepository.findRoleByUserId(userid);
		List<TSysRole> roleList = new ArrayList<TSysRole>();
		for (int i = 0; i < list.size(); i++) {
			TSysRole role = new TSysRole();
			Object[] objs = (Object[]) list.get(i);
			role.setId((String) objs[0]);
			role.setRolename((String) objs[1]);
			roleList.add(role);
		}

		return roleList;

	}

	public String getRoleStr(String userid) {

		String roleStr = "";

		List<TSysRole> list = findRoleByUserId(userid);
		for(int i=0; i <list.size();i++ ) {
			if(i==0){
				roleStr += list.get(i).getRolename();
			}else {
				roleStr += ","+list.get(i).getRolename();
			}
		}

		return roleStr;
	}
	
	public String getRoleidStr(String userid) {

		String roleidStr = "";

		List<TSysRole> list = findRoleByUserId(userid);

		for (TSysRole role : list) {

			roleidStr += role.getId() + ",";

		}

		return roleidStr;
	}
	

	public void deleteuserrole(String userid, String roleid) {

		userRoleRepository.deleteUserRole(userid, roleid);
	}

	public void editPassword(TSysUserInfo userInfo) {

		userRepository.save(userInfo);
	}

	public TSysUserInfo findUserinfoByUsername(String username) {

		return userRepository.findbyUsername(username);
	}

	public String findorgunitByUserId(String userid) {

		String orgid = userRepository.findorgunitByUserId(userid);

		return orgid;
	}

}
