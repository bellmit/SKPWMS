package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.GasRealtimeHisBean;

@Repository("gasRealtimeHistoryDao")
public class GasRealtimeHistoryDaoImpl implements GasRealtimeHistoryDao {

	public List queryRealtimeHis(String starttime, String endTime, int fid,
			int facility, int currentPage, int pageSize) {
		// sql="select t3.FctrlerName,FMonitorTime,t2.FFacilityName,
		// MAX(CASE FPollutantID WHEN '13' THEN frtdvalue ELSE 0 END) AS SO1Rtd,
		// MAX(CASE FPollutantID WHEN '14' THEN frtdvalue ELSE 0 END) AS SO2Rtd,
		// MAX(CASE FPollutantID WHEN '15' THEN frtdvalue ELSE 0 END) AS SO3Rtd,
		// MAX(CASE FPollutantID WHEN '16' THEN frtdvalue ELSE 0 END) AS SO4Rtd,
		// MAX(CASE FPollutantID WHEN '17' THEN frtdvalue ELSE 0 END) AS SO5Rtd,
		// MAX(CASE FPollutantID WHEN '18' THEN frtdvalue ELSE 0 END) AS SO6Rtd,
		// MAX(CASE FPollutantID WHEN '19' THEN frtdvalue ELSE 0 END) AS SO7Rtd,
		// MAX(CASE FPollutantID WHEN '20' THEN frtdvalue ELSE 0 END) AS SO8Rtd,
		// MAX(CASE FPollutantID WHEN '22' THEN frtdvalue ELSE 0 END) AS O1Rtd,
		// MAX(CASE FPollutantID WHEN '22' THEN fzstdvalue ELSE 0 END) AS
		// O1ZSRtd,
		// MAX(CASE FPollutantID WHEN '23' THEN frtdvalue ELSE 0 END) AS O2Rtd,
		// MAX(CASE FPollutantID WHEN '23' THEN fzstdvalue ELSE 0 END) AS
		// O2ZSRtd,
		// MAX(CASE FPollutantID WHEN '24' THEN frtdvalue ELSE 0 END) AS O3Rtd,
		// MAX(CASE FPollutantID WHEN '24' THEN fzstdvalue ELSE 0 END) AS
		// O3ZSRtd
		// from
		// T_RT_WasteGasHis t1
		//
		// left join T_RT_Facility t2 on t1.FFacilityID=t2.FFacilityID
		// left join T_TC_Controler t3 on t3.FID=t1.FControlerID
		// where 1=1 and t1.FControlerID=39 and t1.FFacilityID=2 and
		// t1.FMonitorTime between '2014-10-18' and '2014-10-30'
		// GROUP BY t3.FctrlerName,t2.FFacilityName,t1.FMonitorTime"
		List<GasRealtimeHisBean> list = new ArrayList<GasRealtimeHisBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select t3.FctrlerName,FMonitorTime,t2.FFacilityName,"
				+ " MAX(CASE FPollutantID WHEN '13' THEN frtdvalue ELSE 0 END) AS SO1Rtd,"
				+ " MAX(CASE FPollutantID WHEN '14' THEN frtdvalue ELSE 0 END) AS SO2Rtd, "
				+ " MAX(CASE FPollutantID WHEN '15' THEN frtdvalue ELSE 0 END) AS SO3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '16' THEN frtdvalue ELSE 0 END) AS SO4Rtd,"
				+ " MAX(CASE FPollutantID WHEN '17' THEN frtdvalue ELSE 0 END) AS SO5Rtd,"
				+ " MAX(CASE FPollutantID WHEN '18' THEN frtdvalue ELSE 0 END) AS SO6Rtd, "
				+ " MAX(CASE FPollutantID WHEN '19' THEN frtdvalue ELSE 0 END) AS SO7Rtd, "
				+ " MAX(CASE FPollutantID WHEN '20' THEN frtdvalue ELSE 0 END) AS SO8Rtd, "
				+ " MAX(CASE FPOLLUTANTID WHEN '21' THEN FDischarge ELSE 0 END) AS BO2Discharge, "
				+ " MAX(CASE FPollutantID WHEN '22' THEN frtdvalue ELSE 0 END) AS O1Rtd, "
				+ " MAX(CASE FPollutantID WHEN '22' THEN fzstdvalue ELSE 0 END) AS O1ZSRtd, "
				+ " MAX(CASE FPollutantID WHEN '23' THEN frtdvalue ELSE 0 END) AS O2Rtd, "
				+ " MAX(CASE FPollutantID WHEN '23' THEN fzstdvalue ELSE 0 END) AS O2ZSRtd, "
				+ " MAX(CASE FPollutantID WHEN '24' THEN frtdvalue ELSE 0 END) AS O3Rtd, "
				+ " MAX(CASE FPollutantID WHEN '24' THEN fzstdvalue ELSE 0 END) AS O3ZSRtd "
				+ " from "
				+ " T_RT_WasteGasHis t1 "
				+ " left join T_RT_Facility t2 on  t1.FFacilityID=t2.FFacilityID "
				+ " left join  T_TC_Controler t3 on t3.FID=t1.FControlerID "
				+ " where  1=1 and t1.FControlerID=? and t1.FFacilityID=? and "
				+ " t1.FMonitorTime  between ? and ? "
				+ " GROUP BY t3.FctrlerName,t2.FFacilityName,t1.FMonitorTime";
		Connection conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, fid);
			ps.setInt(2, facility);
			ps.setString(3, starttime);
			ps.setString(4, endTime);
			rs = ps.executeQuery();
			if (null != rs) {
				if ((currentPage - 1) != 0) {
					rs.absolute((currentPage - 1) * pageSize); // 数据指针移动到当前行的当前页的前面一行
				}
				int i = 0;
				while (rs.next()) {
					GasRealtimeHisBean gasRealtimeHisBean = new GasRealtimeHisBean();
					gasRealtimeHisBean.setCtrlname(rs.getString("FctrlerName"));
					gasRealtimeHisBean
							.setPfkname(rs.getString("FFacilityName"));
					gasRealtimeHisBean.setMonitertime(rs
							.getString("FMonitorTime"));
					gasRealtimeHisBean.setBO2pfl(rs.getFloat("BO2Discharge"));
					gasRealtimeHisBean.setO2Rtd(rs.getFloat("O2RTD"));
					gasRealtimeHisBean.setO2ZSRtd(rs.getFloat("O2ZSRTD"));
					gasRealtimeHisBean.setO3Rtd(rs.getFloat("O3RTD"));
					gasRealtimeHisBean.setO3ZSRtd(rs.getFloat("O3ZSRTD"));
					gasRealtimeHisBean.setSO1Rtd(rs.getFloat("SO1Rtd"));
					gasRealtimeHisBean.setSO2Rtd(rs.getFloat("SO2Rtd"));
					gasRealtimeHisBean.setSO3Rtd(rs.getFloat("SO3Rtd"));
					gasRealtimeHisBean.setSO4Rtd(rs.getFloat("SO4Rtd"));
					gasRealtimeHisBean.setSO5Rtd(rs.getFloat("SO5Rtd"));
					gasRealtimeHisBean.setSO6Rtd(rs.getFloat("SO6Rtd"));
					gasRealtimeHisBean.setSO7Rtd(rs.getFloat("SO7Rtd"));
					gasRealtimeHisBean.setSO8Rtd(rs.getFloat("SO8Rtd"));
					gasRealtimeHisBean.setO1Rtd(rs.getFloat("O1Rtd"));
					list.add(gasRealtimeHisBean);
					i++;
					if (i >= pageSize)
						break;
				}
			}
		}

		catch (Exception e) {

			e.printStackTrace();
		} finally {

			DBUtil.closeConnection(rs, ps, conn);
		}
		return list;
	}

	public long realtimeDataCount(String starttime, String endTime, int fid,
			int facility) {
		long realhistorycount = 0;
		String sql = "select count(*) realhistorycount "
				+ " from "
				+ " ( "
				+ " select t3.FctrlerName,FMonitorTime,t2.FFacilityName,"
				+ " MAX(CASE FPollutantID WHEN '13' THEN frtdvalue ELSE 0 END) AS SO1Rtd,"
				+ " MAX(CASE FPollutantID WHEN '14' THEN frtdvalue ELSE 0 END) AS SO2Rtd, "
				+ " MAX(CASE FPollutantID WHEN '15' THEN frtdvalue ELSE 0 END) AS SO3Rtd,"
				+ " MAX(CASE FPollutantID WHEN '16' THEN frtdvalue ELSE 0 END) AS SO4Rtd,"
				+ " MAX(CASE FPollutantID WHEN '17' THEN frtdvalue ELSE 0 END) AS SO5Rtd,"
				+ " MAX(CASE FPollutantID WHEN '18' THEN frtdvalue ELSE 0 END) AS SO6Rtd, "
				+ " MAX(CASE FPollutantID WHEN '19' THEN frtdvalue ELSE 0 END) AS SO7Rtd, "
				+ " MAX(CASE FPollutantID WHEN '20' THEN frtdvalue ELSE 0 END) AS SO8Rtd, "
				+ " MAX(CASE FPollutantID WHEN '22' THEN frtdvalue ELSE 0 END) AS O1Rtd, "
				+ " MAX(CASE FPollutantID WHEN '22' THEN fzstdvalue ELSE 0 END) AS O1ZSRtd, "
				+ " MAX(CASE FPollutantID WHEN '23' THEN frtdvalue ELSE 0 END) AS O2Rtd, "
				+ " MAX(CASE FPollutantID WHEN '23' THEN fzstdvalue ELSE 0 END) AS O2ZSRtd, "
				+ " MAX(CASE FPollutantID WHEN '24' THEN frtdvalue ELSE 0 END) AS O3Rtd, "
				+ " MAX(CASE FPollutantID WHEN '24' THEN fzstdvalue ELSE 0 END) AS O3ZSRtd "
				+ " from "
				+ " T_RT_WasteGasHis t1 "
				+ " left join T_RT_Facility t2 on  t1.FFacilityID=t2.FFacilityID "
				+ " left join  T_TC_Controler t3 on t3.FID=t1.FControlerID "
				+ " where  1=1 and t1.FControlerID=? and t1.FFacilityID=? and "
				+ " t1.FMonitorTime  between ? and ? "
				+ " GROUP BY t3.FctrlerName,t2.FFacilityName,t1.FMonitorTime"
				+ " ) t4 ";

		Connection conn = DBUtil.getConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fid);
			ps.setInt(2, facility);
			ps.setString(3, starttime);
			ps.setString(4, endTime);
			rs = ps.executeQuery();

			if (rs.next()) {

				realhistorycount = rs.getLong("realhistorycount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realhistorycount;
	}

	public static void main(String[] args) {

		long totalsize = new GasRealtimeHistoryDaoImpl().realtimeDataCount(
				"2014-10-18", "2014-10-30", 39, 2);
		
		List list=new GasRealtimeHistoryDaoImpl().queryRealtimeHis("2014-10-18", "2014-10-30", 39, 2, 1, 10);
		System.out.println(list.size());
	}

}
