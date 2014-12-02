package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.MonthReport;

@Repository("monthReportDao")
public class MonthReportDaoImpl implements MonthReportDao {

	public List statisticMonthReport(Integer ttcid, Integer year, Integer month) {

		List<MonthReport> list = new ArrayList<MonthReport>();
		Connection conn = DBUtil.getConnection();
		String sql = "select t3.FDate,t3.FControlerID,"
				+ " MAX(CASE FPOLLUTANTID WHEN '65' THEN t3.FAvgCons ELSE 0 END) AS cod, "
				+ " MAX(CASE FPOLLUTANTID WHEN '89' THEN t3.FAvgCons ELSE 0 END) AS nh3, "
				+ " MAX(CASE FPOLLUTANTID WHEN '65' THEN t3.FTotalFlow ELSE 0 END) AS codf, "
				+ " MAX(CASE FPOLLUTANTID WHEN '89' THEN t3.FTotalFlow ELSE 0 END) AS nh3f "
				+ " from ( "
				+ " select   t1.FDate,t2.FControlerID,FPollutantID,FAvgCons ,FTotalFlow"
				+ " from "
				+ " (select b.FMonthDesc, c.FDate  from DM_Year a, DM_Month b, DM_Date c  "
				+ " where a.FYearID=c.FYearID and b.FMonthID=c.FMonthID and a.FYear= ?  and b.FMonth=?) t1 "
				+ " left join "
				+ " (select fcontrolerid ,FDate,FPollutantID,FAvgCons,FTotalFlow "
				+ " from  T_RT_SewageMonitorPollDay  where  FControlerID=? )  t2 on t1.FDate=t2.FDate  ) t3 "
				+ " GROUP BY  FControlerID,FDate ";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, month);
			ps.setInt(3, ttcid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				MonthReport monthReport = new MonthReport();
				monthReport.setDate(rs.getString(1));
				monthReport.setTtcid(rs.getInt(2));
				monthReport.setCod(rs.getFloat(3));
				monthReport.setNh3(rs.getFloat(4));
				monthReport.setCodf(rs.getFloat(5));
				monthReport.setNh3f(rs.getFloat(6));
				list.add(monthReport);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		MonthReportDao monthReportDao = new MonthReportDaoImpl();

		List list = monthReportDao.statisticMonthReport(33, 2014, 10);

		System.out.println(list.size());
	}

}
