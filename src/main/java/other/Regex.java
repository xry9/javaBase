package other;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
public class Regex {
//	\n \t \r \\ \" \' \\u \\0
	
	/**正则表达式需要转义的字符
	 * $ ---> \$  
		( ---> \(  
		) ---> \)  
		* ---> \*  
		+ ---> \+  
		. ---> \.  
		[ ---> \[  
		] ---> \]  
		? ---> \?  
		\ ---> \\  
		/ ---> \/  
		^ ---> \^  
		{ ---> \{  
		} ---> \}
		- ---> \-
		]^}|$-不转义也可
	 */
	/*
		.匹配除换行符 \n 之外的任何单个字符。 若要匹配包括 \n 在内的任意字符，请使用诸如 [\s\S] 之类
	 */
//	\\w  \\W  \\s  \\S  \\d  \\D
	@Test
	public void test1(){
		String regex="\\.";
		System.out.println(".".matches(regex));
		regex="\\(";
		System.out.println("(".matches(regex));
		
		System.out.println("-------------------");
		String s="+1";//(
		regex="^\\+1$";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(s);
		System.out.println(m.find());

	}
	@Test
	public void ff(){
		//1
		String email="^(\\+86\\s)?\\d{11}";//+前不用\\会报错
		String email1="[a-zA-Z0-9-]";
		System.out.println("+8613312341234".matches(email));
		System.out.println("+86	13312341234".matches(email));
		System.out.println("13312341234".matches(email));
		
		String z1="\\w{3,5}";
		System.out.println("23we".matches(z1));
		
		System.out.println("1------------");
		//2
		String z2=".{3,6}";
		String z3=".";//[.].作用失效了
//		System.out.println("sd3d".matches(z2));
		System.out.println("a".matches(z3));
		
		System.out.println("2-------------");
		//3
		//看来想取消默认边界匹配只能这样处理了
		String s="abc11defg";
		String regex="[0-9]+";//^[0-9]+$
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(s);
		System.out.println(m.find());
		System.out.println(s.matches(regex));
		System.out.println("3-------------");
	}
	@Test
	public void fffF(){
//		String str = "BB";//BBBB
//		Pattern p1 = Pattern.compile("^BB$");
//		Matcher m1 = p1.matcher(str);
//		while (m1.find()) {
//			System.out.println(m1.group());
//		}
		String str = "1234\tab cd efg";
		String regex = "(\\w+) (\\w+) (\\w+)";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(str);
		System.out.println(matcher.find());
		while(matcher.find()){
			System.out.println(876);
			for (int i = 0; i < 4; i++) {
				System.out.println(matcher.group(i));
			}
		}
	}
	@Test
	public void ff1(){
		String str = "BababBababBabBabBabBabB";
		Pattern p1 = Pattern.compile("B(ab|B)*?B");// (ab|B)?,B(ab|B)*B,
		Matcher m1 = p1.matcher(str);
		while (m1.find()) {
			System.out.println(m1.group(0));
		}
//		 String regex ="([^ ]*) ([^ ]*) ([^ ]*) (.*) (\".*?\") (-|[0-9]*) (-|[0-9]*) (\".*?\") (\".*?\")";
	}
}
