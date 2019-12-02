package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
/**
 * 演示Constructor
 */
public class TestConstructor {
	public static void main(String[] args) {
		Class c = BaseClass.class;
		//获取类中所有的构造方法
		Constructor [] cons= c.getDeclaredConstructors();
		for(Constructor con:cons){
			System.out.println(Modifier.toString(con.getModifiers())
					+"::"+con.getName());
			Class [] cc = con.getParameterTypes();
			for(Class ccc:cc){
				System.out.println(ccc.getName());
			}
		}
		System.out.println("-----------");
		//调用构造方法实例化对象
		try {
			Constructor constructor = 
				c.getDeclaredConstructor
					(String.class,int.class,double.class,String.class);
			constructor.setAccessible(true);
			BaseClass baseClass = 
				(BaseClass)constructor.newInstance("涛哥",22,5000,"如花");//也可以   类.newInstance()
			System.out.println(baseClass);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
