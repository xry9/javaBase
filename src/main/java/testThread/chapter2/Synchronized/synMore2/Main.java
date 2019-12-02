package testThread.chapter2.Synchronized.synMore2;

public class Main {

	public int i = 10;

//	可重入锁也存在父子类继承之中
	synchronized public void operateIMainMethod() {
		try {
			i--;
			System.out.println("main print i=" + i);
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
