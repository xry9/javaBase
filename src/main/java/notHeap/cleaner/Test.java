package notHeap.cleaner;

import notHeap.UnsafeInstanceUtil;

public class Test {
    private long address = 0;
    public Test() {
        address = UnsafeInstanceUtil.getUnsafeInstance().allocateMemory(2 * 1024 * 1024);
    }
    public static void main(String[] args) {
        while (true) {
            Test heap = new Test();
            MyOwnCleaner.clear(heap, new FreeMemoryTask(heap.address));
            System.gc();
        }
    }
}

