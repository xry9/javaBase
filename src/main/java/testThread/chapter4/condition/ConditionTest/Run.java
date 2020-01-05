package testThread.chapter4.condition.ConditionTest;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();

		MyThreadA a = new MyThreadA(myService);
		a.start();

		MyThreadB b = new MyThreadB(myService);
		b.start();

	}
}
