package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasTown;

public interface TBasTownRepository  extends JpaRepository<TBasTown, String>,JpaSpecificationExecutor<TBasTown>{

}
