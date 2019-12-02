package singleton;

import org.junit.Test;

public class TestSingleton {
    @Test
    public void test(){
    	Singleton1 s1 = Singleton1.getInstance();
    	System.out.println(s1);
    	Singleton1 s2 = Singleton1.getInstance();
    	System.out.println(s2);
    }
}
