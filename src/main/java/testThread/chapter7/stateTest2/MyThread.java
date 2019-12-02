package testThread.chapter7.stateTest2;

public class MyThread extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("begin sleep");
			Thread.sleep(2000);
			System.out.println("  end sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
