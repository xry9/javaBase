package genericType;

import java.util.ArrayList;
import java.util.List;

public class TypeErasure_test {
	public static void main(String[] args) {
//		Integer i = add(123,456);//这里应该是做了（Integer）的强转，只是我们看不到。报错没出现在return (T) o;这行是不是说明泛型擦除了
		System.out.println(add(123,456));
		System.out.println(add(123,456).getClass());
	}
	public static <T> T add(T x, T y) {//<T extends Integer>
		Object o = "abc";
		return (T) o;
	}
	public void f1(String s){
		
	}
	public void f1(Object o){
		
	}
}
