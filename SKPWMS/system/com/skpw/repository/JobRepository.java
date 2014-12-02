package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TSysjob;

public interface JobRepository extends JpaRepository<TSysjob, String> ,JpaSpecificationExecutor<TSysjob>{

}
