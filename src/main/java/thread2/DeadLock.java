package thread2;

public class DeadLock {
    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WRITE = new Object();

    public void read(){
        synchronized (MUTEX_READ){
            System.out.println("get Read LOCK");
            synchronized (MUTEX_WRITE){
                System.out.println("get Write LOCK");
            }
            System.out.println("release Write LOCK");
        }
        System.out.println("release Read LOCK");
    }

    public void write(){
        synchronized (MUTEX_WRITE){
            System.out.println("get Write LOCK");
            synchronized (MUTEX_READ){
                System.out.println("get Read LOCK");
            }
            System.out.println("release Read LOCK");
        }
        System.out.println("release Write LOCK");
    }

    public static void main(String[] args) {
        DeadLock dl = new DeadLock();
        new Thread(()->{
            while (true){
                dl.read();
            }
        }, "Read-Thread").start();

        new Thread(()->{
            while (true){
                dl.write();
            }
        }, "Write-Thread").start();
    }
}
