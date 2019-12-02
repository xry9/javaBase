package reflection;

public class TestClassForName {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class c = Class.forName("reflection.BaseClass");
		System.out.println(c.newInstance());
	}
}
