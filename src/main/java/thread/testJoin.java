package thread;
public class testJoin {
	public static void main(String[] args) {
		final Thread t1 = new Thread(){
			public void run(){
				for(int i= 0;i<10;i++){
					System.out.println("t1:正在下载图片："+i*10+"%");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
					}
				}
				System.out.println("图片下载完毕");
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				System.out.println("t2:等待图片下载完毕");
				try {
					t1.join();
				} catch (Exception e) {
				}
				System.out.println("t2:显示图片");
			}
		};
		t1.start();
		t2.start();
	}
}
