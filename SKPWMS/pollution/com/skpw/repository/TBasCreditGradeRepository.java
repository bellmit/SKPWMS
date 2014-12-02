package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasCreditGrade;

public interface TBasCreditGradeRepository extends JpaRepository<TBasCreditGrade, String>,JpaSpecificationExecutor<TBasCreditGrade>  {

}
