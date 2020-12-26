package testThread;

import java.util.concurrent.TimeUnit;

public class ThreadState {
 
    public static void main(String[] args) {

    	new Thread(new Running(), "RunningThread").start();
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread-1").start();
//        new Thread(new Waiting(), "WaitingThread-2").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }
    
    /**
     * 表示线程正在运行
     * @author TIANSHOUZHI336
     *
     */
    static class Running implements Runnable{
 
		@Override
		public void run() {
			for (int i = 0; i < Long.MAX_VALUE; i++) {
				i++;
			}
		}
    	
    }
    /**
     * 该线程不断的进行睡眠
     */
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }
 
    /**
     * 该线程在Waiting.class实例上等待
     */
    static class Waiting implements Runnable {                                              
        @Override
        public void run() {
            while (true) {                                                                      
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
 
    /**
     * 该线程在Blocked.class实例上加锁后，不会释放该锁
     */
    static class Blocked implements Runnable {
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
 
    public static class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
  }

}