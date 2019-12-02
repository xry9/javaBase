package testThread.chapter1.suspend_resume.step2;

public class SynchronizedObject {

	synchronized public void printString() {
		System.out.println("begin");
//		同步对象独占，使得其它线程无法访问公共同步对象
		if (Thread.currentThread().getName().equals("a")) {
			System.out.println("==========");
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
}
