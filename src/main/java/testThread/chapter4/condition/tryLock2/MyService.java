package testThread.chapter4.condition.tryLock2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	public ReentrantLock lock = new ReentrantLock();

	public void waitMethod() {
		try {
//			如果线程在给定时间内没有被另一线程操持，且未中断，则获得锁定
			if (lock.tryLock(3, TimeUnit.SECONDS)) {
				System.out.println("      " + Thread.currentThread().getName()
						+ "获得锁的时间：" + System.currentTimeMillis());
				Thread.sleep(2000);//5000
			} else {
				System.out.println("      " + Thread.currentThread().getName()
						+ "没有获得锁");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}
}
