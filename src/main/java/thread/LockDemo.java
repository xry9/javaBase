package thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static void main(String[] args) {
        Lock lock =new ReentrantLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("===run into ...==="+Thread.currentThread());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===run finish ...==="+Thread.currentThread());
                lock.unlock();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }

//    public static int ff(){
//        try {
//            return 1;
//        }catch (Exception e){
//
//        }finally {
//            System.out.println("finally");
//        }
//        return 0;
//    }
}

