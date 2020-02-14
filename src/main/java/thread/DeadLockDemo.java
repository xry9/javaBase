package thread;

public class DeadLockDemo {
    private String A = "A";
    private String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
    private void deadLock() {
        Thread t1 = new Thread(){
            public void run(){
                synchronized (A){
                    try {
                        Thread.sleep(2000);
                        synchronized (B){
                            System.out.println("1");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                synchronized (B){
                    try {
                        Thread.sleep(2000);
                        synchronized (A){
                            System.out.println("2");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}
