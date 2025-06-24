package designPattern.CreationalDesignPattern.singleton_pattern.singletonHandledProperly;

// SafeSingleton.java
import java.io.Serializable;

public class SafeSingleton implements Serializable, Cloneable {
    private static SafeSingleton instance;
    private static boolean instanceCreated = false;

    private SafeSingleton() {
        if (instanceCreated) {
            throw new RuntimeException("Use getInstance()");
        }
        instanceCreated = true;
        System.out.println("SafeSingleton Constructor Called");
    }

    public static SafeSingleton getInstance() {
        if (instance == null) {
            instance = new SafeSingleton();
        }
        return instance;
    }

    // ✅ Prevent serialization
    protected Object readResolve() {
        return getInstance();
    }

    // ✅ Prevent cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }
}

