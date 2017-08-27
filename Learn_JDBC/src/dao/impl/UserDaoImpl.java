package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbHelper;
import dao.UserDao;
import entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		User user = new User();
		try {
			connection = DbHelper.getConnection();
			String sql = "select id,name,birthday,salary from wangcc_user where id=?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {

				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setBirthday(rs.getDate(3));
				user.setSalary(rs.getFloat(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (user != null) {
			return user;
		}
		return null;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

}
