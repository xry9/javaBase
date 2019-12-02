package testThread.chapter6.singleton1;

public class MyObject {

	// 立即加载方式==饿汉模式
	private static MyObject myObject = new MyObject();
	private MyObject() {
	}
	public static MyObject getInstance() {
		// 此代码版本为立即加载
		// 此版本代码的缺点是不能有其它实例变量
		return myObject;
	}
}