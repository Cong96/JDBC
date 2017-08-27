package trying;

import java.util.Properties;

public class PropertiesTest {
	public static void main(String[] args) {
		// Properties prop = new Properties();
		// FileInputStream in = null;
		// try {
		// in = new FileInputStream("src/test.properties");
		// prop.load(in);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// if (e instanceof FileNotFoundException) {
		// System.out.println("找不到指定路径");
		// } else {
		// System.out.println("加载属性文件出错");
		//
		// }
		// e.printStackTrace();
		// } finally {
		// try {
		// in.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		Properties prop = PropertiesUtil.getProperties("src/test.properties");
		System.out.println("driver:" + prop.getProperty("jdbc.driver"));
		System.out.println("url:" + prop.getProperty("jdbc.url"));
		// Properties prop = PropertiesUtil
		// .getProperties("src/dao_config.properties");
		// System.out.println("userDao:" + prop.getProperty("userDao"));
	}

}
