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

@Repository("tBasCityDao")
public class TBasCityDaoImpl implements TBasCityDao {

	public List<Map<String, Object>> findList() {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String sql = "select FCityID, FCityName from T_BAS_City";
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getString("FCityID"));
				map.put("text", rs.getString("FCityName"));
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
