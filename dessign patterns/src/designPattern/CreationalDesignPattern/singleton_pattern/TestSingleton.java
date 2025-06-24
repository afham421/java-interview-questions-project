package designPattern.CreationalDesignPattern.singleton_pattern;

public class TestSingleton {
    public static void main(String[] args) {

        // 1. Lazy Initialization (Not Thread-Safe)
        System.out.println("Lazy Singleton:");
        SingletonLazy lazy1 = SingletonLazy.getInstance();
        SingletonLazy lazy2 = SingletonLazy.getInstance();
        System.out.println(lazy1 == lazy2); // true

        // 2. Thread-Safe Singleton (Synchronized)
        System.out.println("\nSynchronized Singleton:");
        SingletonSynchronized sync1 = SingletonSynchronized.getInstance();
        SingletonSynchronized sync2 = SingletonSynchronized.getInstance();
        System.out.println(sync1 == sync2); // true

        // 3. Double-Checked Locking
        System.out.println("\nDouble-Checked Locking Singleton:");
        SingletonDoubleChecked double1 = SingletonDoubleChecked.getInstance();
        SingletonDoubleChecked double2 = SingletonDoubleChecked.getInstance();
        System.out.println(double1 == double2); // true

        // 4. Eager Initialization
        System.out.println("\nEager Singleton:");
        SingletonEager eager1 = SingletonEager.getInstance();
        SingletonEager eager2 = SingletonEager.getInstance();
        System.out.println(eager1 == eager2); // true

        // 5. Enum Singleton
        System.out.println("\nEnum Singleton:");
        SingletonEnum enum1 = SingletonEnum.INSTANCE;
        SingletonEnum enum2 = SingletonEnum.INSTANCE;
        System.out.println(enum1 == enum2); // true
        enum1.doSomething();
    }
}
