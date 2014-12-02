package com.skpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.skpw.bean.RealTimeData;
import com.skpw.bean.TBasEnterprise;

@Repository("hourLlDataDao")
public class RealTimeDataDaoImpl implements RealTimeDataDao {

	public Map<String, Object> findRealtimeData(int currentPage, int pageSize,
			String id) {
		Map<String, Object> map = new HashMap<String, Object>();

		String sql = "select f.FEnterName, e.FctrlerName, d.FMonitorTime, c.codC, c.codD, c.codS,c.nh3C, c.nh3D, c.nh3S, d.FFLow "
				+ "from T_RT_SewageMonitor d, T_TC_Controler e,  T_BAS_Enterprise f,  "
				+ "	(select a.FControlerID,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FConcentration else 0 end) as codC,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FDischarge else 0 end) as codD,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FSurplus else 0 end) as codS,  "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FConcentration else 0 end) as nh3C, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FDischarge else 0 end) as nh3D, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FSurplus else 0 end) as nh3S "
				+ "	from T_RT_SewageMonitorPoll a inner join T_BAS_Pollutant b "
				+ "	on a.FPollutantID = b.FID "
				+ "	group by a.FControlerID) c "
				+ "where d.FControlerID = c.FControlerID "
				+ "	and c.FControlerID = e.FID "
				+ "	and e.FEnterID = f.FEnterID ";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		// 获取数据总数
		int total = 0;
		// 数据列表
		List<RealTimeData> list = new ArrayList<RealTimeData>();
		try {
			if (id != null && id != "") {
				sql = sql + "   and f.FEnterID = '" + id + "'";
			}
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			rs = st.executeQuery(sql);
			if (rs != null) {
				if (currentPage != 0) {
					rs.absolute(currentPage * pageSize); // 数据指针移动到当前行的当前页的前面一行
				}
				int i = 0;
				while (rs.next()) {
					RealTimeData rtd = new RealTimeData();
					rtd.setWry(rs.getString("FEnterName"));
					rtd.setName(rs.getString("FctrlerName"));
					rtd.setTime(rs.getString("FMonitorTime"));
					rtd.setCodC(rs.getDouble("codC"));
					rtd.setCodD(rs.getDouble("codD"));
					rtd.setCodS(rs.getDouble("codS"));
					rtd.setNh3C(rs.getDouble("nh3C"));
					rtd.setNh3D(rs.getDouble("nh3D"));
					rtd.setNh3S(rs.getDouble("nh3S"));
					rtd.setFlow(rs.getDouble("FFLow"));

					list.add(rtd);
					// 分页条数
					i++;
					if (i >= pageSize)
						break;
				}
				rs.last();
				// 获取数据总数
				total = rs.getRow();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}

		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	public List<TBasEnterprise> findWry() {
		List<TBasEnterprise> list = new ArrayList<TBasEnterprise>();

		String sql = "select FEnterID, FEnterName from T_BAS_Enterprise";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				TBasEnterprise tbe = new TBasEnterprise();
				tbe.setFenterId(rs.getString("FEnterID"));
				tbe.setFenterName(rs.getString("FEnterName"));
				list.add(tbe);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}

	public Map<String, Object> findRealtimeDatanew(int currentPage,
			int pageSize, String id, List<String> zzjgids) {
		Map<String, Object> map = new HashMap<String, Object>();

		String sql = "select f.FEnterName, e.FctrlerName, d.FMonitorTime, c.codC, c.codD, c.codS,c.nh3C, c.nh3D, c.nh3S, d.FFLow,c.faState,c.codFAS,c.nh3FAS"
				+ "     from T_RT_SewageMonitor d, T_TC_Controler e,  T_BAS_Enterprise f,  "
				+ "	    (select a.FControlerID,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FConcentration else 0 end) as codC,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FDischarge else 0 end) as codD,  "
				+ "		SUM(case b.FPollSimName when 'cod' then a.FSurplus else 0 end) as codS,  "
				+ "		MAX(case b.FPollSimName when 'cod' then a.FAlarmSource else null end) as codFAS,"
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FConcentration else 0 end) as nh3C, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FDischarge else 0 end) as nh3D, "
				+ "		SUM(case b.FPollSimName when 'nh3' then a.FSurplus else 0 end) as nh3S,"
				+ "       MAX(case b.FPollSimName when 'nh3' then a.FAlarmSource else null end) as nh3FAS,"
				+ "       MAX(a.FAlarmState) as faState "
				+ "	from T_RT_SewageMonitorPoll a inner join T_BAS_Pollutant b "
				+ "	on a.FPollutantID = b.FID "
				+ "	group by a.FControlerID) c "
				+ "where d.FControlerID = c.FControlerID "
				+ "	and c.FControlerID = e.FID "
				+ "	and e.FEnterID = f.FEnterID ";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		// 获取数据总数
		int total = 0;
		// 数据列表
		List<RealTimeData> list = new ArrayList<RealTimeData>();
		try {
			if (id != null && id != "") {
				sql = sql + "   and f.FEnterID = '" + id + "'";
			}
			if (null != zzjgids && zzjgids.size() > 0) {
				String zzjgStr = "";
				for (int i = 0; i < zzjgids.size(); i++) {
					if (i == 0) {
						zzjgStr += "'" + zzjgids.get(i) + "'";
					} else {
						zzjgStr += ",'" + zzjgids.get(i) + "'";
					}
				}
				sql += " and f.FOrgUnitID in (" + zzjgStr + ") ";
			}
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			rs = st.executeQuery(sql);
			if (rs != null) {
				if (currentPage != 0) {
					rs.absolute(currentPage * pageSize); // 数据指针移动到当前行的当前页的前面一行
				}
				int i = 0;
				while (rs.next()) {
					RealTimeData rtd = new RealTimeData();
					rtd.setWry(rs.getString("FEnterName"));
					rtd.setName(rs.getString("FctrlerName"));
					rtd.setTime(rs.getString("FMonitorTime"));
					rtd.setCodC(rs.getDouble("codC"));
					rtd.setCodD(rs.getDouble("codD"));
					rtd.setCodS(rs.getDouble("codS"));
					rtd.setNh3C(rs.getDouble("nh3C"));
					rtd.setNh3D(rs.getDouble("nh3D"));
					rtd.setNh3S(rs.getDouble("nh3S"));
					rtd.setFlow(rs.getDouble("FFLow"));
					rtd.setCodFAS(rs.getString("codFAS"));

					rtd.setNh3FAS(rs.getString("nh3FAS"));

					rtd.setFaState(rs.getInt("faState"));

					list.add(rtd);
					// 分页条数
					i++;
					if (i >= pageSize)
						break;
				}
				rs.last();
				// 获取数据总数
				total = rs.getRow();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}

		map.put("total", total);
		map.put("rows", list);
		return map;
	}

	public static void main(String[] args) {

		Map map=new RealTimeDataDaoImpl().findRealtimeDatanew(0, 10, null, null);
		
		System.out.println(map);
	}
}
