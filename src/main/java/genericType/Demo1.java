package genericType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Demo1 {
	public static void main(String[] args) {
		System.out
				.println(new ArrayList<Integer>().getClass() == new ArrayList<String>()
						.getClass());
		fun2();
		// fun3();
		// fun4();
	}

	public static void fun2() {
		Point1<Integer, Integer> p1 = new Point1<Integer, Integer>();
		p1.setX(10);
		p1.setY(20);
		p1.printPoint(p1.getX(), p1.getY(), "Tong", 18);
		Point1<Double, String> p2 = new Point1<Double, String>();
		p2.setX(25.4);
		p2.setY("东京180度");
		p2.printPoint(p2.getX(), p2.getY(), "Tong", 18);
		/**
		 * 与使用泛型类不同，使用泛型方法时不必指明参数类型，编译器会根据传递的参数自动查找出具体的类型。
		 * 泛型方法除了定义不同，调用就像普通方法一样。
		 * 注意：泛型方法与泛型类没有必然的联系，泛型方法有自己的类型参数，在普通类中也可以定义泛型方法。泛型方法 printPoint()
		 * 中的类型参数 T1, T2 与泛型类 Point 中的 T1, T2 没有必然的联系，也可以使用其他的标识符代替 public
		 * static <V1, V2> void printPoint(V1 x, V2 y){
		 */
	}

	public static void fun3() {
		Info<String> obj = new InfoImp<String>("www.weixueyuan.net");
		System.out.println("Length Of String: " + obj.getVar().length());
	}

	public static void fun4() {
		// 如果在使用泛型时没有指明数据类型，那么就会擦除泛型类型
		// 因为在使用泛型时没有指明数据类型，为了不出现错误，编译器会将所有数据向上转型为 Object，
		// 所以在取出坐标使用时要向下转型，这与本文一开始不使用泛型没什么两样。
		Point p = new Point(); // 类型擦除
		p.setX(10);
		p.setY(20.8);
		int x = (Integer) p.getX(); // 向下转型
		double y = (Double) p.getY();
		System.out.println("This point is：" + x + ", " + y);
	}

	public <T extends Number> T getMax(T array[]) {
		T max = null;
		for (T element : array) {
			max = element.doubleValue() > max.doubleValue() ? element : max;
		}
		return max;
	}

	// <T extends Number> 表示 T 只接受 Number 及其子类，传入其他类型的数据会报错。这里的限定使用关键字
	// extends，后面可以是类也可以是接口。但这里的 extends 已经不是继承的含义了，应该理解为 T 是继承自 Number
	// 类的类型，或者 T 是实现了 XX 接口的类型。如果是类，只能有一个；但是接口可以有多个，并以“&”分隔，例如 <T extends
	// Interface1 & Interface2>。
	// 注意：一般的应用开发中泛型使用较少，多用在框架或者库的设计中，这里不再深入讲解，主要让大家对泛型有所认识，为后面的教程做铺垫。

	public static void fun5() {
		// public void printPoint(Point p){
		// System.out.println("This point is: " + p.getX() + ", " + p.getY());
		// }
		//
		// 我们知道，如果在使用泛型时没有指名具体的数据类型，就会擦除泛型类型，并向上转型为
		// Object，这与不使用泛型没什么两样。上面的代码没有指明数据类型，相当于：
		//
		// public void printPoint(Point<Object, Object> p){
		// System.out.println("This point is: " + p.getX() + ", " + p.getY());
		// }
		//
		// 为了避免类型擦除，可以使用通配符(?)：
		Point<Integer, Integer> p1 = new Point<Integer, Integer>();
		p1.setX(10);
		p1.setY(20);
		printPoint(p1);

		Point<String, String> p2 = new Point<String, String>();
		p2.setX("东京180度");
		p2.setY("北纬210度");
		printPoint(p2);
	}

	public static void printPoint(Point<?, ?> p) { // 使用通配符
		System.out.println("This point is: " + p.getX() + ", " + p.getY());
	}

	public static void fun6() {
		// 但是，数字坐标与字符串坐标又有区别：数字可以表示x轴或y轴的坐标，字符串可以表示地球经纬度。现在又要求定义两个方法分别处理不同的坐标，一个方法只能接受数字类型的坐标，另一个方法只能接受字符串类型的坐标，怎么办呢？
		// 这个问题的关键是要限制类型参数的范围，请先看下面的代码
		Point<Integer, Integer> p1 = new Point<Integer, Integer>();
		p1.setX(10);
		p1.setY(20);
		printNumPoint(p1);

		Point<String, String> p2 = new Point<String, String>();
		p2.setX("东京180度");
		p2.setY("北纬210度");
		printStrPoint(p2);
	}

	// 借助通配符限制泛型的范围
	public static void printNumPoint(Point<? extends Number, ? extends Number> p) {
		System.out.println("x: " + p.getX() + ", y: " + p.getY());
	}
	public static void printStrPoint(Point<? extends String, ? extends String> p) {
		System.out.println("GPS: " + p.getX() + "，" + p.getY());
	}
	// 使用通配符(?)不但可以限制类型的上限，还可以限制下限。限制下限使用 super 关键字，
	// 例如 <? super Number> 表示只能接受 Number 及其父类。
}

// 定义泛型类
class Point<T1, T2> {
	T1 x;
	T2 y;

	public T1 getX() {
		return x;
	}

	public void setX(T1 x) {
		this.x = x;
	}

	public T2 getY() {
		return y;
	}

	public void setY(T2 y) {
		this.y = y;
	}
}

/**
 * 数组范型 可以使用带范型参数值的类声明数组，却不可有创建数组 List<Integer>[] iListArray; new
 * ArrayList<Integer>[10];//编译时错误
 */
// 定义泛型类
class Point1<T1, T2> {
	T1 x;
	T2 y;
	{//static
		T1 t = (T1) new Object();
	}
	T1 tt = (T1) new Object();
//	static T1 tt1 = (T1) new Object();
	
	public T1 getX() {
		return x;
	}

	public void setX(T1 x) {
		this.x = x;
	}

	public T2 getY() {
		return y;
	}

	public void setY(T2 y) {
		this.y = y;
	}

	// 定义泛型方法
	public <T3, T4> void printPoint(T1 x, T2 y, T3 a, T4 b) {
		System.out.println(a + "--" + b);
		T1 m = x;
		T2 n = y;
		System.out.println("This point is：" + m + ", " + n);
	}
}

// 定义泛型接口
interface Info<T> {
	public T getVar();
}

// 实现接口
class InfoImp<T> implements Info<T> {
	private T var;

	// 定义泛型构造方法
	public InfoImp(T var) {
		this.setVar(var);
	}

	public void setVar(T var) {
		this.var = var;
	}

	public T getVar() {
		return this.var;
	}
}
