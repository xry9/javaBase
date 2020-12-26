package threadunsafe;

import java.lang.reflect.Field;
import java.util.Arrays;


import sun.misc.Unsafe;

public class UnsafeDemo {
    public static Unsafe unsafe = getUnsafe();
    public static void main(String[] args) throws Exception {
//        function1();
//        function2();
//        function3();
//        function4();
//        function5();
//        function6();
//        function7();
//        function8();
    }

    //    2>在当前线程中断的时候或者调用unpark的时候
    public static void function8() throws Exception {
        Unsafe unsafe = getUnsafe();
        Thread currThread = Thread.currentThread();
        new Thread(()->{
            try {
                Thread.sleep(3000);
//                currThread.interrupt();
                unsafe.unpark(currThread);
            } catch (Exception e) {}
        }).start();
        unsafe.park(false, 0);
        System.out.println("SUCCESS222!!!");
    }


    public static void function7() throws NoSuchFieldException {
        Unsafe unsafe = getUnsafe();
        Player player = new Player();
        //反射获取Test对象name属性的Field
        Field field = Player.class.getDeclaredField("name");
        //获取Test对象name属性的内存地址（偏移量）
        long offset = unsafe.objectFieldOffset(field);
        //根据地址获取值
        System.out.println(unsafe.getObject(player, offset));
        //根据地址修改
        unsafe.putObject(player, offset, "myValue");
        System.out.println(player.getName());
        //原子修改
        unsafe.compareAndSwapObject(player, offset, "myValue", "大王让我来巡山");
        System.out.println(player.getName());
    }

    /**
     * 直接分配内存地址：内存管理
     */
    public static void function4() {
        //分配100字节内存  返回初始地址
        long address = unsafe.allocateMemory(100);
        //往分配的内存地址写入值
        unsafe.putInt(address, 55);
        //获取值
        System.out.println(unsafe.getInt(address));
        //分配100字节内存  返回初始地址
        long address1 = unsafe.allocateMemory(100);
        //copy 内存值
        unsafe.copyMemory(address, address1, 4);
        System.out.println(unsafe.getInt(address1));
        //释放内存
        unsafe.freeMemory(address);
        unsafe.freeMemory(address1);
    }

    /**
     * 操作对象属性值
     * @throws Exception
     */
    public static void function3() throws Exception {
        Player player = (Player) unsafe.allocateInstance(Player.class);
        Field fieldName = player.getClass().getDeclaredField("name");
        Field fieldAge = player.getClass().getDeclaredField("age");
        long fileNameaddres = unsafe.objectFieldOffset(fieldName);
        long fileAgeaddres = unsafe.objectFieldOffset(fieldAge);
        unsafe.putObjectVolatile(player, fileNameaddres, "wangWu");
        unsafe.putInt(player,fileAgeaddres, 100);
        System.out.println(player.getAge()+"  "+player.getName());
    }

    /**
     * 实例化对象
     * @throws InstantiationException
     */
    public static void function2() throws InstantiationException {
        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setAge(100);
        player.setName("zhangShan");
        System.out.println(player.getAge()+"  "+player.getName());
    }
    /**
     * 对数组的操作
     */
    public static void function1() {
        int[] num = new int[7];
        //数组的起始地址
        long adress = unsafe.arrayBaseOffset(int[].class);
        //block 大小
        long index = unsafe.arrayIndexScale(int[].class);
        unsafe.putInt(num, adress,1);
        unsafe.putInt(num, adress+index, 2);
        unsafe.putInt(num, adress+index+index, 3);
        unsafe.putInt(num, adress+index+index+index, 4);
        System.out.println(Arrays.toString(num));
    }

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


class Player {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}