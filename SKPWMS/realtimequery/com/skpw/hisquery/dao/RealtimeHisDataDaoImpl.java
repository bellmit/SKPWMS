package com.skpw.hisquery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.skpw.dao.DBUtil;
import com.skpw.hisquery.bean.RealTimeHisData;

@Repository("realtimeHisDataDao")
public class RealtimeHisDataDaoImpl implements RealtimeHisDataDao {

	public List queryRealtimeHis(String starttime, String endTime, int fid,int currentPage, int pageSize) {

		String sql = " select  t4.FctrlerName,t1.FControlerID,t1.FMonitorTime,t3.codC,t3.codD,t3.codS,t3.nh3C,t3.nh3D,t3.nh3S,t1.FFLow"
				+ " from  T_RT_SewageMonitorHis  t1,"
				+ " ( select   t2.FControlerID,t2.FMonitorTime,"
				+ " SUM(case t2.FPollutantID when '65' then t2.FConcentration else 0 end) as codC,"
				+ " SUM(case t2.FPollutantID when '65' then t2.FDischarge else 0 end) as codD,"
				+ " SUM(case t2.FPollutantID when '65' then t2.FSurplus else 0 end) as codS,"
				+ " SUM(case t2.FPollutantID when '89' then t2.FConcentration else 0 end) as nh3C,"
				+ " SUM(case t2.FPollutantID when '89' then t2.FDischarge else 0 end) as nh3D,"
				+ " SUM(case t2.FPollutantID when '89' then t2.FSurplus else 0 end) as nh3S"
				+ " from  T_RT_SewageMonitorPollHis t2"
				+ " group by  FControlerID,FMonitorTime) t3, "
				+ " T_TC_Controler  t4"
				+ " where t1.FControlerID=t3.FControlerID  "
				+ " and t1.FMonitorTime= t3.FMonitorTime "
				+ " and t4.FID=t1.FControlerID"
				+ " and t1.FControlerID=? "
				+ " and  t1.FMonitorTime  between ?  and ?  "
				+ " order by FMonitorTime desc";

		Connection conn = DBUtil.getConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;
		List<RealTimeHisData> list=new ArrayList<RealTimeHisData>();
		try {
			ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, fid);
			ps.setString(2, starttime);
			ps.setString(3, endTime);
		    rs = ps.executeQuery();
		    if(rs != null){
		    	if ((currentPage - 1) != 0) {
					rs.absolute((currentPage - 1) * pageSize); // 数据指针移动到当前行的当前页的前面一行
				}
				int i = 0;
				while(rs.next()){
				RealTimeHisData  realTimeHisData=new RealTimeHisData();
				
				realTimeHisData.setFid(rs.getInt("FControlerID")+"");
				realTimeHisData.setFconname(rs.getString("FctrlerName"));
				realTimeHisData.setTime(rs.getString("FMonitorTime"));
				realTimeHisData.setCodC(rs.getDouble("codC"));
				realTimeHisData.setCodD(rs.getDouble("codD"));
				realTimeHisData.setCodS(rs.getDouble("codS"));
				realTimeHisData.setNh3C(rs.getDouble("nh3c"));
				realTimeHisData.setNh3D(rs.getDouble("nh3d"));
				realTimeHisData.setNh3S(rs.getDouble("nh3s"));
				realTimeHisData.setFlow(rs.getDouble("FFLow"));				
				list.add(realTimeHisData);
				i++;
				if (i >= pageSize)
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public long realtimeDataCount(String starttime, String endTime, int fid) {
		
		long realhistorycount=0;
		String sql = " select count(*) realhistorycount"
				+ " from  T_RT_SewageMonitorHis  t1,"
				+ " ( select   t2.FControlerID,t2.FMonitorTime,"
				+ " SUM(case t2.FPollutantID when '65' then t2.FConcentration else 0 end) as codC,"
				+ " SUM(case t2.FPollutantID when '65' then t2.FDischarge else 0 end) as codD,"
				+ " SUM(case t2.FPollutantID when '65' then t2.FSurplus else 0 end) as codS,"
				+ " SUM(case t2.FPollutantID when '89' then t2.FConcentration else 0 end) as nh3C,"
				+ " SUM(case t2.FPollutantID when '89' then t2.FDischarge else 0 end) as nh3D,"
				+ " SUM(case t2.FPollutantID when '89' then t2.FSurplus else 0 end) as nh3S"
				+ " from  T_RT_SewageMonitorPollHis t2"
				+ " group by  FControlerID,FMonitorTime) t3"
				+ " where t1.FControlerID=t3.FControlerID  "
				+ " and t1.FMonitorTime= t3.FMonitorTime "
				+ " and t1.FControlerID=? "
				+ " and  t1.FMonitorTime  between ?  and ? ";

		Connection conn = DBUtil.getConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fid);
			ps.setString(2, starttime);
			ps.setString(3, endTime);
		    rs = ps.executeQuery();
		    
		    if(rs.next()){
		    	
		    	realhistorycount=rs.getLong("realhistorycount");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return realhistorycount;
	}
}
