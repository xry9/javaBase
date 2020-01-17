package notHeap.cleaner;

import notHeap.UnsafeInstanceUtil;
import sun.misc.Cleaner;

public class ObjectInHeapUseCleaner {
    private long address = 0;
    public ObjectInHeapUseCleaner() {
        address = UnsafeInstanceUtil.getUnsafeInstance().allocateMemory(2 * 1024 * 1024);
    }
    public static void main(String[] args) {
        while (true) {
            System.gc();
            ObjectInHeapUseCleaner heap = new ObjectInHeapUseCleaner();
            // 增加heap的虚引用,定义清理的接口FreeMemoryTask
            Cleaner.create(heap, new FreeMemoryTask(heap.address));
        }
    }
}
