package OOPs_Object_oriented_programming;

class A {
    static void show() {
        System.out.println("Parent static method");
    }
     void show1() {
        System.out.println("Parent static method");
    }
}

class B extends A {
    static void show() {
        System.out.println("Child static method");
    }
     void show1() {
        System.out.println("Child static method");
    }
}

public class StaticMethodOverriding {
    public static void main(String[] args) {
        A p = new B();
        p.show();  // Output: Parent static method
//// Even though p refers to a Child object, it calls the Parent's static method — because static methods are not polymorphic.

        // if its non-static
        p.show1();  // Output: Child static method
        // ✅ B is instantiated
       //// Output: B (because of method overriding)
////  here p calls the method of B class

    }
}
