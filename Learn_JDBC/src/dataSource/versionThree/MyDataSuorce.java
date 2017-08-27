package dataSource.versionThree;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDataSuorce implements DataSource {
	private static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
	private static final String user = "SCOTT";
	private static final String password = "cong960227";
	private static int currentCount = 0;
	private static int maxCount = 10;
	private static int initCount = 5;
	private LinkedList<Connection> connecitonPool = new LinkedList<Connection>();

	public MyDataSuorce() {
		try {

			for (int i = 0; i < initCount; i++) {
				this.connecitonPool.addLast(createConnection());
				currentCount++;

			}

		} catch (SQLException e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError(e);
		}
		// try {
		// for (int i = 0; i < 3; i++) {
		// currentCount++;
		//
		// this.connecitonPool.addLast(createConnection());
		//
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

	}

	public void free(Connection conn) {
		this.connecitonPool.addLast(conn);
	}

	public Connection createConnection() throws SQLException {
		MyConnectionHandler myHandler = new MyConnectionHandler(this);
		Connection realConn = DriverManager.getConnection(url, user, password);
		Connection conn = (Connection) myHandler.bind(realConn);
		return conn;
	};

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		synchronized (this.connecitonPool) {
			if (this.connecitonPool.size() > 0) {
				return this.connecitonPool.removeFirst();

			}
			if (currentCount < maxCount) {
				currentCount++;
				return createConnection();
			}
			throw new SQLException("没有可用链接了");
		}

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

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
