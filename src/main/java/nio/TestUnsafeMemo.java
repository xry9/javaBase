package nio;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class TestUnsafeMemo {
	// -XX:MaxDirectMemorySize=40M
	public static void main(String[] args) throws Exception {
		Unsafe unsafe = getUnsafeInstance();

		while (true) {
			long pointer = unsafe.allocateMemory(1024 * 1024 * 20);
			System.out.println(unsafe.getByte(pointer + 1));
			// 如果不释放内存,运行一段时间会报错java.lang.OutOfMemoryError
//			 unsafe.freeMemory(pointer);
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