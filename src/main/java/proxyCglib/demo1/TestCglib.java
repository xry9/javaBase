package proxyCglib.demo1;

import java.util.Arrays;

public class TestCglib {
	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl bookCglib = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
		System.out.println("===main===9==="+bookCglib.getClass().getName());
		bookCglib.addBook();
		System.out.println(Arrays.toString(bookCglib.getClass().getInterfaces()));
		System.out.println(bookCglib.getClass().getName()+"==="+bookCglib.getClass().getSuperclass().getName());
	}
}
