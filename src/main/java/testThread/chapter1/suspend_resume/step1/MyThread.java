package testThread.chapter1.suspend_resume.step1;

public class MyThread extends Thread {
	private long i = 0;
	public long getI() {
		return i;
	}
	public void setI(long i) {
		this.i = i;
	}
	@Override
	public void run() {
		while (true) {
			i++;
		}
	}
}
