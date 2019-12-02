package testThread.chapter3.jon.joinMore;


public class Run1 {
	public static void main(String[] args) {
		try {
			ThreadB b = new ThreadB();
			ThreadA a = new ThreadA(b);
			a.start();
			b.start();
			b.join(200);
			System.out.println("      main end "
					+ System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//begin A ThreadName=Thread-1  1531497790984
//end A ThreadName=Thread-1  1531497791484
//begin B ThreadName=Thread-0  1531497791486
//end B ThreadName=Thread-0  1531497791986
//    main end 1531497791986

//begin A ThreadName=Thread-1  1472003582279
//end A ThreadName=Thread-1  1472003582779
//                  main end 1472003582779
//begin B ThreadName=Thread-0  1472003582779
//end B ThreadName=Thread-0  1472003583279

//begin A ThreadName=Thread-1  1472003372115
//end A ThreadName=Thread-1  1472003372616
//begin B ThreadName=Thread-0  1472003372616
//                  main end 1472003372616
//end B ThreadName=Thread-0  1472003373116
