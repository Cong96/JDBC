package trying.dateTest;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * @ClassName: TestDateFormat
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016年11月26日 上午12:12:23
 * 
 *       DateFormat 是日期/时间格式化子类的抽象类，它以与语言无关的方式格式化并解析日期或时间。
 */
public class TestDateFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getDateStyle();
		getDate();
	}

	@Test
	public void getDateAll() {
		Date date = new Date();
		DateFormat dateDef = DateFormat.getDateInstance();
		// 为SHORT MEDIUM时，Locale没起作用，依旧是英文格式
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		DateFormat dateLocaleFormat = DateFormat.getDateInstance(
				DateFormat.LONG, Locale.CHINA);
		DateFormat dateLocaleFormat2 = DateFormat.getDateInstance(
				DateFormat.LONG, Locale.CHINESE);
		DateFormat dateTimeDef = DateFormat.getDateTimeInstance();
		DateFormat dateTime = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG);
		DateFormat dateTimeLocale = DateFormat.getDateTimeInstance(
				DateFormat.FULL, DateFormat.FULL, Locale.CHINA);
		DateFormat timeDef = DateFormat.getTimeInstance();
		DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.LONG);
		DateFormat timeLocale = DateFormat.getTimeInstance(DateFormat.SHORT,
				Locale.CHINA);
		System.out.println("无参数的getDateInstance:" + dateDef.format(date));
		System.out
				.println("指定style的getDateInstance:" + dateFormat.format(date));
		System.out.println("指定style和本地语言的getDateInstance:(china)"
				+ dateLocaleFormat.format(date));
		System.out.println("指定style和本地语言的getDateInstance:(chinese)"
				+ dateLocaleFormat2.format(date));
		System.out
				.println("无参的getDateTimeInstance:" + dateTimeDef.format(date));
		System.out.println("指定style的getDateTimeInstance:"
				+ dateTime.format(date));
		System.out.println("指定style和本地语言的getDateTimeInstance:"
				+ dateTimeLocale.format(date));
		System.out.println("无参数的getTimeInstance:" + timeDef.format(date));
		System.out
				.println("指定style的getTimeInstance:" + timeFormat.format(date));
		System.out.println("指定style和本地语言的getTimeInstance:"
				+ timeLocale.format(date));
		System.out.println(dateFormat.format(date));

		Date date1 = new Date();
		System.out.println(date1);
	}

	public static void getDate() {
		Date date = new Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
				Locale.CHINA);
		DateFormat df1 = DateFormat.getDateInstance();
		// 2016年11月29日 星期二 上午11时07分28秒 CST
		DateFormat df2 = DateFormat.getDateTimeInstance(DateFormat.FULL,
				DateFormat.FULL, Locale.CHINA);
		DateFormat df3 = DateFormat.getDateTimeInstance(DateFormat.FULL,
				DateFormat.FULL, Locale.CHINA);
		// DateFormat df2 = DateFormat.getDateTimeInstance();
		String s = df.format(date);
		String s1 = df1.format(date);
		String s2 = df2.format(date);
		String s3 = df3.format(date);
		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
	}

	public static void getDateStyle() {
		DateFormat df = DateFormat.getTimeInstance(DateFormat.FULL,
				Locale.CHINA);
		DateFormat df2 = DateFormat.getTimeInstance(DateFormat.FULL);
		String s = df.format(new Date());
		String s2 = df2.format(new Date());
		System.out.println("FULL:" + s);
		System.out.println("LONG:" + s2);
	}
}
