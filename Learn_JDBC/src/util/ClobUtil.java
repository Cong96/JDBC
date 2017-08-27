package util;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;

public class ClobUtil {
	public static String clobToString(Clob clob) {
		StringBuffer sb = null;
		Reader reader = null;
		try {
			reader = clob.getCharacterStream();
			BufferedReader br = new BufferedReader(reader);
			String s = null;
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
