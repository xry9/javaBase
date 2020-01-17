package jvmp0.reference;

import java.lang.ref.*;

public class ReferenceDemo2 {

    public static void main(String[] args) throws Exception {
//        soft();
        weak();
//        phantom();
    }
    public static void soft() throws Exception {
//        如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，
//        该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存。 软引用可以和一个引用队列（ReferenceQueue）联合使用，
//        如果软引用所引用的对象被垃圾回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中

        ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
        SoftReference<Object> softRef = new SoftReference<Object>(new Object(), refQueue);
        System.out.println(softRef.get()); // java.lang.Object@f9f9d8
        System.out.println(refQueue.poll());// null
        // 清除强引用,触发GC
        System.gc();
        Thread.sleep(200);
        System.out.println(softRef.get());
        System.out.println(refQueue.poll());
    }

    /*
    弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，
    一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
    如果弱引用所引用的对象被垃圾回 收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中
     */
    public static void weak() throws Exception {
        ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
        WeakReference<Object> weakRef = new WeakReference<Object>(new Object(), refQueue);
        System.out.println(weakRef.get()); // java.lang.Object@f9f9d8
//        Object tmp = weakRef.get();
        System.out.println(refQueue.poll());// null

        // 清除强引用,触发GC
        System.gc();
        // 这里特别注意:poll是非阻塞的,remove是阻塞的.
        // JVM将弱引用放入引用队列需要一定的时间,所以这里先睡眠一会儿
        // System.out.println(refQueue.poll());// 这里有可能是null

        Thread.sleep(200);
        System.out.println(weakRef.get());
        Reference ref = refQueue.poll();
        System.out.println(ref+"==="+(ref != null? ref.get():"null"));
        // System.out.println(refQueue.poll());//这里一定是null,因为已经从队列中移除
        // System.out.println(refQueue.remove());//remove这是一个阻塞方法
    }

    //当垃圾回收器回收一个对象时，如果发现它还有虚引用，就会把这个虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收
    //如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。由于Object.finalize()方法的不安全性、低效性，常常使用虚引用完成对象回收前的资源释放工作
    public static void phantom() throws Exception {
//        貌似 弱引用在加入队列时对象已经被回收，而虚引用不是，加入队列时还没有被回收
        ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
        PhantomReference<Object> phantom = new PhantomReference<Object>(new Object(), refQueue);
        System.out.println(phantom.get()); // java.lang.Object@f9f9d8
        System.out.println(refQueue.poll());// null
        System.gc();
        // 调用phanRef.get()不管在什么情况下会一直返回null
        System.out.println(phantom.get());
        // 而当JVM执行GC发现虚引用之后，JVM并没有回收obj，而是将PhantomReference对象插入到对应的虚引用队列refQueue中，当调用refQueue.poll()返回PhantomReference对象时，poll方法会先把PhantomReference的持有队列queue（ReferenceQueue<? super T>）置为NULL，NULL对象继承自ReferenceQueue，将enqueue(Reference paramReference)方法覆盖为return false，而此时obj再次被GC发现时，JVM再将PhantomReference插入到NULL队列中便会插入失败返回false，此时GC便会回收obj。事实上通过这段代码我们也的却看不出来obj是否被回收，但通过 PhantomReference 的javadoc注释中有一句是这样写的：
        //
        //Once the garbage collector decides that an object obj is phantom-reachable, it is being enqueued on the corresponding queue, but its referent is not cleared. That is, the reference queue of the phantom reference must explicitly be processed by some application code.
        //
        //翻译一下（这句话很简单，我相信很多人应该也看得懂）：
        //
        //一旦GC决定一个“obj”是虚可达的，它（指PhantomReference）将会被入队到对应的队列，但是它的指代并没有被清除。也就是说，虚引用的引用队列一定要明确地被一些应用程序代码所处理。
//        https://www.cnblogs.com/newcj/archive/2011/05/15/2046882.html
        Thread.sleep(2000);
        Reference ref = refQueue.poll();
        System.out.println(ref+"==="+(ref != null? ref.get():"null"));
    }
}
