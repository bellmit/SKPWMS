package com.skpw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author Administrator 数据库连接类
 */
public class DBUtil {

	private static Connection conn;

	public static Connection getConnection() {
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		String driver = bundle.getString("jdbc.driverClassName");
		String url = bundle.getString("jdbc.url");
		String user = bundle.getString("jdbc.username");
		String password = bundle.getString("jdbc.password");

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {

			new SQLException("数据库异常。");
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection(ResultSet rs, Statement st,
			Connection conn) {

		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
