package testThread.chapter1.interrupt.stopThread;
public class Run {
	public static void main(String[] args) {
		try {
			MyThread thread = new MyThread();
			thread.start();
			thread.interrupt();
			Thread.sleep(200);
//			在sleep状态下interrupt一个线程会InterruptedException，并清除停止状态
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}
