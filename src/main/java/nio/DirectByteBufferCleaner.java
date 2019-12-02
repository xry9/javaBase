package nio;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

public class DirectByteBufferCleaner {

	public static void main(String[] args) throws Exception {
//		 f1();
//		 gg();
		getUnsafeInstance();
	}

	public static void f1() throws Exception {
//		http://blog.csdn.net/xieyuooo/article/details/7547435
		Thread.sleep(5000);
		System.out.println("start allocate...");
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
		Thread.sleep(5000);
		System.out.println("start clean...");
		if (buffer.isDirect()) {
			((DirectBuffer) buffer).cleaner().clean();
		}
		System.out.println("end clean...");
		Thread.sleep(5000);
		System.out.println("end");
	}

	public static void ff() throws ClassNotFoundException,
			NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		System.out.println("maxMemoryValue:" + sun.misc.VM.maxDirectMemory());
		System.out.println("================================");
		ByteBuffer buffer = ByteBuffer.allocateDirect(0);
		Class<?> c = Class.forName("java.nio.Bits");
		Field maxMemory = c.getDeclaredField("maxMemory");
		maxMemory.setAccessible(true);
		synchronized (c) {
			Long maxMemoryValue = (Long) maxMemory.get(null);
			System.out.println("maxMemoryValue:" + maxMemoryValue);
			maxMemory.setLong(null, 4790077952l);
			maxMemoryValue = (Long) maxMemory.get(null);
			System.out.println("maxMemoryValue:" + maxMemoryValue);
		}
	}

	public static Unsafe getUnsafeInstance() throws Exception {
		// JDK源码中对这个类进行了严格限制，我们不能�?�过常规new的方式去获取该类的实例，也不能�?�过Unsafe.getUnsafeJDK源码中对这个类进行了严格限制，我们不能�?�过常规new的方式去获取该类的实例，也不能�?�过Unsafe.getUnsafe()来获取实例�?�但是我们可以�?�过反射，在我们的应用代码中获取Unsafe类的实例()来获取实例�?�但是我们可以�?�过反射，在我们的应用代码中获取Unsafe类的实例
		// 通过反射获取rt.jar下的Unsafe�?
		Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafeInstance.setAccessible(true);
		// return (Unsafe) theUnsafeInstance.get(null);是等价的
		return (Unsafe) theUnsafeInstance.get(Unsafe.class);
	}

	public static void gg() throws Exception {
		Unsafe unsafe = getUnsafeInstance();
		while (true) {
			long pointer = unsafe.allocateMemory(1024 * 1024 * 200);
			System.out.println(unsafe.getByte(pointer + 1));
			// 如果不释放内�?,运行�?段时间会报错java.lang.OutOfMemoryError
//			 unsafe.freeMemory(pointer);
		}
	}
}
