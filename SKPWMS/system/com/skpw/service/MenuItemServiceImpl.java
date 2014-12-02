package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysMenu;
import com.skpw.repository.MenuItemRepository;

@Service("menuItemService")
public class MenuItemServiceImpl implements MenuItemService {

	@Resource
	private MenuItemRepository menuItemRepository;

	public List<TSysMenu> showTSysMenuInfo() {

		return menuItemRepository.findAll();
	}

	public Page<TSysMenu> showTSysMenuListByCondition(
			Specification<TSysMenu> spec, Pageable pager) {

		return menuItemRepository.findAll(spec, pager);
	}

	public void saveTSysMenu(TSysMenu menu) {

		menuItemRepository.save(menu);
	}

	public void deleteTSysMenu(String menuid) {

		menuItemRepository.delete(menuid);
	}

	public void updateTSysMenu(TSysMenu menu) {

		menuItemRepository.save(menu);
	}

	public TSysMenu initUpdateTSysMenu(String menuid) {

		return menuItemRepository.findOne(menuid);
	}

	public long count() {

		return menuItemRepository.count();
	}

	
	public List<TSysMenu> findNodesByParentId(String id) {
		
		return menuItemRepository.findNodesByParentId(id);
	}

}
