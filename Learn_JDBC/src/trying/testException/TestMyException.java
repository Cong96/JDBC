package trying.testException;

import org.junit.Test;

public class TestMyException {

	/**
	 * @Title: main
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestMyException t = new TestMyException();
		t.manage();
	}

	public void register(int num) throws MyException {
		if (num < 0) {
			throw new MyException("人数不可为负数", 1);
		}
		System.out.println("人数为：" + num);
	};

	@Test
	public void manage() {
		try {
			register(-1);
		} catch (MyException e) {
			// TODO: handle exception
			System.out.println("登记失败，错误码：" + e.getId());
			e.printStackTrace();
		}
		System.out.println("操作结束");
	}

}
