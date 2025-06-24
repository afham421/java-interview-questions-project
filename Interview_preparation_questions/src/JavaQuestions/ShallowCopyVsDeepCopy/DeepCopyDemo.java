package JavaQuestions.ShallowCopyVsDeepCopy;


class Person1 implements Cloneable {
    String name;
    Address address;

    public Person1(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Person1 clone() throws CloneNotSupportedException {
        // Deep copy
        return new Person1(this.name, new Address(this.address.city));
    }
}



public class DeepCopyDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person1 p1 = new Person1("Ali", new Address("Islamabad"));
        Person1 p2 = p1.clone();

        p2.name = "Zain";
        p2.address.city = "Lahore";

        System.out.println(p1.name);         // Ali
        System.out.println(p1.address.city); // Islamabad ✅ (unchanged)
    }
}
