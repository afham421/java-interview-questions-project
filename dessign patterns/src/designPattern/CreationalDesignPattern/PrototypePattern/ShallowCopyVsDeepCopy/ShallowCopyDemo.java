package designPattern.CreationalDesignPattern.PrototypePattern.ShallowCopyVsDeepCopy;

class Address {
    String city;

    public Address(String city) {
        this.city = city;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Person clone() throws CloneNotSupportedException {
        return (Person) super.clone(); // shallow copy // it takes shared referance of object means 2nd object changed effect on first object
    }
}


public class ShallowCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {

        Person p1 = new Person("Ali", new Address("Islamabad"));
        Person p2 = p1.clone();

        p2.name = "Zain";
        p2.address.city = "Lahore";

        System.out.println(p1.name);         // Ali ✅
        System.out.println(p1.address.city); // Lahore ❌ (changed because of shared reference)

//        ✅ Explanation:
//        p2.name = "Zain";
//        ✅ name is a String, which is immutable in Java.
//
//        p1.name and p2.name are separate references after this line.
//
//        So, only p2.name is updated. p1.name stays "Ali".
//
//        p2.address.city = "Lahore";
//        🧨 address is an object (Address class).
//
//        In a shallow copy, p1.address and p2.address point to the same object in memory.
//
//        So, updating p2.address.city changes it for both.


    }
}
