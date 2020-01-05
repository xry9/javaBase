package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    /**
     * 1.1. CAS的全称为Compare-And-Swap，它是一条CPU并发原语。它的功能是判断内存某个位置的值是否为期望值，如果是则更改为新的值，这个过程是原子的。
     * 1.2. CAS并发原语体现在JAVA语言中就是sun.misc.Unsafe类中的CAS方法，JVM会帮我们实现CAS汇编指令。这是一种完全依赖于硬件的功能，通过它实现了原子操作。
     * 再次强调，由于CAS是一种系统原语，原语属于操作系统用语范畴范，是由若干条指令组成的，用于完成某个功能的一个过程，并且原语的执行必须是连续的，
     * 在执行过程中不允许被中断，也就说CAS是一条CPU的原了指令，不会造成所谓的数据不一致问题
     *
     * CAS不是java特有的，而是操作系统需要保证的
     * CAS指令在Intel CPU上称为CMPXCHG指令，它的作用是将指定内存地址的内容与所给的某个值相比，如果相等，
     * 则将其内容替换为指令中提供的新值，如果不相等，则更新失败。这一比较并交换的操作是原子的，不可以被中断
     *
     */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // main do thing
        System.out.println(atomicInteger.compareAndSet(5, 6)+"current data:"+atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 2019)+"current data:"+atomicInteger.get());

        System.out.println(atomicInteger.addAndGet(1));
    }
}