package testThread;

public class JoinDemo {
	/**
	 * 这是我的一个重大学术突破，看join源码发现其原理并不复杂，只是有一个点没看懂
	 * while (isAlive()) {
                wait(0);
       }
       以下测试也就是验证一个结论，Thread对象的wait()与普通对象的不同，该线程结束后，调该线程的wait()处会向下执行，
   而普通对象即便被=null也不会往下走了
   Java多线程编程实战指南 核心篇P192中给了解答，其实此案例也是解答书中疑窦
	 */
	public static void main(String[] args) throws InterruptedException {
		Object o = new Object();
		Thread t2 = new MyThread2();
		Thread t1 = new MyThread1(t2);
		t1.start();
		t2.start();
	}
}

class MyThread1 extends Thread {
	private Thread lock;

	public MyThread1(Thread o) {
		this.lock = o;
	}
	public void run() {
		System.out.println("MyThread1 start...");
		synchronized (lock) {
			try {
				// Thread.sleep(5000);
				lock.wait();
				System.out.println("finish ...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("MyThread1 end...");
		}
	}
}

class MyThread2 extends Thread {
	public void run() {
		System.out.println("MyThread2 start...");
		try {
			 Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MyThread2 end...");
	}
}
