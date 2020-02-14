package classloader.demo;


public class TestT {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Class cc = Class.forName("com.xryj.ExampleT");
//        Object o = cc.newInstance();
//        System.out.println(o);
        MyClassLoader myClassLoader = new MyClassLoader("/home/tyx/", null);
        MyClassLoader myClassLoader1 = new MyClassLoader("/home/tyx/", null);

        Class<?> c = myClassLoader.loadClass("com.xryj.ExampleT");
        Class<?> c1 = myClassLoader.loadClass("com.xryj.ExampleT");
        System.out.println(c.hashCode());
        System.out.println(c1.hashCode());
        System.out.println(c1 == c);

        Object o = c.newInstance();
        System.out.println(o.getClass().getClassLoader());
    }
}
