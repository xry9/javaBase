package jvmp0;

public class ThreadOrder {
	int a ;
	boolean flag = false;
	public void writer(){
		a = 1;
		flag = true;
	}
	public void reader(){
		if(flag){
			int i = a + 1;
		}
	}
	/*
	 * 线程A先执行writer(),线程B接着执行reader(),线程B在int i = a + 1;不一定a已经赋值为1
	 * 因为在writer中两句可能打乱
	 * 为保证有序则两个方法要加:synchronized
	 */
}
