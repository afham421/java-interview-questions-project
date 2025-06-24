package designPattern.CreationalDesignPattern.singleton_pattern.singletonHandledProperly;

// TestSafeSingleton.java
import java.io.*;
import java.lang.reflect.Constructor;

public class TestSafeSingleton {
    public static void main(String[] args) {
        SafeSingleton s1 = SafeSingleton.getInstance();

        // Reflection
        try {
            Constructor<SafeSingleton> constructor = SafeSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SafeSingleton s2 = constructor.newInstance();
            System.out.println("Reflection broken? " + (s1 != s2));
        } catch (Exception e) {
            System.out.println("Reflection blocked: " + e.getMessage());
        }

        // Serialization
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("safe.ser"));
            oos.writeObject(s1);
            oos.close();

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("safe.ser"));
            SafeSingleton s3 = (SafeSingleton) ois.readObject();

            System.out.println("Serialization broken? " + (s1 != s3));
        } catch (Exception e) {
            System.out.println("Serialization error: " + e.getMessage());
        }

        // Cloning
        try {
            SafeSingleton s4 = (SafeSingleton) s1.clone();
            System.out.println("Cloning broken? " + (s1 != s4));
        } catch (Exception e) {
            System.out.println("Cloning blocked: " + e.getMessage());
        }
    }
}

