package com.skpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TBasCountyDaoImpl implements TBasCountyDao{

	@Override
	public String findCities() {
		
		String json = "[";
		
		String sql = "select c.FProvinceID, c.FProvinceName, b.FCityID, b.FCityName, a.FCountyID, a.FCountyName "+ 
					"from T_BAS_County a, T_BAS_City b, T_BAS_Province c "+
					"where a.FCityID=b.FCityID and b.FProvinceID=c.FProvinceID";
		
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			int i=0;
			
			String pid = "";
			String cid = "";
			String pid_new = "";
			String cid_new = "";
			
			while(rs.next()){
				
				String province = rs.getString("FProvinceName");
				String city = rs.getString("FCityName");
				String county = rs.getString("FCountyName");
				
				if(i==0){
					pid = rs.getString("FProvinceID");
					cid = rs.getString("FCityID");
					//添加省
					json = json + "{'id':'"+pid+"',"
								+ "text:'"+province+"',"
								+ "children:[{text:'"+city+"'";
					
					//添加县
					if(county!="" && county!=null){
						json = json + ",children:[{text:'"+county+"'";
					}
					
					i++;
				}
				
				if(i>0){
					pid_new = rs.getString("FProvinceID");
					cid_new = rs.getString("FCityID");
					
					if(!pid.equals(pid)){
						
					}else{
						json = json + "}";
					}
				}
				
			}
			json = json + "]";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	public static void main(String[] args) {
		String json = "[";
		
		String pid = "11111";
		String cid = "22222";
		String province = "sheng";
		String city = "shi";
		String county = "xian";
		
		//添加省
		json = json + "{'id':'"+pid+"',text:'"+province+"'";
		//添加市
		if(cid!="" && cid!=null){
			json = json + ",children:[{text:'"+city+"'";
			//添加县
			if(county!="" && county!=null){
				json = json + ",children:[{text:'"+county+"'";
			}
		}else{
			json = json + "}";
		}
		
		json = json + "]";
	}
}
