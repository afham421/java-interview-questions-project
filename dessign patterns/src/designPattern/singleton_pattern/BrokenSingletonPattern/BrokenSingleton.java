package designPattern.singleton_pattern.BrokenSingletonPattern;

// BrokenSingleton.java
import java.io.Serializable;

public class BrokenSingleton implements Serializable, Cloneable {
    public static BrokenSingleton instance;

    private BrokenSingleton() {
        System.out.println("BrokenSingleton Constructor Called");
    }

    public static BrokenSingleton getInstance() {
        if (instance == null) {
            instance = new BrokenSingleton();
        }
        return instance;
    }

    @Override
    public BrokenSingleton clone() {
        try {
            BrokenSingleton clone = (BrokenSingleton) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    // clone() not overridden → can break singleton
    // readResolve() not implemented → serialization can break singleton
}
