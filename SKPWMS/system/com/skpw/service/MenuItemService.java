package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysMenu;

public interface MenuItemService {

	// 显示所有菜单

	public List<TSysMenu> showTSysMenuInfo();

	// 分页显示所有菜单
	public Page<TSysMenu> showTSysMenuListByCondition(
			Specification<TSysMenu> spec, Pageable pager);

	// 添加菜单
	public void saveTSysMenu(TSysMenu menu);

	// 删除菜单
	public void deleteTSysMenu(String menuid);

	// 修改菜单
	public void updateTSysMenu(TSysMenu menu);

	// 修改初始化
	public TSysMenu initUpdateTSysMenu(String menuid);

	// 菜单记录
	public long count();
	
	//根据父节点得到子节点
	public List<TSysMenu> findNodesByParentId(String id);
}
