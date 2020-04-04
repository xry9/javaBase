package other;

import java.util.ArrayList;
import java.util.List;

public class IterDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");
        System.out.println(list.iterator().next());
        System.out.println(list.iterator().next());
        System.out.println(list.iterator().next());

    }
}
