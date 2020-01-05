package testThread.chapter4.condition.awaitUninterruptiblyTest_2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void testMethod() {
		try {
			lock.lock();
			System.out.println("wait begin");
			Thread.sleep(3000);
			condition.awaitUninterruptibly();
			System.out.println("wait   end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}