package other;

import java.util.Map;
import java.util.Set;

public class StackTraces {

    public static void main(String[] args) {

        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()){
            Thread thread = stackTrace.getKey();
            System.out.println(thread);
        }
    }
}

