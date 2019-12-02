package thread;

public class synchronized3 {
	public static void main(String[] args) {
		final table t=new table();
		System.out.println(Thread.currentThread().getName());//主线程的名字是main,非主线程的名字不确定
		Thread t1=new Thread(){
			public void run(){
				while(true){
					System.out.println(getName()+":"+t.getBean());
					Thread.yield();
				}
			}
		};
		Thread t2=new Thread(){
			public void run(){
				while(true){
					System.out.println(getName()+":"+t.getBean());
					yield();
				}
			}
		};
		t1.start();
		t2.start();
	}
}
class table {
	private int bean=20;
	public int getBean(){
		if(bean==0){
			throw new RuntimeException("没有豆子了!");
		}
		bean--;
		Thread.yield();
		return bean;
	}
}
