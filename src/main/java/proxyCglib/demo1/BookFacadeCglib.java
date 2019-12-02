//package proxyCglib.demo1;
//
//import java.lang.reflect.Method;
//
//import net.sf.cglib.core.DebuggingClassWriter;
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//
///**
// * 使用cglib动态代理
// * @author student
// */
//public class BookFacadeCglib implements MethodInterceptor {
//	private Object target;
//	/**
//	 * 创建代理对象
//	 */
//	public Object getInstance(Object target) {
//		this.target = target;
//		Enhancer enhancer = new Enhancer();
//		enhancer.setSuperclass(this.target.getClass());
//		// 回调方法
//		enhancer.setCallback(this);
//		// 创建代理对象
//		String setProperty = System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\");
//		System.out.println(setProperty);
//		return enhancer.create();
//	}
////	@Override
//	// 回调方法
//	public Object intercept(Object obj, Method method, Object[] args,
//			MethodProxy proxy) throws Throwable {
//		System.out.println("事物开始");
//		proxy.invokeSuper(obj, args);
//		System.out.println("事物结束");
//		return null;
//	}
//}
