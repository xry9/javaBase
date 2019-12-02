package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 
 * 阻塞队列是Java5线程新特征中的内容，Java定义了阻塞队列的接口java.util.concurrent.BlockingQueue，
 * 阻塞队列的概念是，一个指定长度的队列，如果队列满了，添加新元素的操作会被阻塞等待，直到有空位为止。同样，当队列为空时候，
 * 请求队列元素的操作同样会阻塞等待，直到有可用元素为止。 有了这样的功能，就为多线程的排队等候的模型实现开辟了便捷通道，非常有用。
 * ----------------------------------------------------
 * 对于阻塞栈，与阻塞队列相似。不同点在于栈是“后入先出”的结构，每次操作的是栈顶，而队列是“先进先出”的结构，每次操作的是队列头。
 */
public class BlockingQueueThread {
	public static void main(String[] args) throws InterruptedException {
		// blockingQueue();
		blockingDeque();
	}

	public static void blockingQueue() throws InterruptedException {
		BlockingQueue bqueue = new ArrayBlockingQueue(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此队列中，如果没有可用空间，将一直等待（如果有必要）。
			bqueue.put(i);
			System.out.println("向阻塞队列中添加了元素:" + i);
		}
		System.out.println("程序到此运行结束，即将退出----");
	}

	public static void blockingDeque() throws InterruptedException {
		BlockingDeque bDeque = new LinkedBlockingDeque(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此阻塞栈中，如果没有可用空间，将一直等待（如果有必要）。
			bDeque.putFirst(i);
			System.out.println("向阻塞栈中添加了元素:" + i);
		}
		System.out.println("程序到此运行结束，即将退出----");
	}
}
