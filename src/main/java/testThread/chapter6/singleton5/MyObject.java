package testThread.chapter6.singleton5;

public class MyObject {

	private static MyObject myObject;

	private MyObject() {
	}

	public static MyObject getInstance() {
		try {
			synchronized (MyObject.class) {
				if (myObject != null) {
				} else {
					Thread.sleep(300);
					myObject = new MyObject();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return myObject;
	}

}
