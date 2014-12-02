package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.skpw.bean.TBasRship;

public interface TBasRshipRepository extends JpaRepository<TBasRship, String>,JpaSpecificationExecutor<TBasRship> {

}
