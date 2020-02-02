package threadunsafe;

import java.lang.reflect.Field;
import java.util.Arrays;


import sun.misc.Unsafe;

public class UnsafeDemo {
    public static Unsafe unsafe = UnsafeUtil.getUnsafe();
    public static void main(String[] args) throws Exception {
//        function1();
//        function2();
//        function3();
//        function4();
//        function5();
//        function6();
        function7();
    }

    public static void function7() throws NoSuchFieldException {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
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
     * park():方法返回条件
     * 1 当前现程调用过 unpark 方法 (多次调用 按照一次计算)
     * 2 当前线程被中断
     * 3 当park 为 false:时间块到了 单位纳秒
     * 4 当park 为 true:时间是绝对时间（1970）年 到期 单位毫秒
     */
    public static void function6() {
        System.out.println("Start");
        long time = System.currentTimeMillis()+3000l;
        unsafe.park(true,time);
        System.out.println("end");
    }
    
    /**
     * CAS操作
     * @throws Exception
     */
    public static void function5() throws Exception {
        Player player = (Player) unsafe.allocateInstance(Player.class);
        Field age = player.getClass().getDeclaredField("age");
        long addressAge = unsafe.objectFieldOffset(age);
        unsafe.compareAndSwapInt(player, addressAge, 0, 100);
        System.out.println(player.getAge());
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

}