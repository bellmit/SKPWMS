package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TIcRecharge;

@Repository
public interface TIcRechargeRepository extends JpaRepository<TIcRecharge, String>,JpaSpecificationExecutor<TIcRecharge> {

	public TIcRecharge findByFRechargeNo(String fRechargeNo);
}
