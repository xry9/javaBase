package geym;

public class LocalVarGC {
	public void localvarGc1(){
		byte[] a=new byte[6*1024*1024];
		System.gc();
	}
	public void localvarGc2(){
		byte[] a=new byte[6*1024*1024];
		a=null;
		System.gc();
	}
	public void localvarGc3(){
//		先使局部变量a失效 ,变量a经离开了作用域 但是变量a依然存在于局部变量表中，并且也指向这块 byte 数组, 依然无法回收
		{
		byte[] a=new byte[6*1024*1024];
		}
		System.gc();
	}
	public void localvarGc4(){
//		不仅使a失效，更是声明了变量c，使变用了变量a的字，由于变a此时被销毁，故垃圾回收器可以顺利回收 byte 数组
		{
			byte[] a=new byte[6*1024*1024];
		}
		int c=10;
		System.gc();
	}
	public void localvarGc5(){
		localvarGc1();
		System.gc();
	}
	public static void main(String[] args) {
		LocalVarGC ins=new LocalVarGC();
		ins.localvarGc4();
	}

}
