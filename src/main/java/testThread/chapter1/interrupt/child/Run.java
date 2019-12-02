package testThread.chapter1.interrupt.child;


public class Run {
	public static void main(String[] args) {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(100);
//			thread.interrupt();
			Thread.currentThread().interrupt();
			//interrupted这个方法不要用了，会让人迷惑，有清楚状态的功能，用isInterrupted就可以了
			System.out.println("是否中断："+thread.interrupted());
			System.out.println("是否中断："+thread.interrupted());
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

