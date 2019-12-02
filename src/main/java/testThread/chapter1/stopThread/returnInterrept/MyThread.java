package testThread.chapter1.stopThread.returnInterrept;

public class MyThread extends Thread {

	@Override
	public void run() {
		boolean a = true;
		while (a) {
			if (this.isInterrupted()) {
				System.out.println("结束");
				break;
//				return;
			}
			System.out.println("timer=" + System.currentTimeMillis());
		}
		System.out.println("while stop...");
	}
}
