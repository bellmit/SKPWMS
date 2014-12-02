package com.skpw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Repository;

import com.skpw.bean.OrgBean;
import com.skpw.bean.TBasEnterPoll;

@Repository("tBasEnterPollDao")
public class TBasEnterPollDaoImpl implements TBasEnterPollDao {

	@Override
	public List<TBasEnterPoll> getList(String id) {

		List<TBasEnterPoll> list = new ArrayList<TBasEnterPoll>();

		String sql = "select b.FEnterPollID,a.FPollutantID,c.FPollutantName,b.FEnterID,b.bIsChoice,b.FCreatorID,b.FCreatTime,b.FOrgUnitID "
				+ "from T_BAS_PollutantSet a "
				+ "left join (select * from T_BAS_EnterPoll where FEnterID='"
				+ id
				+ "') b "
				+ "on a.FPollutantID=b.FPollutantID "
				+ "left join T_BAS_Pollutant c "
				+ "on a.FPollutantID=c.FPollutantID";

		Connection conn = DBUtil.getConnection();

		Statement st = null;
		ResultSet rs = null;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				TBasEnterPoll tep = new TBasEnterPoll();
				tep.setFenterPollId(rs.getString("FEnterPollID") + ";"
						+ rs.getString("FPollutantID"));
				tep.setFenterId(rs.getString("FEnterID"));
				tep.setFpollutantId(rs.getString("FPollutantID"));
				tep.setBisChoice(rs.getBoolean("bIsChoice"));
				tep.setName(rs.getString("FPollutantName"));

				tep.setFcreatTime(rs.getString("FCreatTime"));
				tep.setFcreatorId(rs.getString("FCreatorID"));
				tep.setForgUnitId(rs.getString("FOrgUnitID"));
				list.add(tep);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public void updateAll(List<String> list, String id) {
		// TODO Auto-generated method stub

		String ids = "";
		for (int i = 0; i < list.size(); i++) {

			if (i == list.size() - 1) {
				ids = ids + "'" + list.get(i) + "'";
			} else {
				ids = ids + "'" + list.get(i) + "',";
			}
		}
		// 打钩
		String sql_true = "update T_BAS_EnterPoll set bIsChoice='true' where FEnterPollID in ("
				+ ids + ")";
		// 取消打钩
		String sql_false = "update T_BAS_EnterPoll set bIsChoice='false' where fenterId = '"
				+ id + "' and FEnterPollID not in (" + ids + ")";
		Connection conn = DBUtil.getConnection();

		Statement st = null;
		try {

			st = conn.createStatement();
			st.execute(sql_true);
			st.execute(sql_false);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// 查找企业的管辖单位
	public List<OrgBean> findOrgByUserid(String userid) {

		String sql = "select  t1.username,t3.FOrgUnitName ,t3.FOrgUnitID"
				+ " from T_SYS_USERINFO  t1, T_SYS_UserOrgUnit t2,T_SYS_OrgUnit t3 "
				+ " where t1.id=t2.FUserID  and t2.FOrgUnitID=t3.FOrgUnitID"
				+ " and t1.id=?";

		List<OrgBean> orgList = new ArrayList<OrgBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, userid);

			rs = ps.executeQuery();

			while (rs.next()) {

				OrgBean orgBean = new OrgBean();

				orgBean.setOrgunitname(rs.getString("FOrgUnitName"));

				orgBean.setOrgunitid(rs.getString("FOrgUnitID"));

				orgBean.setUsername(rs.getString("username"));

				orgList.add(orgBean);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(rs, ps, conn);
		}

		return orgList;
	}

	
	public List<Map<String, Object>> findEnterByUserid(String userid) {

		List list = new ArrayList<Map<String, Object>>();
		String sql = " select  t1.username, t2.FEnterID,t2.FEnterName from T_SYS_USERINFO t1,T_BAS_Enterprise t2 "
				+ " where t1.fenterid=t2.FEnterID  and t1.id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("username", rs.getString("username"));
				map.put("enterid", rs.getString("FEnterID"));
				map.put("entername", rs.getString("FEnterName"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			DBUtil.closeConnection(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<String> findOrgIdsByUserid(String userid) {
		List<String> list =new ArrayList<String>();
		String sql = "select t3.FLongCode "
				+ " from T_SYS_USERINFO  t1, T_SYS_UserOrgUnit t2,T_SYS_OrgUnit t3 "
				+ " where t1.id=t2.FUserID  and t2.FOrgUnitID=t3.FOrgUnitID"
				+ " and t1.id=?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = DBUtil.getConnection();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("FLongCode"));
			}

		} catch (SQLException e) {
			return null;
			//e.printStackTrace();
		}

		finally {
			DBUtil.closeConnection(rs, ps, conn);
		}

		return list;
	}

	@Override
	public List<String> findOrgIdsByOrglongcode(List<String> flongcodes) {
		List<String> list =new ArrayList<String>();
		if(flongcodes != null && flongcodes.size() > 0) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Connection conn = DBUtil.getConnection();
			StringBuilder sb = new StringBuilder("select FOrgUnitID from T_SYS_OrgUnit where 1=2 ");
			for(int i=0; i<flongcodes.size(); i++) {
				sb.append(" or FLongCode=? ");
				sb.append(" or FLongCode like ? ");
			}
			try {
				ps = conn.prepareStatement(sb.toString());
				int k = 0;
				for(int j=0; j<flongcodes.size(); j++) {
					k=j+2;
					ps.setString(j+1, flongcodes.get(j));
					ps.setString(k, flongcodes.get(j)+"$%");
				}
				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(rs.getString("FOrgUnitID"));
				}

			} catch (SQLException e) {
				return null;
				//e.printStackTrace();
			}

			finally {
				DBUtil.closeConnection(rs, ps, conn);
			}
		}

		return list;
	}
	
//	public static void main(String[] args) {
//		List<Map<String, Object>> list=new TBasEnterPollDaoImpl().findEnterByUserid("4028480f47816c35014781786a500001");
//		
//		String entername="";
//		for (Map<String, Object> map : list) {
//			
//			
//			entername=map.get("entername").toString();
//		}
//		
//		System.out.println(entername);
//		System.out.println(list.size());
//	}
}
