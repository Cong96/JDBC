package trying.classLoad;

public class Test {
	public static void main(String[] args) {
		try {
			// Class.forName("trying.classLoad.TestLoad");
			// Class.forName("trying.classLoad.TestLoad", true,
			// Test.class.getClassLoader());
			Class.forName("trying.classLoad.TestLoad", true,
					Test.class.getClassLoader());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
