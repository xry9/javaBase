package singleton;

import org.junit.Test;

/**
 * 这是最简单的单例，这种单例最常见，也很可靠！它有个唯一的缺点就是无法完成 延迟加载——即当系统还没有用到此单例时，单例就会被加载到内存中。
 * 在这里我们可以做个这样的测试：
 */
public class Singleton {
	private Singleton() {
		// System.out.println("createSingleton");
	};

	// 私有构造方法private 用了private修饰后只有类本身或与本类有继承关系的类可以调用
	// 而不允许通过new **()来构造。在方法内部自己可以访问到自己的private方法的。
	private static Singleton instance = new Singleton();

	public static Singleton getInstance() {
		// System.out.println("getInstance");
		return instance;
	}
}
