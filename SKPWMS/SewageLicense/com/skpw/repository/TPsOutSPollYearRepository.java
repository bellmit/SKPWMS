package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsOutSPollYear;

@Repository
public interface TPsOutSPollYearRepository extends JpaRepository<TPsOutSPollYear, String>,JpaSpecificationExecutor<TPsOutSPollYear> {

}
