package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasRegisterType;

public interface TBasRegisterTypeRepository extends JpaRepository<TBasRegisterType, String>,JpaSpecificationExecutor<TBasRegisterType> {

}
