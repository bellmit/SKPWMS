package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TICRechargeDetailsBak;

public interface ICRechargeDetailsRepository extends JpaRepository<TICRechargeDetailsBak, String>,JpaSpecificationExecutor<TICRechargeDetailsBak> {

}
