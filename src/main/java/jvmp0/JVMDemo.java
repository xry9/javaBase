package jvmp0;

import java.util.HashMap;
import java.util.Map;

public class JVMDemo {
	public static void main(String[] args) throws ClassNotFoundException {
//		System.out.println("Hello World...");
		// Permanent
		// Generation即持久代。持久代中要保存用户定义类的meta信息。如果程序中定义了很多类，就有可能很快填满这一代的内存空间，从而导致java.lang.OutOfMemoryError:PermGen.
		// 这一代的内存空间和-Xmx参数没有关系，也和程序所在机器上的剩余内存有多少没有关系

		// -Xmn 设置新生代大小
		// -XX:NewRatio 新生代（eden+2*s）和老年代（不包含永久区）的比值
		// 　　　　例如：4，表示新生代:老年代=1:4，即新生代占整个堆的1/5
		// -XX:SurvivorRatio（幸存代） 设置两个Survivor区和eden的比值
		// 　　　　例如：8，表示两个Survivor:eden=2:8，即一个Survivor占年轻代的1/10
		// -Xmx20m -Xms5m
//		 -Xloggc:D:\gc.log
//		 test1();
//		 test2();
//		 test3();
//		 test4();
//		 test5();
		// -XX:+PrintGCTimeStamps
		// -Xmx20m -Xms20m -Xmn1m -XX:+PrintGCDetails
		// -Xmx20m -Xms20m -Xmn7m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
		// -Xmx20m -Xms20m -XX:NewRatio=1 -XX:SurvivorRatio=2
		// -XX:+PrintGCDetails
		// -Xmx20m -Xms20m -XX:NewRatio=1 -XX:SurvivorRatio=3
		// -XX:+PrintGCDetails

		// 16 30 46 60 76 90 106
		 byte[] b = null;
		 for (int i = 0; i < 640000; i++) {
		 	b = new byte[1 * 1024 * 1024];
		 }

		// -Xss128K
		try {
			TestStackDeep.recursion(0L, 0L, 0L, 0L, 0L, 0L) ;
		} catch (Throwable e) {
			System.out.println("deep of calling = " + TestStackDeep.count);
			e.printStackTrace();
		}

//		for(int i=0;i<10000000;i++){
//			CglibBean bean = new CglibBean("geym.jvm.ch3.perm.bean"+i,new HashMap());
//			System.out.println(bean.getObject().getClass());
//		}

//		MyThread myThread = new MyThread();
//		PrintThread pThread = new PrintThread();
//		myThread.start();
//		pThread.start();

	}

	public static void test1() {
		System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0
				/ 1024 + "M"); // 系统的最大空间
		System.out.println("free mem=" + Runtime.getRuntime().freeMemory()
				/ 1024.0 / 1024 + "M"); // 系统的空闲空间
		System.out.println("total mem=" + Runtime.getRuntime().totalMemory()
				/ 1024.0 / 1024 + "M"); // 当前可用的总空间
	}

	public static void test2() {
		byte[] b = new byte[1 * 1024 * 1024];
		System.out.println("分配了1M空间给数组");
		System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0
				/ 1024 + "M"); // 系统的最大空间
		System.out.println("free mem=" + Runtime.getRuntime().freeMemory()
				/ 1024.0 / 1024 + "M"); // 系统的空闲空间
		System.out.println("total mem=" + Runtime.getRuntime().totalMemory()
				/ 1024.0 / 1024 + "M");
	}

	public static void test3() {
		byte[] b = new byte[10 * 1024 * 1024];
		System.out.println("分配了10M空间给数组");
		System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0
				/ 1024 + "M"); // 系统的最大空间
		System.out.println("free mem=" + Runtime.getRuntime().freeMemory()
				/ 1024.0 / 1024 + "M"); // 系统的空闲空间
		System.out.println("total mem=" + Runtime.getRuntime().totalMemory()
				/ 1024.0 / 1024 + "M"); // 当前可用的总空间
	}

	public static void test4() {
		System.gc();
		System.out.println("Xmx=" + Runtime.getRuntime().maxMemory() / 1024.0
				/ 1024 + "M"); // 系统的最大空间
		System.out.println("free mem=" + Runtime.getRuntime().freeMemory()
				/ 1024.0 / 1024 + "M"); // 系统的空闲空间
		System.out.println("total mem=" + Runtime.getRuntime().totalMemory()
				/ 1024.0 / 1024 + "M"); // 当前可用的总空间
	}
	
	public static void test5(){
		byte[] b = null;
        for (int i = 0; i < 10000; i++){        	
        	b = new byte[1 * 1024 * 1024];
        }
	}


	public static class PrintThread extends Thread{
	    public static final long starttime=System.currentTimeMillis();
	    @Override
	    public void run(){
	        try{
//	        	按道理来说，应该是每隔100ms会打印输出一条日志，但是当执行GC的时候，会出现全局停顿的情况，导致没有按时输出。
	            while(true){
	                long t=System.currentTimeMillis()-starttime;
	                System.out.println("time:"+t);
	                Thread.sleep(100);
	            }
	        }catch(Exception e){
	        }
	    }
	}
	
	public static class MyThread extends Thread{
	    Map<Long,byte[]> map=new HashMap<Long,byte[]>();
	    @Override
	    public void run(){
	        try{
	            while(true){
	                if(map.size()*512/1024/1024>=550){   //如果map消耗的内存消耗大于450时，那就清理内存
	                    System.out.println("=====准备清理=====:"+map.size());
	                    map.clear();
	                }
	                for(int i=0;i<1024;i++){
	                    map.put(System.nanoTime(), new byte[512]);
	                }
	                Thread.sleep(1);
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
	}
}

class TestStackDeep {
	public static int count = 0;
	public static void recursion(long a, long b, long c, long o, long p, long m) {
		long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
		count++;
		recursion(a, b, c, e, f, g);
	}
}

