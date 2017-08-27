package trying.classLoad;

import java.util.Random;

class Initable {
	public static final int staticFinal = 47;
	public static final int staticFinal2 = ClassInitializationTest.rand
			.nextInt(1000);
	static {
		System.out.println("初始化Initable");
	}
}

class Initable2 {
	public static int staticNotFinal = 147;
	static {
		System.out.println("初始化Initable2");
	}
}

class Initable3 {
	public static int staticNotF = 74;
	static {
		System.out.println("初始化 Intiable3");
	}

}

/**
 * @ClassName: ClassInitializationTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016-11-30 上午11:55:45 1.首先说一下 Class initable =
 *       Initable.class;，他的意思是仅仅使用Class的方法将
 * 
 *       Initable.java的字节码装载到虚拟机中，得到对类的引用，并不初
 * 
 *       始化类，更不可能执行static代码块
 * 
 *       2.加载完字节码后，直接访问Initable的static final常量，没有任何
 * 
 *       问题，说明虽然类没有被初始化，但是static final常量已经可以被
 * 
 *       访问（这个是解释接口的重点）
 * 
 * 
 *       3.当访问Initable的在编译时候没有指定值的static final常量的时
 * 
 *       候，他就不能直接访问，看运行结果可知道，他需要初始化类，
 * 
 *       即调用static静态代码块，才可以使用.
 * 
 * 
 *       4.当调用Initable2的static变量的时候，类就必须被执行初始化才能
 * 
 *       被访问 5.当调用Class.Forname()方法的时候，将类装载进入虚拟机，就
 * 
 *       果断执行了static代码块，初始化后才能继续访问.
 * 
 * 
 *       6.因此总结一下，一个类的初始化准备工作如下
 * 
 *       6.1加载：这个由类加载器完成，他去查找字节码，并且创建一个
 * 
 *       Class对象
 * 
 *       6.2连接：验证类中的字节码，为静态域分配存储空间，并且如果
 * 
 *       必须的话，将解析这个类创建的对其他类的引用
 * 
 *       6.3初始化：如果这个类有超类，则对其进行初始化，执行静态初
 * 
 *       始化器和静态初始化代码块
 * 
 *       6.4初始化被延迟到了对静态方法（构造器是隐士的静态方法）或
 * 
 *       非静态方法的首次引用才执行
 * 
 *       7.由上面可以知道，接口不能被初始化，如果想定义常量，必须是
 * 
 *       在接口的字节码被装载到虚拟机的时候他的常量就得被访问，所 以他必须是static final的
 * 
 *       8static final叫编译期常量，不需要初始化就能读取。
 */
public class ClassInitializationTest {
	public static Random rand = new Random(47);

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws ClassNotFoundException {

		Class initable = Initable.class;
		System.out.println("After creating Initable ref");
		System.out.println(Initable.staticFinal);
		System.out.println(Initable.staticFinal2);
		System.out.println(Initable2.staticNotFinal);// 访问static的值
		Class initable3 = Class.forName("trying.classLoad.Initable3");// 装载并且初始化
		System.out.println("After creating Initable3 ref");
		System.out.println(Initable3.staticNotF);
	}
}
