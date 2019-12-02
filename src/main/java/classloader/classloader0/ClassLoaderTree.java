package classloader.classloader0;

import java.util.zip.ZipException;

public class ClassLoaderTree {

	public static void main(String[] args) {
		ClassLoader loader = ClassLoaderTree.class.getClassLoader();
		System.out.println(ClassLoader.getSystemClassLoader());
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
//			sun.misc.Launcher$AppClassLoader@1a9ec16
//			sun.misc.Launcher$ExtClassLoader@cac02f
		}
//		对于开发人员编写的类加载器来说，其父类加载器是加载此类加载器 Java 类的类加载器。因为类加载器Java类如同其它的
//		Java 类一样，也是要由类加载器来加载的。一般来说，开发人员编写的类加载器的父类加载器是系统类加载器
		System.out.println(Object.class.getClassLoader());
		Class c = new ZipException().getClass();
		System.out.println(c.getClassLoader());
		System.out.println(Sample.class.getClassLoader());
	}
}
