package testThread.chapter2.synNotExtends;

import testThread.chapter2.synNotExtends.MyThreadA;
import testThread.chapter2.synNotExtends.MyThreadB;
import testThread.chapter2.synNotExtends.Sub;

public class Test {

	public static void main(String[] args) {
		Sub subRef = new Sub();

		MyThreadA a = new MyThreadA(subRef);
		a.setName("A");
		a.start();

		MyThreadB b = new MyThreadB(subRef);
		b.setName("B");
		b.start();
	}

}
