package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class quitTereadMyself {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService threadpool = Executors.newFixedThreadPool(1);
		for(int i = 0 ;i<100;i++){
			Handler8 han = new Handler8(i);
			threadpool.execute(han);
		}
		threadpool.shutdown();
		while (true) {
			if (threadpool.isTerminated()) {
				System.out.println("结束了！");
				break;
			}
			Thread.sleep(100);
		}
	}
}

class Handler8 implements Runnable {
	private int i = 0 ;
	public Handler8 (int i){
		this.i = i;
	}
	public void run() {
		Long start = System.currentTimeMillis();
		if(i%10==0){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if((System.currentTimeMillis()-start)>900){
				System.out.println((System.currentTimeMillis()-start));
				return;//执行时间过长，则�?出线�?
			}
//			throw new RuntimeException();
		}
		System.out.println(Thread.currentThread()+"--"+i);
	}
}
