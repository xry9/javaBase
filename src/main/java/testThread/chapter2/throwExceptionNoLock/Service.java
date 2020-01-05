package testThread.chapter2.throwExceptionNoLock;

public class Service {

//	当一个线程出现异常，其所持有的锁会自动释放
	synchronized public void testMethod() {
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("ThreadName=" + Thread.currentThread().getName()
					+ " run beginTime=" + System.currentTimeMillis());
			int i = 1;
			while (i == 1) {
				if (("" + Math.random()).substring(0, 8).equals("0.123456")) {
					System.out.println("ThreadName="
							+ Thread.currentThread().getName()
							+ " run   exceptionTime="
							+ System.currentTimeMillis());
					Integer.parseInt("a");
				}
			}
		} else {
			System.out.println("Thread B run Time="
					+ System.currentTimeMillis());
		}
	}
}
