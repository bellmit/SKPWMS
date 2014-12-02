package com.skpw.dao;

import java.util.List;

public interface WarningLogDao {

	public List showWarnLog(int startRow, int rowsCount,String strattime,String endtime);
	
	public List showWarnLognew(int startRow, int rowsCount,String strattime,String endtime,String enterId,List<String> zzjgids);

}
