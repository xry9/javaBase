package testThread.chapter6.singleton7;

public class MyObject {

	private volatile static MyObject myObject;

	private MyObject() {
	}

	public static MyObject getInstance() {
		try {
			if (myObject != null) {
			} else {
				Thread.sleep(300);
				synchronized (MyObject.class) {
					if (myObject == null) {
						myObject = new MyObject();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return myObject;
	}
}
