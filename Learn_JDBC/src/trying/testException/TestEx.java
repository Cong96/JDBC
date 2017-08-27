package trying.testException;

public class TestEx {

	/**
	 * @Title: main
	 * @Description: TODO 异常是运行期间出现的错误,运行期间出现错误以后Java处理这种错误的方法首先是寻找catch块
	 *               查看是否有catch块捕获异常,如果存在，就会自动跳到catch处去处理异常
	 *               如果没有catch块，java会把这个错误跑出去，然后将相关错误信息打印出来。
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 1, 2, 3 };
		System.out.println(arr[2]);
		try {
			System.out.println(arr[2] / 0);
		} catch (ArithmeticException e) {
			// TODO: handle exception
			System.out.println("除数不能为0");
			e.printStackTrace();
		}
		System.out.println("ddd");
	}
}
