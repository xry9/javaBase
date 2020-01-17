package notHeap.cleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
public class MyOwnCleaner {
    private static ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
    private static Map<Reference<Object>, Runnable> taskMap = new HashMap<Reference<Object>, Runnable>();
    static {
        new CleanerThread().start();
    }
    public static void clear(Object heapObject, Runnable task) {
        // 当heapObject没有强引用的时候,reference会自动被JVM加入到引用队列中
        // 不管使用有人持有reference对象的强引用
        PhantomReference<Object> reference = new PhantomReference<Object>(heapObject, refQueue);
        taskMap.put(reference, task);
    }
    // 清理线程
    private static class CleanerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    @SuppressWarnings("unchecked")
                    Reference<Object> refer = (Reference<Object>) refQueue.remove();
                    Runnable r = taskMap.remove(refer);
                    r.run();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
