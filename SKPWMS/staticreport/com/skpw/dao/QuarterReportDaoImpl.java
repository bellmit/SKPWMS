package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.QuarterReport;

@Repository("quarterReportDao")
public class QuarterReportDaoImpl implements QuarterReportDao {

	public List statisticQuarterReport(Integer ttcid, String time) {

		List<QuarterReport> list = new ArrayList<QuarterReport>();

		Connection conn = DBUtil.getConnection();
		String sql = "select "
				+ " q.FQuarterID ,"
				+ " SUM(codF) codF,"
				+ " SUM(nh3F) nh3F,"
				+ " sum(t4.coddischarge) coddischarge,"
				+ " sum(t4.nh3discharge) nh3discharge "
				+ " from DM_Quarter q,"
				+ " ( "
				+ " select t2.FMonthDesc,t2.FQuarterID,t2.FMonthID,"
				+ " SUM(codf) codF,"
				+ " SUM(nh3f) nh3F "
				+ " from "
				+ " ( "

				+ " select b.FMonthDesc,b.FMonthID, b.FQuarterID,c.FDate  from DM_Year a, DM_Month b, DM_Date c "

				+ " where a.FYearID=c.FYearID  and b.FMonthID=c.FMonthID  and a.FYear= ?  ) t2  "

				+ " left join( "

				+ " SELECT t1.FDate ,"

				+ " MAX(CASE t1.fpollutantid WHEN '65' THEN t1.FTotalFlow ELSE 0 END)  codf,"

				+ " MAX(CASE t1.fpollutantid WHEN '89' THEN t1.FTotalFlow ELSE 0 END)  nh3f "

				+ " FROM T_RT_SewageMonitorPollDay  t1  WHERE t1.FControlerID=? "

				+ " GROUP BY t1.FDate "

				+ " ) t1"

				+ " on t2.FDate=t1.FDate "

				+ " group by FMonthDesc,FQuarterID ,FMonthID "

				+ " )  t3 "

				+ " left join"
				+ "( "
				+ " select  FMonthID,FControlerID,"
				+ " SUM(case FPollutantID when '65' then FDischarge else 0 end) as coddischarge,"
				+ " SUM(case FPollutantID when '89' then FDischarge else 0 end) as nh3discharge "
				+ " from T_RT_SewageMonthAdjust " + " where   FControlerID=? "
				+ " group by FMonthID,FControlerID" + " ) t4 "
				+ " on t4.FMonthID=t3.FMonthID "
				+ " where   t3.FQuarterID=q.FQuarterID "
				+ " group by  q.FQuarterID ";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, time);

			ps.setInt(2, ttcid);
			ps.setInt(3, ttcid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				QuarterReport quarterReport = new QuarterReport();
				quarterReport.setCodf(rs.getFloat(2) > 0 ? rs.getFloat(2) : 0);
				quarterReport.setNh3f(rs.getFloat(3) > 0 ? rs.getFloat(3) : 0);
				quarterReport.setCoda(rs.getFloat(4) > 0 ? rs.getFloat(4) : 0);
				quarterReport.setNh3a(rs.getFloat(5) > 0 ? rs.getFloat(5) : 0);
				list.add(quarterReport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {

		QuarterReportDao quarterReportDao = new QuarterReportDaoImpl();

		List list = quarterReportDao.statisticQuarterReport(33, "2013");

		System.out.println(list.size());
	}

}
