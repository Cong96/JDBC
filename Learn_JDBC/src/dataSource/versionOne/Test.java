package dataSource.versionOne;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		for (int i = 0; i < 10; i++) {
			try {
				conn = DbHelper.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(conn);
			DbHelper.free(null, null, conn);
		}
	}

}
