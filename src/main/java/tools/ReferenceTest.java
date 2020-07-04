package tools;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class ReferenceTest {
    private static List<Reference> roots = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ReferenceQueue rq = new ReferenceQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true) {
                    try {
                        Reference r = rq.remove();
                        //为null说明referent被回收
                        if (i % 10000 == 0) {
                            System.out.println("reference:"+r+"=== get:"+r.get()+"===========================queue remove num:"+i);
                        }
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for(int i=0;i<100_0000;i++) {
            byte[] a = new byte[1024];
            // 分别验证SoftReference,WeakReference,PhantomReference
//            Reference r = new SoftReference(a, rq);
//            Reference r = new WeakReference(a, rq);
            Reference r = new PhantomReference(a, rq);
//            roots.add(r);
//            System.gc();
            if (i%1000==0){
                System.out.println("produce"+i+"===");
            }
//            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println("finish......");
    }
}

//-Xmx200m -Xms200m -XX:+PrintGCDetails
