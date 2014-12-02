package com.stepwell.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.bean.RealtimeBean;
import com.skpw.dao.DBUtil;
import com.stepwell.bean.MapRealTimeData;

@Repository("mapRealtimeDao")
public class MapRealtimeDaoImpl implements MapRealtimeDao {

	public List findRealtimeData(String id) {
		
		String sql = "select  f.FEnterID,f.FEnterName,f.FLongitude,f.FLatitude, "
				+ " e.FctrlerName,g.FMonitorTime,g.codC, g.codD, g.codS,g.nh3C, g.nh3D, g.nh3S, g.FFLow"
				+ " from  T_BAS_Enterprise  f "
				+ " left join  T_TC_Controler  e  on e.FEnterID = f.FEnterID "
				+ " left join ( "
				+ " select  d.FMonitorTime, c.FControlerID,c.codC, c.codD, c.codS,c.nh3C, c.nh3D, c.nh3S, d.FFLow "
				+ " from T_RT_SewageMonitor d,(select a.FControlerID, "
				+ " SUM(case b.FPollSimName when 'cod' then a.FConcentration else 0 end) as codC, "
				+ " SUM(case b.FPollSimName when 'cod' then a.FDischarge else 0 end) as codD, "
				+ " SUM(case b.FPollSimName when 'cod' then a.FSurplus else 0 end) as codS, "
				+ " SUM(case b.FPollSimName when 'nh3' then a.FConcentration else 0 end) as nh3C, "
				+ " SUM(case b.FPollSimName when 'nh3' then a.FDischarge else 0 end) as nh3D,"
				+ " SUM(case b.FPollSimName when 'nh3' then a.FSurplus else 0 end) as nh3S "
				+ " from T_RT_SewageMonitorPoll a "
				+ " inner join T_BAS_Pollutant b  on a.FPollutantID = b.FID "
				+ " group by a.FControlerID) c"
				+ " where d.FControlerID = c.FControlerID"
				+ " ) g  "
				+ " on g.FControlerID=e.FID ";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		// 数据列表
		List<MapRealTimeData> list = new ArrayList<MapRealTimeData>();
		try {
			if (id != null && id != "") {
				sql = sql + "   where  f.FEnterName like '%" + id + "%'";
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					MapRealTimeData rtd = new MapRealTimeData();
					rtd.setWryid(rs.getString("FEnterID"));

					rtd.setWry(rs.getString("FEnterName"));

					rtd.setFlatitude(rs.getFloat("FLatitude"));// 纬度

					rtd.setFlongitude(rs.getFloat("FLongitude"));// 精度
					rtd.setName(rs.getString("FctrlerName"));
					rtd.setTime(rs.getString("FMonitorTime")!=null?rs.getString("FMonitorTime").substring(0,
							rs.getString("FMonitorTime").length() - 2):"-");
					rtd.setCodC(rs.getDouble("codC")>0?rs.getDouble("codC")+"":"-");
					rtd.setCodD(rs.getDouble("codD")>0?rs.getDouble("codD")+"":"-");
					rtd.setCodS(rs.getDouble("codS")>0?rs.getDouble("codS")+"":"-");
					rtd.setNh3C(rs.getDouble("nh3C")>0?rs.getDouble("nh3C")+"":"-");
					rtd.setNh3D(rs.getDouble("nh3D")>0?rs.getDouble("nh3D")+"":"-");
					rtd.setNh3S(rs.getDouble("nh3S")>0?rs.getDouble("nh3S")+"":"-");
					rtd.setFlow(rs.getDouble("FFLow")>0?rs.getDouble("FFLow")+"":"-");
					list.add(rtd);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}

		return list;

	}
	
	public List findRealtimeDatanew(String id,String name,List<String> zzjgids) {
		
		String sql = "select  f.FEnterID,f.FEnterName,f.FLongitude,f.FLatitude, "
				+ " e.FctrlerName,e.FPollTypeCode,g.FMonitorTime,g.codC, g.codD, g.codS,g.nh3C, g.nh3D, g.nh3S, g.FFLow,g.codFAS,g.nh3FAS,g.faState "
				+ " from  T_BAS_Enterprise  f "
				+ " left join  T_TC_Controler  e  on e.FEnterID = f.FEnterID "
				+ " left join ( "
				+ " select  d.FMonitorTime, c.FControlerID,c.codC, c.codD, c.codS,c.nh3C, c.nh3D, c.nh3S,c.codFAS,c.nh3FAS,c.faState, d.FFLow "
				+ " from T_RT_SewageMonitor d,(select a.FControlerID, "
				+ " SUM(case b.FPollSimName when 'cod' then a.FConcentration else 0 end) as codC, "
				+ " SUM(case b.FPollSimName when 'cod' then a.FDischarge else 0 end) as codD, "
				+ " SUM(case b.FPollSimName when 'cod' then a.FSurplus else 0 end) as codS, "
				+ " MAX(case b.FPollSimName when 'cod' then a.FAlarmSource else null end) as codFAS, "
				+ " SUM(case b.FPollSimName when 'nh3' then a.FConcentration else 0 end) as nh3C, "
				+ " SUM(case b.FPollSimName when 'nh3' then a.FDischarge else 0 end) as nh3D,"
				+ " SUM(case b.FPollSimName when 'nh3' then a.FSurplus else 0 end) as nh3S, "
				+ " MAX(case b.FPollSimName when 'nh3' then a.FAlarmSource else null end) as nh3FAS, "
				+ " MAX(a.FAlarmState) as faState "
				+ " from T_RT_SewageMonitorPoll a "
				+ " inner join T_BAS_Pollutant b  on a.FPollutantID = b.FID "
				+ " group by a.FControlerID) c"
				+ " where d.FControlerID = c.FControlerID"
				+ " ) g  "
				+ " on g.FControlerID=e.FID "
				+" where f.FLongitude != 0 and f.FLatitude != 0 and f.FLongitude is not NULL and f.FLatitude is not NULL ";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		// 数据列表
		List<MapRealTimeData> list = new ArrayList<MapRealTimeData>();
		try {
			if(null != id && !"".equals(id)) {
				sql = sql + " and f.FEnterID='"+id+"' ";
			}
			if (name != null && name != "") {
				sql = sql + " and f.FEnterName like '%" + name + "%'";
			}
			if(null != zzjgids && zzjgids.size() > 0) {
				String zzjgStr = "";
				for(int i=0; i<zzjgids.size(); i++) {
					if(i == 0) {
						zzjgStr += "'" + zzjgids.get(i) + "'";
					} else{
						zzjgStr += ",'" + zzjgids.get(i) + "'";
					}
				}
				sql = sql + " and f.FOrgUnitID in (" + zzjgStr +") ";
			}
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					MapRealTimeData rtd = new MapRealTimeData();
					rtd.setWryid(rs.getString("FEnterID"));

					rtd.setWry(rs.getString("FEnterName"));

					rtd.setFlatitude(rs.getFloat("FLatitude"));// 纬度

					rtd.setFlongitude(rs.getFloat("FLongitude"));// 精度
					rtd.setName(rs.getString("FctrlerName"));
					rtd.setFpollTypeCode(rs.getString("FPollTypeCode"));
					rtd.setTime(rs.getString("FMonitorTime")!=null?rs.getString("FMonitorTime").substring(0,
							rs.getString("FMonitorTime").length() - 2):"-");
					rtd.setCodC(rs.getDouble("codC")>0?rs.getDouble("codC")+"":"-");
					rtd.setCodD(rs.getDouble("codD")>0?rs.getDouble("codD")+"":"-");
					rtd.setCodS(rs.getDouble("codS")>0?rs.getDouble("codS")+"":"-");
					rtd.setNh3C(rs.getDouble("nh3C")>0?rs.getDouble("nh3C")+"":"-");
					rtd.setNh3D(rs.getDouble("nh3D")>0?rs.getDouble("nh3D")+"":"-");
					rtd.setNh3S(rs.getDouble("nh3S")>0?rs.getDouble("nh3S")+"":"-");
					rtd.setFlow(rs.getDouble("FFLow")>0?rs.getDouble("FFLow")+"":"-");
					rtd.setCodFAS(rs.getString("codFAS"));
					rtd.setNh3FAS(rs.getString("nh3FAS"));
					rtd.setFaState(rs.getInt("faState"));
					list.add(rtd);
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}

		return list;

	}
	
	public List<RealtimeBean> showRealtimeByPagenew(String qyid) {
		List<RealtimeBean> list = new ArrayList<RealtimeBean>();
		Statement st = null;
		ResultSet rs = null;
		String qyidStr="";
		if(null != qyid && !"".equals(qyid.trim())) {
			qyidStr += " and t4.FEnterID = '"+qyid+"' ";
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
				while (rs.next()) {
					RealtimeBean realtime = new RealtimeBean();
					// realtime.setCtrlid(rs.getInt("FControlerID"));
					realtime.setCtrlname(rs.getString("FctrlerName"));
					realtime.setPfkname(rs.getString("FFacilityName"));
					realtime.setMonitertime(rs.getString("FMonitorTime"));
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
	
	public static void main(String[] args) {
		List list=new MapRealtimeDaoImpl().findRealtimeData("妈");
		System.out.println(list.size());
	}
}
