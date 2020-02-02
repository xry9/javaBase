package jvmp0;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CglibTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClassNotFoundException {
        // 设置类成员属性  
        HashMap propertyMap = new HashMap();
        propertyMap.put("id", Class.forName("java.lang.Integer"));
        propertyMap.put("name", Class.forName("java.lang.String"));
        propertyMap.put("address", Class.forName("java.lang.String"));
        // 生成动态 Bean  
        CglibBean bean = new CglibBean("", propertyMap);
        // 给 Bean 设置值  
        bean.setValue("id", new Integer(123));
        bean.setValue("name", "454");
        bean.setValue("address", "789");
        // 从 Bean 中获取值，当然了获得值的类型是 Object
        System.out.println("  >> id      = " + bean.getValue("id"));
        System.out.println("  >> name    = " + bean.getValue("name"));
        System.out.println("  >> address = " + bean.getValue("address"));
        // 获得bean的实体  
        Object object = bean.getObject();
        System.out.println(object.getClass());
        // 通过反射查看所有方法名  
        Class clazz = object.getClass();  
        Method[] methods = clazz.getDeclaredMethods();  
        for (int i = 0; i < methods.length; i++) {  
            System.out.println(methods[i].getName());
        }
    }
}
class CglibBean {
    public  Object object = null;
    public BeanMap beanMap = null;
    public CglibBean() {
        super();
    }
    public CglibBean(String name, Map propertyMap) throws ClassNotFoundException {
        this.object = generateBean(name, propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }
    public Object getValue(String property) {
        return beanMap.get(property);
    }
    public Object getObject() {
        return this.object;
    }
    private Object generateBean(String name, Map propertyMap) throws ClassNotFoundException {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Iterator i = keySet.iterator(); i.hasNext();) {
            String key = (String) i.next();
            generator.addProperty(key, (Class) propertyMap.get(key));
        }
        generator.addProperty(name, Class.forName("java.lang.String"));
        return generator.create();
    }
}