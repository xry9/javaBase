package thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
public class testThread {
	public static void main(String[] args) {
		testThread t=new testThread();
		t.f1();
		t.f2();
	}
	public void f1(){
		BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
		for(int i=0;i<20;i++){
			try {
				boolean b=queue.offer(i,1,TimeUnit.SECONDS);
				System.out.println("存入是否成功"+b);
			} catch (Exception e) {
			}
		}
	}
	public void f2(){
		BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);
		for(int i=0;i<10;i++){
			queue.offer(i);
		}
		for(int i=0;i<20;i++){
			try {
				Integer unm=queue.poll(1,TimeUnit.SECONDS);
				System.out.println("元素"+unm);
			} catch (Exception e) {
			}
		}
	}
}
