package strategy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao {
	public int update(String sql, Object[] args) {
		return super.update(sql, args);
	}

	// 策略模式。
	public String findusername(String sql, int id) {
		Object[] args = { id };
		String name = (String) super.find(sql, args, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub

				return rs.getObject("name");
			}
		});
		return name;
	}
}
