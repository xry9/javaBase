package testThread.chapter2.Volatile.step3;

public class RunThread extends Thread {
	private volatile boolean isRunning = true;//volatile 
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	@Override
	public void run() {
		System.out.println("111111");
		while (isRunning == true) {
			System.out.println("是不是有执行语句对结果有影响");
		}
		System.out.println("2222");
	}
}
