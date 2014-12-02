package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.WarningLog;

public interface WarningLogRepository  extends JpaRepository<WarningLog, String>,JpaSpecificationExecutor<WarningLog>{

}
