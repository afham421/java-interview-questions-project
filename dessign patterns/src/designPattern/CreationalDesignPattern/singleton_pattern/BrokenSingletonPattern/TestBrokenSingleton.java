package designPattern.CreationalDesignPattern.singleton_pattern.BrokenSingletonPattern;

// TestBrokenSingleton.java
import java.io.*;
import java.lang.reflect.Constructor;

public class TestBrokenSingleton {
    public static void main(String[] args) throws Exception {
        BrokenSingleton s1 = BrokenSingleton.getInstance();

        // 1. Reflection
        Constructor<BrokenSingleton> constructor = BrokenSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        BrokenSingleton s2 = constructor.newInstance();

        // 2. Serialization
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("broken.ser"));
        oos.writeObject(s1);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("broken.ser"));
        BrokenSingleton s3 = (BrokenSingleton) ois.readObject();

        // 3. Cloning
        BrokenSingleton s4 = (BrokenSingleton) s1.clone();

        System.out.println("Reflection broken? " + (s1 != s2));
        System.out.println("Serialization broken? " + (s1 != s3));
        System.out.println("Cloning broken? " + (s1 != s4));
    }
}
