package nio;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class ObjectInHeap {
	private long address = 0;

	private Unsafe unsafe = ObjectInHeap.getUnsafeInstance();

	public ObjectInHeap() {
		address = unsafe.allocateMemory(2 * 1024 * 1024);
	}

	// Exception in thread "main" java.lang.OutOfMemoryError
	public static void main(String[] args) {
		while (true) {
			ObjectInHeap heap = new ObjectInHeap();
			System.out.println("memory address=" + heap.address);
		}
	}

	public static Unsafe getUnsafeInstance() {
		// JDK源码中对这个类进行了严格限制，我们不能通过常规new的方式去获取该类的实例，也不能通过Unsafe.getUnsafeJDK源码中对这个类进行了严格限制，我们不能通过常规new的方式去获取该类的实例，也不能通过Unsafe.getUnsafe()来获取实例。但是我们可以通过反射，在我们的应用代码中获取Unsafe类的实例()来获取实例。但是我们可以通过反射，在我们的应用代码中获取Unsafe类的实例
		// 通过反射获取rt.jar下的Unsafe类
		Field theUnsafeInstance;
		try {
			theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
			theUnsafeInstance.setAccessible(true);
			// return (Unsafe) theUnsafeInstance.get(null);是等价的
			return (Unsafe) theUnsafeInstance.get(Unsafe.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}