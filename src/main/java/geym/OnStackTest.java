package geym;

import java.util.ArrayList;
import java.util.List;

public class OnStackTest {
//    -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB  -XX:+EliminateAllocations -XX:+DoEscapeAnalysis
    public static class User{
        public long id=0;
//        public long id1=1;
//        public long id2=2;
//        public long id3=2;
//        public long id4=2;
//        public long id5=2;
//        public long id6=2;
//        public long id7=2;
//        public long id8=2;
//        public long id9=2;
//        public byte[] b= new byte[12];
        public String name="";
    }

    //        对象 User 以局部变 的形式存在，并且该对象并没有被 alloc()函数返回，或者出现了任何形式的公开。因此，它并未发生逃逸，所以对于这种情况，虚拟机就有可能将 User 分配在枝上，而不在堆上
    public static void alloc(){
        User u=new User();
        u.id=5;
        u.name="geym";
    }
    public static User alloc1(){
        User u=new User();
        u.id=5;
        u.name="geym";
        return u;
    }
    public static void main(String[] args) throws InterruptedException {
        List<User> list = new ArrayList<>(1_00_0000);
        User[] us = new User[2];
        User user ;
        long b=System.currentTimeMillis();
        long ll = 0;
        int k = 1;
        for(int i=0;i<20_0000_0000;i++){
//            if (k%1_0000==0){
//                list.clear();
//                k = 0;
//            }
            k++;
            User u = alloc1();
            us[i%2] = u;
            ll = u.id;
//            list.add(u);
//            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}
