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

@Repository("tIcRechargeYearDao")
public class TIcRechargeYearDaoImpl implements TIcRechargeYearDao {

	@Override
	public List<Map<String, Object>> findlastxkpflByqyid(int page, int rows,
			String qyid) {
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		StringBuilder sql = new StringBuilder("select MAX(B.FDate) FDate,A.FYearID,C.FPollutantName,MAX(A.FAfterQty) FAfterQty from T_IC_RechargeYear A left join T_IC_Recharge B on A.FRechargeID = B.FRechargeID left join T_BAS_Pollutant C on A.FPollutantID = C.FPollutantID ");
		sql.append(" where B.FEnterID='");
		sql.append(qyid);
		sql.append("' ");
		sql.append(" group by A.FYearID,A.FPollutantID,C.FPollutantName order by FDate desc");
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if (rs != null) {
				while (rs.next()) {
					Map<String, Object> m = new HashMap<String, Object>();
					m.put("fYearID", rs.getInt("FYearID"));
					m.put("fAfterQty", rs.getInt("FAfterQty"));
					m.put("fPollutantName", rs.getString("FPollutantName"));
					m.put("fDate", rs.getDate("FDate"));
					list.add(m);
				}
			}
		} catch (SQLException e) {

		} finally {
			DBUtil.closeConnection(rs, st, conn);
		}
		return list;
	}

}
