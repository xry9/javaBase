package testThread.chapter6.singleton8;


public class MyThread extends Thread {
	@Override
	public void run() {
		System.out.println(MyObject.getInstance().hashCode());
	}
}
