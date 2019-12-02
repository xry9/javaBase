package testThread.chapter2.synNotExtends;

public class Sub extends Main {

//	同步不可以继承
	@Override
	public void serviceMethod() {//synchronized
		try {
			System.out.println("int sub 下一步 sleep begin threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("int sub 下一步 sleep   end threadName="
					+ Thread.currentThread().getName() + " time="
					+ System.currentTimeMillis());
			super.serviceMethod();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
