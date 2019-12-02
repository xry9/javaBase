package testThread.chapter1.suspend_resume.step2;

public class Run {
	public static void main(String[] args) {
		try {
			final SynchronizedObject object = new SynchronizedObject();
			Thread thread1 = new Thread() {
				@Override
				public void run() {
					object.printString();
				}
			};
			thread1.setName("a");
			thread1.start();
			Thread.sleep(1000);
			Thread thread2 = new Thread() {
				@Override
				public void run() {
					System.out.println("1111111111111111111111111111111111");
					System.out.println("2222222222222222222222222222222222");
					object.printString();
				}
			};
			thread2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
