package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.junit.Test;

import util.DbHelper;

public class TestTranscational {
	public static void main(String[] args) {
		testpoint();
	}

	/**
	 * @Title: test
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public static void test() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DbHelper.getConnection();
			conn.setAutoCommit(false);
			String sql = "update wangcc_user set salary=salary+100 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.executeUpdate();
			sql = "select salary from wangcc_user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			rs = ps.executeQuery();
			float salary = 0.0f;
			if (rs.next()) {
				salary = rs.getFloat(1);

			}
			if (salary > 20000) {
				throw new RuntimeException("已超过最大值");
			}
			System.out.println("ddd");
			conn.commit();

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
		}
	}

	@Test
	public static void testpoint() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Savepoint sp = null;
		try {
			conn = DbHelper.getConnection();
			conn.setAutoCommit(false);
			String sql = "update wangcc_user set salary=salary+100 where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.executeUpdate();
			sp = conn.setSavepoint();
			sql = "update wangcc_user set salary=salary+100 where id=3";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "select salary from wangcc_user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			rs = ps.executeQuery();
			float salary = 0.0f;
			if (rs.next()) {
				salary = rs.getFloat(1);

			}
			if (salary > 20000) {
				throw new RuntimeException("已超过最大值");
			}

			System.out.println("ddd");
			sql = "update wangcc_user set salary=salary+100 where id=2";
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			// TODO: handle exception
			if (conn != null && sp != null) {
				try {
					conn.rollback(sp);
					conn.commit();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		} finally {
			DbHelper.free(rs, ps, conn);
		}
	}
}
