package jvmp0;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Queue;

//https://www.cnblogs.com/huajiezh/p/5835618.html
public class ReferenceDemo {
	// public static void main(String[] args) {
	// Object o = new Object();
	// SoftReference aSoftRef = new SoftReference(o);
	// ReferenceQueue q1 = new ReferenceQueue();
	// Reference ref;
	// while ((ref = q1.poll()) != null) {
	// }
	// }
	public static void main(String[] args) {
		WeakReference<Object> reference = new WeakReference<Object>(new Object());
		System.out.println(reference.get());
		System.gc();// 通知GVM回收资源
		System.out.println(reference.get());
	}
}
