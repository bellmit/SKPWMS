package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.ReportData;

@Repository("quarterDataDao")
public class QuarterDataDaoImpl implements QuarterDataDao {

	public List<ReportData> statisticQuarter(int cId, String time) {

		String sql = "select q.FQuarterID,SUM(FTotalFlow) FTotalFlow,SUM(codF) codF,SUM(nh3F) nh3F,"
				+ " sum(t4.coddischarge) coddischarge,sum(t4.nh3discharge) nh3discharge"
				+ " from"
				+ " DM_Quarter q,"
				+ " ( "
				+ " select t2.FMonthDesc,t2.FMonthID,t2.FQuarterID,SUM(t1.FTotalFlow) FTotalFlow,SUM(codf) codF,SUM(nh3f) nh3F"
				+ " from ( "
				+ " select b.FMonthDesc,b.FMonthID, b.FQuarterID,c.FDate  from DM_Year a, DM_Month b, DM_Date c"
				+ " where a.FYearID=c.FYearID  and b.FMonthID=c.FMonthID  and a.FYear= ?  ) t2 "
				+ " left join "
				+ "(  "
				+ " SELECT t1.FDate,c.FTotalFlow,"
				+ " MAX(CASE t1.fpollutantid WHEN '65' THEN t1.FTotalFlow ELSE 0 END)  codf,"
				+ " MAX(CASE t1.fpollutantid WHEN '89' THEN t1.FTotalFlow ELSE 0 END)  nh3f"
				+ " FROM T_RT_SewageMonitorPollDay  t1 "
				+ " inner join T_RT_SewageMonitorDay c "
				+ " on t1.FControlerID=c.FControlerID and t1.FDate=c.FDate"
				+ " WHERE t1.FControlerID=? "
				+ " GROUP BY t1.FDate,c.FTotalFlow "
				+ " ) t1 "
				+ " on t2.FDate=t1.FDate"
				+ " group by FMonthDesc,FQuarterID,FMonthID"
				+ " )  t3 "
				+ " left join "

				+ "( "

				+" select  FMonthID,FControlerID,"
				 
				+" SUM(case FPollutantID when '65' then FDischarge else 0 end) as coddischarge," 
				  
				+" SUM(case FPollutantID when '89' then FDischarge else 0 end) as nh3discharge " 
				  
				+" from T_RT_SewageMonthAdjust "
				  
				+" where   FControlerID=? "  
				+" group by FMonthID,FControlerID " 
				+" ) t4  on t4.FMonthID=t3.FMonthID "
				+ " where   t3.FQuarterID=q.FQuarterID "
				+ " group by  q.FQuarterID";

		List<ReportData> list = new ArrayList<ReportData>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, time);
			ps.setInt(2, cId);
			ps.setInt(3, cId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ReportData rtd = new ReportData();
				String mointertime=rs.getString("FQuarterID");
				int f=Integer.valueOf(mointertime.substring(5,6));				
				switch (f) {
				case 1:
					mointertime="第一季度";
					break;
				case 2:
					mointertime="第二季度";
					break;
				case 3:
					mointertime="第三季度";
					break;
				case 4:
					mointertime="第四季度";
					break;

				default:
					break;
				}
				rtd.setTime(mointertime);
				rtd.setCodF((rs.getDouble("codF") > 0) ? rs.getDouble("codF")
						+ "" : "-");
				rtd.setNh3F((rs.getDouble("nh3F") > 0) ? rs.getDouble("nh3F")
						+ "" : "-");
				rtd.setFlow((rs.getDouble("FTotalFlow") > 0) ? rs
						.getDouble("FTotalFlow") + "" : "-");
				rtd.setFlow((rs.getDouble("FTotalFlow") > 0) ? rs
						.getDouble("FTotalFlow") + "" : "-");
				rtd.setCodA((rs.getDouble("coddischarge") > 0) ? rs
						.getDouble("coddischarge") + "" : "-");
				rtd.setNh3A((rs.getDouble("nh3discharge") > 0) ? rs
						.getDouble("nh3discharge") + "" : "-");
				list.add(rtd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			DBUtil.closeConnection(rs, ps, conn);
		}

		return list;
	}

