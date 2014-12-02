package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skpw.bean.TBasVillage;

public interface TBasVillageRepository  extends JpaRepository<TBasVillage, String>,JpaSpecificationExecutor<TBasVillage> {

}
