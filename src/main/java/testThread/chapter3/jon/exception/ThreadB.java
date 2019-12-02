package testThread.chapter3.jon.exception;

public class ThreadB extends Thread {

	@Override
	public void run() {
		try {
			ThreadA a = new ThreadA();
			a.start();
			a.join();
			System.out.println("run end");
		} catch (InterruptedException e) {
			System.out.println("run catch");
			e.printStackTrace();
		}
	}

}
