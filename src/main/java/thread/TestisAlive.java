package thread;

import java.util.ArrayList;
import java.util.List;

public class TestisAlive {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Runtime.getRuntime().availableProcessors());
		List<Thread> list = new ArrayList<>();
		for(int i=0;i<5;i++){
			Thread t = new Thread(){
				public void run(){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			list.add(t);
			t.start();
		}
		for(Thread t:list){
			System.out.println(t.isAlive());
		}
		System.out.println("-----------");
		Thread.sleep(1000);
		for(Thread t:list){
			System.out.println(t.isAlive());
		}
		System.out.println("-----------");
		Thread.sleep(100);
		for(Thread t:list){
			System.out.println(t.isAlive());
		}
	}
}