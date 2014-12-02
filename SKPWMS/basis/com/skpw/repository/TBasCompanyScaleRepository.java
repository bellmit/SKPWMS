package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasCompanyScale;

public interface TBasCompanyScaleRepository extends JpaRepository<TBasCompanyScale, String>,JpaSpecificationExecutor<TBasCompanyScale> {

}
