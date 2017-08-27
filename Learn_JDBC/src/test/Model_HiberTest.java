package test;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import util.DbHelper;

public class Model_HiberTest {

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

	public static <T> T test(String sql, Class<T> clazz) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = DbHelper.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] columnNames = new String[count];
			for (int i = 0; i < count; i++) {
				// 获取用于打印输出和显示的指定列的建议标题。建议标题通常由 SQL AS 子句来指定。也就是获取别名
				// 如果未指定 SQL AS，则从 getColumnLabel 返回的值将和 getColumnName 方法返回的值相同。
				columnNames[i] = rsmd.getColumnLabel(i + 1);
			}
			T object = clazz.newInstance();
			if (rs.next()) {
				Method[] methods = object.getClass().getMethods();
				for (int i = 0; i < columnNames.length; i++) {
					String methodName = "set" + columnNames[i];
					for (Method method : methods) {
						if (methodName.equalsIgnoreCase(method.getName())) {
							method.invoke(object, rs.getObject(columnNames[i]));
						}
					}
				}
			}
			return object;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
		}
		return null;
	}
}
