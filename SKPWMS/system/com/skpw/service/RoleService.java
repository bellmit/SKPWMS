package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysAuthority;
import com.skpw.bean.TSysRole;

public interface RoleService {
	
	
		// 显示所有角色
		public List<TSysRole> showRoleInfo();

		// 分页显示所有角色
		public Page<TSysRole> showRoleListByCondition(Specification<TSysRole> spec,
				Pageable pager);

		// 添加角色
		public void saveRole(TSysRole role);
		// 删除角色
		public void deleteRole(String roleid);

		// 修改角色
		public void updateRole(TSysRole role);

		// 查找角色
		public TSysRole findRoleById(String roleid);

		// 角色记录
		public long count(Specification<TSysRole> spec);
		
		public List<TSysAuthority> findAuthorityByroleId(String roleid);

}
