package OOPs_Object_oriented_programming.InterfaceVsAbstract;

public class InterfaceVsAbstract implements MyInterface {

//    Interface:
//    Purpose: An interface is primarily used to define a contract or a set of rules that a class must follow. It specifies what a class should do, but not how it should do it.
//    Abstract Class:
//    Purpose: An abstract class is used to provide a common base with some shared code (default behavior) and also abstract methods that must be implemented by subclasses.
//    It defines both what a class should do and provides a base implementation for some of those actions.

//    Abstract classes cannot be instantiated because they are intended to provide a partial implementation and enforce a design.
//    They may contain abstract methods that require concrete implementations in subclasses.
//    The use of abstract classes ensures that a complete and consistent design is followed, and concrete classes implement all required functionality.

    @Override
    public void abstractMethod() {
        System.out.println("abstractMethod");
    }
    // Optionally override the default method
//    @Override
//    public void defaultMethod() {
//        InterfaceVsAbstract1.MyInterface.super.defaultMethod();
//    }

    public static void main(String[] args) {

        InterfaceVsAbstract obj = new InterfaceVsAbstract();
        obj.abstractMethod();  // Output: Abstract method implementation in MyClass.
        obj.defaultMethod();   // Output: Overridden default method in MyClass.

        MyInterface.staticMethod(); // Output: This is a static method in the interface.

    }
}

//Interface:
//Prior to Java 8, interfaces could only contain abstract methods (methods without a body).
//Since Java 8, interfaces can have default methods (with a method body) and static methods.Cannot have constructors because you cannot instantiate an interface directly.
//All methods in an interface are implicitly public and abstract (except default and static methods).
interface MyInterface {
    //Can only have public, static, and final keyword fields (constants). They must be initialized at the time of declaration.
//     Fields in an interface are always public, static, and final keyword.
//    They must be initialized at the time of declaration, making them constants.
//    These fields are shared across all classes that implement the interface and cannot be modified.
    int CONSTANT = 10; // Implicitly public, static, final keyword

    void abstractMethod(); // Abstract method

    default void defaultMethod() { // Default method we didn't need to Override in every child class forcefully. Now with the help of default method, we can implement it
        System.out.println("This is a default method.");
    }

//    Usage:
//    Backward Compatibility: Adding new methods to an interface without forcing all implementing classes to provide an implementation.
//    Providing Common Behavior: Sharing common method implementations among multiple classes.
    static void staticMethod() { // Static method
        System.out.println("This is a static method.");
    }
//    No Inheritance: Static methods in interfaces cannot be overridden by implementing classes.
//    Accessed via Interface: These methods are called using the interface name, not through instances of the implementing classes.
//    Usage:
//    Utility Methods: Providing utility methods that are related to the interface but don’t require an instance of a class.
//    Helper Methods: Static methods can offer common functionality related to the interface's purpose.

}

//Abstract Class:
//Can have both abstract methods (without a body) and concrete methods (with a body).
//An abstract class can also have constructors, fields, and methods with any access modifier (public, protected, private).
//>>>>>>>>>>>>.Can have constructors. Even though you cannot instantiate an abstract class directly, its constructors are called when a subclass is instantiated.>>>>>>>>>>>>>>>>>>>>>
abstract class MyAbstractClass {
    //Can have instance variables (non-static fields) with any access modifier, and these can be modified by the class or its subclasses.
    //There is no need to initialize here unlike interface
    int x; // Field

    abstract void abstractMethod(); // Abstract method
//    Abstract Methods:
//    Requirement: You must implement all abstract methods in the abstract class if your subclass is a concrete (non-abstract) class.
//    Reason: Abstract methods have nobody and are meant to be implemented by subclasses. If the subclass does not implement these methods, the subclass must itself be declared as abstract.
//Subclasses as Abstract:
//    Option: If you choose not to implement all abstract methods in the subclass, you must declare the subclass itself as abstract.
//    Implication: This means that the subclass cannot be instantiated directly and may either be further subclassed or have its abstract methods implemented by another subclass.

    void concreteMethod() { // Concrete method
        System.out.println("This is a concrete method.");
    }
//    Concrete Methods:
//    Requirement: You are not required to override or implement concrete methods (methods with a body) from the abstract class.
//    Reason: These methods already have an implementation in the abstract class, and the subclass can inherit and use them as is. However, you can choose to override them if needed.


}

/// /////////////////////////////////Inheritance////////////////////

//Interface:
//A class can implement multiple interfaces (multiple inheritance of a type).
//An interface can extend multiple other interfaces.
//interface A { /* ... */ }
//interface B { /* ... */ }
//interface C extends A, B { /* ... */ }
//class MyClass implements A, B { /* ... */ }

//Abstract Class:
//A class can inherit only one abstract class due to Java's single inheritance model.
//An abstract class can extend another class (abstract or concrete) and implement interfaces.
//Abstract class A { /* ... */ }
//abstract class B extends A { /* ... */ }
//class MyClass extends B { /* ... */ }