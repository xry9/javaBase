package thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class executorService {
	public static void main(String[] args) {
		ExecutorService threadpool=Executors.newFixedThreadPool(3);
		for(int i=0;i<5;i++){
			Handler han=new Handler();
			threadpool.execute(han);
		}
		System.out.println("主线程\t"+Thread.currentThread());
		System.out.println("main方法线程结束");
//		threadpool.shutdown();//不执行shutdown程序会一直活着
	}
}
class Handler implements Runnable{
	public void run(){
		String name=Thread.currentThread().getName();
		System.out.println("执行当前任务的线程为:"+name);
		for(int i=0;i<10;i++){
//			System.out.println(name+":"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println(name+"任务完毕");
	}
}
