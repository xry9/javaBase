package singleton;

/**
 * 上面的单例获取实例时，是需要加上同步的，如果不加上同步，在多线程的环境中， 当线程1完成新建单例操作，而在完成赋值操作之前，线程2就可能判
 * 断instance为空，此时，线程2也将启动新建单例的操作，那么多个就出现了 多个实例被新建，也就违反了我们使用单例模式的初衷了。
 * 
 */
public class Singleton1 {
	private Singleton1() {
		// System.out.println("createSingleton");
	}

	private static Singleton1 instance = null;

	public static synchronized Singleton1 getInstance() {
		return instance == null ? instance = new Singleton1() : instance;
	}
}
