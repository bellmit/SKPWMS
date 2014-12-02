package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TIcRechargeYear;

@Repository
public interface TIcRechargeYearRepository extends JpaRepository<TIcRechargeYear, String>,JpaSpecificationExecutor<TIcRechargeYear> {

}
