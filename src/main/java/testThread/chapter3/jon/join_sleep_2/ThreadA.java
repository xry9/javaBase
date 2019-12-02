package testThread.chapter3.jon.join_sleep_2;

public class ThreadA extends Thread {

	private ThreadB b;

	public ThreadA(ThreadB b) {
		super();
		this.b = b;
	}

	@Override
	public void run() {
		try {
			synchronized (b) {
				b.start();
				b.join();//join释放锁
				System.out.println("BBBBBBBB");
				for (int i = 0; i < Integer.MAX_VALUE/10; i++) {
					String newString = new String();
					Math.random();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
