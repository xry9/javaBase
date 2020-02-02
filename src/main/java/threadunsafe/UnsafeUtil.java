package threadunsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {

    public static Unsafe getUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            //因为 Unsafe 的 theUnsafe 字段是private 的，所以这里需要设置成可访问的
            field.setAccessible(true);
            //Unsafe 的这个属性 theUnsafe 是静态的所以这里的get参数就是null
            Unsafe unsafe = (Unsafe)field.get(null);
//            Unsafe unsafe = (Unsafe)field.get(Unsafe.class);
            return unsafe;
        } catch (Exception e) {}
        return null;
    }

}
