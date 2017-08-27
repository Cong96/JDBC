package util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ���͹�����
 * 
 * @author Li Yongqiang
 */
public class GenericsUtils {
	/**
	 * 获取泛型的类型
	 * 
	 * @param clazz
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	public static Class getGenericType(Class clazz) {
		Type genType = clazz.getGenericSuperclass();// //得到泛型父类
		Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
		if (!(types[0] instanceof Class)) {
			return Object.class;
		}
		return (Class) types[0];
	}

	/**
	 * ��ȡ����������
	 * 
	 * @param clazz
	 * @return �����
	 */
	@SuppressWarnings("unchecked")
	public static String getGenericName(Class clazz) {
		return clazz.getSimpleName();
	}
}
