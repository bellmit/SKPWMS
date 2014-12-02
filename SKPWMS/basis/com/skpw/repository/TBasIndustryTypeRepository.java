package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.TBasIndustryType;

public interface TBasIndustryTypeRepository extends JpaRepository<TBasIndustryType, String>,JpaSpecificationExecutor<TBasIndustryType>{

	@Query("from TBasIndustryType order by FLongCode")
	public List<TBasIndustryType> findAllOrderByID();
}
