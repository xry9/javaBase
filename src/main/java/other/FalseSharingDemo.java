//package other;
//
//import thread.Tools;
//
//public class FalseSharingDemo extends Thread {
//  final CountingTask task;
//
//  public FalseSharingDemo(CountingTask task) {
//    this.task = task;
//  }
//
//  @Override
//  public void run() {
//    final CountingTask t = task;
//    final long count = t.getIterations();
//    for (long i = 0; i < count; i++) {
//      t.setValue(t.getValue() + i);
//    }
//  }
//
//  public static void main(String[] args) throws Exception {
//    int argc = args.length;
//    int N;// 工作者线程数
//    N = argc > 0 ? Integer.valueOf(args[0]) : Runtime.getRuntime()
//        .availableProcessors();
//    long iterations;
//    iterations = argc > 1 ? Long.valueOf(args[1])
//        : 400 * 1000 * 1000L;
//
//    String taskImplClassName;
//    taskImplClassName = System.getProperty("x.task.impl");
//    if (null == taskImplClassName) {
//      taskImplClassName = "DefaultCountingTask";
//    }
//
//    CountingTask[] tasks = createTasks(taskImplClassName, N, iterations);
//    Thread[] demoThreads = new Thread[N];
//    for (int i = 0; i < N; i++) {
//      demoThreads[i] = new FalseSharingDemo(tasks[i]);
//    }
//    long start = System.currentTimeMillis();
//    // 启动并等待指定的线程终止
//    Tools.startAndWaitTerminated(demoThreads);
//    System.out.printf("Duration: %,d ms %n", System.currentTimeMillis() - start);
//  }
//
//  private static CountingTask[] createTasks(String taskImplClassName, int N,
//      long iterations) {
//    CountingTask[] tasks = new CountingTask[N];
//    // 这里必须连续创建多个XXCountingTask实例,
//    // 创建这些实例期间不能创建其他实例以提高Java虚拟机为这些对象分配连续的内存空间的几率。
//    if ("DefaultCountingTask".equals(taskImplClassName)) {
//      for (int i = 0; i < N; i++) {
//        tasks[i] = new DefaultCountingTask(iterations);
//      }
//    } else if ("AutoPaddedCountingTask".equals(taskImplClassName)) {
//      for (int i = 0; i < N; i++) {
//        tasks[i] = new AutoPaddedCountingTask(iterations);
//      }
//    } else {
//      for (int i = 0; i < N; i++) {
//        tasks[i] = new ManuallyPaddedCountingTask(iterations);
//      }
//    }
//    return tasks;
//  }
//}
//
//interface CountingTask {
//  public void setValue(long value);
//  public long getValue();
//  public long getIterations();
//}
//
//class DefaultCountingTask implements CountingTask {
//  private final long iterations;
//  private volatile long value;
//
//  public DefaultCountingTask() {
//    this(1000000);
//  }
//
//  public DefaultCountingTask(long iterations) {
//    this.iterations = iterations;
//  }
//
//  @Override
//  public long getIterations() {
//    return iterations;
//  }
//
//  @Override
//  public void setValue(long value) {
//    this.value = value;
//  }
//
//  @Override
//  public long getValue() {
//    return value;
//  }
//}
//
//
//class AutoPaddedCountingTask implements CountingTask {
//  private final long iterations;
//  @sun.misc.Contended
//  public volatile long value;
//
//  public AutoPaddedCountingTask() {
//    this(1000000);
//  }
//
//  public AutoPaddedCountingTask(long iterations) {
//    this.iterations = iterations;
//  }
//
//  @Override
//  public long getIterations() {
//    return iterations;
//  }
//
//  @Override
//  public long getValue() {
//    return value;
//  }
//
//  @Override
//  public void setValue(long value) {
//    this.value = value;
//  }
//}
//
//
//class ManuallyPaddedCountingTask implements CountingTask {
//  private final long iterations;
//  public volatile long value;
//  // 填充
//  protected volatile long p1, p2, p3, p4;
//
//  public ManuallyPaddedCountingTask() {
//    this(1000000);
//  }
//
//  public ManuallyPaddedCountingTask(long iterations) {
//    this.iterations = iterations;
//  }
//
//  @Override
//  public long getIterations() {
//    return iterations;
//  }
//
//  @Override
//  public long getValue() {
//    return value;
//  }
//
//  @Override
//  public void setValue(long value) {
//    this.value = value;
//  }
//
//}