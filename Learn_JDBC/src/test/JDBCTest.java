package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import service.UserService;
import util.DbHelper;
import dao.UserDao;
import entity.User;
import factory.Daofactory;

public class JDBCTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		query();
	}

	@Test
	public static void query() {
		// Class<?> clazz;
		// UserDao userDao = null;
		// try {
		// clazz = Class.forName("dao.impl.UserDaoImpl");
		// userDao = (UserDao) clazz.newInstance();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		UserDao userDao = (UserDao) Daofactory.getDao("userDao");
		// UserDaoImpl userDao = new UserDaoImpl();
		UserService userService = new UserService(userDao);
		User user = userService.getUser(6);
		System.out.println(user.toString());
	}

	public static void testaddBatch() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DbHelper.getConnection();

			String sql = "insert into wangcc_user (name,birthday,salary) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < 20; i++) {

				ps.setString(1, "james");
				ps.setDate(2, new Date(System.currentTimeMillis()));
				ps.setFloat(3, 15000);
				ps.addBatch();

			}
			ps.executeBatch();

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
		}

	}
}
