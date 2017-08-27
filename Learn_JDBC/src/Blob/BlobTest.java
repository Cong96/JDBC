package Blob;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.sql.BLOB;
import oracle.sql.CLOB;

import org.junit.Test;

import util.DbHelper;

/**
 * @ClassName: BlobTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wangcc
 * @date 2016-11-25 下午2:55:11
 * 
 */
public class BlobTest {
	public static void main(String[] args) {
		insertClob();
	}

	/**
	 * @Title: insertClob
	 * @Description: TODO 摘自深入浅出hibernate 问题在于oracle中Blob/Clob字段访问的独特方式 oracle中
	 *               Blob/Clob字段本身就拥有一个游标(cursor),JDBC必须通过游标对该字段进行操作
	 *               在字段创建之前，我们无法获取游标句柄，这意味着，我们必须首先先创建一个空的字段
	 *               再从这个空的字段中获取游标，写入我们期望保存的数据。
	 * 
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	/**
	 * @Title: insertClob
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void insertClob() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DbHelper.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into user_blob(id,name,image,resume) values(?,?,?，?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setString(2, "james");
			ps.setBlob(3, BLOB.empty_lob());
			ps.setClob(4, CLOB.empty_lob());
			ps.executeUpdate();
			ps.close();
			String sql1 = "select image,resume from user_blob where id=?  for update";
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			rs.next();
			BLOB blob = (BLOB) rs.getBlob(1);
			CLOB clob = (CLOB) rs.getClob(2);
			FileInputStream in = new FileInputStream("F:\\CLobTest\\test.txt");
			OutputStream out = blob.getBinaryOutputStream();
			byte[] buf = new byte[10240];
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();

			clob.putString(1, "This is my clob");
			String sql2 = "update user_blob set image=? and resume=? where id=?";
			ps = conn.prepareStatement(sql2);
			ps.setBlob(1, blob);
			ps.setClob(2, clob);
			ps.setInt(3, 1);
			ps.executeUpdate();
			System.out.println("DONE!");
			conn.commit();
			System.out.println("DONE!");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbHelper.free(rs, ps, conn);
		}
	}

	@Test
	public void query() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		UserBlob userBlob = new UserBlob();
		try {
			conn = DbHelper.getConnection();
			String sql = "select id,name,image,resume from user_blob where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				userBlob.setId(rs.getInt(1));
				userBlob.setName(rs.getString(2));
				userBlob.setImage(rs.getBlob(3));
				userBlob.setResume(rs.getClob(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Clob clob = userBlob.getResume();
		Reader reader = null;
		System.out.println("Dddd");
		try {
			reader = clob.getCharacterStream();
			BufferedReader br = new BufferedReader(reader);
			String s = null;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (e instanceof IOException) {
				System.out.println("流转化错误");
			}
			System.out.println("数据库读取错误");
			e.printStackTrace();
		} finally {
			DbHelper.free(rs, ps, conn);
		}
		// Blob blob = userBlob.getImage();
		// InputStream in;
		// try {
		// in = blob.getBinaryStream();
		// int b = 0;
		// while ((b = in.read()) != -1) {
		// System.out.print((char) b);
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// if (e instanceof SQLException) {
		// System.out.println("数据库操作错误");
		// } else {
		// System.out.println("读取文件错误");
		// }
		// e.printStackTrace();
		// }
	}
}
