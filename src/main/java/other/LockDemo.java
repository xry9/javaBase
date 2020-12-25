package other;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class LockDemo {
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    ReentrantReadWriteLock lockRw = new ReentrantReadWriteLock();
    Lock readLock = lockRw.readLock();
    Lock writeLock = lockRw.writeLock();
    Object oLock = new Object();
    public static void main(String[] args) throws InterruptedException {
        LockDemo demo = new LockDemo();
//        Thread thread1 = new MyThread0(demo, 1);
//        Thread thread2 = new MyThread0(demo, 2);
        Thread thread3 = new MyThread0(demo, true);
        Thread thread4 = new MyThread0(demo, false);
        Thread thread5 = new MyThread0(demo, false);
        Thread thread6 = new MyThread0(demo, false);
        Thread thread7 = new MyThread0(demo, false);
//        Thread thread4 = new MyThread(demo, true);
//        Thread thread5 = new MyThread(demo, true);
//        thread1.start();
//        thread2.start();
        thread3.start();
        Thread.sleep(500);
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
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
        System.out.println("=== read into ===");
        readLock.lock();
        System.out.println("=== read start ===");
        Thread.sleep(1000);
        System.out.println("=== read end ===");
        readLock.unlock();
    }

    public void write0() throws InterruptedException {
//        System.out.println("=== write into ===");
        lock.lock();
//        lock.tryLock(1, TimeUnit.SECONDS);
//        lock.lockInterruptibly();
//        lock.tryLock();
        System.out.println("=== write start ===");
        Thread.sleep(2000);
//        Integer.parseInt("abc");
        double k = 0;
//        for (int i=0;i<100000000;i++){//400000000
//            k = Math.random();
//        }
        System.out.println("=== write end ==="+Thread.currentThread()+"==="+k);
        lock.unlock();
    }

    public void writeCondition1() throws InterruptedException {
        lock.lock();
        System.out.println("=== Condition1 start ===");
        condition1.await();
        System.out.println("=== Condition1 end ===");
        lock.unlock();
    }

    public void writeCondition2() throws InterruptedException {
        lock.lock();
        System.out.println("=== Condition2 start ===");
        condition2.await();
        System.out.println("=== Condition2 end ===");
        lock.unlock();
    }

    public void writeSignal() throws InterruptedException {
        lock.lock();
        Thread.sleep(2000);
        System.out.println("=== Signal start ===");
        condition2.signal();
        Thread.sleep(2000);
        condition1.signal();
        Thread.sleep(2000);
        System.out.println("=== Signal end ===");
        lock.unlock();
    }

    public void other() throws InterruptedException {
        System.out.println("=== other start ===");
        lock.lock();
        Thread.sleep(20000);
        System.out.println("=== other end ===");
        lock.unlock();
    }

    public void write() throws InterruptedException {
        writeLock.lock();
//        writeLock.lockInterruptibly();
        System.out.println("=== write start ===");
        Thread.sleep(2000);
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
class MyThread0 extends Thread{
    LockDemo lockDemo;
    boolean isWriteLock = false;
    int condition = 0;
    public MyThread0(LockDemo lockDemo, int condition){
        this.lockDemo = lockDemo;
        this.condition = condition;
    }
    public MyThread0(LockDemo lockDemo, boolean isWriteLock){
        this.lockDemo = lockDemo;
        this.isWriteLock = isWriteLock;
    }
    @Override
    public void run() {
        try {
            if (isWriteLock){
                lockDemo.write();
            }else {
                lockDemo.read();
            }
//            lockDemo.write0();
        } catch (InterruptedException e) {e.printStackTrace();}
    }
}