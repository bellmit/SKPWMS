package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TBasPollutant;

@Repository
public interface TBasPollutantRepository extends JpaRepository<TBasPollutant, String>,JpaSpecificationExecutor<TBasPollutant> {

}
