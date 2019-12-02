package singleton;

import java.io.Serializable;

//3.内部类托管单例

public class Singleton3 implements Serializable {
	private Singleton3() {
	}

	private static class SingletonHolder {
		private static Singleton3 instance = new Singleton3();
	}

	public static Singleton3 getInstance() {
		return SingletonHolder.instance;
	}

	// 阻止生成新的实例,没有这个反序列化后不是同一个对象了
	public Object readResolve() {
		return SingletonHolder.instance;
	}
}
