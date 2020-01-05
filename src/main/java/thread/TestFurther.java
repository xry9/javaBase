package thread;

import java.util.concurrent.*;

public class TestFurther {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		FutureTask<Integer> futureTask = new FutureTask<>(
				new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
						System.out.println("不告诉你");
						Thread.sleep(1000 * 20);
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
			Future<?> f1 = executorService.submit(futureTask);
			Object r1 = futureTask.get();
			System.out.println(r1);

//			Future<?> f2 = executorService.submit(futureTask1);
//			Object r2 = futureTask1.get(2, TimeUnit.SECONDS);
//			System.out.println(r2);

			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
//		catch (TimeoutException e) {
//			System.out.println("超时了吧~~~");
//			e.printStackTrace();
//			executorService.shutdown();
//		}
	}
}