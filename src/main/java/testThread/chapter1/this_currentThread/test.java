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
//		�ǲ���interrupt()��������alive���߳��ϲ�������
		System.out.println(this.isAlive());
		this.interrupt();
		System.out.println(this.isInterrupted());
		System.out.println(Thread.currentThread().isAlive());
		Thread.currentThread().interrupt();
		System.out.println(this.interrupted());
		System.out.println(Thread.currentThread());
	}
}

