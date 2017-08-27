package dataSource.versionOne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import dataSource.versionOne.MyDataSource;

public class DbHelper {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";

	private static MyDataSource dataSource = null;
	static {
		try {
			Class.forName(driver);
			dataSource = new MyDataSource();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception:" + e.getMessage() + "");
			throw new ExceptionInInitializerError(e);
		}

	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() throws SQLException {

		return dataSource.getConnection();
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			dataSource.free(conn);
		}
	}
}
