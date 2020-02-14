package other;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassLoaderTest {
    public static void main(String[] args){
//        ClassLoaderDemo.test();
//        MyClassLoaderTest.test();
        NameSpace.test();
    }
}

/*
    对应任意一个class，都需要有加载它的类加载器和这个类本省确立其在JVM中的唯一性，
    这也就是运行时包。
 */

/*
    10.1.1 根类加载器介绍
        Bootstrap类加载器，用C++编写，主要负责虚拟机核心类库的加载，比如java.lang包
        都是以根加载器加载的。

    10.1.2 扩展类加载器介绍
        主要用于加载JAVA_HOME下的jre\lb\ext子目录里面的类库。

    10.1.3 系统类加载器介绍
        负责加载classpath下的类苦苦资源。
 */
class ClassLoaderDemo{
    public static void test(){
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        //根加载器是的不到引用的
        System.out.println("BootStrap:"+String.class.getClassLoader());
    }
}

/*
    10.2.1 自定义类加载器，问候世界
 */
class HelloWorld
{
    static
    {
        System.out.println("Hello World Class is Initialized.");
    }

    public String welcome()
    {
        return "Hello World";
    }
}

class MyClassLoader extends ClassLoader{
    private final static Path DEFAULT_CLASS_DIR=
            Paths.get("C:\\Users\\Administrator\\Desktop\\test");

    private final Path classDir;

    public MyClassLoader(){
        super();
        this.classDir=DEFAULT_CLASS_DIR;
    }

    public MyClassLoader(String classDir){
        super();
        this.classDir=Paths.get(classDir);
    }

    public MyClassLoader(String classDir,ClassLoader parent){
        super(parent);
        this.classDir=Paths.get(classDir);
    }

    private byte[] readClassBytes(String name)
        throws ClassNotFoundException{
        String classPath = name.replace(".","/");
        Path classFullPath = classDir.resolve(Paths.get(classPath+".class"));
        if(!classFullPath.toFile().exists()){
            throw new ClassNotFoundException("The class "+name+" not found.");
        }

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath,baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("The class "+name+" occur error.");
        }
    }

    public Class<?> findClass(String name)
        throws ClassNotFoundException{
        byte[] classBytes = this.readClassBytes(name);

        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load the class "+name);
        }

        //调用defineClass方法定义class
        return this.defineClass(name,classBytes,0,classBytes.length);
    }
}

