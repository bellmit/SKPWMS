package com.skpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.ReportData;

@Repository("hourDataDao")
public class HourDataDaoImpl implements HourDataDao {

	@Override
	public List<ReportData> findListByPage(int cId, String time) {

		List<ReportData> list = new ArrayList<ReportData>();

		String sql = "with t1 as "
				+ "(select a.FHour,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FAvgCons else 0 end) as codC, "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FTotalFlow else 0 end) as codF, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FAvgCons else 0 end) as nh3C, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FTotalFlow else 0 end) as nh3F "
				+ "	 from T_RT_SewageMonitorPollHour a "
				+ "		inner join T_BAS_Pollutant b on a.FPollutantID = b.FID "
				+ "	 where FDate = '"
				+ time
				+ "' and FControlerID = "
				+ cId
				+ "	 group by a.FHour), "
				+ "t2 as  "
				+ "(select FHour,FTotalFlow from T_RT_SewageMonitorHour where FDate = '"
				+ time
				+ "' and FControlerID = "
				+ cId
				+ ") "
				+ "select a.FDesc, t1.codC, t1.codF, t1.nh3C, t1.nh3F, t2.FTotalFlow  "
				+ "from DM_Hour a " + "	left join t1 On a.FHour = t1.FHour "
				+ "	left join t2 On a.FHour = t2.FHour " + "order by a.FHour ";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ReportData rtd = new ReportData();
				rtd.setTime(rs.getString("FDesc"));
				rtd.setCodC((rs.getDouble("codC") > 0) ? rs.getDouble("codC")
						+ "" : "-");
				rtd.setCodF((rs.getDouble("codF") > 0) ? rs.getDouble("codF")
						+ "" : "-");
				rtd.setNh3C((rs.getDouble("nh3C") > 0) ? rs.getDouble("nh3C")
						+ "" : "-");
				rtd.setNh3F((rs.getDouble("nh3F") > 0) ? rs.getDouble("nh3F")
						+ "" : "-");
				rtd.setFlow((rs.getDouble("FTotalFlow") > 0) ? rs
						.getDouble("FTotalFlow") + "" : "-");
				list.add(rtd);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}

	@Override
	public List<ReportData> findMMAS(int cId, String time) {

		List<ReportData> list = new ArrayList<ReportData>();
		String sql = "with t1 as ( "
				+ "select a.FHour,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FAvgCons else 0 end) as codC, "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FTotalFlow else 0 end) as codF, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FAvgCons else 0 end) as nh3C, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FTotalFlow else 0 end) as nh3F, "
				+ "		c.FTotalFlow	 "
				+ "	 from T_RT_SewageMonitorPollHour a  "
				+ "		inner join T_BAS_Pollutant b on a.FPollutantID = b.FID  "
				+ "		inner join T_RT_SewageMonitorHour c on a.FControlerID=c.FControlerID and a.FDate=c.FDate and a.FHour=c.FHour "
				+ "	 where a.FDate = '"
				+ time
				+ "' and a.FControlerID = "
				+ cId
				+ " "
				+ "	 group by a.FHour, c.FTotalFlow)  "
				+ "select MAX(t1.codC) codCMax, MAX(t1.codF) codFMax, MAX(t1.nh3C) nh3CMax, MAX(t1.nh3F) nh3FMax, MAX(t1.FTotalFlow) ftfMax, "
				+ "	   MIN(t1.codC) codCMin, MIN(t1.codF) codFMIN, MIN(t1.nh3C) nh3CMIN, MIN(t1.nh3F) nh3FMIN, MIN(t1.FTotalFlow) ftfMIN, "
				+ "	   AVG(t1.codC) codCAvg, AVG(t1.codF) codFAVG, AVG(t1.nh3C) nh3CAVG, AVG(t1.nh3F) nh3FAVG, AVG(t1.FTotalFlow) ftfAVG, "
				+ "	   SUM(t1.codC) codCSum, SUM(t1.codF) codFSUM, SUM(t1.nh3C) nh3CSUM, SUM(t1.nh3F) nh3FSUM, SUM(t1.FTotalFlow) ftfSUM "
				+ "from t1 ";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
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
				max.setCodC(rs.getDouble("codCMax") + "");
				max.setCodF(rs.getDouble("codFMax") + "");
				max.setNh3C(rs.getDouble("nh3CMax") + "");
				max.setNh3F(rs.getDouble("nh3FMax") + "");
				max.setFlow(rs.getDouble("ftfMax") + "");

				// 最小值
				min.setCodC(rs.getDouble("codCMin") + "");
				min.setCodF(rs.getDouble("codFMin") + "");
				min.setNh3C(rs.getDouble("nh3CMin") + "");
				min.setNh3F(rs.getDouble("nh3FMin") + "");
				min.setFlow(rs.getDouble("ftfMIN") + "");

				// 平均值
				
				avg.setCodC(new DecimalFormat("######0.00").format(rs.getDouble("codCAvg")));
				avg.setCodF(new DecimalFormat("######0.00").format(rs.getDouble("codFAvg")));
				avg.setNh3C(new DecimalFormat("######0.00").format(rs.getDouble("nh3CAvg")));
				avg.setNh3F(new DecimalFormat("######0.00").format(rs.getDouble("nh3FAvg")));
				avg.setFlow(new DecimalFormat("######0.00").format(rs.getDouble("ftfAVG")));
				
//				avg.setCodC(rs.getDouble("codCAvg") + "");
//				avg.setCodF(rs.getDouble("codFAvg") + "");
//				avg.setNh3C(rs.getDouble("nh3CAvg") + "");
//				avg.setNh3F(rs.getDouble("nh3FAvg") + "");
//				avg.setFlow(rs.getDouble("ftfAVG") + "");

				// 总量
				sum.setCodC("-");
				sum.setCodF(rs.getDouble("codFSum") + "");
				sum.setNh3C("-");
				sum.setNh3F(rs.getDouble("nh3FSum") + "");
				sum.setFlow(rs.getDouble("ftfSUM") + "");

			} else {
				// 最大值
				max.setCodC("-");
				max.setCodF("-");
				max.setNh3C("-");
				max.setNh3F("-");
				max.setFlow("-");

				// 最小值
				min.setCodC("-");
				min.setCodF("-");
				min.setNh3C("-");
				min.setNh3F("-");
				min.setFlow("-");

				// 平均值
				avg.setCodC("-");
				avg.setCodF("-");
				avg.setNh3C("-");
				avg.setNh3F("-");
				avg.setFlow("-");

				// 总量
				sum.setCodC("-");
				sum.setCodF("-");
				sum.setNh3C("-");
				sum.setNh3F("-");
				sum.setFlow("-");
			}
			list.add(max);
			list.add(min);
			list.add(avg);
			list.add(sum);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}
	
	
	
}
