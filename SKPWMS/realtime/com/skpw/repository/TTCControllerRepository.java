package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TTcControler;

public interface TTCControllerRepository extends  JpaRepository<TTcControler, String> {

	@Query("from TTcControler where fenterId = ?1")
	public List<TTcControler> findByFenterId(String id);
	
	@Query("select t.fid,t.fctrlerName from TTcControler t where fenterId = ?1")
	public List<TTcControler> findByFenterId1(String id);
	
	@Query("from TTcControler t  where  t.fid=?1")
	public TTcControler findcrtlByFid(int fid);
	
	@Modifying
	@Transactional(readOnly=false)
	@Query("delete from TTcControler t where t.fenterId = :fenterId")
	public void deleteByEnterId(@Param("fenterId")String fenterId);
}
