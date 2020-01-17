package notHeap;

import java.nio.ByteBuffer;

public class TestDirectByteBuffer {
    // -verbose:gc -XX:+PrintGCDetails -XX:MaxDirectMemorySize=40M

//    加上-XX:+DisableExplicitGC,也会报OOM(Direct buffer memory),这个参数作用是禁止代码中显示调用GC，System.gc()
//    查看GC 日志显然堆内存（包括新生代和老年代）内存很充足，但是堆外内存溢出了。也就是说NIO直接内存的回收，需要依赖于System.gc()。如果我们的应用中使用了java nio中的direct memory，那么使用-XX:+DisableExplicitGC一定要小心，存在潜在的内存泄露风险
    public static void main(String[] args) throws Exception {
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
        }
    }
}
