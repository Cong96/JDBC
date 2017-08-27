package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DbHelper;

public class RollTest {
	public static void main(String[] args) {
		testsenseConfrim();
	}

	/**
	 * @Title: testsense
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void testsense() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "select * from wangcc_user";
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				if (name.equals("kobe")) {
					System.out.println(rs.getString("name"));
					System.out.println(rs.getFloat("salary"));
					rs.updateString("name", "kebi");
					rs.updateRow();
					System.out.println("done");
				}
			}
			System.out.println("done!");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
		}
	}

	public static void testsenseConfrim() {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "select * from wangcc_user";
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String name = rs.getString("name");
				if (name.equals("kobe")) {
					System.out.println(rs.getString("NAme"));
					System.out.println(rs.getFloat("Salary"));
					rs.updateString("name", "kebi");
					rs.updateRow();
					System.out.println("done");
				}
			}
			System.out.println("done!");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, st, conn);
		}
	}
}