	public List<ReportData> findMMAS(int cId, String time) {

		String sql = "select " 
				+ " MAX(FTotalFlow) totalflowMax,MAX(codF) codFMax, MAX(nh3F) nh3FMax,MAX(coddischarge) codAMax,MAX(nh3discharge)nh3AMax, "
				+ " AVG(FTotalFlow) totalflowAvg, AVG(codF) codFAvg, AVG(nh3F) nh3FAvg, AVG(coddischarge) codAAvg,AVG(nh3discharge)nh3AAvg,"
				+ " MIN(FTotalFlow) totalflowMin,MIN(codF) codFMin,MIN(nh3F) nh3FMin,MIN(coddischarge) codAMin,MIN(nh3discharge)nh3AMin,"
				+ " SUM(FTotalFlow) totalflowSum,SUM(codF) codFSum,SUM(nh3F) nh3FSum,SUM(coddischarge) codASum,SUM(nh3discharge)nh3ASum"
				+ " from (select q.FQuarterID,SUM(FTotalFlow) FTotalFlow ,SUM(codF) codF,SUM(nh3F) nh3F,"
				+ " sum(t4.coddischarge) coddischarge,sum(t4.nh3discharge) nh3discharge"
				+ " from"
				+ " DM_Quarter q,"
				+ " ( "
				+ " select t2.FMonthDesc,t2.FMonthID,t2.FQuarterID,SUM(t1.FTotalFlow) FTotalFlow,SUM(codf) codF,SUM(nh3f) nh3F"
				+ " from ( "
				+ " select b.FMonthDesc,b.FMonthID,b.FQuarterID,c.FDate  from DM_Year a, DM_Month b, DM_Date c"
				+ " where a.FYearID=c.FYearID  and b.FMonthID=c.FMonthID  and a.FYear= ?  ) t2 "
				+ " left join "
				+ "(  "
				+ " SELECT t1.FDate,c.FTotalFlow,"
				+ " MAX(CASE t1.fpollutantid WHEN '65' THEN t1.FTotalFlow ELSE 0 END)  codf,"
				+ " MAX(CASE t1.fpollutantid WHEN '89' THEN t1.FTotalFlow ELSE 0 END)  nh3f"
				+ " FROM T_RT_SewageMonitorPollDay  t1 "
				+ " inner join T_RT_SewageMonitorDay c "
				+ " on t1.FControlerID=c.FControlerID and t1.FDate=c.FDate"
				+ " WHERE t1.FControlerID=? "
				+ " GROUP BY t1.FDate,c.FTotalFlow "
				+ " ) t1 "
				+ " on t2.FDate=t1.FDate"
				+ " group by FMonthDesc,FQuarterID,FMonthID"
				+ " )  t3 "
				+ " left join "

				+ "( "

				+" select  FMonthID,FControlerID,"
				 
				+" SUM(case FPollutantID when '65' then FDischarge else 0 end) as coddischarge," 
				  
				+" SUM(case FPollutantID when '89' then FDischarge else 0 end) as nh3discharge " 
				  
				+" from T_RT_SewageMonthAdjust "
				  
				+" where   FControlerID=? "  
				+" group by FMonthID,FControlerID " 
				+" ) t4  on t4.FMonthID=t3.FMonthID "
				+ " where   t3.FQuarterID=q.FQuarterID "
				+ " group by  q.FQuarterID ) t4";

		List<ReportData> list = new ArrayList<ReportData>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, time);
			ps.setInt(2, cId);
			ps.setInt(3, cId);
			rs = ps.executeQuery();
			
			ReportData max = new ReportData();//最大值
			ReportData min = new ReportData();//最小值
			ReportData avg = new ReportData();//平均值
			ReportData sum = new ReportData();//总和
			max.setTime("最大值");
			min.setTime("最小值");
			avg.setTime("平均值");
			sum.setTime("总量");
			
			if (rs.next()) {
				//最大值
				max.setCodF(rs.getDouble("codFMax")+"");
				max.setNh3F(rs.getDouble("nh3FMax")+"");
				max.setFlow(rs.getDouble("totalflowMax")+"");
				max.setCodA(rs.getDouble("codAMax")+"");
				max.setNh3A(rs.getDouble("nh3AMax")+"");
				//最小值
				
				min.setCodF(rs.getDouble("codFMin")+"");
				
				min.setNh3F(rs.getDouble("nh3FMin")+"");
				min.setFlow(rs.getDouble("totalflowMin")+"");
				
				min.setCodA(rs.getDouble("codAMin")+"");
				min.setNh3A(rs.getDouble("nh3AMin")+"");
				
				//平均值
				
				
				avg.setCodF(new DecimalFormat("######0.00").format(rs.getDouble("codFAvg")));
				avg.setNh3F(new DecimalFormat("######0.00").format(rs.getDouble("nh3FAvg")));
				avg.setFlow(new DecimalFormat("######0.00").format(rs.getDouble("totalflowAvg")));
				
				avg.setCodA(new DecimalFormat("######0.00").format(rs.getDouble("codAAvg")));
				avg.setNh3A(new DecimalFormat("######0.00").format(rs.getDouble("nh3AAvg")));
				
				//总量
				
				sum.setCodF(rs.getDouble("codFSum")+"");
				sum.setNh3F(rs.getDouble("nh3FSum")+"");
				sum.setFlow(rs.getDouble("totalflowSum")+"");
				sum.setCodA(rs.getDouble("codASum")+"");
				sum.setNh3A(rs.getDouble("nh3ASum")+"");
				
			}else{
				//最大值
				
				max.setCodF("-");
				
				max.setNh3F("-");
				max.setFlow("-");
				max.setCodA("-");
				max.setNh3A("-");
				
				//最小值
				
				min.setCodF("-");
				
				min.setNh3F("-");
				min.setFlow("-");
				min.setCodA("-");
				min.setNh3A("-");
				
				//平均值
				
				avg.setCodF("-");
				
				avg.setNh3F("-");
				avg.setFlow("-");
				avg.setCodA("-");
				avg.setNh3A("-");
				
				//总量
				
				sum.setCodF("-");
				
				sum.setNh3F("-");
				sum.setFlow("-");
				sum.setCodA("-");
				sum.setNh3A("-");
			}
			list.add(max);
			list.add(min);
			list.add(avg);
			list.add(sum);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			DBUtil.closeConnection(rs, ps, conn);
		}

		return list;
	}
	
	
}
