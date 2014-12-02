package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysRole;
import com.skpw.bean.TSysUserInfo;

public interface UserService {

	// 显示所有用户
	public List<TSysUserInfo> showUserInfo();

	// 分页显示所有用户
	public Page<TSysUserInfo> showUserListByCondition(Specification<TSysUserInfo> spec,
			Pageable pager);

	// 添加用户
	public TSysUserInfo saveUser(TSysUserInfo userInfo);
	// 删除用户
	public void deleteUser(String userid);

	// 修改用户
	public void updateUser(TSysUserInfo userInfo);

	// 修改初始化
	public TSysUserInfo initUpdateUser(String userid);

	// 用户记录
	public long count(Specification<TSysUserInfo> spec);
	
	//用户登陆
	public TSysUserInfo login(String username,String password);
	
	//通过用户查找角色
	public abstract List<TSysRole> findRoleByUserId(String userid);
	
	//获取角色字符串
	public String getRoleStr(String userid) ;
	
	public String getRoleidStr(String userid) ;
	
	//删除用户角色关系
	public abstract void  deleteuserrole(String userid,String roleid);

	//修改密码
	public void editPassword(TSysUserInfo userInfo);
	
	//通过用户名查找用户
	public TSysUserInfo findUserinfoByUsername(String username);
	
	//通过用户查找组织id
	public abstract String findorgunitByUserId(String userid);
	
}
