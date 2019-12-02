package objectSize;

public class ClassIntrospectorTest {
//	http://blog.csdn.net/iter_zc/article/details/41822719/
	public static void main(String[] args) throws IllegalAccessException {
		final ClassIntrospector ci = new ClassIntrospector();
		ObjectInfo res;
//	20 * n + 16(12 + 4)
//		res = ci.introspect(new ObjectC());
		ObjectC[] os = new ObjectC[5];
		for (int i = 0; i < os.length; i++) {
			os[i] = new ObjectC();
		}
		res = ci.introspect(os);
		System.out.println(res.getDeepSize());
		System.out.println(res.length);
		System.out.println(res.offset);
		System.out.println(res.size);
	}

	private static class ObjectA {
		String str; // 4
		int i1; // 4
		byte b1; // 1
		byte b2; // 1
		int i2; // 4
		ObjectB obj; // 4
		byte b3; // 1
	}

	private static class ObjectB {
//		Integer i; // 4
//		ObjectC oc = new ObjectC();
//		int ib1; // 4
//		int ib2; // 4
//		int i3; // 4
	}
	private static class ObjectC {
//		Integer ic = 1; // 4
		int ic1; // 4
//		byte b1; // 4
	}
}