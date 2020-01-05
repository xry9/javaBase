package testThread.chapter3.join.joinLong;


public class Test {

	public static void main(String[] args) {
		try {
			MyThread threadTest = new MyThread();
			threadTest.start();
			threadTest.join(2000);
			Thread.sleep(2000);
			System.out.println("main  end timer=" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
