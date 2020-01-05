package testThread.chapter4.condition.lockInterruptiblyTest2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	public ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void waitMethod() {
		try {
//			如果当前线程未中断则获得锁，如果已被中断则出现异常
			lock.lockInterruptibly();
			System.out.println("lock " + Thread.currentThread().getName() + " start...");
			for (int i = 0; i < Integer.MAX_VALUE / 100; i++) {
				String newString = new String();
				Math.random();
			}
			System.out.println("lock " + Thread.currentThread().getName() + " end...");
		} catch (InterruptedException e) {
			System.out.println("线程"+Thread.currentThread().getName()+"进入catch~!");
			e.printStackTrace();
		} finally {
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}
}