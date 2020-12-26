package threadunsafe;

import java.util.concurrent.atomic.AtomicReference;

class User{
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User u1 = new User("Z3",22);
        User u2 = new User("li4",25);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(u1);
        System.out.println(atomicReference.compareAndSet(u1, u2)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(u1, u2)+"\t"+atomicReference.get().toString());

    }
}