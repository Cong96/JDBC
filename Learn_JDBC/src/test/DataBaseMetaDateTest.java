package test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

import util.DbHelper;

/**
 * @ClassName: DataBaseMetaDateTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016-11-28 下午4:29:26
 * 
 */
public class DataBaseMetaDateTest {

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testParameter() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbHelper.getConnection();
			String sql = "select * from wangcc_user where id=1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] columnNames = new String[count];
			for (int i = 1; i <= count; i++) {
				// 如果调用方法 ResultSet.getObject 从列中获取值，则返回构造其实例的 Java
				// 类的完全限定名称。ResultSet.getObject 可能返回此方法所返回的类的子类。
				// java.math.BigDecimal
				System.out.println(rsmd.getColumnClassName(i));
				// 获取用于打印输出和显示的指定列的建议标题。 建议标题通常由 SQL AS 子句来指定。也就是获取别名
				// 如果未指定 SQL AS，则从 getColumnLabel 返回的值将和 getColumnName 方法返回的值相同
				System.out.println(rsmd.getColumnLabel(i));
				// 获取指定列的 SQL 类型。
				System.out.println(rsmd.getColumnType(i));
				System.out.println(rsmd.getColumnTypeName(i));
				System.out.println(rsmd.getColumnName(i));
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
		}
	}

	/**
	 * @Title: test
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws 这些信息对于我们使用人员来说可能没有太大的用处
	 *             ，但是对于开发框架的人来说用处很大的，比如Hibernate框架，他要做到兼容所有的数据库特性的话，
	 *             必须要将不同特性统一起来
	 *             ，所以他肯定要获取数据库的元数据信息的，后面会说到Hibernate中有一个配置叫做：方言，这个就是用来设置数据库名称的。
	 */
	@Test
	public void test() {
		Connection conn = null;

		try {
			conn = DbHelper.getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			System.out.println("Database name:"
					+ metaData.getDatabaseProductName());
			System.out.println("driverName:" + metaData.getDriverName());
			System.out.println("isSupportBatch:"
					+ metaData.supportsBatchUpdates());
			System.out.println("isSupportTranscational:"
					+ metaData.supportsTransactions());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbHelper.free(null, null, conn);
		}
	}
}
