package testThread.chapter1.alive;

public class CountOperate extends Thread {
//	书中没有给出明确解释，应该是这样的this表示CountOperate实例，Thread.currentThread()表示t1（Thread t1 = new Thread(c);）
	public CountOperate() {
		System.out.println("CountOperate---begin");
		System.out.println("Thread.currentThread().getName()="
				+ Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()="
				+ Thread.currentThread().isAlive());
		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());
		System.out.println("CountOperate---end");
	}
	@Override
	public void run() {
		System.out.println("run---begin");
		System.out.println("Thread.currentThread().getName()="
				+ Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()="
				+ Thread.currentThread().isAlive());
		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());
		System.out.println("this==Thread.currentThread()=" + (this==Thread.currentThread()));
		System.out.println("run---end");
	}
}
