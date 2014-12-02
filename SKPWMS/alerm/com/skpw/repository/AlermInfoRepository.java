package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.Alerminfo;

public interface AlermInfoRepository extends JpaRepository<Alerminfo, String>,JpaSpecificationExecutor<Alerminfo> {

}
