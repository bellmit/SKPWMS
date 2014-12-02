package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skpw.bean.WarningItem;

public interface WarningItemRepository extends  JpaRepository<WarningItem, String> {

}
