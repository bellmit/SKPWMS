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

@Repository("dayDataDao")
public class DayDataDaoImpl implements DayDataDao{

	@Override
	public List<ReportData> findListByPage(int cId, String time) {

		List<ReportData> list = new ArrayList<ReportData>();

		//获取年和月
		String t[] = time.split("-");
		int year = Integer.parseInt(t[0]);
		int month = Integer.parseInt(t[1]);
		
		String sql = "with t1 as( "+
				"select c.FDate, c.FDateDesc from DM_Year a, DM_Month b, DM_Date c "+
				"where a.FYearID=c.FYearID "+
				"and b.FMonthID=c.FMonthID "+
				"and a.FYear= " + year + " "+
				"and b.FMonth= " + month + " ), "+
				"t2 as(select a.FDate,   "+
				"		SUM(case b.FPollSimName when 'cod' then a.FAvgCons else 0 end) as codC, "+
				"		SUM(case b.FPollSimName when 'cod' then a.FTotalFlow else 0 end) as codF, "+
				"		SUM(case b.FPollSimName when 'nh3' then a.FAvgCons else 0 end) as nh3C, "+
				"		SUM(case b.FPollSimName when 'nh3' then a.FTotalFlow else 0 end) as nh3F, "+
				"		c.FTotalFlow "+
				"from T_RT_SewageMonitorPollDay a "+
				"inner join T_BAS_Pollutant b on a.FPollutantID=b.FID "+
				"inner join T_RT_SewageMonitorDay c on a.FControlerID=c.FControlerID and a.FDate=c.FDate "+
				"right join t1 on a.FDate=t1.FDate "+
				"where a.FControlerID= " + cId + " "+
				"group by a.FDate,c.FTotalFlow) "+
				"select t1.FDateDesc FDateDesc, AVG(t2.codC) codC,AVG(t2.codF) codF,AVG(t2.nh3C) nh3C,AVG(t2.nh3F) nh3F,AVG(t2.FTotalFlow) FTotalFlow "+
				"from t1 "+
				"left join t2 on t1.FDate=t2.FDate "+
				"group by FDateDesc ";
							
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				ReportData rtd = new ReportData();
				rtd.setTime(rs.getString("FDateDesc"));
				rtd.setCodC((rs.getDouble("codC")>0)?rs.getDouble("codC")+"":"-");
				rtd.setCodF((rs.getDouble("codF")>0)?rs.getDouble("codF")+"":"-");
				rtd.setNh3C((rs.getDouble("nh3C")>0)?rs.getDouble("nh3C")+"":"-");
				rtd.setNh3F((rs.getDouble("nh3F")>0)?rs.getDouble("nh3F")+"":"-");
				rtd.setFlow((rs.getDouble("FTotalFlow")>0)?rs.getDouble("FTotalFlow")+"":"-");
				list.add(rtd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}

	public List<ReportData> findMMAS(int cId, String time) {
		
		List<ReportData> list = new ArrayList<ReportData>();
		
		//获取年和月
		String t[] = time.split("-");
		int year = Integer.parseInt(t[0]);
		int month = Integer.parseInt(t[1]);
		
		String sql = "with t1 as( "+
					"select c.FDate, c.FDateDesc from DM_Year a, DM_Month b, DM_Date c "+
					"where a.FYearID=c.FYearID "+
					"and b.FMonthID=c.FMonthID "+
					"and a.FYear=" + year + "  "+
					"and b.FMonth= " + month + " ), "+
					"t2 as( "+
					"select t1.FDateDesc, "+
					"		SUM(case b.FPollSimName when 'cod' then a.FAvgCons else 0 end) as codC, "+
					"		SUM(case b.FPollSimName when 'cod' then a.FTotalFlow else 0 end) as codF, "+
					"		SUM(case b.FPollSimName when 'nd' then a.FAvgCons else 0 end) as nh3C, "+
					"		SUM(case b.FPollSimName when 'nd' then a.FTotalFlow else 0 end) as nh3F, "+
					"		c.FTotalFlow "+
					"from T_RT_SewageMonitorPollDay a "+
					"inner join T_BAS_Pollutant b on a.FPollutantID=b.FID "+
					"inner join T_RT_SewageMonitorDay c on a.FControlerID=c.FControlerID and a.FDate=c.FDate "+
					"right join t1 on a.FDate=t1.FDate "+
					"where a.FControlerID= " + cId + " " +
					"group by a.FControlerID,t1.FDateDesc,c.FTotalFlow) "+
					"select MAX(t2.codC) codCMax, MAX(t2.codF) codFMax, MAX(t2.nh3C) nh3CMax, MAX(t2.nh3F) nh3FMax, MAX(t2.FTotalFlow) ftfMax, "+
					"	   MIN(t2.codC) codCMin, MIN(t2.codF) codFMIN, MIN(t2.nh3C) nh3CMIN, MIN(t2.nh3F) nh3FMIN, MIN(t2.FTotalFlow) ftfMIN, "+
					"	   AVG(t2.codC) codCAvg, AVG(t2.codF) codFAVG, AVG(t2.nh3C) nh3CAVG, AVG(t2.nh3F) nh3FAVG, AVG(t2.FTotalFlow) ftfAVG, "+
					"	   SUM(t2.codC) codCSum, SUM(t2.codF) codFSUM, SUM(t2.nh3C) nh3CSUM, SUM(t2.nh3F) nh3FSUM, SUM(t2.FTotalFlow) ftfSUM "+ 
					"from t2 ";
		

		
		
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			ReportData max = new ReportData();//最大值
			ReportData min = new ReportData();//最小值
			ReportData avg = new ReportData();//平均值
			ReportData sum = new ReportData();//总和
			max.setTime("最大值");
			min.setTime("最小值");
			avg.setTime("平均值");
			sum.setTime("总量");
			
			while(rs.next()){				
				//最大值
				max.setCodC(rs.getDouble("codCMax")+"");
				max.setCodF(rs.getDouble("codFMax")+"");
				max.setNh3C(rs.getDouble("nh3CMax")+"");
				max.setNh3F(rs.getDouble("nh3FMax")+"");
				max.setFlow(rs.getDouble("ftfMax")+"");
				
				//最小值
				min.setCodC(rs.getDouble("codCMin")+"");
				min.setCodF(rs.getDouble("codFMin")+"");
				min.setNh3C(rs.getDouble("nh3CMin")+"");
				min.setNh3F(rs.getDouble("nh3FMin")+"");
				min.setFlow(rs.getDouble("ftfMIN")+"");
				
				//平均值
				
				avg.setCodC(new DecimalFormat("######0.00").format(rs.getDouble("codCAvg")));
				avg.setCodF(new DecimalFormat("######0.00").format(rs.getDouble("codFAvg")));
				avg.setNh3C(new DecimalFormat("######0.00").format(rs.getDouble("nh3CAvg")));
				avg.setNh3F(new DecimalFormat("######0.00").format(rs.getDouble("nh3FAvg")));
				avg.setFlow(new DecimalFormat("######0.00").format(rs.getDouble("ftfAVG")));
				
//				avg.setCodC(rs.getDouble("codCAvg")+"");
//				avg.setCodF(rs.getDouble("codFAvg")+"");
//				avg.setNh3C(rs.getDouble("nh3CAvg")+"");
//				avg.setNh3F(rs.getDouble("nh3FAvg")+"");
//				avg.setFlow(rs.getDouble("ftfAVG")+"");
				
				//总量
				sum.setCodC("-");
				sum.setCodF(rs.getDouble("codFSum")+"");
				sum.setNh3C("-");
				sum.setNh3F(rs.getDouble("nh3FSum")+"");
				sum.setFlow(rs.getDouble("ftfSUM")+"");
				
			}
			list.add(max);
			list.add(min);
			list.add(avg);
			list.add(sum);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}

	
	
}
