package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasEnterEnviron;

public interface WryhbsxRepository extends JpaRepository<TBasEnterEnviron, String>,JpaSpecificationExecutor<TBasEnterEnviron> {

	
	
}
