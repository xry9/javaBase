package testThread.chapter1.this_currentThread;

public class testThreadDemo2 {
	public static void main(String[] args) {
		MyThread2 myThread = new MyThread2();
		myThread.start();
//		Thread t = new Thread(myThread);
//		t.start();
	}
}
class MyThread2 extends Thread{
	public MyThread2(){
		System.out.println("Start-------------");
		System.out.println(Thread.currentThread());
		System.out.println(this);
		System.out.println("End---------------");
	}
	@SuppressWarnings("static-access")
	public void run(){
		System.out.println("Start**************");
		System.out.println(this==Thread.currentThread());
		System.out.println(Thread.currentThread());
		System.out.println(this.isAlive());
		System.out.println("End**************");
	}
}
