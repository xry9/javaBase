package testThread.chapter1.interrupt;


public class Run {
	public static void main(String[] args) {
		//interrupted这个方法不要用了，会让人迷惑，有清楚状态的功能，用isInterrupted就可以了
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(100);//10
			thread.interrupt();//在当前线程内和线程外效果是一样的呀
			System.out.println("是否中断："+thread.isInterrupted());//线程没执行完判断才有意义
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end!");
	}
}

