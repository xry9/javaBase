package proxyCglib.demo2;

import net.sf.cglib.proxy.Enhancer;

public class test {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserServiceImpl.class);
		enhancer.setCallback(new MyMethodInterceptor());
		UserServiceImpl userService = (UserServiceImpl)enhancer.create();
		userService.add();
	}
}
//http://www.cnblogs.com/chinajava/p/5880887.html