package other;

import java.nio.ByteBuffer;
import java.util.*;

public class MyDemo {
    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(500);
//        ByteBuffer bb = ByteBuffer.allocateDirect(500);
        long l = System.currentTimeMillis();
        int k = 0;
        for (int j=0;j<10000_0000;j++){
            for (int i=0;i<10;i++){
                bb.put((byte)i);
            }
            bb.flip();
            for (int i=0;i<10;i++){
                k = bb.get()+i;
            }
            bb.flip();
        }
//        operateCompare();
//        System.out.println("Heap Buffer: " + test(ByteBuffer.allocate(100)) + " ms");
//        System.out.println("Direct Buffer: " + test(ByteBuffer.allocateDirect(100)) + " ms");
//        arrTest();
        System.out.println(System.currentTimeMillis() - l);
    }


    private static double test(ByteBuffer buffer) {
        long[] time = new long[100];
        Arrays.fill(time, testByteBuffer(buffer));
        return Arrays.stream(time).average().orElseThrow(null);
    }

    private static long testByteBuffer(ByteBuffer buffer) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i++) {
            for (byte b = 0; b < 100; b++) {
                buffer.put(b);
            }
            buffer.clear();
        }
        return System.currentTimeMillis() - start;
    }

    public static void arrTest(){
        long[] times = new long[100];
        for (int i = 0; i < 100; i++) {
            long time = System.currentTimeMillis();
            for (int j = 0; j < 100_0000; j++) {
                byte[] array = new byte[100];
                for (int k = 0; k < 100; k++) {
                    array[k] = (byte) k;
                }
            }
            times[i] = System.currentTimeMillis() - time;
        }
        System.out.println("Array: " + Arrays.stream(times).average().orElseThrow(null) + " ms");

    }

    public static void operateCompare(){
        int time =1000000000;
        ByteBuffer buffer = ByteBuffer.allocate(2*time);
        long st = System.currentTimeMillis();
        for(int i = 0; i < time;i++) {
// putChar(char value) 用来写入 char 值的相对 put 方法
            buffer.putChar('a');
        }
        buffer.flip();
        for (int i = 0; i < time; i++) {
            buffer.getChar();
        }
        long et = System.currentTimeMillis();
        System.out.println("在进行"+time+"次读写操作时，非直接内存读写耗时：" + (et-st) +"ms");
        ByteBuffer buffer_d = ByteBuffer.allocateDirect(2*time);
        long st_direct = System.currentTimeMillis();
        for(int i = 0; i < time; i++) {
// putChar(char value) 用来写入 char 值的相对 put 方法
            buffer_d.putChar('a');
        }
        buffer_d.flip();
        for(int i=0;i < time; i++) {
            buffer_d.getChar();
        }
        long et_direct = System.currentTimeMillis();
        System.out.println("在进行"+time+"次读写操作时，直接内存读写耗时:"+(et_direct - st_direct) +"ms");
    }

}
