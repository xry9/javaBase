package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestField {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		Class c = BaseClass.class;
		//获取属性信息
		//c.getField("name"); 获取给定属性名的Field对象
		Field [] fields = c.getFields();
		for(Field f : fields ){
			System.out.println(Modifier.toString(f.getModifiers())+
					":"+f.getName()+
					":"+f.getType());
		}
		System.out.println("----------------");
		//
		fields = c.getDeclaredFields();
		for(Field f : fields ){
			System.out.println(Modifier.toString(f.getModifiers())+
					":"+f.getName()+
					":"+f.getType());
		}
		System.out.println("-----------");
		//使用获取到的属性
		try {
			BaseClass baseClass =(BaseClass) c.newInstance();//也可以    构造方法.newInstance()
			System.out.println(baseClass);
			Field f = c.getField("name");
			f.set(baseClass, "涛哥");
			System.out.println(baseClass);
			//
			f = c.getDeclaredField("girlFriend");
			f.setAccessible(true);
			f.set(baseClass, "凤姐");
			System.out.println(baseClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
