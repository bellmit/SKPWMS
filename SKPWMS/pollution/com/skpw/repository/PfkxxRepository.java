package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TPsOutSewagebak;

public interface PfkxxRepository extends JpaRepository<TPsOutSewagebak, String>,JpaSpecificationExecutor<TPsOutSewagebak> {

	
	
}
