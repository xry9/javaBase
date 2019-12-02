package thread;

public class WaitThread1 extends Thread {
	Calculator c;
	public WaitThread1(Calculator c) {
		this.c = c;
	}
	public void run() {
		synchronized (c) {
			try {
				System.out.println(Thread.currentThread() + "等待计算结果。。。");
//				Thread.sleep(100);
				c.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "计算结果为：" + c.total);
		}
	}
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		// 启动三个线程，分别获取计算结果
		calculator.run();
//		calculator.start();
		new WaitThread1(calculator).start();
		new WaitThread1(calculator).start();
		new WaitThread1(calculator).start();
//		calculator.start();
//		calculator.run();
		// 启动计算线程
	}
}
class Calculator extends Thread {
	int total;
	public void run() {
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		synchronized (this) {
			System.out.println("go into ...");
			for (int i = 0; i < 100; i++) {
				total ++ ;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			notifyAll();
			System.out.println("notifyall");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("End...");
		// 通知所有在此对象上等待的线程
	}
}
