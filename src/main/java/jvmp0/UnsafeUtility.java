package jvmp0;
import java.lang.reflect.Field;

import sun.misc.Unsafe;
public class UnsafeUtility {

	private static Unsafe unsafe;
	static {
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			unsafe = (Unsafe) f.get(null);
		} catch (Exception e) {
		}
	}

	public static Unsafe getUnsafe() {
		return unsafe;
	}

}