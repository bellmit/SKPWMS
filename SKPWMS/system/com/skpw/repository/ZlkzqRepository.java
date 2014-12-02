package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasZlkzq;

public interface ZlkzqRepository extends JpaRepository<TBasZlkzq, String>, JpaSpecificationExecutor<TBasZlkzq> {

}
