package jvmp0.reference.phantom;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PhantomTest {
    public static void main(String[] args) throws Exception {
        //打开回收
        ResourceCloseDeamon deamon = new ResourceCloseDeamon();
        deamon.setDaemon(true);
        deamon.start();

        // touch a.class b.txt
        // echo “hello” > a.class
        //保留对象,防止gc把stream回收掉,其不到演示效果
        List<Closeable> all=new ArrayList<>();
        FileInputStream inputStream;
        FileOutputStream outputStream;

        for (int i = 0; i < 100000; i++) {
            inputStream = new FileInputStream("/home/tyx/a.class");
            outputStream = new FileOutputStream("/home/tyx/b.txt");
            FileOperation operation = new FileOperation(inputStream, outputStream);
            operation.operate();
            TimeUnit.MILLISECONDS.sleep(100);

            List<Closeable>closeables=new ArrayList<>();
            closeables.add(inputStream);
            closeables.add(outputStream);
            all.addAll(closeables);
            ResourceCloseDeamon.register(operation,closeables);
            //用下面命令查看文件句柄,如果把上面register注释掉,就会发现句柄数量不断上升
            //jps | grep PhantomTest |awk '{print $1}' |head -1 |xargs  lsof -p  | grep /User/robin
            System.gc();
        }
    }
}