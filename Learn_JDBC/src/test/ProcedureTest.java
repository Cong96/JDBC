package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import oracle.jdbc.driver.OracleCallableStatement;
import util.DbHelper;
import entity.User;

/**
 * @ClassName: ProcedureTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016年11月26日 下午3:33:19
 * 
 *       oracle创建存储过程,不能规定类型的长度，只能给出指定类型。
 * 
 */
public class ProcedureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// getname();
		test();
	}

	public static void test() {
		Connection conn = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "{call u_getsalary(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.FLOAT);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
			cs.setFloat(4, 16000);
			cs.execute();
			int ret_code = cs.getInt(1);
			String ret_msg = cs.getString(2);
			if (ret_code == 1) {
				System.out.println(ret_msg);
			} else {
				System.out.println("CODE:" + ret_code + "msg:" + ret_msg);

				rs = ((OracleCallableStatement) cs).getCursor(3);
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getInt(1));
					user.setName(rs.getString(2));
					user.setBirthday(rs.getDate(3));
					user.setSalary(rs.getFloat(4));
					System.out.println(user.toString());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
			try {
				cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void getname() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		CallableStatement cs = null;
		try {

			conn = DbHelper.getConnection();
			String sql = "{ call getname(?,?)}";
			cs = conn.prepareCall(sql);

			cs.setInt(1, 1);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.executeUpdate();

			String name = cs.getString(2);
			System.out.println("name:" + name);
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
			try {
				cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
