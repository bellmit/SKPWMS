package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.QuarterReportBean;

@Repository("gasQuarterStatisticDao")
public class GasQuarterStatisticDaoImpl implements GasQuarterStatisticDao {

	public List<QuarterReportBean> quarterStatisticReport(int year,
			int ffacilityid, int controlid) {
		String sql = "select m.FQuarterID,sum(S01Rtd) S01Rtd,SUM(S02Rtd) S02Rtd ,SUM(S03Rtd) S03Rtd,SUM(BO2Discharge) BO2Discharge,"
				+ " SUM(S04Rtd) S04Rtd,SUM(S05Rtd) S05Rtd,SUM(S06Rtd) S06Rtd,SUM(S07Rtd) S07Rtd,SUM(S08Rtd) S08Rtd,"
				+ " sum(O1Rtd) O1Rtd,sum(O1ZSRtd) O1ZSRtd,sum(O1FZsDischarge) O1FZsDischarge, sum(O1RtdFDischarge) O1RtdFDischarge,"
				+ " sum(O2Rtd) O2Rtd,sum(O2ZSRtd) O2ZSRtd,sum(O2FZsDischarge) O2FZsDischarge, sum(O2RtdFDischarge) O2RtdFDischarge,"
				+ " sum(O3Rtd) O3Rtd,sum(O3ZSRtd) O3ZSRtd,sum(O3FZsDischarge) O3FZsDischarge, sum(O3RtdFDischarge) O3RtdFDischarge"
				+ " from DM_Quarter m "
				+ " left join( "
				+ " select t5.FYearID,FMonthID,t5.FQuarterID,t5.FControlerID,t5.FFacilityID, "
				+ " sum(S01Rtd) S01Rtd,SUM(S02Rtd) S02Rtd ,SUM(S03Rtd) S03Rtd,SUM(BO2Discharge) BO2Discharge,"
				+ " SUM(S04Rtd) S04Rtd,SUM(S05Rtd) S05Rtd,SUM(S06Rtd) S06Rtd,SUM(S07Rtd) S07Rtd,SUM(S08Rtd) S08Rtd, "
				+ " sum(O1Rtd) O1Rtd,sum(O1ZSRtd) O1ZSRtd,sum(O1FZsDischarge) O1FZsDischarge,sum(O1RtdFDischarge) O1RtdFDischarge, "
				+ " sum(O2Rtd) O2Rtd,sum(O2ZSRtd) O2ZSRtd,sum(O2FZsDischarge) O2FZsDischarge, sum(O2RtdFDischarge) O2RtdFDischarge,"
				+ " sum(O3Rtd) O3Rtd,sum(O3ZSRtd) O3ZSRtd,sum(O3FZsDischarge) O3FZsDischarge,sum(O3RtdFDischarge) O3RtdFDischarge"
				+ " from (  "
				+ " select  t4.FYearID,t3.FMonthID,t3.FQuarterID,FControlerID,FFacilityID,FMonitorTime, "
				+ " MAX(CASE FPOLLUTANTID WHEN '13' THEN FAVGVALUE ELSE 0 END) AS S01Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '14' THEN FAVGVALUE ELSE 0 END) AS S02Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '15' THEN FAVGVALUE ELSE 0 END) AS S03Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '16' THEN FAVGVALUE ELSE 0 END) AS S04Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '17' THEN FAVGVALUE ELSE 0 END) AS S05Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '18' THEN FAVGVALUE ELSE 0 END) AS S06Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '19' THEN FAVGVALUE ELSE 0 END) AS S07Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '20' THEN FAVGVALUE ELSE 0 END) AS S08Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '21' THEN FDischarge ELSE 0 END) AS BO2Discharge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FAVGVALUE ELSE 0 END) AS O1Rtd,  "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FDischarge ELSE 0 END) AS O1RtdFDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FZSAVGVALUE ELSE 0 END) AS O1ZSRtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FZsDischarge ELSE 0 END) AS O1FZsDischarge,"
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FAVGVALUE ELSE 0 END) AS O2Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FDischarge ELSE 0 END) AS O2RtdFDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FZSAVGVALUE ELSE 0 END) AS O2ZSRtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FZsDischarge ELSE 0 END) AS O2FZsDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FAVGVALUE ELSE 0 END) AS O3Rtd,"
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FDischarge ELSE 0 END) AS O3RtdFDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FZSAVGVALUE ELSE 0 END) AS O3ZSRtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FZsDischarge ELSE 0 END) AS O3FZsDischarge "
				+ " from T_RT_WasteGasDay  t1 "
				+ " left join  DM_Date  t2   on t2.FDate=t1.FMonitorTime "
				+ " left join  DM_Month t3  on  t3.FMonthID=t2.FMonthID "
				+ " left join   DM_Year t4  on t4.FYearID=t3.FYearID "
				+ " where t1.FControlerID=? and  t1.FFacilityID=? "
				+ " group by t4.FYearID,t3.FMonthID,t3.FQuarterID,FControlerID,FFacilityID,FMonitorTime ) t5 "
				+ " group by t5.FYearID,t5.FMonthID,t5.FQuarterID,t5.FControlerID,t5.FFacilityID "
				+ " )t6 on t6.FQuarterID=m.FQuarterID "
				+ " where  m.FYearID=? " + " group by m.FQuarterID ";

		PreparedStatement ps = null;

		ResultSet rs = null;
		
		List<QuarterReportBean> list=new ArrayList<QuarterReportBean>();
		Connection conn = DBUtil.getConnection();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,controlid );
			ps.setInt(2, ffacilityid);
			ps.setInt(3,year);			
			rs = ps.executeQuery();
			while (rs.next()) {
				QuarterReportBean quarterReportBean = new QuarterReportBean();
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
				quarterReportBean.setMonitertime(mointertime);			
				quarterReportBean.setSO1Rtd(rs.getFloat("S01Rtd")> 0 ? rs.getFloat("S01Rtd") + "" : "-");
				quarterReportBean.setSO2Rtd(rs.getFloat("S02Rtd")> 0 ? rs.getFloat("S02Rtd") + "" : "-");
				quarterReportBean.setSO3Rtd(rs.getFloat("S03Rtd")> 0 ? rs.getFloat("S03Rtd") + "" : "-");
				quarterReportBean.setSO4Rtd(rs.getFloat("S04Rtd")> 0 ? rs.getFloat("S04Rtd") + "" : "-");
				quarterReportBean.setSO5Rtd(rs.getFloat("S05Rtd")> 0 ? rs.getFloat("S05Rtd") + "" : "-");
				quarterReportBean.setSO6Rtd(rs.getFloat("S06Rtd")> 0 ? rs.getFloat("S06Rtd") + "" : "-");
				quarterReportBean.setSO7Rtd(rs.getFloat("S07Rtd")> 0 ? rs.getFloat("S07Rtd") + "" : "-");
				quarterReportBean.setSO8Rtd(rs.getFloat("S08Rtd")> 0 ? rs.getFloat("S08Rtd") + "" : "-");					
				quarterReportBean.setBO2RtdPfl(rs.getFloat("BO2Discharge")> 0 ? rs.getFloat("BO2Discharge") + "" : "-");
				quarterReportBean.setO1Rtd(rs.getFloat("O1Rtd")> 0 ? rs.getFloat("O1Rtd") + "" : "-");
				quarterReportBean.setO1Rtdpfl(rs.getFloat("O1RtdFDischarge")> 0 ? rs.getFloat("O1RtdFDischarge") + "" : "-");
				quarterReportBean.setO1ZSRtd(rs.getFloat("O1ZSRtd")> 0 ? rs.getFloat("O1ZSRtd") + "" : "-");
				quarterReportBean.setO1ZSRtdPfl(rs.getFloat("O1FZsDischarge")> 0 ? rs.getFloat("O1FZsDischarge") + "" : "-");
				quarterReportBean.setO2Rtd(rs.getFloat("O2Rtd") > 0 ? rs.getFloat("O2Rtd") + "" : "-");
				quarterReportBean.setO2Rtdpfl(rs.getFloat("O2RtdFDischarge")> 0 ? rs.getFloat("O2RtdFDischarge") + "" : "-");
				quarterReportBean.setO2ZSRtd(rs.getFloat("O2ZSRtd") > 0 ? rs.getFloat("O2ZSRtd") + "" : "-");
				quarterReportBean.setO2ZSRtdPPfl(rs.getFloat("O2FZsDischarge")>0?rs.getFloat("O2FZsDischarge") + "" : "-");
				quarterReportBean.setO3Rtd(rs.getFloat("O3Rtd") > 0 ? rs.getFloat("O3Rtd") + "" : "-");
				quarterReportBean.setO3Rtdpfl(rs.getFloat("O3RtdFDischarge")> 0 ? rs.getFloat("O3RtdFDischarge") + "" : "-");
				quarterReportBean.setO3ZSRtd(rs.getFloat("O3ZSRtd") > 0 ? rs.getFloat("O3ZSRtd") + "" : "-");
				quarterReportBean.setO3ZSRtdPfl(rs.getFloat("O3FZsDischarge")>0?rs.getFloat("O3FZsDischarge") + "" : "-");				
				list.add(quarterReportBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, ps, conn);

		}
		return list;
	}

	public List<QuarterReportBean> findMMAS(int year, int ffacilityid,int controlid) {
		
		String sql="select "
				+ " MAX(T7.S01Rtd) S01RtdMax,MAX(T7.S02Rtd) S02RtdMax,MAX(T7.S03Rtd) S03RtdMax,MAX(T7.BO2Discharge) BO2DischargeMax,"
				+ " MAX(T7.S04Rtd) S04RtdMax,MAX(T7.S05Rtd) S05RtdMax, MAX(T7.S06Rtd) S06RtdMax,MAX(T7.S07Rtd) S07RtdMax,MAX(T7.S08Rtd) S08RtdMax,"
				+ " MAX(T7.O1Rtd) O1RtdMax,MAX(T7.O1ZSRtd) O1ZSRtdMax,MAX(T7.O1FZsDischarge) O1FZsDischargeMax,"
				+ " MAX(T7.O2Rtd) O2RtdMax,MAX(T7.O2ZSRtd) O2ZSRtdMax,MAX(T7.O2FZsDischarge) O2FZsDischargeMax,"
				+ " MAX(T7.O3Rtd) O3RtdMax,MAX(T7.O3ZSRtd) O3ZSRtdMax,MAX(T7.O3FZsDischarge) O3FZsDischargeMax, "
				+ " MAX(T7.O1RtdFDischarge) O1RtdFDischargeMax,MAX(T7.O2RtdFDischarge) O2RtdFDischargeMax,MAX(T7.O3RtdFDischarge) O3RtdFDischargeMax,"
				+ " MIN(T7.S01Rtd) S01RtdMIN,MIN(T7.S02Rtd) S02RtdMIN,MIN(T7.S03Rtd) S03RtdMIN,MIN(T7.BO2Discharge) BO2DischargeMin,"
				+ " MIN(T7.S04Rtd) S04RtdMIN,MIN(T7.S05Rtd) S05RtdMIN, MIN(T7.S06Rtd) S06RtdMIN,MIN(T7.S07Rtd) S07RtdMIN,MIN(T7.S08Rtd) S08RtdMIN,"
				+ " MIN(T7.O1Rtd) O1RtdMIN,MIN(T7.O1ZSRtd) O1ZSRtdMIN,MIN(T7.O1FZsDischarge) O1FZsDischargeMIN,"
				+ " MIN(T7.O2Rtd) O2RtdMIN,MIN(T7.O2ZSRtd) O2ZSRtdMIN,MIN(T7.O2FZsDischarge) O2FZsDischargeMIN,"
				+ " MIN(T7.O3Rtd) O3RtdMIN,MIN(T7.O3ZSRtd) O3ZSRtdMIN,MIN(T7.O3FZsDischarge) O3FZsDischargeMIN, "
				+ " MIN(T7.O1RtdFDischarge) O1RtdFDischargeMin,MIN(T7.O2RtdFDischarge) O2RtdFDischargeMin,MIN(T7.O3RtdFDischarge) O3RtdFDischargeMin,"
				+ " AVG(T7.S01Rtd) S01RtdAVG,AVG(T7.S02Rtd) S02RtdAVG,AVG(T7.S03Rtd) S03RtdAVG,AVG(T7.BO2Discharge) BO2DischargeAvg,"
				+ " AVG(T7.S04Rtd) S04RtdAVG,AVG(T7.S05Rtd) S05RtdAVG, AVG(T7.S06Rtd) S06RtdAVG,AVG(T7.S07Rtd) S07RtdAVG,AVG(T7.S08Rtd) S08RtdAVG,"
				+ " AVG(T7.O1Rtd) O1RtdAVG,AVG(T7.O1ZSRtd) O1ZSRtdAVG,AVG(T7.O1FZsDischarge) O1FZsDischargeAVG,"
				+ " AVG(T7.O2Rtd) O2RtdAVG,AVG(T7.O2ZSRtd) O2ZSRtdAVG,AVG(T7.O2FZsDischarge) O2FZsDischargeAVG,"
				+ " AVG(T7.O3Rtd) O3RtdAVG,AVG(T7.O3ZSRtd) O3ZSRtdAVG,AVG(T7.O3FZsDischarge) O3FZsDischargeAVG, "
				+ " AVG(T7.O1RtdFDischarge) O1RtdFDischargeAvg,AVG(T7.O2RtdFDischarge) O2RtdFDischargeAvg,AVG(T7.O3RtdFDischarge) O3RtdFDischargeAvg,"
				+ " SUM(T7.S01Rtd) S01RtdSUM,SUM(T7.S02Rtd) S02RtdSUM,SUM(T7.S03Rtd) S03RtdSUM,SUM(T7.BO2Discharge) BO2DischargeSum,"
				+ " SUM(T7.S04Rtd) S04RtdSUM,SUM(T7.S05Rtd) S05RtdSUM, SUM(T7.S06Rtd) S06RtdSUM,SUM(T7.S07Rtd) S07RtdSUM,SUM(T7.S08Rtd) S08RtdSUM,"
				+ " SUM(T7.O1Rtd) O1RtdSUM,SUM(T7.O1ZSRtd) O1ZSRtdSUM,SUM(T7.O1FZsDischarge) O1FZsDischargeSUM,"
				+ " SUM(T7.O2Rtd) O2RtdSUM,SUM(T7.O2ZSRtd) O2ZSRtdSUM,SUM(T7.O2FZsDischarge) O2FZsDischargeSUM,"
				+ " SUM(T7.O1RtdFDischarge) O1RtdFDischargeSum,SUM(T7.O2RtdFDischarge) O2RtdFDischargeSum,SUM(T7.O3RtdFDischarge) O3RtdFDischargeSum,"
				+ " SUM(T7.O3Rtd) O3RtdSUM,SUM(T7.O3ZSRtd) O3ZSRtdSUM,SUM(T7.O3FZsDischarge) O3FZsDischargeSUM "
				+ " from ("				
				+ " select m.FQuarterID,sum(S01Rtd) S01Rtd,SUM(S02Rtd) S02Rtd ,SUM(S03Rtd) S03Rtd,SUM(BO2Discharge) BO2Discharge,"
				+ " SUM(S04Rtd) S04Rtd,SUM(S05Rtd) S05Rtd,SUM(S06Rtd) S06Rtd,SUM(S07Rtd) S07Rtd,SUM(S08Rtd) S08Rtd,"
				+ " sum(O1Rtd) O1Rtd,sum(O1ZSRtd) O1ZSRtd,sum(O1FZsDischarge) O1FZsDischarge,sum(O1RtdFDischarge) O1RtdFDischarge, "
				+ " sum(O2Rtd) O2Rtd,sum(O2ZSRtd) O2ZSRtd,sum(O2FZsDischarge) O2FZsDischarge,sum(O2RtdFDischarge) O2RtdFDischarge, "
				+ " sum(O3Rtd) O3Rtd,sum(O3ZSRtd) O3ZSRtd,sum(O3FZsDischarge) O3FZsDischarge,sum(O3RtdFDischarge) O3RtdFDischarge"
				+ " from DM_Quarter m "
				+ " left join( "
				+ " select t5.FYearID,FMonthID,t5.FQuarterID,t5.FControlerID,t5.FFacilityID, "
				+ " sum(S01Rtd) S01Rtd,SUM(S02Rtd) S02Rtd ,SUM(S03Rtd) S03Rtd,SUM(BO2Discharge) BO2Discharge,"
				+ " SUM(S04Rtd) S04Rtd,SUM(S05Rtd) S05Rtd,SUM(S06Rtd) S06Rtd,SUM(S07Rtd) S07Rtd,SUM(S08Rtd) S08Rtd, "
				+ " sum(O1Rtd) O1Rtd,sum(O1ZSRtd) O1ZSRtd,sum(O1FZsDischarge) O1FZsDischarge,sum(O1RtdFDischarge) O1RtdFDischarge, "
				+ " sum(O2Rtd) O2Rtd,sum(O2ZSRtd) O2ZSRtd,sum(O2FZsDischarge) O2FZsDischarge,sum(O2RtdFDischarge) O2RtdFDischarge, "
				+ " sum(O3Rtd) O3Rtd,sum(O3ZSRtd) O3ZSRtd,sum(O3FZsDischarge) O3FZsDischarge,sum(O3RtdFDischarge) O3RtdFDischarge "
				+ " from (  "
				+ " select  t4.FYearID,t3.FMonthID,t3.FQuarterID,FControlerID,FFacilityID,FMonitorTime, "
				+ " MAX(CASE FPOLLUTANTID WHEN '13' THEN FAVGVALUE ELSE 0 END) AS S01Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '14' THEN FAVGVALUE ELSE 0 END) AS S02Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '15' THEN FAVGVALUE ELSE 0 END) AS S03Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '16' THEN FAVGVALUE ELSE 0 END) AS S04Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '17' THEN FAVGVALUE ELSE 0 END) AS S05Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '18' THEN FAVGVALUE ELSE 0 END) AS S06Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '19' THEN FAVGVALUE ELSE 0 END) AS S07Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '20' THEN FAVGVALUE ELSE 0 END) AS S08Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '21' THEN FDischarge ELSE 0 END) AS BO2Discharge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FAVGVALUE ELSE 0 END) AS O1Rtd,  "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FDischarge ELSE 0 END) AS O1RtdFDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FZSAVGVALUE ELSE 0 END) AS O1ZSRtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '22' THEN FZsDischarge ELSE 0 END) AS O1FZsDischarge,"
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FAVGVALUE ELSE 0 END) AS O2Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FDischarge ELSE 0 END) AS O2RtdFDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FZSAVGVALUE ELSE 0 END) AS O2ZSRtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '23' THEN FZsDischarge ELSE 0 END) AS O2FZsDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FAVGVALUE ELSE 0 END) AS O3Rtd,"
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FDischarge ELSE 0 END) AS O3RtdFDischarge, "
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FZSAVGVALUE ELSE 0 END) AS O3ZSRtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '24' THEN FZsDischarge ELSE 0 END) AS O3FZsDischarge "
				+ " from T_RT_WasteGasDay  t1 "
				+ " left join  DM_Date  t2   on t2.FDate=t1.FMonitorTime "
				+ " left join  DM_Month t3  on  t3.FMonthID=t2.FMonthID "
				+ " left join   DM_Year t4  on t4.FYearID=t3.FYearID "
				+ " where t1.FControlerID=? and  t1.FFacilityID=? "
				+ " group by t4.FYearID,t3.FMonthID,t3.FQuarterID,FControlerID,FFacilityID,FMonitorTime ) t5 "
				+ " group by t5.FYearID,t5.FMonthID,t5.FQuarterID,t5.FControlerID,t5.FFacilityID "
				+ " )t6 on t6.FQuarterID=m.FQuarterID "
				+ " where  m.FYearID=? " + " group by m.FQuarterID "
				+ " ) T7";
		List<QuarterReportBean> list = new ArrayList<QuarterReportBean>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,controlid );
			ps.setInt(2, ffacilityid);
			ps.setInt(3,year);
			rs = ps.executeQuery();
			
			QuarterReportBean max = new QuarterReportBean();//最大值
			QuarterReportBean min = new QuarterReportBean();//最小值
			QuarterReportBean avg = new QuarterReportBean();//平均值
			QuarterReportBean sum = new QuarterReportBean();//总和
			max.setMonitertime("最大值");
			min.setMonitertime("最小值");
			avg.setMonitertime("平均值");
			sum.setMonitertime("总量");
			
			if (rs.next()) {

				// 最大值
				max.setSO1Rtd(rs.getDouble("S01RtdMax")+"");
				max.setSO2Rtd(rs.getDouble("S02RtdMax")+"");
				max.setSO3Rtd(rs.getDouble("S03RtdMax")+"");
				max.setSO4Rtd(rs.getDouble("S04RtdMax")+"");
				max.setSO5Rtd(rs.getDouble("S05RtdMax")+"");
				max.setSO6Rtd(rs.getDouble("S06RtdMax")+"");
				max.setSO7Rtd(rs.getDouble("S07RtdMax")+"");
				max.setSO8Rtd(rs.getDouble("S08RtdMax")+"");
				max.setBO2RtdPfl(rs.getDouble("BO2DischargeMax")+"");
				max.setO1Rtd(rs.getDouble("O1RtdMax")+"");
				max.setO1ZSRtd(rs.getDouble("O1ZSRtdMax")+"");
				max.setO1ZSRtdPfl(rs.getDouble("O1FZsDischargeMax")+"");
				max.setO2Rtd(rs.getDouble("O2RtdMax") + "");
				max.setO2ZSRtd(rs.getDouble("O2ZSRtdMax") + "");
				max.setO2ZSRtdPPfl(rs.getDouble("O2FZsDischargeMax") + "");
				max.setO3Rtd(rs.getDouble("O3RtdMax") + "");
				max.setO3ZSRtd(rs.getDouble("O3ZSRtdMax") + "");
				max.setO3ZSRtdPfl(rs.getDouble("O3FZsDischargeMax") + "");
				max.setO1Rtdpfl(rs.getDouble("O1RtdFDischargeMax") + "");
				max.setO2Rtdpfl(rs.getDouble("O2RtdFDischargeMax") + "");
				max.setO3Rtdpfl(rs.getDouble("O3RtdFDischargeMax") + "");
				// 最小值
				min.setSO1Rtd(rs.getDouble("S01RtdMin")+"");
				min.setSO2Rtd(rs.getDouble("S02Rtdmin")+"");
				min.setSO3Rtd(rs.getDouble("S03Rtdmin")+"");
				min.setSO4Rtd(rs.getDouble("S04Rtdmin")+"");
				min.setSO5Rtd(rs.getDouble("S05Rtdmin")+"");
				min.setSO6Rtd(rs.getDouble("S06Rtdmin")+"");
				min.setSO7Rtd(rs.getDouble("S07Rtdmin")+"");
				min.setSO8Rtd(rs.getDouble("S08Rtdmin")+"");
				min.setBO2RtdPfl(rs.getDouble("BO2DischargeMin")+"");
				min.setO1Rtd(rs.getDouble("O1Rtdmin")+"");
				min.setO1ZSRtd(rs.getDouble("O1ZSRtdmin")+"");
				min.setO1ZSRtdPfl(rs.getDouble("O1FZsDischargemin")+"");
				min.setO2Rtd(rs.getDouble("O2Rtdmin") + "");
				min.setO2ZSRtd(rs.getDouble("O2ZSRtdmin") + "");
				min.setO2ZSRtdPPfl(rs.getDouble("O2FZsDischargemin") + "");
				min.setO3Rtd(rs.getDouble("O3Rtdmin") + "");
				min.setO3ZSRtd(rs.getDouble("O3ZSRtdmin") + "");
				min.setO3ZSRtdPfl(rs.getDouble("O3FZsDischargeMin") + "");
				min.setO1Rtdpfl(rs.getDouble("O1RtdFDischargeMin") + "");
				min.setO2Rtdpfl(rs.getDouble("O2RtdFDischargeMin") + "");
				min.setO3Rtdpfl(rs.getDouble("O3RtdFDischargeMin") + "");

				// 平均值
				
				avg.setSO1Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S01Rtdavg")));
				avg.setSO2Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S02Rtdavg")));
				avg.setSO3Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S03Rtdavg")));
				avg.setSO4Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S04Rtdavg")));
				avg.setSO5Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S05Rtdavg")));
				avg.setSO6Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S06Rtdavg")));
				avg.setSO7Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S07Rtdavg")));
				avg.setSO8Rtd(new DecimalFormat("######0.00").format(rs.getDouble("S08Rtdavg")));
				avg.setBO2RtdPfl(new DecimalFormat("######0.00").format(rs.getDouble("BO2DischargeAvg")));
				avg.setO1Rtd(new DecimalFormat("######0.00").format(rs.getDouble("O1Rtdavg")));
				avg.setO1ZSRtd(new DecimalFormat("######0.00").format(rs.getDouble("O1ZSRtdavg")));
				avg.setO1ZSRtdPfl(new DecimalFormat("######0.00").format(rs.getDouble("O1FZsDischargeavg")));
				avg.setO2Rtd(new DecimalFormat("######0.00").format(rs.getDouble("O2Rtdavg")));
				avg.setO2ZSRtd(new DecimalFormat("######0.00").format(rs.getDouble("O2ZSRtdavg")));
				avg.setO2ZSRtdPPfl(new DecimalFormat("######0.00").format(rs.getDouble("O2FZsDischargeavg")));
				avg.setO3Rtd(new DecimalFormat("######0.00").format(rs.getDouble("O3Rtdavg")));
				avg.setO3ZSRtd(new DecimalFormat("######0.00").format(rs.getDouble("O3ZSRtdavg")));
				avg.setO3ZSRtdPfl(new DecimalFormat("######0.00").format(rs.getDouble("O3FZsDischargeavg")));
				avg.setO1Rtdpfl(rs.getDouble("O1RtdFDischargeMax") + "");
				avg.setO2Rtdpfl(rs.getDouble("O2RtdFDischargeMax") + "");
				avg.setO3Rtdpfl(rs.getDouble("O3RtdFDischargeMax") + "");
				// 总量
				sum.setSO1Rtd(rs.getDouble("S01Rtdsum")+"");
				sum.setSO2Rtd(rs.getDouble("S02Rtdsum")+"");
				sum.setSO3Rtd(rs.getDouble("S03Rtdsum")+"");
				sum.setSO4Rtd(rs.getDouble("S04Rtdsum")+"");
				sum.setSO5Rtd(rs.getDouble("S05Rtdsum")+"");
				sum.setSO6Rtd(rs.getDouble("S06Rtdsum")+"");
				sum.setSO7Rtd(rs.getDouble("S07Rtdsum")+"");
				sum.setSO8Rtd(rs.getDouble("S08Rtdsum")+"");
				sum.setBO2RtdPfl(rs.getDouble("BO2DischargeSum")+"");
				sum.setO1Rtd(rs.getDouble("O1Rtdsum")+"");
				sum.setO1ZSRtd(rs.getDouble("O1ZSRtdsum")+"");
				sum.setO1ZSRtdPfl(rs.getDouble("O1FZsDischargesum")+"");
				sum.setO2Rtd(rs.getDouble("O2Rtdsum") + "");
				sum.setO2ZSRtd(rs.getDouble("O2ZSRtdsum") + "");
				sum.setO2ZSRtdPPfl(rs.getDouble("O2FZsDischargesum") + "");
				sum.setO3Rtd(rs.getDouble("O3Rtdsum") + "");
				sum.setO3ZSRtd(rs.getDouble("O3ZSRtdsum") + "");
				sum.setO3ZSRtdPfl(rs.getDouble("O3FZsDischargesum") + "");
				sum.setO1Rtdpfl(rs.getDouble("O1RtdFDischargeSum") + "");
				sum.setO2Rtdpfl(rs.getDouble("O2RtdFDischargeSum") + "");
				sum.setO3Rtdpfl(rs.getDouble("O3RtdFDischargeSum") + "");
			} else {
				// 最大值
				max.setSO1Rtd("-");
				max.setSO2Rtd("-");
				max.setSO3Rtd("-");
				max.setSO4Rtd("-");
				max.setSO5Rtd("-");
				max.setSO6Rtd("-");
				max.setSO7Rtd("-");
				max.setSO8Rtd("-");
				max.setBO2RtdPfl("-");
				max.setO1Rtd("-");
				max.setO1ZSRtd("-");
				max.setO1ZSRtdPfl("-");
				max.setO2Rtd("-");
				max.setO2ZSRtd("-");
				max.setO2ZSRtdPPfl("-");
				max.setO3Rtd("-");
				max.setO3ZSRtd("-");
				max.setO3ZSRtdPfl("-");
				max.setO1Rtdpfl("-");
				max.setO2Rtdpfl("-");
				max.setO3Rtdpfl("-");
				// 最小值
				min.setSO1Rtd("-");
				min.setSO2Rtd("-");
				min.setSO3Rtd("-");
				min.setSO4Rtd("-");
				min.setSO5Rtd("-");
				min.setSO6Rtd("-");
				min.setSO7Rtd("-");
				min.setSO8Rtd("-");
				min.setBO2RtdPfl("-");
				min.setO1Rtd("-");
				min.setO1ZSRtd("-");
				min.setO1ZSRtdPfl("-");
				min.setO2Rtd("-");
				min.setO2ZSRtd("-");
				min.setO2ZSRtdPPfl("-");
				min.setO3Rtd("-");
				min.setO3ZSRtd("-");
				min.setO3ZSRtdPfl("-");
				min.setO1Rtdpfl("-");
				min.setO2Rtdpfl("-");
				min.setO3Rtdpfl("-");
				// 平均值
				avg.setSO1Rtd("-");
				avg.setSO2Rtd("-");
				avg.setSO3Rtd("-");
				avg.setSO4Rtd("-");
				avg.setSO5Rtd("-");
				avg.setSO6Rtd("-");
				avg.setSO7Rtd("-");
				avg.setSO8Rtd("-");
				avg.setBO2RtdPfl("-");
				avg.setO1Rtd("-");
				avg.setO1ZSRtd("-");
				avg.setO1ZSRtdPfl("-");
				avg.setO2Rtd("-");
				avg.setO2ZSRtd("-");
				avg.setO2ZSRtdPPfl("-");
				avg.setO3Rtd("-");
				avg.setO3ZSRtd("-");
				avg.setO3ZSRtdPfl("-");
				avg.setO1Rtdpfl("-");
				avg.setO2Rtdpfl("-");
				avg.setO3Rtdpfl("-");
				// 总量
				sum.setSO1Rtd("-");
				sum.setSO2Rtd("-");
				sum.setSO3Rtd("-");
				sum.setSO4Rtd("-");
				sum.setSO5Rtd("-");
				sum.setSO6Rtd("-");
				sum.setSO7Rtd("-");
				sum.setSO8Rtd("-");
				sum.setBO2RtdPfl("-");
				sum.setO1Rtd("-");
				sum.setO1ZSRtd("-");
				sum.setO1ZSRtdPfl("-");
				sum.setO2Rtd("-");
				sum.setO2ZSRtd("-");
				sum.setO2ZSRtdPPfl("-");
				sum.setO3Rtd("-");
				sum.setO3ZSRtd("-");
				sum.setO3ZSRtdPfl("-");
				sum.setO1Rtdpfl("-");
				sum.setO2Rtdpfl("-");
				sum.setO3Rtdpfl("-");
			}

			list.add(max);

			list.add(min);

			list.add(avg);

			list.add(sum);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			DBUtil.closeConnection(rs, ps, conn);
		}

		return list;
	}
	
	public static void main(String[] args) {
		
		
		List list= new GasQuarterStatisticDaoImpl().findMMAS(2014, 1, 39);
		
		System.out.println(list.size());
	}

}
