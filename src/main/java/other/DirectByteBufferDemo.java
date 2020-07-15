package other;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DirectByteBufferDemo {
//    -XX:+DisableExplicitGC
    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        for (int i=0;i<10000;i++){
            ByteBuffer db = ByteBuffer.allocateDirect(10 * 1000 * 1000);
            list.add(db);
            System.out.println("========"+i);
        }
    }
}
