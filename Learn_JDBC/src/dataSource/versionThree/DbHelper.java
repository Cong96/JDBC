package dataSource.versionThree;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbHelper {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";

	private static MyDataSuorce dataSource = null;
	static {
		try {
			Class.forName(driver);
			dataSource = new MyDataSuorce();
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
		Connection conn = null;
		conn = dataSource.getConnection();
		return conn;
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
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
