package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasProvince;

public interface TBasProvinceRepository extends JpaRepository<TBasProvince, String>,JpaSpecificationExecutor<TBasProvince> {

}
