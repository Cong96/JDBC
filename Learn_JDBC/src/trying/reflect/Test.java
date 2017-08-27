package trying.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MyInterface {
	public String say(String name, int age);
}

class RealSubject1 implements MyInterface {
	public String say(String name, int age) {
		return "name:" + name + "age:" + age;

	};
}

class MyInvocationHandler1 implements InvocationHandler {
	private Object object;

	public Object bind(Object object) {
		this.object = object;
		return Proxy.newProxyInstance(object.getClass().getClassLoader(),
				object.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		if (method.getName().equals("say")) {

			// Class<?>[] parameterTypes = method.getParameterTypes();
			// for (Class<?> clas : parameterTypes) {
			// String parameterName = clas.getName();
			// System.out.println("参数名称:" + parameterName);
			// }
			args[0] = "james";
			System.out.println("kobe");
			return method.invoke(object, args);
		}
		return null;
	}

}

public class Test {

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyInvocationHandler1 myHandler1 = new MyInvocationHandler1();
		MyInterface myInterface = (MyInterface) myHandler1
				.bind(new RealSubject1());
		String name = myInterface.say("kobe", 38);
		System.out.println(name);
	}

}
