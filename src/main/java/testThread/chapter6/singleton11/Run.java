package testThread.chapter6.singleton11;


public class Run {

	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();

		t1.start();
		t2.start();
		t3.start();
	}
}
//	枚举和静态代码块的特性相似，在使用枚举类时构造方法会被自动调用