class MyClassLoaderTest{
    public static void test(){
        try{
            MyClassLoader classLoader = new MyClassLoader();

            Class<?> myClass = classLoader.loadClass("test.HelloWorld");

            System.out.println(myClass.getClassLoader());

            Object helloWorld = myClass.newInstance();
            System.out.println(helloWorld);

            Method welcomeMethod = myClass.getMethod("welcome");
            String result = (String)welcomeMethod.invoke(helloWorld);
            System.out.println("Result:"+result);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

/*
    10.2.2 双亲委托机制详细介绍

    当一个类加载器被调用了loadClass之后，它并不会直接将其加载，而是先交给当前类
    加载器的父加载器尝试加载（这个地方应该只是传递，绝对没有尝试加载，我从示意图中
    看出来的），知道最顶层的父加载器，然后再一次向下进行加载。

    我们担心HelloWorld.class被系统加载器加载，我们有什么办法不用删除类文件，依旧
    不用担心被系统加载器加载？
        方案一：绕过系统加载器，将扩展类加载器作为MyClassLoader的父加载器
        方案二：在构造MyClassLoader的时候指定其父类加载器为null

    LoadClass方法流程：
        1.从当前类加载器的已加载类缓存中根据类的全路径名查询是否存在该类，如果存在
            则直接返回。
        2.如果当前类（加载器）存在父加载器，则调用父加载器的loadClass方法对其进
            行加载。
        3.如果当前类加载器不存在父加载器，则直接调用根加载器对该类进行加载。
        4.如果当前类的所有父类加载器，都没有成功的记载class，则尝试使用当前类加载
            器的findClass方法对其进行加载。
        5.如果最后类被成功加载了，则做一些性能统计工作

 */

/*
    10.2.3 破坏双亲委托机制

    需求：
        JDK提供的双亲委托机制并非一个强制的模型，程序咖啡啊人员是可以对其记性灵活发挥
        破坏这种委托机制的，比如我们想要在程序运行时某个模块功能的升级，甚至是在不停止
        服务的前提下增加新的功能，这就是我们常说的热部署。热部署首先要卸载掉加载该模块
        所有Class的类加载器，卸载类加载器会导致所有类的卸载，很显然我们无法对JVM三大
        内置加载器进行卸载，我们只有通过控制自定义类加载器才能做到这一点。

    案例流程：
        1.根据类的全路径名称进行加锁，确保每一个类在多线程的情况下只被加载一次。
        2.到已加载了的缓存中查看该类是否已经被加载了，如果已被加载，则直接返回
        3.若缓存中没有被加载的类，则需要对其进行首次加载，如果类的全路径是以
            java和javax开头，则直接委托给系统类加载器对其进行加载。
        4.如果不是以java和javax开头，则尝试我们自定义的类加载器进行加载
        5.若自定义类加载器仍然没有完成对类的加载，则委托给父类加载器进行加载或者
            系统类加载器进行加载。
        6.经过若干次尝试之后，如果还是无法加载，则抛出异常
 */
class BrokerDelegateClassLoader extends ClassLoader {
    private final static Path DEFAULT_CLASS_DIR =
            Paths.get("C:\\Users\\Administrator\\Desktop\\test");

    private final Path classDir;

    public BrokerDelegateClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public BrokerDelegateClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    public BrokerDelegateClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    private byte[] readClassBytes(String name)
            throws ClassNotFoundException {
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException("The class " + name + " not found.");
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("The class " + name + " occur error.");
        }
    }

    public Class<?> findClass(String name)
            throws ClassNotFoundException {
        byte[] classBytes = this.readClassBytes(name);

        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("Can not load the class " + name);
        }

        //调用defineClass方法定义class
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            Class<?> klass = findLoadedClass(name);
            if (klass == null) {
                if (name.startsWith("java.") || name.startsWith("javax")) {
                    try {
                        klass = getSystemClassLoader().loadClass(name);
                    } catch (Exception e) {
                        //ignore
                    }
                } else {
                    try {
                        klass = this.findClass(name);
                    } catch (ClassNotFoundException e) {
                        //ignore
                    }

                    if (klass == null) {
                        if (getParent() != null) {
                            klass = getParent().loadClass(name);
                        } else {
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }
            if (null == klass) {
                throw new ClassNotFoundException("The class " + name + " not found.");
            }
            if (resolve) {
                resolveClass(klass);
            }
            return klass;
        }
    }
}

/*
    10.2.4 类加载器命名空间、运行时包、类的卸载

    1.类加载器命名空间
        每个类加载器实例都有各自的命名空间，命名空间是有该加载器及所有父加载器所构成的，
        因此在每个类加载器中同一个class都是独一无二的。

        （一）不同类加载器加载用同一个class
        （二）相同类加载器，不同加载器实例，加载用一个class
 */
class NameSpace{
    public static void test(){
        ClassLoader classLoader = NameSpace.class.getClassLoader();

        try {
            Class<?> a = classLoader.loadClass("Test.class");
            Class<?> b = classLoader.loadClass("Test.class");

            System.out.println(a.hashCode());
            System.out.println(b.hashCode());
            System.out.println(a==b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/*
    2.运行时包
        在JVM运行时class会有一个运行时包，运行时的包是由类加载器的命名空间和类的
        全限定名称共同组成的。这样做的好处同样是出于安全和封装的考虑，在java.lang.
        String中存在仅包可见的方法void getChars(char[] var1, int var2)，
        java.lang包以外的class是无法直接对其访问的。假设用户想自己定义一个类
        java.lang.HackString，并且由自定义的类加载器进行加载，尝试访问getChars
        方法，由于java.lang.HashString和java.lang.String是由不同的类加载器进
        性加载的，它们拥有各自不同的运行时包，因此HackString是无法访问java.lang.
        String的包可见方法及成员变量的。
            ——说的我们在用一个运行时包里，可以访问一样，这段描述
                我还是有点不理解
                
    3.初始化类加载器
    
    问题：
        由于运行时包的存在，JVM规定了不同的运行时包下的类彼此之间是不可以进行访问的，
        为什么我们在开发的程序中可以访问java.lang包下的类呢？我们知道，java.lang
        包是由根加载器进行加载的，而我们开发的程序或者第三方类库一般是由系统类加载器
        加载的，为什么我们能够new Object()或者new String()
        
    这段话描述的不明不白，只能凑合着理解：
        每一个类在经过ClassLoader的加载之后，在虚拟机中都会有对应的Class实例，如果某个类
        C被类加载器CL加载，那么CL就被称为C的初始化类加载器。JVM为每一个类加载器维护了一个
        列表，该列表中记录了该类加载器作为初始化类加载器的所有class，在加载一个类时，JVM
        使用这些列表来判断该类是否已经被加载过了，是否需要首次加载。
        
        根据JVM规范的规定，在类的加载过程中，所有参与的类加载器，即使没有亲自加载过类，也都
        会被标识为该类的初始类加载器，比如java.lang.String首先经过了BroderDelegateClass
        类加载器，依次又经过了系统类加载器、扩展类加载器、根类加载器。这些类加载器都是java
        .lang.String的初始化类加载器，JVM会在每一个类加载器维护的列表中添加该class类型
        
            ——Amazing！！！
 */

/*
    4.类的卸载
    
    一个Class满足三个条件时被回收：
        1.该类所有的实例都被GC；
        2.加载该类的ClassLoader实例被回收
        3.该类的class实例没有在其他地方被引用
 */