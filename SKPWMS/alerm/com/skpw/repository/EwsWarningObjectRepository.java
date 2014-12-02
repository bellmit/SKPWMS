package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.EwsWarningObject;

/**
 * @author hjy
 * 预警对象
 */
public interface EwsWarningObjectRepository  extends  JpaRepository<EwsWarningObject, String> {
	@Query("from EwsWarningObject a  where a.warningSetup.id=?1")
	public List<EwsWarningObject> showEwsWarningObjectByWarnid(String warnid);
}
