package testThread.chapter4.condition.lockInterruptiblyTest2;


public class Run {

	public static void main(String[] args) throws InterruptedException {
		final MyService service = new MyService();
		Runnable runnableRef = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};

		Thread threadA = new Thread(runnableRef);
		threadA.setName("A");
		threadA.start();
		Thread.sleep(50);
		Thread threadB = new Thread(runnableRef);
		threadB.setName("B");
		threadB.start();
		threadB.interrupt();
	}
}
