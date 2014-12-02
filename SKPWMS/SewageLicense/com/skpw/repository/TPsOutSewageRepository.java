package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsOutSewage;

@Repository
public interface TPsOutSewageRepository extends JpaRepository<TPsOutSewage, String>,JpaSpecificationExecutor<TPsOutSewage> {

}
