package other;

import java.util.Map;
import java.util.Set;

public class StaticDemo {

    public static void main(String[] args) {
        StaticDemo0 s1 = new StaticDemo0();
        StaticDemo0 s2 = new StaticDemo0();
        s1.bytes1 = null;
        System.out.println(s2.bytes1);
//        s1 = null;
        System.gc();
        System.out.println("finally...");

        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()){
            Thread thread = stackTrace.getKey();

        }
    }
}

class StaticDemo0{
    public static byte[] bytes1 = new byte[5 * 1024 * 1024];

}

