package testThread.chapter1.this_currentThread;

public class test {
	public static void main(String[] args) {
		myThread t = new myThread();
//		t.start();
		Thread tt = new Thread(t);
		tt.start();
	}
}

class myThread extends Thread{
	public void run() {
//		是不是interrupt()方法用在alive的线程上才有意义
		System.out.println(this.isAlive());
		this.interrupt();
		System.out.println(this.isInterrupted());
		System.out.println(Thread.currentThread().isAlive());
		Thread.currentThread().interrupt();
		System.out.println(this.interrupted());
		System.out.println(Thread.currentThread());
	}
}

