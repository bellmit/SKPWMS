package com.skpw.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.skpw.bean.Resource;

public class ResourceRowMapper implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {

		Resource resource = new Resource(rs.getString("FRoleID"),
				rs.getString("FRoleName"), rs.getString("FAuthorityName"),
				rs.getString("FUrl"));
		return resource;
	}
}
