package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.ICCard;

public interface ICCardRepository  extends   JpaRepository<ICCard, String>{

	@Query("from ICCard t where t.cardPhyNo=:cardPhyNo")
	public List<ICCard> findIcCardByphyNo(@Param("cardPhyNo")String cardPhyNo);
	
	@Transactional
	@Modifying
	@Query("update ICCard t set t.status=1 where t.cardPhyNo=:cardPhyNo")
	public int updateIccStateByphyNo(@Param("cardPhyNo")String cardPhyNo);
}
