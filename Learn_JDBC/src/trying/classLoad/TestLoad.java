package trying.classLoad;

public class TestLoad {
	static {
		System.out.println("静态代码块");
	}

	public TestLoad() {
		System.out.println("创建类实例");
	}

}
