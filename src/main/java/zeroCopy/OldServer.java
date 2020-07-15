package zeroCopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8899);
		while (true) {
			Socket socket =serverSocket.accept();
			DataInputStream datainputStream = new DataInputStream(socket.getInputStream());
			byte[] byteArray = new byte[4096];
			while (true) {
				int readCount  = datainputStream.read(byteArray);
				if(-1 == readCount){
					break;
				}
			}
		}
 
	}
}
