package genericType;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TypeErasure {
	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		// fun1();
		fun2();
		// fun3();
	}

	static void fun1() {
		ArrayList<String> arrayList1 = new ArrayList<String>();
		arrayList1.add("abc");
		ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
		arrayList2.add(123);
		System.out.println(arrayList1.getClass() == arrayList2.getClass());
		/*
		 * 在这个例子中，我们定义了两个ArrayList数组，不过一个是ArrayList<String>泛型类型，只能存储字符串。
		 * 一个是ArrayList<Integer>泛型类型，只能存储整形。最后，我们通过arrayList1对象和arrayList2对象
		 * 的getClass方法获取它们的类的信息，最后发现结果为true。说明泛型类型String和Integer都被擦除掉了，
		 * 只剩下了原始类型。
		 */
	}

	static void fun2() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		ArrayList<Integer> arrayList3 = new ArrayList<Integer>();
		arrayList3.add(1);// 这样调用add方法只能存储整形，因为泛型类型的实例为Integer
		arrayList3.getClass().getMethod("add", Object.class).invoke(arrayList3, "abc");
		for (int i = 0; i < arrayList3.size(); i++) {
			// System.out.println(arrayList3.get(i).getClass());// 不获取class就不会报错
//			System.out.println(((Integer) arrayList3.get(i)).toString());
			Object o = arrayList3.get(i);
//			System.out.println(o.toString());
//			System.out.println(arrayList3.get(i).toString());
//			System.out.println(o.getClass());
//			Java范型时编译时技术，在运行时不包含范型信息，仅仅Class的实例中包含了类型参数的定义信息。
		}
		/*
		 * 在程序中定义了一个ArrayList泛型类型实例化为Integer的对象，如果直接调用add方法，那么只能存储整形的数据。
		 * 不过当我们利用反射调用add方法的时候，却可以存储字符串。这说明了Integer泛型实例在编译之后被擦除了，只保留了原始类型。
		 */
	}

	static void fun3() {
		/** 不指定泛型的时候 */
		int i = add(1, 2); // 这两个参数都是Integer，所以T为Integer类型
		Number f = add(1, 1.2);// 这两个参数一个是Integer，一个是Float，所以取同一父类的最小级，为Number
		Object o = add(1, "asd");// 这两个参数一个是Integer，一个是String，所以取同一父类的最小级，为Object

		/** 指定泛型的时候 */
		int a = TypeErasure.<Integer> add(1, 2);// 指定了Integer，所以只能为Integer类型或者其子类
		// int b=TypeErasure.<Integer>add(1, 2.2);//编译错误，指定了Integer，不能为Float
		Number c = TypeErasure.<Number> add(1, 2.2); // 指定为Number，所以可以为Integer和Float
	}

	public static <T> T add(T x, T y) {
		return y;
	}

}
