package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.YearReport;

@Repository("yearReportDao")
public class YearReportDaoImpl implements YearReportDao {

	public List statisticYearReport(Integer ttcid, String time) {

		List<YearReport> list = new ArrayList<YearReport>();

		Connection conn = DBUtil.getConnection();

		String sql = " select FMonthDesc,acodF,anh3F,coddischarge,nh3discharge "
				+ " from "
				+ " ( "
				+ " select "
				+ " t1.FMonthDesc,t1.FMonthID,"
				+ " SUM(t2.codF) acodF,"
				+ " SUM(t2.nh3F) anh3F "
				+ " from"
				+ " ( "
				+ " select b.FMonthDesc,b.FMonthID, c.FDate "

				+ " from DM_Year a, DM_Month b, DM_Date c "

				+ " where a.FYearID=c.FYearID and b.FMonthID=c.FMonthID and a.FYear= ? "

				+ " )  t1 "

				+ " left join "

				+ " ( "

				+ " select a.FControlerID, a.fdate , "

				+ " MAX(CASE a.fpollutantid WHEN '65' THEN a.FTotalFlow ELSE 0 END)  codF ,"

				+ " MAX(CASE a.fpollutantid WHEN '89' THEN a.FTotalFlow ELSE 0 END)  nh3F "

				+ " from T_RT_SewageMonitorPollDay a where a.FControlerID=? "

				+ " group by FControlerID,a.fdate ) t2 "

				+ " on  t1.FDate=t2.FDate "

				+ " group by t1.FMonthDesc,t1.FMonthID "

				+ " ) t3 "

				+ " left join ( "

				+ " select  FMonthID,FControlerID, "
				+ " SUM(case FPollutantID when '65' then FDischarge else 0 end) as coddischarge,"
				+ " SUM(case FPollutantID when '89' then FDischarge else 0 end) as nh3discharge "
				+ " from T_RT_SewageMonthAdjust"
				+ " where   FControlerID=? "
				+ " group by FMonthID,FControlerID "
				+ " ) t4 "
				+ " on t4.FMonthID=t3.FMonthID ";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, time);

			ps.setInt(2, ttcid);
			ps.setInt(3, ttcid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				YearReport yearReport = new YearReport();
				yearReport.setCodf(rs.getFloat(2) > 0 ? rs.getFloat(2) : 0);
				yearReport.setNh3f(rs.getFloat(3) > 0 ? rs.getFloat(3) : 0);
				yearReport.setCoda(rs.getFloat(4) > 0 ? rs.getFloat(4) : 0);
				yearReport.setNh3a(rs.getFloat(5) > 0 ? rs.getFloat(5) : 0);
				list.add(yearReport);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String[] args) {

		YearReportDao yearReportDao = new YearReportDaoImpl();

		List list = yearReportDao.statisticYearReport(33, "2014");
		System.out.println(list.size());
	}
}
