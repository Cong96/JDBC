package trying.testException;

/**
 * @ClassName: MyException
 * @Description: TODO(这里用一句话描述这个类的作用) 自定义异常 必须继承Exception 所有可处理异常都必须继承Exception
 * @author A18ccms a18ccms_gmail_com
 * @date 2016-11-25 下午4:18:42
 * 
 */
public class MyException extends Exception {

	private int id;

	public MyException(String message, int id) {
		super(message);
		this.id = id;
	}

	public int getId() {
		return id;
	};
}
