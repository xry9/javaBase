package other;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Demo {
    public static void main(String[] args) {
        Map<HH, String> map = new HashMap<>();
        HH hh = new HH(1,2);
        map.put(hh, "hhhh");
        System.out.println(hh.hashCode());
        System.out.println(map.get(hh));
        hh.a = 3;
        hh.b = 4;
        System.out.println(hh.hashCode());
        System.out.println(map.get(hh));
    }

}
class HH{
    public int a ;
    public int b ;

    public HH(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HH hh = (HH) o;
        return a == hh.a &&
                b == hh.b;
    }

    @Override
    public int hashCode() {
        System.out.println("====="+super.hashCode());
        return Objects.hash(a, b);
    }
}
