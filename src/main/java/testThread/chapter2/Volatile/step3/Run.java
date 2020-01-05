package testThread.chapter2.Volatile.step3;


public class Run {
	public static void main(String[] args) throws InterruptedException {
		RunThread thread = new RunThread();
		thread.start();
//		Thread.sleep(1000);//加不加延迟对结果有影响
		thread.setRunning(false);
		System.out.println("false");
	}
}
