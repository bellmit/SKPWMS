package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TSysLog;

public interface LogRepository extends JpaRepository<TSysLog, String>,JpaSpecificationExecutor<TSysLog> {

}
