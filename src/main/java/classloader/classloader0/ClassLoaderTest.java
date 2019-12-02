package classloader.classloader0;

import java.util.List;

public class ClassLoaderTest {  
	  
    @SuppressWarnings("rawtypes")  
    public static void main(String[] args){  
        //输出ClassLoaderText的类加载器名称  
        System.out.println("ClassLoaderText类的加载器的名称:"+ClassLoaderTest.class.getClassLoader().getClass().getName());  
        System.out.println("System类的加载器的名称:"+System.class.getClassLoader());  
        System.out.println("List类的加载器的名称:"+List.class.getClassLoader());  
          
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();  
        while(cl != null){  
            System.out.print(cl.getClass().getName()+"->");  
            cl = cl.getParent();  
        }  
        System.out.println(cl);  
    }  
      
}  
//ClassLoaderText类的加载器的名称:sun.misc.Launcher$AppClassLoader
//System类的加载器的名称:null
//List类的加载器的名称:null
//sun.misc.Launcher$AppClassLoader->sun.misc.Launcher$ExtClassLoader->null

//因为System类，List，Map等这样的系统提供jar类都在rt.jar中，所以由BootStrap类加载器加载，因为BootStrap是祖先类，
//不是Java编写的，所以打印出class为null

//把本类打成jar包(my_lib.jar)，放在F:\JDK1\jdk1.7.0_75\jre\lib\ext下，再运行就是这样
//ClassLoaderText类的加载器的名称:sun.misc.Launcher$ExtClassLoader
//System类的加载器的名称:null
//List类的加载器的名称:null
//sun.misc.Launcher$ExtClassLoader->null
//首先AppClassLoader类加载器发请求给ExtClassLoader,然后ExtClassLoader发请求给BootStrap，但是BootStrap没有找到ClassLoaderTest类，所以交给ExtClassLoader处理，这时候ExtClassLoader在my_lib.jar中找到了ClassLoaderTest类，所以就把它加载了，然后结束了。
