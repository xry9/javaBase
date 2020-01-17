package notHeap;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafeMemo {
    // -XX:MaxDirectMemorySize=40M
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = UnsafeInstanceUtil.getUnsafeInstance();

        while (true) {
            long pointer = unsafe.allocateMemory(1024 * 1024 * 20);
            System.out.println(unsafe.getByte(pointer + 1));
            // 如果不释放内存,运行一段时间会报错 java.lang.OutOfMemoryError,(jdk6) ,jdk8 没报出OutOfMemoryError 但是本质上应该是一样的
             unsafe.freeMemory(pointer);
        }
    }



}
