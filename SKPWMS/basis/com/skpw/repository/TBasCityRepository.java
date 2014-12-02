package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasCity;

public interface TBasCityRepository extends JpaRepository<TBasCity, String>,JpaSpecificationExecutor<TBasCity> {

}
