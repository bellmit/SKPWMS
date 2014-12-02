package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.ReportData;

@Repository("monthDataDao")
public class MonthDataDaoImpl implements MonthDataDao {

	@Override
	public List<ReportData> findListByPage(int cId, String time) {

		List<ReportData> list = new ArrayList<ReportData>();

		// 获取年
		int year = Integer.parseInt(time);

		String sql = "select FMonthDesc,acod,anh3,coddischarge,nh3discharge,totalflow"
				+ " from (select t1.FMonthDesc,t1.FMonthID,"
				+ " sum(t2.cod) acod,sum(t2.nh3) anh3,sum(t2.FTotalFlow) totalflow "
				+ " from ("
				+ " select b.FMonthDesc,b.FMonthID,c.FDate from DM_Year a, DM_Month b, DM_Date c"
				+ " where a.FYearID=c.FYearID and b.FMonthID=c.FMonthID and a.FYear= ? )  t1"
				+ " left  join ( "
				+ " select a.fdate , "
				+ " MAX(CASE a.fpollutantid WHEN '65' THEN a.FTotalFlow ELSE 0 END)  cod ,"
				+ " MAX(CASE a.fpollutantid WHEN '89' THEN a.FTotalFlow ELSE 0 END)  nh3,"
				+ " b.FTotalFlow"
				+ " from T_RT_SewageMonitorPollDay a"
				+ " left join T_RT_SewageMonitorDay b"
				+ " on a.FControlerID=b.FControlerID and a.FDate=b.FDate "
				+ " where a.FControlerID=? "
				+ " group by a.FDate,b.FTotalFlow "
				+ " ) t2 "
				+ " on t1.FDate=t2.FDate"
				+ " group by t1.FMonthDesc,t1.FMonthID)  t3"
				+ " left join ("
				+ "select  FMonthID,FControlerID,"
				+ " SUM(case FPollutantID when '65' then FDischarge else 0 end) as coddischarge,"
				+ " SUM(case FPollutantID when '89' then FDischarge else 0 end) as nh3discharge"
				+ " from T_RT_SewageMonthAdjust"
				+ " where   FControlerID=? "
				+ " group by FMonthID,FControlerID"
				+ " ) t4"
				+ " on t4.FMonthID=t3.FMonthID";

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, cId);
			ps.setInt(3, cId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportData rtd = new ReportData();
				rtd.setTime(rs.getString("FMonthDesc"));
				
				rtd.setCodF((rs.getDouble("acod") > 0) ? rs.getDouble("acod")
						+ "" : "-");
				rtd.setNh3F((rs.getDouble("anh3") > 0) ? rs.getDouble("anh3")
						+ "" : "-");
				rtd.setCodA((rs.getDouble("coddischarge") > 0) ? rs.getDouble("coddischarge")+ "" : "-");
				rtd.setNh3A((rs.getDouble("nh3discharge") > 0) ? rs.getDouble("nh3discharge")+ "" : "-");
				rtd.setFlow((rs.getDouble("totalflow") > 0) ? rs
						.getDouble("totalflow") + "" : "-");
				list.add(rtd);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<ReportData> findMMAS(int cId, String time) {

		List<ReportData> list = new ArrayList<ReportData>();

		// 获取年
		int year = Integer.parseInt(time);

		String sql = "select "
				+ " MAX(t5.acod) acodMax, MAX(t5.anh3) anh3Max, MAX(t5.coddischarge) coddischargeMax, MAX(t5.nh3discharge) nh3dischargeMax, MAX(t5.totalflow) totalflowMax, "
				+ "	MIN(t5.acod) acodMin, MIN(t5.anh3) anh3MIN, MIN(t5.coddischarge) coddischargeMIN, MIN(t5.nh3discharge) nh3dischargeMIN, MIN(t5.totalflow) totalflowMIN,"
				+ "	AVG(t5.acod) acodAvg, AVG(t5.anh3) anh3AVG, AVG(t5.coddischarge) coddischargeAVG, AVG(t5.nh3discharge) nh3dischargeAVG,  AVG(t5.totalflow) totalflowAVG, "
				+ "	SUM(t5.acod) acodSum, SUM(t5.anh3) anh3SUM, SUM(t5.coddischarge) coddischargeSUM, SUM(t5.nh3discharge) nh3dischargeSUM,  SUM(t5.totalflow) totalflowSUM "
				+ " from " 
				+ " (select FMonthDesc,acod,anh3,coddischarge,nh3discharge,totalflow "
				+ " 	from (select t1.FMonthDesc,t1.FMonthID,"
				+ " sum(t2.cod) acod,sum(t2.nh3) anh3,sum(t2.FTotalFlow) totalflow "
				+ " from ("
				+ " select b.FMonthDesc,b.FMonthID,c.FDate from DM_Year a, DM_Month b, DM_Date c"
				+ " where a.FYearID=c.FYearID and b.FMonthID=c.FMonthID and a.FYear= ? )  t1"
				+ " left  join ( "
				+ " select a.fdate , "
				+ " MAX(CASE a.fpollutantid WHEN '65' THEN a.FTotalFlow ELSE 0 END)  cod ,"
				+ " MAX(CASE a.fpollutantid WHEN '89' THEN a.FTotalFlow ELSE 0 END)  nh3,"
				+ " b.FTotalFlow"
				+ " from T_RT_SewageMonitorPollDay a"
				+ " left join T_RT_SewageMonitorDay b"
				+ " on a.FControlerID=b.FControlerID and a.FDate=b.FDate "
				+ " where a.FControlerID=? "
				+ " group by a.FDate,b.FTotalFlow "
				+ " ) t2 "
				+ " on t1.FDate=t2.FDate"
				+ " group by t1.FMonthDesc,t1.FMonthID)  t3"
				+ " left join ("
				+ "select  FMonthID,FControlerID,"
				+ " SUM(case FPollutantID when '65' then FDischarge else 0 end) as coddischarge,"
				+ " SUM(case FPollutantID when '89' then FDischarge else 0 end) as nh3discharge"
				+ " from T_RT_SewageMonthAdjust"
				+ " where   FControlerID=? "
				+ " group by FMonthID,FControlerID"
				+ " ) t4"
				+ " on t4.FMonthID=t3.FMonthID ) t5"; 

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, year);
			ps.setInt(2, cId);
			ps.setInt(3, cId);
			rs = ps.executeQuery();
			ReportData max = new ReportData();// 最大值
			ReportData min = new ReportData();// 最小值
			ReportData avg = new ReportData();// 平均值
			ReportData sum = new ReportData();// 总和
			max.setTime("最大值");
			min.setTime("最小值");
			avg.setTime("平均值");
			sum.setTime("总量");

			if (rs.next()) {
				// 最大值
				max.setCodF(rs.getDouble("acodMax") + "");
				max.setNh3F(rs.getDouble("anh3Max") + "");
				max.setCodA(rs.getDouble("coddischargeMax") + "");
				max.setNh3A(rs.getDouble("nh3dischargeMax") + "");
				max.setFlow(rs.getDouble("totalflowMax") + "");

				// 最小值
				min.setCodF(rs.getDouble("acodMin") + "");
				min.setNh3F(rs.getDouble("anh3Min") + "");
				min.setCodA(rs.getDouble("coddischargeMin") + "");
				min.setNh3A(rs.getDouble("nh3dischargeMin") + "");
				min.setFlow(rs.getDouble("totalflowMin") + "");

				// 平均值				
				avg.setCodF(new DecimalFormat("######0.00").format(rs.getDouble("acodAvg")));
				avg.setNh3F(new DecimalFormat("######0.00").format(rs.getDouble("anh3Avg")));
				avg.setCodA(new DecimalFormat("######0.00").format(rs.getDouble("coddischargeAvg")));
				avg.setNh3A(new DecimalFormat("######0.00").format(rs.getDouble("nh3dischargeAvg")));
				avg.setFlow(new DecimalFormat("######0.00").format(rs.getDouble("totalflowAvg")));
				
				// 总量
				sum.setCodF(rs.getDouble("acodSum") + "");
				sum.setNh3F(rs.getDouble("anh3Sum") + "");
				sum.setCodA(rs.getDouble("coddischargeSum") + "");
				sum.setNh3A(rs.getDouble("nh3dischargeSum") + "");
				sum.setFlow(rs.getDouble("totalflowSum") + "");

			} else {
				// 最大值
				max.setCodF("-");
				max.setNh3F("-");
				max.setCodA("-");
				max.setNh3A("-");
				max.setFlow("-");

				// 最小值
				min.setCodF("-");
				min.setNh3F("-");
				min.setCodA("-");
				min.setNh3A("-");
				min.setFlow("-");

				// 平均值
				avg.setCodF("-");
				avg.setNh3F("-");
				avg.setCodA("-");
				avg.setNh3A("-");
				avg.setFlow("-");

				// 总量
				sum.setCodF("-");
				sum.setNh3F("-");
				sum.setCodA("-");
				sum.setNh3A("-");
				sum.setFlow("-");
			}
			list.add(max);
			list.add(min);
			list.add(avg);
			list.add(sum);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, ps, conn);
		}
		return list;
	}

	public static void main(String[] args) {

		int ctrlid = 36;
		List list = new MonthDataDaoImpl().findMMAS(ctrlid, "2014");

		System.out.println(list.size());

	}

}
