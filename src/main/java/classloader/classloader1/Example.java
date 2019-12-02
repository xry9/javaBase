package classloader.classloader1;

public class Example {
	private Example	 example  ;
	public void setExample(Object instance ) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		System.out.println(instance.getClass().getClassLoader());
		System.out.println("start.........");
		System.out.println(new Example().getClass().getClassLoader());
		//是不是因为Example类已被加载过了，所以new这种形式也没有重新用系统加载器加载该类
//		System.out.println(BaseClass.class.getClassLoader().loadClass("classloader.classloader1.Example").getClassLoader());
//		Class c = BaseClass.class.getClassLoader().loadClass("classloader.Example");
//		Example e1 = (Example) c.newInstance();
		
		System.out.println("end.........");
		this.example = (Example)instance;
		System.out.println("Hello TongYongxu");
	}
}
