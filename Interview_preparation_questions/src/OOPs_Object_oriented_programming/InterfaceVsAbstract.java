package OOPs_Object_oriented_programming;

/**
 * Interface vs Abstract Class — quick demo + interview points
 *
 * Interface  → contract / capability (what to do). Multiple inheritance of type.
 * Abstract   → base type with shared code + forced subclass parts (related classes).
 *
 * | Point              | Interface                                      | Abstract class                          |
 * |--------------------|------------------------------------------------|-----------------------------------------|
 * | Instantiation      | No                                             | No                                      |
 * | Constructor        | No                                             | Yes (for subclasses via super)          |
 * | Fields             | public static final constants only             | Instance fields, any access modifier    |
 * | Methods            | abstract + default/static (Java 8+) + private (9+) | abstract + concrete                    |
 * | Inheritance        | class implements many; interface extends many  | class extends only ONE class            |
 */
public class InterfaceVsAbstract implements MyInterface {

    @Override
    public void abstractMethod() {
        System.out.println("Abstract method implementation in InterfaceVsAbstract");
    }

    // Optional: override default method
    // @Override
    // public void defaultMethod() {
    //     MyInterface.super.defaultMethod();
    //     System.out.println("Extra logic in override");
    // }

    public static void main(String[] args) {
        // ---- Interface demo ----
        InterfaceVsAbstract obj = new InterfaceVsAbstract();
        obj.abstractMethod();           // must implement
        obj.defaultMethod();            // inherited default (no override needed)
        MyInterface.staticMethod();     // call via interface name, not instance
        System.out.println("CONSTANT = " + MyInterface.CONSTANT);

        // ---- Abstract class demo ----
        // MyAbstractClass a = new MyAbstractClass(); // ❌ illegal
        MyAbstractClass animal = new Dog();
        animal.abstractMethod();
        animal.concreteMethod();
        System.out.println("x = " + animal.x);
    }
}

// ========================= INTERFACE =========================
// Pre-Java 8: only abstract methods
// Java 8+: default + static methods
// Java 9+: private methods (helpers inside interface)
// No constructors. Fields are always public static final (constants).
interface MyInterface {
    int CONSTANT = 10; // implicitly public static final

    void abstractMethod(); // implicitly public abstract

    // default → shared behavior; implementing class need NOT override
    default void defaultMethod() {
        System.out.println("This is a default method.");
    }

    // static → utility on the interface type; NOT inherited/overridden
    static void staticMethod() {
        System.out.println("This is a static method.");
    }
}

// ========================= ABSTRACT CLASS =========================
// Can have constructors, instance fields, any access modifiers
// Can mix abstract + concrete methods
abstract class MyAbstractClass {
    int x; // instance field — no need to initialize at declaration (unlike interface)

    protected MyAbstractClass(int x) {
        this.x = x; // constructor runs when subclass is created
    }

    abstract void abstractMethod(); // subclass MUST implement (or stay abstract)

    void concreteMethod() { // inherited as-is; override optional
        System.out.println("This is a concrete method.");
    }
}

class Dog extends MyAbstractClass {
    Dog() {
        super(1); // must call abstract parent constructor
    }

    @Override
    void abstractMethod() {
        System.out.println("Dog abstractMethod");
    }
}

// ========================= INHERITANCE RULES =========================
// Interface: class can implement multiple interfaces; interface can extend multiple interfaces
// Abstract:  class can extend only ONE class (abstract or concrete)
//
// interface A {}
// interface B {}
// interface C extends A, B {}
// class X implements A, B {}
//
// abstract class P {}
// abstract class Q extends P {}
// class R extends Q {}
