package thread;
import java.util.concurrent.CountDownLatch;

public class SingleMultiThread {
	public static void main(String[] args) {
		int num = 10_0000;
		test1(num);
		test2(num);
	}
	private static void test1(int max) {
		long t1 = System.currentTimeMillis();
		int n = method1(max);
		long t2 = System.currentTimeMillis();
		System.out.println("method1: value=" + n + ",time=" + (t2 - t1)/ 1000.0);
	}

	private static int method1(int max) {
		int num = 0;
		for (int i = 1; i <= max; i++) {
			boolean flag = true;
			for (int j = 2; j < i - 1; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag && i > num)
				num = i;
		}
		return num;
	}
	private static void test2(int max) {
		long t1 = System.currentTimeMillis();
		int threadNumber = 20;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);
		int step = max / threadNumber;
		for (int i = 0; i <= max; i += step) {
			if (i - step >= 0) {
				Calc calc = new Calc(i - step + 1, i, countDownLatch);
				Thread thread = new Thread(calc);
				thread.start();
			}
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		System.out.println("method2: value=" + Calc.getVal() + ",time="
				+ (t2 - t1) / 1000.0);
	}
}

class Calc implements Runnable {
	private static Integer val = 0;
	private int min;
	private int max;
	private CountDownLatch cdl;
	public Calc(int min, int max, CountDownLatch cdl) {
		this.min = min;
		this.max = max;
		this.cdl = cdl;
	}
	public static int getVal() {
		return val;
	}
	public void run() {
		int num = 0;
		for (int i = min; i <= max; i++) {
			boolean flag = true;
			for (int j = 2; j < i - 1; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				}
			}
			if (flag && i > num)
				num = i;
		}
		synchronized (val) {
			if (num > val)
				val = num;
		}
		cdl.countDown();
	}
}
/*
50:
	method1: value=99991,time=2.084
	method2: value=99991,time=0.733
20:
	method1: value=99991,time=2.106
	method2: value=99991,time=0.763
*/