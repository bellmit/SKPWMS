package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasEnterManagement;

public interface WryglsxRepository extends JpaRepository<TBasEnterManagement, String>,JpaSpecificationExecutor<TBasEnterManagement> {

	
	
}
