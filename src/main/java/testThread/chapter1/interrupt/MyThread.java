package testThread.chapter1.interrupt;

public class MyThread extends Thread {
	@Override
	public void run() {
		int count = 0; boolean flag = false;
		super.run();
		for (int i = 0; i < 50000; i++) {
			if(!flag && Thread.currentThread().isInterrupted()){
				count = i;
				flag = true;
//				break;
			}
			if (flag && (i-count)>=10000){
				break;
			}
			if (i%1000==0){
				System.out.println("i=" + (i + 1));
			}
		}
		System.out.println("finishly ....");
	}
}
