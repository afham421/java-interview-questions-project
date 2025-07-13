package JavaQuestions.DesignPattern.CreationalDesignPattern.singleton_pattern;

//🔧 Basic Singleton Example (Lazy Initialization - Not Thread Safe)
class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
        // private constructor
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy(); // creates instance only when needed
        }
        return instance;
    }
}
//⚠️ Not thread-safe: In multithreading, multiple instances can be created.


////🔐 Thread-Safe Singleton (Synchronized Method) >>>>>> means only one thread can access at a time
class SingletonSynchronized {
    private static SingletonSynchronized instance;

    private SingletonSynchronized() {}

    public static synchronized SingletonSynchronized getInstance() {
        if (instance == null) {
            instance = new SingletonSynchronized();
        }
        return instance;
    }
}

////🟡 Slower due to method-level synchronization.



////⚡ Best Practice: Double-Checked Locking (Thread-safe & Fast)

class SingletonDoubleChecked {
    private static volatile SingletonDoubleChecked instance;

    private SingletonDoubleChecked() {}

    public static SingletonDoubleChecked getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleChecked.class) {
                if (instance == null) {
                    instance = new SingletonDoubleChecked();
                }
            }
        }
        return instance;
    }
}

////✅ Recommended for production-level code (efficient + thread-safe).





////🧊 Eager Initialization (Thread-safe but always creates instance)

class SingletonEager {
    private static final SingletonEager instance = new SingletonEager();

    private SingletonEager() {}

    public static SingletonEager getInstance() {
        return instance;
    }
}

////⚠️ Instance created even if not used — may waste resources.




////🧵 Singleton with Enum (Best Practice)

enum SingletonEnum {
    INSTANCE;

    public void doSomething() {
        System.out.println("Doing something in Enum Singleton.");
    }
}

////✅ Thread-safe, serialization-safe, and simplest way to implement singleton in Java.
