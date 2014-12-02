package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.EwsWarningModel;

/**
 * @author hjy
 *  预警模式
 */
public interface EwsWarningModelRepository  extends  JpaRepository<EwsWarningModel, String> {
	
	@Query("from EwsWarningModel a  where a.warningSetup.id=?1")
	public List<EwsWarningModel> showEwsWarningModelByWarnid(String warnid);
}
