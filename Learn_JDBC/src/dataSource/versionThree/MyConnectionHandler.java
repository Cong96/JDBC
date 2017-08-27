package dataSource.versionThree;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class MyConnectionHandler implements InvocationHandler {
	private Connection conn;
	private MyDataSuorce dataSuorce;
	private Connection wrappedConnection;

	public MyConnectionHandler(MyDataSuorce dataSuorce) {
		this.dataSuorce = dataSuorce;
	}

	public Object bind(Connection conn) {
		this.conn = conn;
		// wrappedConnection = (Connection)
		// Proxy.newProxyInstance(conn.getClass()
		// .getClassLoader(), conn.getClass().getInterfaces(), this);
		/**
		 * public static Object newProxyInstance(ClassLoader loader, Class<?>[]
		 * interfaces, InvocationHandler h) throws IllegalArgumentException
		 * loader:定义代理类的类加载器 interfaces:代理类要实现的接口列表 h - 指派方法调用的调用处理程序
		 * 返回：一个带有代理类的指定调用处理程序的代理实例，它由指定的类加载器定义，并实现指定的接口
		 */
		// wrappedConnection = (Connection)
		// Proxy.newProxyInstance(conn.getClass()
		// .getClassLoader(), conn.getClass().getInterfaces(), this);
		wrappedConnection = (Connection) Proxy.newProxyInstance(conn.getClass()
				.getClassLoader(), new Class[] { Connection.class }, this);
		return wrappedConnection;
	}

	/*
	 * (non-Javadoc) 在这个函数中我们可以对被代理对象的相应方法做相应的处理，可以对实际操作对象的对应方法做调用前后的一些操作 Spring
	 * 切面编程以及过滤器，拦截器等等都是这个原理。
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub

		if ("close".equals(method.getName())) {
			dataSuorce.free(conn);

		} else {
			/**
			 * public Object invoke(Object obj, Object... args) throws
			 * IllegalAccessException, IllegalArgumentException,
			 * InvocationTargetException
			 */
			// method.invoke(obj,args);返回使用参数 args 在 obj 上指派该对象所表示方法的结果
			// 这里一定要加上return 否则就变成了不管使用Connection对象的任何方法都return null这时就会出错了

			return method.invoke(conn, args);
		}
		return null;
		// 不一定要return 一个特定的值，千万不要思维定式
	}

}
