package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skpw.bean.WarningModel;

public interface WarningModelRepository  extends  JpaRepository<WarningModel, String> {

}
