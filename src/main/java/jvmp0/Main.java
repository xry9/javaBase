package jvmp0;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import sun.misc.Unsafe;

public class Main {
//	http://blog.csdn.net/iter_zc/article/details/40820919
	public static void main(String[] args) {
//		 testNewInstance();
//		 testConstructor();
//		 testConstructorWityParameterTypes();
		 testUnsafeAllocateInstance();
	}

	public static void testUnsafeAllocateInstance() {
		Unsafe unsafe = UnsafeUtility.getUnsafe();
		try {
			DemoClass obj = (DemoClass) unsafe.allocateInstance(DemoClass.class);
			System.out.println(obj.getValue1());
			System.out.println(obj.getValue2());
			obj.setValue1(1);
			obj.setValue2(2);
			System.out.println(obj.getValue1());
			System.out.println(obj.getValue2());
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

	public static void testNewInstance() {
		try {
			DemoClass obj = DemoClass.class.newInstance();
			System.out.println(obj.getValue1());
			System.out.println(obj.getValue2());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void testConstructor() {
		try {
			Class[] cls = new Class[] { int.class, int.class };
			Constructor c = DemoClass.class.getDeclaredConstructor(cls);
			DemoClass obj = (DemoClass) c.newInstance(0, 0);
			System.out.println(obj.getValue1());
			System.out.println(obj.getValue2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testConstructorWityParameterTypes() {
		try {
			Constructor[] c = DemoClass.class.getDeclaredConstructors();
			Type[] parameterTypes = c[0].getGenericParameterTypes();
			DemoClass obj = (DemoClass) c[0].newInstance(0, 0);
			System.out.println(obj.getValue1());
			System.out.println(obj.getValue2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class DemoClass {
	private int value1;

	private int value2 = 10;

	
	public DemoClass(int value1, int value2) {
		this.value1 = value1;
		this.value2 = value2;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}
}