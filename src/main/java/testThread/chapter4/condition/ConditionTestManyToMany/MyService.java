package testThread.chapter4.condition.ConditionTestManyToMany;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;

	public void set() {
		try {
			lock.lock();
			while (hasValue == true) {
				System.out.println("★★★★★★★★");
				condition.await();
			}
			System.out.println("★★★★");
			hasValue = true;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void get() {
		try {
			lock.lock();
			while (hasValue == false) {
				System.out.println("☆☆☆☆☆☆☆☆");
				condition.await();
			}
			System.out.println("☆☆☆☆");
			hasValue = false;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
