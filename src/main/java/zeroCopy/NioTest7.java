package zeroCopy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 它是一个直接缓存区是一个文件内存映射区域，MappedByteBuffer 它是可以通过Filechanle.map来实现的，MappedByteBuffer 是一种允许java程序直接在内存中访问的特殊的文件，
 * 可以将整个文件或者文件的一部分映射到内存中，并有操作系统来完成内容的修改并写入到文件当中，我们的应用程序只需要处理内存的数据，这样可以迅速的处理IO操作，用于内存映射文件的内存本
 * 身是在java堆的外面也叫堆外内存
 */
public class NioTest7 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte)'a');
        //只需操作内存 不需要写入文件 怎么把数据写入文件当中 都是由操作系统来完成 不需要我们去管  
        mappedByteBuffer.put(3,(byte)'b');
        randomAccessFile.close();
    }
}
