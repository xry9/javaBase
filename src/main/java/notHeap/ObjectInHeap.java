package notHeap;

import sun.misc.Unsafe;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ObjectInHeap {
    private long address = 0;
    private Unsafe unsafe = UnsafeInstanceUtil.getUnsafeInstance();
    public ObjectInHeap() {
        int size = 10 * 1024 * 1024;
        address = unsafe.allocateMemory(size);
//        unsafe.setMemory(address, size, (byte) 0);
    }
    // Exception in thread "main" java.lang.OutOfMemoryError
    public static void main(String[] args) {
        System.out.println("start...");
        ObjectInHeap heap = new ObjectInHeap();
        long first = heap.address;
        while (true) {
            heap = new ObjectInHeap();
            System.out.println("memory address=" + heap.address+"==="+((first - heap.address)/(1024*1024*10)));
        }
    }
}