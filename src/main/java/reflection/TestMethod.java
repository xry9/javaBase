package reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestMethod {
	public static void main(String[] args) {
		Class c = BaseClass.class;
		Method m = null ;
		//获取Method对象
		try {
			m = c.getMethod("sayHi", new Class[]{String.class});
//			getMethod(String name, Class<?>... parameterTypes)
//			c.getMethod("sayHi", String.class);
			System.out.println(Modifier.toString(m.getModifiers())+":"
					+m.getReturnType().getName()+":"+
					m.getName());
			
			Class [] pt = m.getParameterTypes();
			for(Class cc :pt){
				System.out.println(cc.getName());
			}

			System.out.println("-----------");
			BaseClass baseClass = (BaseClass) c.newInstance();
			m = c.getDeclaredMethod("setGirlFriend", String.class);
			//
			m.setAccessible(true);
			Object ob = baseClass;
			Object oc = "如花";
			String s = (String) m.invoke(ob,oc);
			m = c.getDeclaredMethod("getGirlFriend");
			Object o1 = baseClass;
			Object o = m.invoke(o1);
			System.out.println(o);
			System.out.println(baseClass);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
