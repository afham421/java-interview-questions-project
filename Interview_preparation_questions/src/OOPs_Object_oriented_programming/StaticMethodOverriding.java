package OOPs_Object_oriented_programming;

/**
 * Can we override static methods? → NO.
 * Static methods are HIDDEN (method hiding), not overridden.
 * Call is resolved by REFERENCE TYPE at compile time (no runtime polymorphism).
 *
 * Instance methods ARE overridden → resolved by OBJECT TYPE at runtime.
 *
 * Note: `p.super.show1()` is INVALID. `super` works only inside the subclass body.
 */
class A {
    static void show() {
        System.out.println("Parent static method");
    }

    void show1() {
        System.out.println("Parent instance method");
    }
}

class B extends A {
    // This HIDES A's show() — it does NOT override it
    static void show() {
        System.out.println("Child static method");
    }

    @Override
    void show1() {
        super.show1(); // ✅ call parent instance method from inside child
        System.out.println("Child instance method");
    }
}

public class StaticMethodOverriding {
    public static void main(String[] args) {
        A p = new B(); // reference type A, object type B

        // Static → based on reference type (A), NOT object type
        p.show();      // Output: Parent static method
        A.show();      // clearer: call static via class name → Parent
        B.show();      // Child static method

        // Instance → based on object type (B) → true overriding / runtime polymorphism
        p.show1();
        // Output:
        // Parent instance method   (from super.show1())
        // Child instance method
        //
        // ❌ p.super.show1();  // illegal — super is not used on a variable like p
    }
}
