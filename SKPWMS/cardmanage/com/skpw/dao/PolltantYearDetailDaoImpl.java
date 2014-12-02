package com.skpw.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("polltantYearDetailDao")
public class PolltantYearDetailDaoImpl implements PolltantYearDetailDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;	

	public List showPolltantYeayDetail(String enterid) {
		String SQL = "SELECT A.FOUTPID,A.FOUTPCODE,B.FENTERNAME,C.FYEARID,C.FDISCHARGE,D.FPOLLUTANTNAME,E.FUPPERLIMIT "
				+ " FROM T_PS_OUTPERMIT A,T_BAS_ENTERPRISE B,T_PS_OUTSPOLLYEAR C,T_BAS_POLLUTANT D,T_PS_OUTSPOLL E "
				+ " WHERE A.FENTERID=B.FENTERID"
				+ " AND A.FOUTPID=C.FOUTPID"
				+ " AND C.FPOLLUTANTID=D.FPOLLUTANTID"
				+ " AND A.FOUTPID=E.FOUTPID"
				+"  AND C.FPollutantID = E.FPollutantID"
				+ " AND C.FOutSewageID = E.FOutSewageID "
				+ " AND A.FEnterID='"+enterid+"'"
				+ " UNION ALL "
				+ " SELECT A.FOUTPID,A.FOUTPCODE,B.FENTERNAME,C.FYEARID,C.FDISCHARGE,D.FPOLLUTANTNAME,E.FUPPERLIMIT "
				+ " FROM T_PS_OUTPERMIT A,T_BAS_ENTERPRISE B,T_PS_OutWGPollYear C,T_BAS_POLLUTANT D,T_PS_OutWGPoll E "
				+ " WHERE A.FENTERID=B.FENTERID"
				+ " AND A.FOUTPID=C.FOUTPID"
				+ " AND C.FPOLLUTANTID=D.FPOLLUTANTID"
				+ " AND A.FOUTPID=E.FOUTPID"
				+"  AND C.FPollutantID = E.FPollutantID"
				+ " AND C.FWGOutletID = E.FWGOutletID "
				+ " AND A.FEnterID='"+enterid+"'"+" order by FPollutantName,FYearID";
		List list = this.jdbcTemplate.query(SQL,new PollutantYeayDetailRowMapper());

		return list;
	}

}
