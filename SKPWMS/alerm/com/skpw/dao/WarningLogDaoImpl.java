package com.skpw.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("warningLogDao")
public class WarningLogDaoImpl implements WarningLogDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	String SQL = "select a.FWarningLogID,b.FEnterName,c.FctrlerName,d.FPollutantName,e.FWarningItemName,a.FThreshold,a.FRealValue,a.FStdValue,a.FContent,a.FWarningTime,a.FStatus"

			+ " from T_EWS_WarningLog a "

			+ " left join T_BAS_Enterprise  b on a.FEnterID=b.FID"

			+ " left join T_TC_Controler  c on a.FControlerID=c.FID"

			+ " left join T_BAS_Pollutant d on a.FPollutantID=d.FID"

			+ " left join T_BAS_WarningItem e  on a.FWarningItemID=e.FWarningItemID where 1=1 ";

	public List showWarnLog(int startRow, int rowsCount,String strattime,String endtime) {			
		
		StringBuilder sb=new StringBuilder(SQL);
		
		if (strattime != null && endtime != null) {

			sb.append(" and  FWarningTime between '" + strattime + "' and '" + endtime
					+ "'");
		}
		String strQuerySql = "select top " + rowsCount + " *  from" + "(" 
                + sb.toString() + ") as table1" + " where  FWarningLogID" 
                + " not in (select top " + rowsCount * startRow + "  FWarningLogID" 
                + " from (" + sb.toString() + ") as table2  order  by FWarningLogID) " 
                + " order  by FWarningLogID"; 
		
		List list = this.jdbcTemplate.query(strQuerySql.toString(),new WarningLogRowMapper());

		return list;
	}
	
	public List showWarnLognew(int startRow, int rowsCount,String strattime,String endtime,String enterId,List<String> zzjgids) {			
		
		StringBuilder sb=new StringBuilder(SQL);
		
		if(null != enterId && !"".equals(enterId)) {
			sb.append(" and b.FEnterID='"+enterId+"' ");
		}
		
		if(null != zzjgids && zzjgids.size() > 0) {
			String zzjgStr = "";
			for(int i=0; i<zzjgids.size(); i++) {
				if(i == 0) {
					zzjgStr += "'"+zzjgids.get(i)+"'";
				} else {
					zzjgStr += ",'"+zzjgids.get(i)+"'";
				}
			}
			sb.append(" and b.FOrgUnitID in ("+zzjgStr+") ");
		}
		
		if (strattime != null && endtime != null) {

			sb.append(" and  FWarningTime between '" + strattime + "' and '" + endtime
					+ "'");
		}
		String strQuerySql = "select top " + rowsCount + " * from" + "(" 
                + sb.toString() + ") as table1" + " where  FWarningLogID" 
                + " not in (select top " + rowsCount * startRow + "  FWarningLogID" 
                + " from (" + sb.toString() + ") as table2 order  by FWarningLogID) " 
                + " order  by FWarningLogID"; 
		
		List list = this.jdbcTemplate.query(strQuerySql.toString(),new WarningLogRowMapper());

		return list;
	}

}
