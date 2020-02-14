package other;

import java.util.Map;
import java.util.Random;

public class ThreadChange {
    public static void main(String[] args) {
        Thread threads[] = new MyThread[4];
        Object lock = new Object();
        for (int i=0;i< threads.length;i++){
            threads[i] = new MyThread("thread"+i, lock);
        }
        for (int i=0;i< threads.length;i++){
            threads[i].start();
        }
    }
}

class MyThread extends Thread{
    public String name ;
    public Object lock ;
    public MyThread(String name, Object lock){
        this.name = name;
        this.lock = lock;
    }
    @Override
    public void run() {
        double d = 0;
        long l1 = System.currentTimeMillis();
        for (int i=0;i<Integer.MAX_VALUE/100;i++){
            d = Math.random();
        }
//        synchronized (lock){
//        }
        long l2 = System.currentTimeMillis();
        System.out.println(d+"==="+Thread.currentThread()+"==="+(l2 - l1));
    }
}
