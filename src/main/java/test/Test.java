package test;

import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
public class Test{
    class GrandFather{
        void thinking(){
            System.out.println("i am grandfather");
        }
    }
    class Father extends GrandFather{
        void thinking(){
            System.out.println("i am father");
        }
    }
    class Son extends Father{
        void thinking(){
            try{
                MethodType mt=MethodType.methodType(void.class);
                MethodHandle mh=lookup().findVirtual(GrandFather.class,"thinking",mt).bindTo(new Test().new GrandFather());
                mh.invokeExact();
            }catch(Throwable e){
            }
        }
    }
    public static void main(String[] args){
        (new Test().new Son()).thinking();
    }
}