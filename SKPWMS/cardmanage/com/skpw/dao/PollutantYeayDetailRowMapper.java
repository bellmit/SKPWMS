package com.skpw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.skpw.bean.PollutantYearDetail;

public class PollutantYeayDetailRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		PollutantYearDetail pollutantYearDetail = new PollutantYearDetail(
				rs.getString("FPollutantName"), rs.getString("FYearID"),
				rs.getFloat("FDischarge"), rs.getFloat("FUpperLimit"));

		return pollutantYearDetail;
	}

}
