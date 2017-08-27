package dataSource.versionOne;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @ClassName: MyDataSource
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016年11月28日 下午11:09:04
 * 
 *       初始化数据源的时候，初始化一定量的连接放到池子中，当用户使用getConnection()方法取出连接的时候，
 *       我们会判断这个连接池中还有没有连接了
 *       ，有就直接取出第一个连接返回，没有的话，我们在判断当前的连接数有没有超过最大连接数，超过的话，就抛出一个异常
 *       (其实这里还可以选择等待其他连接的释放，这个具体实现是很麻烦的)，没有超过的话，就创建连接，并且将其放入池子中。
 *       我们自定义的数据源是实现了JDBC中的DataSource接口的
 *       ，这个接口很重要的，后面我们会说到apache的数据源都是要实现这个接口的，这个接口统一了数据源的标准
 *       ，这个接口中有很多实现的，所以看到我们的数据源类中有很多没必要的方法
 *       ，但是那个方法都是要实现的，最重要的就是要实现getConnection方法，其他的实现都只需要调用super.XXX就可以了。
 */
public class MyDataSource implements DataSource {
	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
	private static final String user = "SCOTT";
	private static final String password = "cong960227";
	private static int currentCount = 0;
	private static int maxCount = 10;
	private static int initCount = 5;
	// 初始化连接数，最大连接数，当前的连接数，连接池(因为我们可能需要频繁的添加连接和删除连接所以使用LinkedList,因为这个list是链表结构的，增加和删除效率高)
	private LinkedList<Connection> connectionPool = new LinkedList<Connection>();

	public MyDataSource() {
		try {
			for (int i = 0; i < initCount; i++) {
				this.connectionPool.addLast(createConnection());
				currentCount++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError(e);
		}

	}

	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		synchronized (connectionPool) {
			if (connectionPool.size() > 0) {
				return this.connectionPool.removeFirst();

			}
			if (currentCount < maxCount) {
				currentCount++;
				return createConnection();

			}
			throw new SQLException("没有可用链接了");
		}

	}

	public void free(Connection conn) {
		this.connectionPool.addLast(conn);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
