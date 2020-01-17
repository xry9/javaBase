package thread;

import java.util.concurrent.TimeUnit;

public class ThreadHook {

	public static void main(String[] args) {
		    //钩子线程1
          Runtime.getRuntime().addShutdownHook(new Thread() {
        	  public void run() {
        		  System.out.println("The hook thread 1 is running");
        		  try {
					TimeUnit.MICROSECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		  System.out.println("The hook thread 1 will exit.");
        	  }
          });
          
          Runtime.getRuntime().addShutdownHook(new Thread() {
        	  public void run() {
        		  System.out.println("The hook thread 2 is running");
        		  try {
					TimeUnit.MICROSECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		  System.out.println("The hook thread 2 will exit.");
        	  }
        	  
          });
          System.out.println("The program will is stopping.");
	}
}