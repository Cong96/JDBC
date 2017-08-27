package trying.classLoad;

class Parent {
	protected static int c = 10;
	static {
		System.out.println("Parent Initial");
	}

	public static void say() {
		System.out.println("number=" + c);
	}
}

class Son extends Parent {
	static {
		System.out.println("Son Initial");
	}

}

/**
 * @ClassName: TestInitial
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016-11-30 下午1:50:06
 * 
 *       由此可见，一般的，我们在某个类中定义了其他类的成员变量引用，只要该变量没有 new 出一个新的对象，则 JVM
 *       也不会初始化这个类，类此时只是被加载了而已，而没有链接和初始化。
 */
public class TestInitial {

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	static {
		System.out.println("TestInitial Initial");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Son.c);
		Son.say();

	}

}
