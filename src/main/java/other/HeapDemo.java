package other;

import java.util.ArrayList;
import java.util.List;

public class HeapDemo {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        List<byte[]> list = new ArrayList<>();
        for (int i=0;i<2000;i++){
            list.add(new byte[1024*1024]);
        }
        System.out.println("======");
        Thread.sleep(Integer.MAX_VALUE);
        System.out.println(System.currentTimeMillis()-l);
    }
}


