package classloader.classloader1;

public class BaseClass {
	public String name ;
	protected int age ;
	double salary ;
	private String  girlFriend ;
	public BaseClass(){
	}
	private BaseClass(String name,int age,double salary,String girlFriend){
		this.name =name ;
		this.age = age ;
		this.salary =salary;
		this.girlFriend = girlFriend ;
	}
	public String sayHi(String name){
		System.out.println("My name is "+name);
		return name;
	}
	protected void printAge(){
		System.out.println("My age is "+age);
	}
	private void setGirlFriend(String name){
		this.girlFriend =name ;
		System.out.println("My girlFriend is "+girlFriend );
	} 
	public String getGirlFriend() {
		return girlFriend;
	}
	public String toString(){
		return this. name + ":"+this.age+":"
		+this.salary+":"+this.girlFriend;
	}
}
