package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasCtrlLevel;

public interface TBasCtrlLevelRepository extends JpaRepository<TBasCtrlLevel, String>,JpaSpecificationExecutor<TBasCtrlLevel>  {

}
