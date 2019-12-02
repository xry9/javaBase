package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskForMultiCompute {

	public static void main(String[] args)  {
		FutureTaskForMultiCompute inst = new FutureTaskForMultiCompute();
		// 创建任务集合
		List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
		// 创建线程
		ExecutorService exec = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 10; i++) {
			// 传入Callable对象创建FutureTask对象
			FutureTask<Integer> ft = new FutureTask<Integer>(
					inst.new ComputeTask(0, "" + i,2000));
			taskList.add(ft);
			// 提交给线程池执行任务，也可以通过exec.invokeAll(taskList)�?次�?�提交所有任�?;
			exec.submit(ft);
		}
		
		FutureTask<Integer> ft_ = new FutureTask<Integer>(inst.new ComputeTask(0, "" + 10,10000));
		taskList.add(ft_);
		exec.submit(ft_);
//		FutureTask<Integer> ft1 = new FutureTask<Integer>(inst.new ComputeTask(0, "" + 11,5000));
//		taskList.add(ft1);
//		exec.submit(ft1);
		
		System.out.println("计算任务提交完成, 主线程接着干其他事情！");
		Integer totalResult = 0;
		for (FutureTask<Integer> ft : taskList) {
			try {
				try {
//					FutureTask的get()方法会自动阻塞,直到获取计算结果为止
					totalResult = totalResult + ft.get(5, TimeUnit.SECONDS);
				} catch (TimeoutException e) {
					System.out.println("超时了吧,取消它");
//					ft.cancel(true);
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		// 关闭线程
		exec.shutdown();
		System.out.println("多任务计算后的结果是:" + totalResult);
	} 

	private class ComputeTask implements Callable<Integer> {
		private Integer result = 0;
		private String taskName = "";
		private Integer blockTime ;
		public ComputeTask(Integer iniResult, String taskName,Integer blockTime) {
			result = iniResult;
			this.taskName = taskName;
			this.blockTime = blockTime;
			System.out.println("生成子线程计算任务: " + taskName);
		}
		public String getTaskName() {
			return this.taskName;
		}
		@Override
		public Integer call() throws Exception {
			for (int i = 0; i <= 100; i++) {
				result = i;
			}
			// 休眠blockTime，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果是等待直至完成�?
			Thread.sleep(blockTime);
			System.out.println("子线程计算任务: " + taskName + " 执行完成!");
			return result;
		}
	}
//	http://blog.csdn.net/linchunquan/article/details/22382487
}