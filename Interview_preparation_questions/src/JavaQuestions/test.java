package JavaQuestions;


import java.lang.reflect.Method;

public class test {



    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName("java.util.ArrayList");
        Method method = clazz.getMethod("size");
        System.out.println("Method name: " + method.getName());



    }
}
