package trying.dateTest;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * @ClassName: TestCalendar
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016年11月26日 上午12:27:04
 * 
 *       Calendar 类是一个抽象类，它为“特定瞬间”与一组诸如 “YEAR”、“MONTH”、“DAY_OF_MONTH”、
 *       等日历字段之间的转换提供了一些方法，并为操作日历字段（例如获得下星期的日期）提供了一些方法。 Calendar实例化有两种方式，第一种是
 *       Calendar nowTime = new GregorianCalendar();，第二种是Calendar
 *       calendar=Calendar.getInstance();
 */
public class TestCalendar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// getTime();
	}

	@Test
	public void test() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 10, 29);
		System.out.println(calendar.getTime());
	}

	public static void getTime() {
		Calendar nowTime = new GregorianCalendar();
		int year = nowTime.get(Calendar.YEAR);
		int month = nowTime.get(Calendar.MONTH) + 1;
		int day = nowTime.get(Calendar.DAY_OF_MONTH);
		System.out.println("现在的日期是：" + year + "年" + month + "月" + day + "日");

	}

}
