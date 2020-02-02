package classloader.classloader0;

import java.net.URL;

import sun.misc.*;

//public class ClassLoaderTest1 {
//	  public static void main(String[] args) throws Exception {
//
//	    ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
//	    ClassLoader extClassloader = appClassLoader.getParent();
//	    ClassLoader bootstrapLoader  = extClassloader.getParent();
//	    System.out.println("the bootstrapLoader : " + bootstrapLoader);
//	    System.out.println("the extClassloader : " + extClassloader);
//	    System.out.println("the appClassLoader : " + appClassLoader);
//	    System.out.println();
//	    System.out.println("bootstrapLoader加载以下文件：");
//	    URL[] urls = Launcher.getBootstrapClassPath().getURLs();//虽然报错仍可以执行
//	    for (int i = 0; i < urls.length; i++) {
//	        System.out.println(urls[i]);
//	    }
//	    System.out.println();
//	    System.out.println("extClassloader加载以下文件：");
//	    System.out.println(System.getProperty("java.ext.dirs"));
//	    System.out.println();
//	    System.out.println("appClassLoader加载以下文件：");
//	    System.out.println(System.getProperty("java.class.path"));
//	  }
//	}


//the bootstrapLoader : null
//the extClassloader : sun.misc.Launcher$ExtClassLoader@5fcf29
//the appClassLoader : sun.misc.Launcher$AppClassLoader@1a28362
//
//bootstrapLoader加载以下文件：
//file:/F:/JDK1/jdk1.7.0_75/jre/lib/resources.jar
//file:/F:/JDK1/jdk1.7.0_75/jre/lib/rt.jar
//file:/F:/JDK1/jdk1.7.0_75/jre/lib/sunrsasign.jar
//file:/F:/JDK1/jdk1.7.0_75/jre/lib/jsse.jar
//file:/F:/JDK1/jdk1.7.0_75/jre/lib/jce.jar
//file:/F:/JDK1/jdk1.7.0_75/jre/lib/charsets.jar
//file:/F:/JDK1/jdk1.7.0_75/jre/lib/jfr.jar
//file:/F:/JDK1/jdk1.7.0_75/jre/classes
//
//extClassloader加载以下文件：
//F:\JDK1\jdk1.7.0_75\jre\lib\ext;C:\Windows\Sun\Java\lib\ext
//
//appClassLoader加载以下文件：
//F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\bin;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\dom4j-1.6.1.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\jaxen-1.1.1.jar;F:\Myeclipse10ZnZuang\MyEclipse Professional\plugins\org.junit_4.10.0.v4_10_0_v20120426-0900\junit.jar;F:\Myeclipse10ZnZuang\MyEclipse Professional\plugins\org.hamcrest.core_1.1.0.v20090501071000.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\json-lib-2.3-jdk15.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\commons-lang-2.4.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\ezmorph-1.0.6.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\commons-logging-1.1.1.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\commons-collections-3.2.1.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\commons-beanutils-1.8.2.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\sunjce_provider.jar;F:\WorkspacesforMyeclipse10\MyEclipse Professional\java\lib\ikexpression-2.1.2.jar
