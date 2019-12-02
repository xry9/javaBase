package testThread.chapter1.interrupt.child;


public class Run3 {
	public static void main(String[] args) {
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(100);
			thread.interrupt();//在当前线程内和线程外效果是一样的呀
			System.out.println("是否中断："+thread.isInterrupted());//线程没执行完判断才有意义
			System.out.println("是否中断："+Thread.currentThread().isInterrupted());
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

