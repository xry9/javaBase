package singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestSingleton1 {

    @Test
    public void test3() throws Exception{
        Singleton3 s1 = null;
        Singleton3 s2 = Singleton3.getInstance();
        //1.将实例串行话到文件
		File file=new File("src"+File.separator+"singleton"+File.separator+"123"+File.separator+"singleton.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos =new ObjectOutputStream(fos);
        oos.writeObject(s2);
        oos.flush();
        oos.close();
        //2.从文件中读取出单例
        FileInputStream fis = new FileInputStream("src"+File.separator+"singleton"+File.separator+"123"+File.separator+"singleton.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        s1 = (Singleton3) ois.readObject();
        if(s1==s2){
            System.out.println("同一个实例");
        }else{
            System.out.println("不是同一个实例");
        }
    }
}
