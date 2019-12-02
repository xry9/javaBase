package genericType;

import java.util.ArrayList;
import java.util.List;

public class base {
	public static void main(String[] args) {
//		fun1();
//		fun2();
//		fun3();
		fun4();
	}
	public static void fun1(){
		int[] a = new int[3];
		Object o = a ;
		System.out.println(a.getClass().getName());
//		[I@13bcbc8
	}
	public static void fun2(){
		 List<String> l1 = new ArrayList<String>();
	     List<Integer> l2 = new ArrayList<Integer>();
	     System.out.println(l1.getClass());
	     System.out.println(l1.getClass() == l2.getClass());
	}
	public static void fun3(){
		Class<String> a = String.class;
	}
	public static void fun4(){
		Integer i = new Integer(2);
		Number n = i;
		n = new Float(2.2);
		System.out.println(i+"--"+n);
		////////////////////////////
		Integer[] integer = new Integer[5]; 
		Number[] number = integer; 
		System.out.println(number[0]);// null 
//		number[0] = new Float(7.65); 
		System.out.println(number[0]); 
		System.out.println(integer[0]); 
		List<Integer> list = new ArrayList<Integer>(); 
//		 List<Number> listObj = list;
		List<Integer> l = new ArrayList<Integer>();
		printList(l);
	}
	static void printList(List l) { 
		for (Object o : l) 
			System.out.println(o); 
	} 
//	static void printList(List<Object> l) { 
//		for (Object o : l) 
//			System.out.println(o); 
//	} 
}
