package testThread.chapter3.jon.join_sleep_1;

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
				Thread.sleep(6000);
//				sleep不释放锁
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
