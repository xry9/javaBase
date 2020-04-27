package zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewServer {
 	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		ServerSocket  serverSocket=serverSocketChannel.socket();
		serverSocket.setReuseAddress(true);
		serverSocket.bind(new InetSocketAddress(8899));
		ByteBuffer by = ByteBuffer.allocate(4096);
		while(true){
			SocketChannel socketChannel =serverSocketChannel.accept();
			socketChannel.configureBlocking(true);
			int readCount = 0;
			while(-1!=readCount){
				readCount = socketChannel.read(by);
				by.rewind();
			}
		}
	}
}