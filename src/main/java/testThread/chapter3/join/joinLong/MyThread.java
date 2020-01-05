package testThread.chapter3.join.joinLong;

public class MyThread extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("begin Timer=" + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("end Timer=" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
