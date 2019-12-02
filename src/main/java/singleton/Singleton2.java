package singleton;
//3.内部类托管单例

public class Singleton2 {
    private Singleton2(){}
    private static class SingletonHolder{
        private static Singleton2 instance=new Singleton2();
    }
    public static Singleton2 getInstance(){
        return SingletonHolder.instance;
    }
}

/**
 * 	在这个单例中，我们通过静态内部类来托管单例，当这个单例被加载时，不会初始化单例类，只有当getInstance方法被调用的时候，才会去加载
	SingletonHolder，从而才会去初始化instance。并且，单例的加载是在内部类的加载的时候完成的，所以天生对线程友好，而且也不需要
	synchnoized关键字，可以说是兼具了以上的两个优点。

 */
