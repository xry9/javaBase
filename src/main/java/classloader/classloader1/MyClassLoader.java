package classloader.classloader1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 自定义类加载器
 * 需要注意：
 * 在自定义加载器的时候，需继承ClassLoader类
 * 
 * 重写findClass()即可，loadClass()方法封装了代理模式，
 * 该方法会首先调用findLoadedClass()方法来检查该类是否
 * 被加载过，如果没有加载过就调用父类的loadClass()来尝试加载
 * 该类，如果父类加载器无法加载，就调用findClass()方法来查找该类，
 * 为了保证正确的代理模式，最好不要重写loadClass(),只重写findClass()
 * 方法。
 */
public class MyClassLoader extends ClassLoader {
	private String classPath ;
	
	public MyClassLoader(String classPath){
		this.classPath = classPath;
	}
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		System.out.println("***********");
		//return super.findClass(name);
		//1.查找.class文件，并将文件字节加载到内存中
		byte[] classData = getClassData(className);
		System.out.println("字节码："+Arrays.toString(classData));
		//2.判断是否找到.class文件
		if(classData == null){
			//找不到抛出对应的异常
			throw new ClassNotFoundException();
		}else{
			return super.defineClass(className,classData, 0, classData.length);
		}
	}
	
	private byte[] getClassData(String className){
		//获取.class文件的位置
		String path = classNameToPath(className);
		try{
			System.out.println("将要找的文件路径:"+path);
			FileInputStream  fis = new FileInputStream(path);
			ByteArrayOutputStream   baos = new ByteArrayOutputStream();
			
			//读取字节写入内存
			byte [] buffer = new byte[1024];
			int length = 0 ;
			while((length=fis.read(buffer))!=-1){
				baos.write(buffer,0,length);
			}
			//转换为字节数组返回
			return baos.toByteArray() ;
		}catch(IOException e ){
			e.printStackTrace();
		}
		return null ;
	}
	
	public String classNameToPath(String className){
		return classPath+File.separator+className.replace(".",File.separator)+".class";
	}	
}
