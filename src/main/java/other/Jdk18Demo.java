package other;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Jdk18Demo {
    public static void main(String[] args) {
        Function<String,String> func =  String::toUpperCase;
        String rr = func.apply("abc");
        System.out.println(rr);

        List<String> l = Arrays.asList("a","b","c");
        l.stream().map(s -> s.toUpperCase());
        l.stream().map(func);

        l.forEach(new MyConsumer<>());
        System.out.println();
        l.forEach(s -> System.out.print(s));
        System.out.println("=====================");
        Consumer<String> printStrConsumer = DoubleColon::printStr;
        printStrConsumer.accept("printStrConsumer");

        Consumer<DoubleColon> toUpperConsumer = DoubleColon::toUpper;
        toUpperConsumer.accept(new DoubleColon());
        BiConsumer<DoubleColon,String> toLowerConsumer = DoubleColon::toLower;
        toLowerConsumer.accept(new DoubleColon(),"toLowerConsumer");
        BiFunction<DoubleColon,String,Integer> toIntFunction = DoubleColon::toInt;
        int i = toIntFunction.apply(new DoubleColon(),"toInt");
        System.out.println("=====================");

        TestBiConsumer obj = new TestBiConsumer();
        obj.test((x,y) -> System.out.println("do something ..."));
        obj.test(DoubleColon::toLower);
//        用::提取的函数，最主要的区别在于静态与非静态方法，非静态方法比静态方法多一个参数，就是被调用的实例
    }
}

class MyConsumer<String> implements Consumer<String> {
    @Override
    public void accept(String s) {
        System.out.print(s);
    }
}
class DoubleColon {
    public static void printStr(String str) {
        System.out.println("printStr : " + str);
    }
    public void toUpper(){
        System.out.println("toUpper : " + this.toString());
    }
    public void toLower(String str){
        System.out.println("toLower : " + str);
    }
    public int toInt(String str){
        System.out.println("toInt : " + str);
        return 1;
    }
}

class TestBiConsumer {
    public void test(BiConsumer<DoubleColon,String> consumer){
        System.out.println("do something ...");
    }
}
