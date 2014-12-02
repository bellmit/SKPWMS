package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasUnitClass;

public interface TBasUnitClassRepository extends JpaRepository<TBasUnitClass, String>,JpaSpecificationExecutor<TBasUnitClass> {

}
