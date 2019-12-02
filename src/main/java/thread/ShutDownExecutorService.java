package thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ShutDownExecutorService {
	public static void main(String args[]) throws InterruptedException {
		ExecutorService exe = Executors.newFixedThreadPool(2);
		for (int i = 1; i <= 10; i++) {
			exe.execute(new SubThread(i));
		}
		exe.shutdown();
//		exe.shutdownNow();
		while (true) {
			System.out.println("go into");
			if (exe.isTerminated()) {
				System.out.println("结束了！");
				break;
			}
			Thread.sleep(10);
		}
	}
}
class SubThread extends Thread {
	private final int i;
	public SubThread(int i) {
		this.i = i;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(i);
	}
}