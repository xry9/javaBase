package zeroCopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewClient {
 
	/**
	 * https://blog.csdn.net/lm324114/article/details/78222892
	 */
	public static void main(String[] args) throws IOException {
		SocketChannel socket = SocketChannel.open();
		socket.connect(new InetSocketAddress("localhost",8899));
		socket.configureBlocking(true);
		FileInputStream inputSteam = new FileInputStream("/home/tyx/zdata.txt");
		FileChannel filechannel = inputSteam.getChannel();
		long startTime = System.currentTimeMillis();
		long transCount = filechannel.transferTo(0, filechannel.size(), socket);
		System.out.println("发送总字节数 ："+transCount +"，耗时:"+( System.currentTimeMillis() - startTime));
		socket.close();
	}
}

