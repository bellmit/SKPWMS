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

import com.skpw.bean.TTcControler;

@Repository("tTcControlerDao")
public class TTcControlerDaoImpl implements TTcControlerDao {

	@Override
	public List<Map<String, Object>> getMapList(String id) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		String sql = "select FID, FctrlerName,FEnterID from T_TC_Controler";
		if(id!=null && id!=""){
			sql = " select a.FID, a.FctrlerName,b.FEnterID "
				 +" from T_TC_Controler a, T_BAS_Enterprise b, T_BAS_County c "
				 +" where a.FEnterID = b.FEnterID and b.FCountyID= c.FCountyID and c.FCountyID='"+id+"'";
		}
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getInt("FID"));
				map.put("text", rs.getString("FctrlerName"));
				map.put("qyid", rs.getString("FEnterID"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findTTcControlersOfWry(String id) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String sql = "select fcontrolerId,fctrlerName,fid,FCtrlerSN from T_TC_Controler where fenterId = '"
				+ id + "'";

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getString("fid"));
				map.put("text", rs.getString("fctrlerName"));
				map.put("fid", rs.getString("fid"));
				map.put("mn", rs.getString("FCtrlerSN"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}
}
