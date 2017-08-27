package trying.dateTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * @ClassName: TestSimpleDateFormat
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016年11月26日 上午12:11:07
 * 
 *       SimpleDateFormat类主要用于完成日期之间的转换 你需要指定一个模板 2016年-11月-26日-12时-09分-56秒 ==
 * 
 *       Y yyyy 年份 M MM 月 dd 日 HH 时(24小时制) hh 时(12小时制) mm 分 ss秒 S毫秒
 */
public class TestSimpleDateFormat {

	public static void main(String[] args) {
		StringtoDate();
	}

	@Test
	public void retest() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S");
		String s = "2016-11-29-09-12-12-123";
		DateFormat df = DateFormat.getDateInstance();
		Date date = null;
		try {
			date = sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
	}

	public static void StringtoDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年-MM月-dd日-hh时-mm分-ss秒");
		String s = "2016年-11月-26日-12时-09分-56秒";
		Date date = null;
		try {
			date = sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(date);
	}

	public static void testDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy年-MM月-dd日-hh时-mm分-ss秒-S毫秒");
		String s = sdf.format(date);
		System.out.println(s);
	}

	public static void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-S");
		String s = sdf.format(System.currentTimeMillis());
		System.out.println(s);

	}
}