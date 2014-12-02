package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.skpw.bean.TBasPollSourceType;

public interface TBasPollSourceTypeRepository extends JpaRepository<TBasPollSourceType, String>,JpaSpecificationExecutor<TBasPollSourceType> {

}
