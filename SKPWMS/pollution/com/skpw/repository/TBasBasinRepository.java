package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasBasin;

public interface TBasBasinRepository  extends JpaRepository<TBasBasin, String>,JpaSpecificationExecutor<TBasBasin>  {

}
