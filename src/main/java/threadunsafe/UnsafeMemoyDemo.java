package threadunsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeMemoyDemo {
	// -XX:MaxDirectMemorySize=40M
	public static void main(String[] args) throws Exception {
		Unsafe unsafe = UnsafeUtil.getUnsafe();
		while (true) {
			long pointer = unsafe.allocateMemory(1024 * 1024 * 20);
			System.out.println(unsafe.getByte(pointer + 1));
			// 如果不释放内存,运行一段时间会报错java.lang.OutOfMemoryError
//			 unsafe.freeMemory(pointer);
		}
	}

}