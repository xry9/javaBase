package other;

import java.util.Optional;

class Persion{
    
    private Car car;
    
    public Car getCar() {
        return this.car;
    }
}

class Car{
    
    private Brand insurance;
    
    public Brand getInsurance() {
        return this.insurance;
    }
}
class Brand {
    private String name;
    
    public String getName() {
        return this.name;
    }
}

public class BeforeOptional {
    
    public static void main(String[] args) {
        getBrandName(new Persion());
        getBrandNameDefault(new Persion());


        System.out.println("===========================================");
        //********************** 1. Optional 的构建**********************
        //1.1创建一个空的optional(内部new了一个空的optional),不支持get方法，会报异常NoSuchElementException
        Optional<Brand> empty = Optional.<Brand>empty();
//        empty.get();

        //1.2创建一个optional,传入的值不能为null,返回Optional<Object>,为null时会报NullPointerException
        Optional<Brand> of = Optional.of(new Brand());
        System.out.println(of.get());

        //1.3创建一个可空可有的optional,对前2种的总结(如果传null,走empty方法，非null，走of方法)
        Optional<Brand> brand = Optional.ofNullable(new Brand());
        Optional<Object> ofNullable2 = Optional.ofNullable(null);

        //********************** 2. Optional 的获取操作**********************

        //2.1 如果当前有值，返回这个值，否则报NoSuchElementException("No value present");
        brand.get();

        //2.2 如果有值，会传出insurance实例,如果没值，会给出传入的参数;(参数是supplier)
        brand.orElseGet(Brand::new);

        //2.3 如果有值，会传出insurance实例,如果没值，会给出传入的参数;(参数是Insurance 实例)
        brand.orElse(new Brand());

        //2.4 如果有值，会传出insurance实例，如果没值，可以抛出异常
        brand.orElseThrow(RuntimeException::new);
        brand.orElseThrow(()->new RuntimeException("not have reference"));


        //********************** 3. Optional 的复合操作**********************

        //3.1 返回一个boolean值
        brand.isPresent();

        //3.2 如果有值，有值时可以进行操作(consumer操作)
        brand.ifPresent(System.out::println);

        //3.3 filter操作，它会通过predicate操作，对brand 以及表达式进行非空的验证，通过后，方能得到结果
        brand.filter(b->b.getName() == null).get();

        //3.4 map操作：同filter,不同之处在于map会改变get到的类型
        brand.map(b->b.getName()).get();

        //3.5 flatMap 得到复合操作后的Optional<flatMap参数得到的值的optional>
        Optional<Persion2> per = Optional.ofNullable(new Persion2());
        Optional<Optional<Car2>> map = per.map(Persion2::getCar);    //这个是对Optional<optional<Car2>进行了再一次的封装
        Optional<Car2> flatMap = per.flatMap(Persion2::getCar);        //这个得到的还是原来的Optional<Object>
    }

    public static String getInsuranceName(Persion2 per) {
        return Optional.ofNullable(per).flatMap(Persion2::getCar).flatMap(Car2::getBrand2).map(b->b.getName()).orElse("Unknown");//是不是一行代码就搞定了
    }

    private static String getBrandName(Persion persion) {
        
        if(null !=persion.getCar()) {
            Car car = persion.getCar();
            if(null !=car.getInsurance()) {
                Brand insurance = car.getInsurance();
                if(null !=insurance.getName()) {
                    return insurance.getName();
                }
            }
        }
        
        return "";
    }
    
    private static String getBrandNameDefault(Persion persion) {
        String defaultValue = "UNKNOW";
        if(null ==persion) 
             return defaultValue;
        Car car = persion.getCar();
        if(null == car)
            return defaultValue;
        Brand insurance = car.getInsurance();    
        if(null == insurance)
            return defaultValue;
        return insurance.getName();
    }
}

class Persion2{
    private Optional<Car2> car;//注意这里要加上Optional的改写
    public Optional<Car2> getCar() {
        return car;
    }
}
class Car2{
    private Optional<Brand2> Brand2;
    public Optional<Brand2> getBrand2() {
        return Brand2;
    }
}
class Brand2{
    private String name;
    public String getName() {
        return this.name;
    }
}
