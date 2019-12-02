package testThread.chapter1.suspend_resume.step3;

public class MyThread extends Thread {
	private long i = 0;

	@Override
	public void run() {
		System.out.println(System.out);
		System.out.println(System.out);
		while (true) {
			i++;
			System.out.println(i);
		}
	}
}
