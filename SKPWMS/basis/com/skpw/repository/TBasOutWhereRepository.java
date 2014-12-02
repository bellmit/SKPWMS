package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.skpw.bean.TBasOutWhere;

public interface TBasOutWhereRepository extends JpaRepository<TBasOutWhere, String>,JpaSpecificationExecutor<TBasOutWhere> {

}
