package testThread.chapter3.wait_notify.wait_interrupt;

public class Test {
	public static void main(String[] args) {
		try {
			Object lock = new Object();
			ThreadA a = new ThreadA(lock);
			a.start();
			Thread.sleep(2000);
			a.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
