package trying.testException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestException {

	/**
	 * * 任何方法往外抛能处理的异常的时候都有一种简单的写法：“throws Exception”，
	 * 因为Exception类是所有能处理的异常类的根基类，因此抛出Exception类就会抛出所有能够被处理的异常类里了。 使用“throws
	 * Exception”抛出所有能被处理的异常之后，这些被抛出来的异常就是交给JAVA运行时系统处理了，
	 * 除了在做测试以外，在实际当中编程的时候，在main方法里抛Exception是一个非常不好的编程习惯
	 * ，应该使用try……catch去捕获异常并处理掉捕获后的异常。
	 * 不能直接在main方法里把Exception抛出去交给JAVA运行时系统出力就完事了，这是一种不负责任的表现。
	 * 而处理的方法是把这些异常的相关错误堆栈信息全部打印出来。 =======================
	 * ======================= 不写修饰符时 默认为friendly ======================= 作用域
	 * 当前类 同一package 子孙类 其他package
	 * 
	 * public √ √ √ √
	 * 
	 * protected √ √ √ ×
	 * 
	 * friendly √ √ × ×
	 * 
	 * private √ × × ×
	 * 
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f2();
	}

	public void fn() throws Exception {
	};

	public void m1(int i) {
	}

	public void m2(int i) {
		if (i == 0) {
			throw new ArithmeticException("i不能为0");
		}
	}

	public static void f() throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream("D:\\test.txt");
		int b = 0;
		while ((b = in.read()) != -1) {
			System.out.print((char) b);
		}
		in.close();
	}

	public static void f2() {
		try {
			f();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("找不到指定路径");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取异常");
			e.printStackTrace();
		}
	}

}
