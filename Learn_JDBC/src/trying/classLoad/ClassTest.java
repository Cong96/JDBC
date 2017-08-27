package trying.classLoad;

public class ClassTest {
	public static void main(String[] args) {
		try {
			Class.forName("trying.classLoad.TestLoad");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestLoad testLoad = new TestLoad();
		TestLoad testLoad1 = new TestLoad();
	}

}
