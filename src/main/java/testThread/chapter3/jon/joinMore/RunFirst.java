package testThread.chapter3.jon.joinMore;


public class RunFirst {

	public static void main(String[] args) {
		ThreadB b = new ThreadB();
		ThreadA a = new ThreadA(b);
		a.start();
		b.start();
		System.out.println("   main end=" + System.currentTimeMillis());
	}
}
//begin A ThreadName=Thread-1  1531534406145
//main end=1531534406145
//end A ThreadName=Thread-1  1531534406649
//begin B ThreadName=Thread-0  1531534406649
//end B ThreadName=Thread-0  1531534407149

//main end=1531534420925
//begin A ThreadName=Thread-1  1531534420925
//end A ThreadName=Thread-1  1531534421425
//begin B ThreadName=Thread-0  1531534421425
//end B ThreadName=Thread-0  1531534421930
