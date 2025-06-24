package JavaQuestions.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DynamicProxy {
//    A dynamic proxy in Java is a mechanism that allows you to create proxy instances at runtime, which can be used to intercept method calls on an interface.
//    This is useful for adding functionality such as logging, transaction management, or access control without modifying the original code.
//    Dynamic proxies are part of the Java Reflection API and are commonly used in frameworks like Spring AOP (Aspect-Oriented Programming).
//    Dynamic proxies in Java are used for a variety of purposes, particularly in scenarios where you want to add, modify,
//    or intercept the behavior of objects at runtime without altering the original code.
    public static void main(String[] args) {

//        Map fees = new HashMap(); // it is simple implementation
//        fees.put("java",2000);
//        System.out.println(fees);


        Map fees = (Map) Proxy.newProxyInstance(  // this code is for dynamic proxy
                HashMap.class.getClassLoader() // here you will pass class implementation that you want like hashmap, hashset, etc.
                , new Class[]{Map.class}
                , new ObjectHandler(new HashMap<>()) // here you will pass an object that you want to implement through proxy.For example, here we are passing a hashmap object
        );

        fees.put("java",2000); // dynamic proxy we use for doing some operation before and after invoking the actual method like here we print before and after the put method
        fees.put("python",1500); // it will give invalid key because we add check before put method
        System.out.println(fees);
    }

}

class ObjectHandler implements InvocationHandler{

    private Object object;

    public ObjectHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //logic before method call
        System.out.println("before method call");
        //here we can apply other logics likes
        if (method.getName().equals("put")){
            if (args[0].equals("python")){
                System.out.println("invalid Key value : " + args[0]);
                return null;
            }
        }
        Object invoke = method.invoke(object, args);
        //logic after method call // like above we can implement logic after method call as we need to
        System.out.println("after method call");

        return invoke;
    }
}

