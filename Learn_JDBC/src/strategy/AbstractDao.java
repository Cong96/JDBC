package strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbHelper;

public abstract class AbstractDao {
	// 增删改都可以
	protected int update(String sql, Object[] args) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			conn = DbHelper.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			count = ps.executeUpdate();
			System.out.println("更新行数：" + count);

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
		}
		return count;
	}

	protected Object find(String sql, Object[] args, RowMapper rowmapper) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object obj = null;
		try {
			conn = DbHelper.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			obj = rowmapper.mapRow(rs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
