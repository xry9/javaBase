package jvmp0.reference;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;

//https://www.cnblogs.com/huajiezh/p/5835618.html
public class ReferenceDemo {
	public static void main(String[] args) throws InterruptedException {
//		weakDemo();
		cache();
	}

	public static void weakDemo() throws InterruptedException {
		ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
		List<SoftReference<Object>> list = new ArrayList<>(Integer.MAX_VALUE/10);
		for (int i=0;i<100000;i++){
			list.add(new SoftReference<>(new Object(),refQueue));
			if (i%1000000==0){
				System.out.println(i);
			}
		}
		System.gc();
//		Thread.sleep(200);
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		for (int i=0;i<100000;i++){
			Reference<Object> ref = (Reference<Object>) refQueue.poll();
			if (ref!=null){
				count1 ++;
				if (ref.get()!=null){
					count3++;
				}
			}
			if (list.get(i)!=null){
				count2 ++;
			}
		}
		System.out.println(count1+"==="+count2+"==="+count3);
	}

	public static void cache() throws InterruptedException {
		ReferenceQueue<Object> refQueue = new ReferenceQueue<Object>();
		List<Reference<ClassRef>> list = new ArrayList<>();
		// 虚引用也称为“幽灵引用”或者“幻影引用”，它是最弱的一种引用关系,一个对是否有虚引用的存在，完全不会对其生存时间构成影响
		//为什么 WeakReference 比 PhantomReference GC 次数更多，我想应该是 WeakReference 引用的对象 GC 时肯定被清理，而 PhantomReference 的对象可能在老年代，就不被清理呀
//		由 SoftReference,WeakReference 满足 count_size=size,count_refget=0,count_get+count_ref=count_size , 而 PhantomReference 不满足第三个条件，能得出什么结论不用多说了
		List<ClassRef> list2 = new ArrayList<>();
		int size = 100;
		for (int i=0;i<size;i++){
			// SoftReference WeakReference PhantomReference 这三种不一样
			list.add(new SoftReference<ClassRef>(new ClassRef(), refQueue));
//			list2.add(new ClassRef());
		}
//		System.gc();
//		Thread.sleep(200);
		int count_size = 0;
		int count_get = 0;
		int count_ref = 0;
		int count_refget = 0;
		for (int i=0;i<size;i++){
			Reference<ClassRef> ref = list.get(i);
			if (ref!=null){
				count_size ++;
				if (ref.get()!=null){
					count_get++;
				}
			}
		}
		for (int i=0;i<size;i++){
			Reference<ClassRef> ref = (Reference<ClassRef>) refQueue.poll();
			if (ref!=null){
				count_ref ++;
				if (ref.get()!=null){
					count_refget++;
				}
			}
		}
		System.out.println(count_size+"==="+count_get+"==="+count_ref+"==="+count_refget);
	}
}
class ClassRef{
	public byte[] bytes;
	public ClassRef(){
		bytes = new byte[1024*1024*10];
	}

}