package notHeap;

import sun.misc.Unsafe;

import java.nio.ByteBuffer;

public class RevisedObjectInHeap {
    private long address = 0;
    private ByteBuffer buffer;
    private Unsafe unsafe = UnsafeInstanceUtil.getUnsafeInstance();

    // 让对象占用堆内存,触发[Full GC
    private byte[] bytes = null;

    public RevisedObjectInHeap() {
//        address = unsafe.allocateMemory(2 * 1024 * 1024);
        buffer = ByteBuffer.allocateDirect(1 * 1024 * 1024);
//        增大堆内存消耗,促进GC
        bytes = new byte[1024 * 1024];
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize." + bytes.length);
        unsafe.freeMemory(address);
    }

    public static void main(String[] args) {
        while (true) {
            RevisedObjectInHeap heap = new RevisedObjectInHeap();
            System.out.println("memory address=" + heap.address);
        }
    }

}