package testThread.chapter2.Volatile.step4;

public class MyThread extends Thread {
	public  static int count;//volatile

	 private static void addCount() {//synchronized 
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println("count=" + count);
	}

	@Override
	public void run() {
		addCount();
	}

}
