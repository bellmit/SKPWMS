package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skpw.bean.CardInfo;

public interface CardInfoRepository extends JpaRepository<CardInfo, String>,JpaSpecificationExecutor<CardInfo>{
	
	@Query("from CardInfo as t where t.enterprise.fenterId=:enterId")
	public List<CardInfo> findCardInfoByEnterId(@Param("enterId")String enterId);
	
	public CardInfo findByCardid(String cardid);
	
	//根据card物理卡号查找cardInfo
	@Query(nativeQuery=true,
			value="SELECT cif.* FROM T_IC_CardInfo cif JOIN T_IC_Card c ON cif.FCardID=c.FCardID WHERE c.FCardPhyNo=?1")
	public CardInfo findByCardPhyNo(String fCardPhyNo);
}
