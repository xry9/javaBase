package threadunsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class ParkDemo {

//    1> 针对当前线程已经调用过unpark(多次调用unpark的效果和调用一次unpark的效果一样)
//    public static void main(String[] args) throws Exception {
//        Unsafe unsafe = UnsafeUtil.getUnsafe();
//        Thread currThread = Thread.currentThread();
////        unsafe.unpark(currThread);
////        unsafe.unpark(currThread);
////        unsafe.unpark(currThread);
//        unsafe.park(false, 0);
//        System.out.println("SUCCESS!!!");
//    }

//    2>在当前线程中断的时候或者调用unpark的时候
//    public static void main(String[] args) throws Exception {
//        Unsafe unsafe = UnsafeUtil.getUnsafe();
//        Thread currThread = Thread.currentThread();
//        new Thread(()->{
//            try {
//                Thread.sleep(3000);
//                currThread.interrupt();
////                unsafe.unpark(currThread);
//            } catch (Exception e) {}
//        }).start();
//        unsafe.park(false, 0);
//        System.out.println("SUCCESS!!!");
//    }

//  3>如果是相对时间也就是isAbsolute为false（注意这里后面的单位纳秒）到期的时候
//    public static void main(String[] args) throws Exception {
//        Unsafe unsafe = UnsafeUtil.getUnsafe();
//        //相对时间后面的参数单位是纳秒
//        unsafe.park(false, 3000_000_000l);
//        System.out.println("SUCCESS!!!");
//    }

//  4>如果是绝对时间也就是isAbsolute为true(注意后面的单位是毫秒)到期的时候
//    public static void main(String[] args) throws Exception {
//        Unsafe unsafe = UnsafeUtil.getUnsafe();
//        long time = System.currentTimeMillis()+3000;
//        //绝对时间后面的参数单位是毫秒
//        unsafe.park(true, time);
//        System.out.println("SUCCESS!!!");
//    }


}
