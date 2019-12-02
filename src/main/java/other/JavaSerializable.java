package other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class JavaSerializable {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File file=new File("src"+File.separator+"package4"+File.separator+"emp.obj");
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		Emp emp=new Emp("tong",18,5000.01);
		
//		oos.writeObject(emp);
		oos.writeObject(new String(new char[]{'q','a'}));
		
		System.out.println("锟斤拷锟叫伙拷锟斤拷锟�");
		oos.close();
		System.out.println("------------");
		FileInputStream fis=new FileInputStream(file);
		ObjectInputStream ois=new ObjectInputStream(fis);
		
//		emp=(Emp) ois.readObject();
//		System.out.println(emp);
		System.out.println(ois.readObject());
		System.out.println("锟斤拷锟斤拷锟叫伙拷锟斤拷锟�");
	}
}

class Emp implements Serializable{
	private static final long serialVersionUID=1l;
	private String name;
	private int age;
	private double sal;
	
	public Emp(){
		
	}
	public Emp(String name, int age, double sal) {
		super();
		this.name = name;
		this.age = age;
		this.sal = sal;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public String toString() {
		return "Emp [age=" + age + ", name=" + name + ", sal=" + sal + "]";
	}
		
}
