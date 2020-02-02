package jvmp0;

public class TestStackDeep {
	public static int count = 0;
	public static void recursion(long a, long b, long c, long o, long p, long m) {
		long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, q = 7, x = 8, y = 9, z = 10;
		count++;
		recursion(a, b, c, e, f, g);
	}
}
