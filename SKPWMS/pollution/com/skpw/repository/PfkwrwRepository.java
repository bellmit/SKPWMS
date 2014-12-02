package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.skpw.bean.TBasPfkwrw;

public interface PfkwrwRepository extends JpaRepository<TBasPfkwrw, String>,JpaSpecificationExecutor<TBasPfkwrw> {

	
	
}
