package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TBasEnterPoll;

public interface TBasEnterPollRepository extends JpaRepository<TBasEnterPoll, String>,JpaSpecificationExecutor<TBasEnterPoll> {

	@Query("from TBasEnterPoll where fenterId = ?1 and bisChoice = 'true'")
	public List<TBasEnterPoll> findAllForList(String id);
	
	@Modifying
	@Transactional(readOnly = false)
	@Query("delete from TBasEnterPoll where fenterId=:enterId")
	public void deleteByEnterId(@Param("enterId")String enterId);
}
