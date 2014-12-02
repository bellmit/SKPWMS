package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.TBasCounty;

public interface TBasCountyRepository  extends JpaRepository<TBasCounty, String>,JpaSpecificationExecutor<TBasCounty>{
	
	//查找深圳市县级行政单位
	
	@Query("from TBasCounty c  where c.fcountyId like '44030%' ")
	public   List<TBasCounty>   findCountryByAll();
}
