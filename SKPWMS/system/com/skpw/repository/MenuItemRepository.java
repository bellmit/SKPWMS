package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skpw.bean.TSysMenu;

public interface MenuItemRepository extends JpaRepository<TSysMenu, String>,JpaSpecificationExecutor<TSysMenu> {
		
	@Query("from TSysMenu as t where t.parentMenu.id=:id")
	public List<TSysMenu> findNodesByParentId(@Param("id")String id);
}
