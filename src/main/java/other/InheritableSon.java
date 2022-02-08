package other;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Inherited;

/**
 * 自定义的Annotation。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
@interface Inheritable { }
@Inheritable
class InheritableFather {
    public InheritableFather() {
        System.out.println("InheritableFather:"+InheritableFather.class.isAnnotationPresent(Inheritable.class));
    }
}
public class InheritableSon extends InheritableFather {
    public InheritableSon() {
        super();
        System.out.println("InheritableSon:"+InheritableSon.class.isAnnotationPresent(Inheritable.class));
    }
    public static void main(String[] args) {
        InheritableSon is = new InheritableSon();
    }
}
