package factory;

import java.util.Properties;

import trying.PropertiesUtil;
import dao.BaseDao;
import dao.UserDao;

public class Daofactory {
	private static final String dao_config = "src/dao_config.properties";

	public static UserDao getUserdao() {
		Class<?> clazz;
		UserDao userDao = null;
		Properties prop = PropertiesUtil
				.getProperties("src/dao_config.properties");
		String classname = prop.getProperty("userDao");
		try {
			clazz = Class.forName(classname);
			userDao = (UserDao) clazz.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userDao;

	}

	public static String getClassName(String className) {
		Properties prop = PropertiesUtil.getProperties(dao_config);
		return prop.getProperty(className);
	}

	public static BaseDao getDao(String className) {
		BaseDao baseDao = null;
		String name = getClassName(className);
		try {
			Class<?> clazz = Class.forName(name);
			baseDao = (BaseDao) clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baseDao;
	}

}