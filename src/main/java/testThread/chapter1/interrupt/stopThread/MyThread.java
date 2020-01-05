package testThread.chapter1.interrupt.stopThread;
public class MyThread extends Thread {
	@Override
	public void run() {
		super.run();
		try {
			System.out.println("run begin");
			Thread.sleep(200000);
			System.out.println("run end");
		} catch (InterruptedException e) {
			System.out.println("catch!\t"+this.isInterrupted());
			e.printStackTrace();
		}
	}
}
