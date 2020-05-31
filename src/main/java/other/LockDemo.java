package other;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class LockDemo {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    ReentrantReadWriteLock lockRw = new ReentrantReadWriteLock();
    Lock readLock = lockRw.readLock();
    Lock writeLock = lockRw.writeLock();
    Object oLock = new Object();
    public static void main(String[] args) throws InterruptedException {

        LockDemo demo = new LockDemo();
        Thread thread1 = new MyThread(demo, true);
        Thread thread2 = new MyThread(demo, false);
//        Thread thread3 = new MyThread(demo, true);
//        Thread thread4 = new MyThread(demo, true);
//        Thread thread5 = new MyThread(demo, true);
        thread1.start();
        thread2.start();
//        thread3.start();
//        Thread.sleep(1000);
//        thread2.interrupt();
//        thread4.start();
//        thread5.start();

    }


    public void synf() throws InterruptedException {
        synchronized(oLock) {
            System.out.println("=== wait into ===");
            oLock.wait();
            System.out.println("=== wait out ===");
        }
    }

    public void synf1() throws InterruptedException {
        synchronized(oLock){
            System.out.println("=== notify into ===");
            oLock.notify();
            Thread.sleep(2000);
            System.out.println("=== notify out ===");
        }
    }

    public void read() throws InterruptedException {
        readLock.lock();
        System.out.println("=== read start ===");
        Thread.sleep(20000);
        System.out.println("=== read end ===");
        readLock.unlock();
    }

    public void write0() throws InterruptedException {
//        System.out.println("=== write into ===");
//        lock.lock();
        lock.tryLock(1, TimeUnit.SECONDS);
//        lock.lockInterruptibly();
//        lock.tryLock();
        System.out.println("=== write start ===");
        Thread.sleep(5000);
        double k = 0;
//        for (int i=0;i<100000000;i++){//400000000
//            k = Math.random();
//        }
        System.out.println("=== write end ==="+Thread.currentThread()+"==="+k);
        lock.unlock();
    }

    public void writeCondition() throws InterruptedException {
        lock.lock();
        System.out.println("=== Condition start ===");
        condition.await();
        System.out.println("=== Condition end ===");
        lock.unlock();
    }

    public void writeSignal() throws InterruptedException {
        lock.lock();
        Thread.sleep(5000);
        System.out.println("=== Signal start ===");
        condition.signal();
        System.out.println("=== Signal end ===");
        lock.unlock();
    }

    public void write() throws InterruptedException {
//        writeLock.lock();
//        writeLock.lockInterruptibly();
        System.out.println("=== write start ===");
//        Thread.sleep(2000);
        double k = 0;
        for (int i=0;i<4;i++){//400000000
            k = Math.random();
//            for (int j=0;j<Integer.MAX_VALUE;j++){
//                k = j;
//            }
        }
        System.out.println("=== write end ==="+k);
        writeLock.unlock();
    }
}
class MyThread extends Thread{
    LockDemo lockDemo;
    boolean isWriteLock = false;
    public MyThread(LockDemo lockDemo, boolean isWriteLock){
        this.lockDemo = lockDemo;
        this.isWriteLock = isWriteLock;
    }
    @Override
    public void run() {
        try {
            if (isWriteLock){
                lockDemo.writeCondition();
            }else {
                lockDemo.writeSignal();
            }
        } catch (InterruptedException e) {e.printStackTrace();}
    }
}