package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasWaterDisRule;

public interface TBasWaterDisRuleRepository extends JpaRepository<TBasWaterDisRule, String>,JpaSpecificationExecutor<TBasWaterDisRule> {

}
