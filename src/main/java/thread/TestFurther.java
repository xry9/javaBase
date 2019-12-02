package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestFurther {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		FutureTask<Integer> futureTask = new FutureTask<>(
				new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						System.out.println("不告诉你");
						Thread.sleep(1000 * 3);
						System.out.println("futureTask");
						return 2;
					}
				});
		FutureTask<Boolean> futureTask1 = new FutureTask<>(
				new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						System.out.println("不告诉你");
						Thread.sleep(1000 * 3);
						System.out.println("futureTask1");
						return true;
					}
				});
		try {
			System.out.println(executorService.submit(futureTask).get(4, TimeUnit.SECONDS));
			executorService.submit(futureTask1).get(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("超时了吧~~~");
			e.printStackTrace();
			executorService.shutdown();
		}
	}
}