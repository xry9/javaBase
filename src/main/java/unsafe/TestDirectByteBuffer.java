package unsafe;
import java.nio.ByteBuffer;

public class TestDirectByteBuffer {
//http://blog.csdn.net/aitangyong/article/details/39403031
	// -verbose:gc -XX:+PrintGCDetails -XX:MaxDirectMemorySize=40M
	// 加上-XX:+DisableExplicitGC,也会报OOM(Direct buffer memory)
	public static void main(String[] args) throws Exception {
		while (true) {
			ByteBuffer.allocateDirect(10 * 1024 * 1024);//100M试试
		}
	}
}