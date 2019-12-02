package testThread.chapter6.singleton9;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class MyObject implements Serializable {

	private static final long serialVersionUID = 888L;

	private static class MyObjectHandler {
		private static final MyObject myObject = new MyObject();
	}

	private MyObject() {
	}

	public static MyObject getInstance() {
		return MyObjectHandler.myObject;
	}

//	protected Object readResolve() throws ObjectStreamException {
//		return MyObjectHandler.myObject;
//	}

}
