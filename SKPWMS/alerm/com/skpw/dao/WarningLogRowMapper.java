package com.skpw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.skpw.bean.WarningLog1;

public class WarningLogRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		WarningLog1 warningLog1 = new WarningLog1(
				rs.getString("fwarninglogid"), rs.getString("FEnterName"),
				rs.getString("FctrlerName"), rs.getString("FPollutantName"),
				rs.getString("FWarningItemName"), rs.getFloat("FThreshold"),
				rs.getFloat("FRealValue"), rs.getFloat("FStdValue"),
				rs.getString("FWarningTime"), rs.getString("FStatus"),rs.getString("FContent"));
		return warningLog1;
	}
}
