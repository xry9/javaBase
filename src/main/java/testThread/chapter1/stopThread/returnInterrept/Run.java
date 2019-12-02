package testThread.chapter1.stopThread.returnInterrept;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		MyThread t=new MyThread();
		t.start();
		Thread.sleep(1000);
		System.out.println("t.isAlive():"+t.isAlive());
		t.interrupt();
		System.out.println("t.isAlive():"+t.isAlive());
		Thread.sleep(100);
		System.out.println("t.isAlive():"+t.isAlive());
	}
}
