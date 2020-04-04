package genericType;

import java.io.Serializable;

public class Demo<T> {
    T a;
    T[] arr;
//    T c = arr[0];
    public Demo(T a, T[] arr){
        this.a = a;
        this.arr = arr;
    }
    public Demo(){}
    public static void main(String args[]) {
        Container<String> c = new SubContainer();
        String s = "";
        c.add(s);
        Demo<Info> demo = new Demo();
        demo.f2();
    }
    public void ff(T[] arr){}

    T[] elements;
    public void f2(){
        this.elements = (T[]) new Object[2];
        this.elements = (T[]) new Integer[2];
        Object[] oo = new Integer[2];
    }

}

class Container<T>{
    public void add(T t){
        System.out.println("fulei");

    }

}

class SubContainer extends Container<String>{
    public void add(String s){

        System.out.println("zilei");
    }

}
