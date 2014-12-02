package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TIcRechargeDetails;

@Repository
public interface TIcRechargeDetailsRepository extends JpaRepository<TIcRechargeDetails, String>,JpaSpecificationExecutor<TIcRechargeDetails> {

}
