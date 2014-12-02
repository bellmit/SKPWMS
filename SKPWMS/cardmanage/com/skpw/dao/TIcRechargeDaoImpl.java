package com.skpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository("tIcRechargeDao")
public class TIcRechargeDaoImpl implements TIcRechargeDao {

	@Override
	public List<String> findTicRechargeBycardinfoId(String cardinfoid) {

		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<String> l = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("SELECT FRechargeID FROM T_IC_Recharge WHERE FCardInfoID='");
		sql.append(cardinfoid);
		sql.append("'");
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if (rs != null) {
				while (rs.next()) {
					l.add(rs.getString("FRechargeID"));
				}
			}
		} catch (SQLException e) {

		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}

		return l;
	}

}
