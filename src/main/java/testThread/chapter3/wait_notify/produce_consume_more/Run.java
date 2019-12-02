package testThread.chapter3.wait_notify.produce_consume_more;


public class Run {

	public static void main(String[] args) throws InterruptedException {
		String lock = new String("");
		P p = new P(lock);
		C r = new C(lock);
		ThreadP[] pThread = new ThreadP[2];
		ThreadC[] rThread = new ThreadC[2];
		for (int i = 0; i < 2; i++) {
			pThread[i] = new ThreadP(p);
			pThread[i].setName("生产者" + (i + 1));
			rThread[i] = new ThreadC(r);
			rThread[i].setName("消费者" + (i + 1));
			pThread[i].start();
			rThread[i].start();
		}
		Thread.sleep(3000);
		Thread[] threadArray = new Thread[Thread.currentThread()
				.getThreadGroup().activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threadArray);

		for (int i = 0; i < threadArray.length; i++) {
			System.out.println(threadArray[i].getName() + " "
					+ threadArray[i].getState());
		}
	}
}

//	假死的原因可能是连续唤醒同类，改用notifyAll()方法，唤醒所有线程就可以解决了