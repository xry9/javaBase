package classloader.demo;

public class Example {
	private Example	 example  ;
	public void setExample(Object instance ) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		System.out.println("start.........");
		System.out.println(new Example().getClass().getClassLoader());
		System.out.println("end.........");
		this.example = (Example)instance;

	}
}
