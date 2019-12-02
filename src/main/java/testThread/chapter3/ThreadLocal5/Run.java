package testThread.chapter3.ThreadLocal5;


public class Run {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("Main=" + Tools.tl.get());
				Thread.sleep(100);
			}
			Thread.sleep(2000);
			ThreadA a = new ThreadA();
			a.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
