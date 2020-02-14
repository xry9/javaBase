package thread;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Object lock = new Object();
        Thread thread = new Thread("ThreadState-0"){
            @Override
            public void run() {
                long l1 = System.currentTimeMillis();
                double d = 0;
                for (int i=0;i<Integer.MAX_VALUE/1;i++){
                    System.out.println(Thread.currentThread());
                    d = Math.random();
                }
                long l2 = System.currentTimeMillis();
                System.out.println(l2 - l1);
            }
        };

        Thread thread1 = new Thread("ThreadState-1"){
            @Override
            public void run() {
                double d = 0;
                for (int i=0;i<Integer.MAX_VALUE/10;i++){
                    System.out.println(Thread.currentThread());
                    d = Math.random();
                }
            }
        };

        Thread thread2 = new Thread("ThreadState-2"){
            @Override
            public void run() {
                double d = 0;
                for (int i=0;i<Integer.MAX_VALUE/10;i++){
                    System.out.println(Thread.currentThread());
                    d = Math.random();
                }
            }
        };

        Thread thread3 = new Thread("ThreadState-3"){
            @Override
            public void run() {
                double d = 0;
                for (int i=0;i<Integer.MAX_VALUE/10;i++){
                    System.out.println(Thread.currentThread());
                    d = Math.random();
                }
            }
        };
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
