package classloader.classloader1;

import java.lang.reflect.Method;

public class TestExample {
	public static void main(String[] args) {
		//测试同一个类，不同类加载器加载
		//获取类所在的路径
		String classPath = "I:\\wp\\javaBase\\bin1";
		//打要测试的类编译好，放在一个其它路径，删除该测试类，再运行，
		//如果这样可以，从网络上接收类字节码运行自然不在话下，而且亲测好使
 		MyClassLoader msl1 = new MyClassLoader(classPath);
		MyClassLoader msl2 = new MyClassLoader(classPath);
		String className = "classloader.classloader1.Example";

		try {
			//获取class对象
//			Class c = Class.forName(className, true, msl1);
//			System.out.println(c.getClassLoader());
			Class<?> class1 = msl1.findClass(className);
			Class<?> class2 = msl2.findClass(className);
			System.out.println("ClassLoader:"+class1.getClassLoader());

			//构造对象
			Object obj1 = class1.newInstance();
			Object obj2 = class1.newInstance();
//			System.out.println(obj2 instanceof Example);
//			obj2 = new Example();
//			System.out.println(obj2 instanceof Example);
			
			System.out.println("obj1:"+obj1+"\n"+"obj2:"+obj2);
			//执行方法
			Method setExampleMethod = class1.getMethod("setExample", Object.class);
			setExampleMethod.invoke(obj1, obj2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
