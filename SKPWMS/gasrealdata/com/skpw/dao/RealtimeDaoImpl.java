package com.skpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.RealtimeBean;

@Repository("realDao")
public class RealtimeDaoImpl implements RealtimeDao {

	public List<RealtimeBean> showRealtimeByPage(int firstSize, int maxSize) {
		List<RealtimeBean> list = new ArrayList<RealtimeBean>();
		Statement st = null;
		ResultSet rs = null;
		String sql = "SELECT  FctrlerName ,FMonitorTime,FFacilityName,"
				+ " MAX(CASE FPollutantID WHEN '13' THEN frtdvalue ELSE 0 END) AS SO1Rtd,"
				+ " MAX(CASE FPollutantID WHEN '14' THEN frtdvalue ELSE 0 END) AS SO2Rtd,"
				+ " MAX(CASE FPollutantID WHEN '15' THEN frtdvalue ELSE 0 END) AS SO3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '16' THEN frtdvalue ELSE 0 END) AS SO4Rtd,"
				+ " MAX(CASE FPollutantID WHEN '17' THEN frtdvalue ELSE 0 END) AS SO5Rtd,"
				+ " MAX(CASE FPollutantID WHEN '18' THEN frtdvalue ELSE 0 END) AS SO6Rtd,"
				+ " MAX(CASE FPollutantID WHEN '19' THEN frtdvalue ELSE 0 END) AS SO7Rtd,"
				+ " MAX(CASE FPollutantID WHEN '20' THEN frtdvalue ELSE 0 END) AS SO8Rtd,"
				+ " MAX(CASE FPOLLUTANTID WHEN '21' THEN FDischarge ELSE 0 END) AS BO2Discharge, "
				+ " MAX(CASE FPollutantID WHEN '22' THEN frtdvalue ELSE 0 END) AS O1Rtd,"
				+ " MAX(CASE FPollutantID WHEN '22' THEN fzstdvalue ELSE 0 END) AS O1ZSRtd,"
				+ " MAX(CASE FPollutantID WHEN '22' THEN FAlarmSource else null end) as O1FAS,"			
				+ " MAX(CASE FPollutantID WHEN '23' THEN frtdvalue ELSE 0 END) AS O2Rtd,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN fzstdvalue ELSE 0 END) AS O2ZSRtd,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN FAlarmSource else null end) as O2FAS,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN frtdvalue ELSE 0 END) AS O3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN fzstdvalue ELSE 0 END) AS O3ZSRtd, "
				+ " MAX(CASE FPollutantID WHEN '24' THEN FAlarmSource else null end) as O3FAS "
				+ " FROM "
				+ " (  select t3.*,t4.FctrlerName"
				+ " from ( select t1.* ,t2.FFacilityName  from  T_RT_WasteGas t1,T_RT_Facility t2 "
				+ " where   t1.FFacilityID=t2.FFacilityID " + " ) t3 "
				+ " left join  T_TC_Controler   t4  on t4.FID=t3.FControlerID "
				+ " ) t5" + " GROUP BY FctrlerName,FFacilityName, FMonitorTime";
		Connection conn = DBUtil.getConnection();
		try {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			if (null != rs) {
				if ((firstSize - 1) != 0) {
					rs.absolute((firstSize - 1) * maxSize); // 数据指针移动到当前行的当前页的前面一行
				}
				int i = 0;
				while (rs.next()) {
					RealtimeBean realtime = new RealtimeBean();
					// realtime.setCtrlid(rs.getInt("FControlerID"));
					realtime.setCtrlname(rs.getString("FctrlerName"));
					realtime.setPfkname(rs.getString("FFacilityName"));
					realtime.setMonitertime(rs.getString("FMonitorTime"));
					realtime.setBO2Rtd(rs.getFloat("BO2Discharge"));
					realtime.setO2Rtd(rs.getFloat("O2RTD"));
					realtime.setO2ZSRtd(rs.getFloat("O2ZSRTD"));
					realtime.setO3Rtd(rs.getFloat("O3RTD"));
					realtime.setO3ZSRtd(rs.getFloat("O3ZSRTD"));
					realtime.setSO1Rtd(rs.getFloat("SO1Rtd"));
					realtime.setSO2Rtd(rs.getFloat("SO2Rtd"));
					realtime.setSO3Rtd(rs.getFloat("SO3Rtd"));
					realtime.setSO4Rtd(rs.getFloat("SO4Rtd"));
					realtime.setSO5Rtd(rs.getFloat("SO5Rtd"));
					realtime.setSO6Rtd(rs.getFloat("SO6Rtd"));
					realtime.setSO7Rtd(rs.getFloat("SO7Rtd"));
					realtime.setSO8Rtd(rs.getFloat("SO8Rtd"));
					realtime.setO1Rtd(rs.getFloat("O1Rtd"));
					list.add(realtime);
					i++;
					if (i >= maxSize)
						break;
				}
			}
		}

		catch (Exception e) {

			e.printStackTrace();
		} finally {

			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}

	public long realTimeCount() {
		long allpages = 0;
		String sql = "select count(*) "
				+ " from "
				+ " (SELECT  FControlerID ,FMonitorTime,FFacilityName,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN frtdvalue ELSE 0 END) AS O2Rtd,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN fzstdvalue ELSE 0 END) AS O2ZSRtd,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN frtdvalue ELSE 0 END) AS O3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN fzstdvalue ELSE 0 END) AS O3ZSRtd"
				+ " FROM " + " (select t1.* ,t2.FFacilityName"
				+ " from  T_RT_WasteGas t1,T_RT_Facility t2 "
				+ " where   t1.FFacilityID=t2.FFacilityID " + " ) t3 "
				+ " GROUP BY FControlerID,FFacilityName, FMonitorTime) t4";
		Connection conn = DBUtil.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				allpages = rs.getLong(1);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return allpages;

	}
	
	
	public List<RealtimeBean> showRealtimeByPagenew(int firstSize, int maxSize,List<String> qyids) {
		List<RealtimeBean> list = new ArrayList<RealtimeBean>();
		Statement st = null;
		ResultSet rs = null;
		String qyidStr="";
		if(null != qyids && qyids.size() > 0) {
			qyidStr += " and t4.FEnterID in (";
			for(int i=0; i<qyids.size(); i++) {
				if(i == 0) {
					qyidStr += "'" + qyids.get(i) +"'";
				}
				else {
					qyidStr += ",'" + qyids.get(i) +"'";
				}
			}
			qyidStr += ") ";
		}
		
		String sql = "SELECT  FctrlerName ,FMonitorTime,FFacilityName,"
				+ " MAX(CASE FPollutantID WHEN '13' THEN frtdvalue ELSE 0 END) AS SO1Rtd,"
				+ " MAX(CASE FPollutantID WHEN '14' THEN frtdvalue ELSE 0 END) AS SO2Rtd,"
				+ " MAX(CASE FPollutantID WHEN '15' THEN frtdvalue ELSE 0 END) AS SO3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '16' THEN frtdvalue ELSE 0 END) AS SO4Rtd,"
				+ " MAX(CASE FPollutantID WHEN '17' THEN frtdvalue ELSE 0 END) AS SO5Rtd,"
				+ " MAX(CASE FPollutantID WHEN '18' THEN frtdvalue ELSE 0 END) AS SO6Rtd,"
				+ " MAX(CASE FPollutantID WHEN '19' THEN frtdvalue ELSE 0 END) AS SO7Rtd,"
				+ " MAX(CASE FPollutantID WHEN '20' THEN frtdvalue ELSE 0 END) AS SO8Rtd,"
				+ " MAX(CASE FPOLLUTANTID WHEN '21' THEN FDischarge ELSE 0 END) AS BO2Discharge, "
				+ " MAX(CASE FPollutantID WHEN '22' THEN frtdvalue ELSE 0 END) AS O1Rtd,"
				+ " MAX(CASE FPollutantID WHEN '22' THEN fzstdvalue ELSE 0 END) AS O1ZSRtd,"
				+ " MAX(CASE FPollutantID WHEN '22' THEN FAlarmSource else null end) as O1FAS,"	
				+ " MAX(CASE FPollutantID WHEN '23' THEN frtdvalue ELSE 0 END) AS O2Rtd,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN fzstdvalue ELSE 0 END) AS O2ZSRtd,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN FAlarmSource else null end) as O2FAS,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN frtdvalue ELSE 0 END) AS O3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN fzstdvalue ELSE 0 END) AS O3ZSRtd, "
				+ " MAX(CASE FPollutantID WHEN '24' THEN FAlarmSource else null end) as O3FAS "
				+ " FROM "
				+ " (  select t3.*,t4.FctrlerName"
				+ " from ( select t1.* ,t2.FFacilityName  from  T_RT_WasteGas t1,T_RT_Facility t2 "
				+ " where   t1.FFacilityID=t2.FFacilityID " + " ) t3 "
				+ " inner join  T_TC_Controler   t4  on t4.FID=t3.FControlerID "
				+ qyidStr
				+ " ) t5" + " GROUP BY FctrlerName,FFacilityName, FMonitorTime";
		Connection conn = DBUtil.getConnection();
		try {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			if (null != rs) {
				if ((firstSize - 1) != 0) {
					rs.absolute((firstSize - 1) * maxSize); // 数据指针移动到当前行的当前页的前面一行
				}
				int i = 0;
				while (rs.next()) {
					RealtimeBean realtime = new RealtimeBean();
					// realtime.setCtrlid(rs.getInt("FControlerID"));
					realtime.setCtrlname(rs.getString("FctrlerName"));
					realtime.setPfkname(rs.getString("FFacilityName"));
					realtime.setMonitertime(rs.getString("FMonitorTime"));
					realtime.setBO2Rtd(rs.getFloat("BO2Discharge"));
					realtime.setO2Rtd(rs.getFloat("O2RTD"));
					realtime.setO2ZSRtd(rs.getFloat("O2ZSRTD"));
					realtime.setO3Rtd(rs.getFloat("O3RTD"));
					realtime.setO3ZSRtd(rs.getFloat("O3ZSRTD"));
					realtime.setSO1Rtd(rs.getFloat("SO1Rtd"));
					realtime.setSO2Rtd(rs.getFloat("SO2Rtd"));
					realtime.setSO3Rtd(rs.getFloat("SO3Rtd"));
					realtime.setSO4Rtd(rs.getFloat("SO4Rtd"));
					realtime.setSO5Rtd(rs.getFloat("SO5Rtd"));
					realtime.setSO6Rtd(rs.getFloat("SO6Rtd"));
					realtime.setSO7Rtd(rs.getFloat("SO7Rtd"));
					realtime.setSO8Rtd(rs.getFloat("SO8Rtd"));
					realtime.setO1Rtd(rs.getFloat("O1Rtd"));
					realtime.setO1FAS(rs.getString("O1FAS"));
					realtime.setO1ZSRtd(rs.getFloat("O1ZSRtd"));
					realtime.setO2FAS(rs.getString("O2FAS"));
					realtime.setO3FAS(rs.getString("O3FAS"));
					list.add(realtime);
					i++;
					if (i >= maxSize)
						break;
				}
			}
		}

		catch (Exception e) {

			e.printStackTrace();
		} finally {

			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}
	
	public long realTimeCountnew(List<String> qyids) {
		long allpages = 0;
		String qyidStr="";
		if(null != qyids && qyids.size() > 0) {
			qyidStr += " and ttc.FEnterID in (";
			for(int i=0; i<qyids.size(); i++) {
				if(i == 0) {
					qyidStr += "'" + qyids.get(i) +"'";
				}
				else {
					qyidStr += ",'" + qyids.get(i) +"'";
				}
			}
			qyidStr += ") ";
		}
		
		String sql = "select count(*) "
				+ " from "
				+ " (SELECT  FControlerID ,FMonitorTime,FFacilityName,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN frtdvalue ELSE 0 END) AS O2Rtd,"
				+ " MAX(CASE FPollutantID WHEN '23' THEN fzstdvalue ELSE 0 END) AS O2ZSRtd,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN frtdvalue ELSE 0 END) AS O3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '24' THEN fzstdvalue ELSE 0 END) AS O3ZSRtd"
				+ " FROM " + " (select t1.* ,t2.FFacilityName"
				+ " from  T_RT_WasteGas t1,T_RT_Facility t2,T_TC_Controler ttc "
				+ " where   t1.FFacilityID=t2.FFacilityID and ttc.fid=t1.FControlerID "+qyidStr+" ) t3 "
				+ " GROUP BY FControlerID,FFacilityName, FMonitorTime) t4";
		Connection conn = DBUtil.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				allpages = rs.getLong(1);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return allpages;

	}

	public static void main(String[] args) {
		List list = new RealtimeDaoImpl().showRealtimeByPagenew(0, 100, null);

		System.out.println(list.size());
	}
}
