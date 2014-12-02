package com.skpw.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

@Repository("resourceDao")
public class ResourceDaoImpl implements ResourceDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	// 通过角色获取所有资源
	public List getResourceByRoleID(String roleid) {

		String SQL = "SELECT A.FROLEID,A.FROLENAME,C.FAUTHORITYNAME,D.FURL  FROM T_SYS_ROLE  A ,T_SYS_AUTHORITYROLE B,T_SYS_MENUAUTHORITY C,T_SYS_MENUITEM D"

				+ " WHERE  A.FROLEID=B.FROLEID"

				+ " AND  B.FAUTHORITYID=C.FAUTHORITYID"

				+ " AND C.FMENUID=D.FMENUID"

				+ " AND A.FROLEID='" + roleid + "'";
		List list = this.jdbcTemplate.query(SQL, new ResourceRowMapper());

		return list;
	}

	public List<String> getValues(String authorityid) {

		String sql = "select b.FUrl  from T_SYS_MenuAuthority a"

		+ " left join T_SYS_MenuItem b  on a.FMenuID=b.FMenuID"

		+ " where a.FAuthorityID='" + authorityid + "'";

		return this.jdbcTemplate.query(sql,
				new ParameterizedRowMapper<String>() {

					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						return rs.getString("FUrl");
					}
				});

	}

	// 通过用户名获取权限id
	public List getAuthorityid(String username) {
		String SQL = "SELECT T4.FAUTHORITYID  FROM  T_SYS_USERINFO T1, T_SYS_USERROLE T2,T_SYS_ROLE T3 ,T_SYS_AUTHORITYROLE T4"
				+ " WHERE T1.ID=T2.FUSERID   AND  T2.FROLEID=T3.FROLEID  AND T3.FROLEID=T4.FROLEID  AND T1.USERNAME='"
				+ username + "'";
		return jdbcTemplate.query(SQL, new ParameterizedRowMapper<String>() {

			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("FAUTHORITYID");
			}
		});

	}
}
