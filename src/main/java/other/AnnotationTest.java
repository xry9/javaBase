package other;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Inherited;
import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String[] value() default "unknown";
}
class Person {
    @MyAnnotation
    @Deprecated
    public void empty(){
        System.out.println("\nempty");
    }
    @MyAnnotation(value={"girl","boy"})
    public void somebody(String name, int age){
        System.out.println("\nsomebody: "+name+", "+age);
    }
}
public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Class<Person> c = Person.class;
        Method mSomebody = c.getMethod("somebody", new Class[]{String.class, int.class});
        mSomebody.invoke(person, new Object[]{"lily", 18});
        iteratorAnnotations(mSomebody);
        Method mEmpty = c.getMethod("empty", new Class[]{});
        mEmpty.invoke(person, new Object[]{});
        iteratorAnnotations(mEmpty);
    }
    public static void iteratorAnnotations(Method method) {
        if(method.isAnnotationPresent(MyAnnotation.class)){
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            String[] values = myAnnotation.value();
            for (String str:values)
                System.out.println(str+", ");
        }
        Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation);
        }
    }
}