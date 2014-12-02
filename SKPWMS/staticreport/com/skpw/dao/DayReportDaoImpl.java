package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.DayReport;

@Repository("dayReportDao")
public class DayReportDaoImpl implements DayReportDao {

	public List statisticDayReport1(Integer ttcid, String time) {

		List<DayReport> list = new ArrayList<DayReport>();
		Connection conn = DBUtil.getConnection();
		
		String sql = "SELECT FControlerID,FDate,fhour,"
				+ " MAX(CASE FPOLLUTANTID WHEN '65' THEN FAvgCons ELSE 0 END) AS cod,"
				+ " MAX(CASE FPOLLUTANTID WHEN '89' THEN FAvgCons ELSE 0 END) AS nh3, "
				+ " MAX(CASE FPOLLUTANTID WHEN '65' THEN FTotalFlow ELSE 0 END) AS codpfl, "
				+ " MAX(CASE FPOLLUTANTID WHEN '89' THEN FTotalFlow ELSE 0 END) AS nh3pfl "
				+ " FROM ("
				+ " SELECT T1.FHOUR,FCONTROLERID ,FDate,FPOLLUTANTID,FAvgCons,FTotalFlow FROM  DM_HOUR  T1 "
				+ " LEFT JOIN  ( "
				+ " SELECT FHOUR,FCONTROLERID ,FDate,FPOLLUTANTID,FAvgCons,FTotalFlow"
				+ " FROM  T_RT_SewageMonitorPollHour WHERE   FDate=?  AND  FCONTROLERID=?) T2"
				+ " ON T1.FHOUR =T2.FHOUR)  T3  "
				+ " GROUP BY FHOUR,FCONTROLERID, FDate ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, time);
			ps.setInt(2, ttcid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DayReport dayReport = new DayReport();
				dayReport.setTtcid(rs.getInt(1));
				dayReport.setDate(rs.getString(2));
				dayReport.setHour(rs.getInt(3));
				dayReport.setCod(rs.getFloat(4));
				dayReport.setNh3(rs.getFloat(5));
				dayReport.setCodpfl(rs.getFloat(6));
				dayReport.setNh3pfl(rs.getFloat(7));
				list.add(dayReport);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}
	
	
	
}
