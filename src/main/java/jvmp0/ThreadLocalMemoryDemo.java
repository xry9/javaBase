package jvmp0;

import java.lang.ref.WeakReference;

public class ThreadLocalMemoryDemo {
    public static void main(String[] args) throws InterruptedException {
//        -Xmx50m -Xms50m -XX:+PrintGCDetails
//        List<Object> list = new ArrayList<>();
//        for (int i=0;i<10_0000;i++){
//            AA aa = new AA();
//            RR rr = new RR(aa);
//            list.add(rr);
//            if (i%1000==0){
//                System.out.println(i);
//            }
//        }
//        System.out.println(list.size());
//        System.out.println(list.get(10_000));

        ThreadLocal tl = new ThreadLocal();
        tl.set(new byte[1024*1024*10]);
//        tl.set(null);
        tl = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl1 = new ThreadLocal();
        tl1.set(new byte[1024*1024*10]);
//        tl1.set(null);
        tl1 = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl2 = new ThreadLocal();
        tl2.set(new byte[1024*1024*10]);
//        tl2.set(null);
        tl2 = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl3 = new ThreadLocal();
        tl3.set(new byte[1024*1024*10]);
//        tl3.set(null);
        tl3 = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl4 = new ThreadLocal();
        tl4.set(new byte[1024*1024*10]);
//        tl4.set(null);
        tl4 = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl5 = new ThreadLocal();
        tl5.set(new byte[1024*1024*10]);
//        tl5.set(null);
        tl5 = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl6 = new ThreadLocal();
        tl6.set(new byte[1024*1024*10]);
//        tl6.set(null);
        tl6 = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl7 = new ThreadLocal();
        tl7.set(new byte[1024*1024*10]);
//        tl7.set(null);
        tl7 = null;
        System.gc();
        Thread.sleep(100);

        ThreadLocal tl8 = new ThreadLocal();
        tl8.set(new byte[1024*1024*10]);
//        tl8.set(null);
        tl8 = null;
        System.gc();
        Thread.sleep(100);


    }
}

class RR extends WeakReference<AA>{
    public RR(AA referent) {
        super(referent);
    }
}

class AA {
    public AA(){

    }
//    long l1 = 10l;
//    long l2 = 10l;
//    long l3 = 10l;
//    long l4 = 10l;
//    long l5 = 10l;
//    long l6 = 10l;
//    long l7 = 10l;
//    long l8 = 10l;
//    long l9 = 10l;
    byte[] bytes = new byte[1024];
}
