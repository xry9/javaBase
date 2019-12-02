package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class synchronized1 {
	public static void main(String[] args) {
		ExecutorService threadpool = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 6; i++) {
			Handler1 han = new Handler1();
			threadpool.execute(han);
		}
	}
}

class Handler1 implements Runnable {
	public static int a;
	public static String str = "";
	public HH hh = new HH();

	public void run() {
		this.f();
	}

	public synchronized void f() {
		this.a++;
		str += a;
		// System.out.println(Thread.currentThread()+": "+a);
		// Thread[pool-1-thread-1,5,main]: 2
		// Thread[pool-1-thread-4,5,main]: 4
		// Thread[pool-1-thread-2,5,main]: 2
		// Thread[pool-1-thread-6,5,main]: 5
		// Thread[pool-1-thread-3,5,main]: 3
		// Thread[pool-1-thread-5,5,main]: 6
		System.out.println(str);
		// 2
		// 235
		// 235
		// 23
		// 2
		// 2356

	}

	// public void f(){
	// synchronized(hh){
	// hh.a++;
	// System.out.println(Thread.currentThread()+": "+hh.a);
	// }
	// }
}

class HH {
	public int a = 0;
}
