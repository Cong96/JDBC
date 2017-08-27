package trying.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Subject {
	public String say(String name, int age);
}

class RealSubject implements Subject {

	@Override
	public String say(String name, int age) {
		// TODO Auto-generated method stub
		return "name:" + name + "age:" + age;
	}

}

class MyInvocationHandler implements InvocationHandler {
	private Object object;

	public Object bind(Object object) {
		this.object = object;
		/*
		 * static Object newProxyInstance(ClassLoader loader, Class<?>[]
		 * interfaces, InvocationHandler h)
		 * 　newProxyInstance方法用来返回一个代理对象，这个方法总共有3个参数，ClassLoader
		 * loader用来指明生成代理对象使用哪个类装载器，这个类加载器和真实业务对象并没有关系，只是指定生该代理对象的类装载器而已
		 * 也就是说，指定到底他是在哪个类中生成的。 Class<?>[]
		 * interfaces用来指明生成哪个对象的代理对象，通过接口指定，InvocationHandler h
		 * 用来指明产生的这个代理对象要做什么事情。 所以我们只需要调用newProxyInstance方法就可以得到某一个对象的代理对象了。
		 */
		Object obj = Proxy.newProxyInstance(object.getClass().getClassLoader(),
				object.getClass().getInterfaces(), this);
		return obj;
	}

	// invoke，对目标业务对象的处理
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		if (method.getName().equals("say")) {
			return "代理";
		} else {
			return method.invoke(object, args);
		}

	}

}

/**
 * @ClassName: TestInvocation
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016-11-28 下午2:50:04
 * 
 *       动态代理是框架的基础！ 我们在开发中之所以要产生一个对象的代理对象，主要用于拦截对真实业务对象的访问。
 */
public class TestInvocation {
	public static void main(String[] args) {
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
		Subject sub = (Subject) myInvocationHandler.bind(new RealSubject());
		String name = sub.say("kobe", 38);
		System.out.println(name);
	}
}
