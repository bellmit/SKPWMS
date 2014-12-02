package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.WarningSetup;

public interface WarningSetupRepository extends JpaRepository<WarningSetup, String>,JpaSpecificationExecutor<WarningSetup> {

}
