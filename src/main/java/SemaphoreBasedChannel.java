import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * 基于Semaphore的支持流量控制的传输通道实现
 *
 *   Semaphore 相当于虚拟资源配额管理器，它可以用来控制在同一时间内对虚拟资源的访问次数。在访问相当资源时必须先申请相应的配额，并在资源访问结束后返回相应的配额。
 * 如果当前配额不足,semaphore.acquire();会使执行线程暂停。Semaphore 内部维护一个队列用于存储这里被暂停的线程。semaphore.acquire() 会使当前配额 -1 ，semaphore.release();
 * 会使当前配额 +1 ，并唤醒队列中任意一个等待的线程
 *
 */
public class SemaphoreBasedChannel<P> implements Channel<P> {
  private final BlockingQueue<P> queue;
  private final Semaphore semaphore;

  public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowLimit) {
    this(queue, flowLimit, false);
  }

  public SemaphoreBasedChannel(BlockingQueue<P> queue, int flowLimit,
      boolean isFair) {
    this.queue = queue;
    this.semaphore = new Semaphore(flowLimit, isFair);
  }

  @Override
  public P take() throws InterruptedException {
    return queue.take();
  }

  @Override
  public void put(P product) throws InterruptedException {
    semaphore.acquire();// 申请一个配额
    try {
      queue.put(product);// 访问虚拟资源
    } finally {
      semaphore.release();// 返还一个配额
    }
  }
}



interface Channel<P> {
  void put(P product) throws InterruptedException;
  P take() throws InterruptedException;
}