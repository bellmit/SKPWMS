package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.skpw.bean.TBasPollSourceLevel;

public interface TBasPollSourceLevelRepository extends JpaRepository<TBasPollSourceLevel, String>,JpaSpecificationExecutor<TBasPollSourceLevel> {

}
