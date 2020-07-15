package zeroCopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class OldClinet {
 
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost",8899);
		FileInputStream inputSteam = new FileInputStream("/home/tyx/zdata.txt");
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		byte[] byteArray = new byte[4096];
		int readCount = 0;
		long total = 0;
		long startTime = System.currentTimeMillis();
		while((readCount = inputSteam.read(byteArray)) >=0){
			total += readCount;
			dataOutputStream.write(byteArray);
		}
		dataOutputStream.close();
		System.out.println("发送总字节数 ："+total +"，耗时:"+( System.currentTimeMillis() - startTime));
		
	}
 
}
