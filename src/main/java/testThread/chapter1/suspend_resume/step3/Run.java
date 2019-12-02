package testThread.chapter1.suspend_resume.step3;


public class Run {

	public static void main(String[] args) {

		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(1000);
			thread.suspend();
			System.out.println("main end!");
			System.out.println(System.out);
			System.out.println(System.out);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
