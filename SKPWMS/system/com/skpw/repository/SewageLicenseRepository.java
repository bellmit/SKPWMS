package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasSewageLicence;

public interface SewageLicenseRepository extends JpaRepository<TBasSewageLicence, String>,JpaSpecificationExecutor<TBasSewageLicence>{

}
