package other;

import java.util.ArrayList;
import java.util.List;

public class Memory3 {
	public static void main(String[] args) {
		long l = System.currentTimeMillis();
		List<Memory3> list = new ArrayList<>();
		for (int i = 0; i < 10000_0000; i++) {
//			new test();
			list.add(new Memory3());
		}
		System.out.println(System.currentTimeMillis()-l);
	}
}
