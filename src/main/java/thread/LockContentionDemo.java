package thread;


import java.util.HashMap;
import java.util.Map;

public class LockContentionDemo {
    static long lockDuration = 100;
    static SharedResource sr = new SharedResource();
    static long lockAccessFrequency = 50;

    public static void main(String[] args) throws InterruptedException {
//        int argc = args.length;
//        if (argc>0){
//            lockDuration = Long.parseLong(args[0]);
//            if (argc>1){
//                lockAccessFrequency = Long.parseLong(args[1]);
//            }
//        }
        int N = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[N];
        for (int i=0;i<N;i++){
            threads[i] = new Thread(){
                @Override
                public void run() {
                    for (;;){
                        sr.access();
//                        try {
//                            Thread.sleep(lockAccessFrequency);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            };
        }
        Tools.startAndWaitTerminated(threads);
        Tools.delayedAction("The program will be terminated", new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 120);
    }

    static class SharedResource{
        Map<String, Integer> map = new HashMap<>();
        public synchronized void access(){
//            try {
//                Thread.sleep(lockDuration);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            String key = Thread.currentThread().getName().toString();
            if (map.containsKey(key)){
                map.put(key, map.get(key) + 1);
                System.out.println(key + "===" + map.get(key));
            }else {
                map.put(key, 1);
            }
        }
    }
}
