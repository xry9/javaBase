package genericType;

import java.util.ArrayList;
import java.util.List;

public class TypeErasure_test {
	public static void main(String[] args) {
//		Integer i = add(123,456);//����Ӧ�������ˣ�Integer����ǿת��ֻ�����ǿ�����������û������return (T) o;�����ǲ���˵�����Ͳ�����
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
