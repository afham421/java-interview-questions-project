package JavaQuestions.Java_17_features;

public class Record_class {
    // 📌 Version: Records → Java 14 (preview) → Java 16 (standard). Java 17 LTS mein commonly use.
    // Record = compact data-carrier class. Compiler auto-generate karta hai:
    //   - private final fields (components)
    //   - canonical constructor = the main constructor whose parameters match the record’s components, in the same order.
    //   - accessors: name()  (NOT getName() — Bean style nahi)
    //   - equals(), hashCode(), toString()  (sab components pe based)

    // ✅ Benefits
    // - Less boilerplate (no manual getters/equals/hashCode/toString)
    // - Immutable by default (components private final)
    // - Great for DTOs, keys, value objects

    // ❌ Limitations / Rules (interview-important)
    // - Cannot extend another class (implicitly extends java.lang.Record)
    // - Record itself is implicitly final → cannot be subclassed
    // - Can implement interfaces
    // - Extra instance fields NOT allowed (sirf header components)
    // - Shallow immutability: agar component mutable object hai (e.g. List), uska content change ho sakta hai

    // 🔹 What is Canonical constructor?
    // Canonical = main / full constructor jiske params record components se match karte hain (same order).
    // Example: record Person1(String name, int age) {}
    // Compiler auto-generate karta hai:
    //   public Person1(String name, int age) {
    //       this.name = name;
    //       this.age = age;
    //   }
    // Isi ko call karta hai: new Person1("Alice", 30)
    // “Canonical” = official all-args constructor for every component.
    // Koi bhi extra constructor MUST eventually this(...) se isi ko call kare:
    //   public Person1(String name) { this(name, 0); }  // → canonical

    // 🔹 Compact vs Canonical constructor
    // |              | Canonical                                              | Compact                                      |
    // | Shape        | Person1(String name, int age) { this.name = name; ... } | Person1 { /* only logic */ }                 |
    // | Params listed? | Yes                                                  | No (header se infer)                         |
    // | Field assign?| You write this.x = x (ya compiler)                     | Compiler assigns AFTER your body             |
    // | Typical use  | Full control / rarely write by hand                    | Validation (if age < 0 ...)                  |

    // Canonical: public Person1(String name, int age) { this.name = name; this.age = age; }  // auto
    // Compact:   public Person1 { /* validation */ }  // no params, no this.x = x; assignment auto

    // DTOs best practice:
    // Immutable DTO → Record
    // Mutable DTO (setters) → normal class

    // 🧠 Interview one-liner
    // “A record is an immutable data carrier with auto equals/hashCode/toString and name()-style accessors.”

    public static void main(String[] args) {
        Point point = new Point(10, 20);

        // Accessors are x() / y() — not getX() / getY()
        System.out.println("X: " + point.x());
        System.out.println("Y: " + point.y());
        // No setters — record components are final; update = new instance banao

        // Auto toString → Point[x=10, y=20]
        System.out.println("Point: " + point);

        // equals/hashCode components pe based → true
        Point anotherPoint = new Point(10, 20);
        System.out.println("Points are equal: " + point.equals(anotherPoint));

        Person1 person1 = new Person1("joki", 21);
        System.out.println(person1.greet());

        // Canonical constructor (compact validation bhi chalega)
        Person1 person = new Person1("Alice", 30);
        System.out.println(person);

        // Overloaded constructors → must delegate to canonical via this(...)
        Person1 person2 = new Person1("Bob");
        System.out.println(person2);

        Person1 person3 = new Person1();
        System.out.println(person3);

        // Compact constructor validation
        try {
            Person1 person4 = new Person1("Charlie", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }

}

record Point(int x, int y) {
    // Empty body OK — sab auto-generated
}

// class fdf extends Point {}  // ❌ compile error — record is implicitly final

record Person1(String name, int age) {

    // Compact constructor — validation ke liye best place
    // Params already in scope; field assignment compiler karta hai
    public Person1 {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
    }

    // Extra constructors MUST call this(...) → canonical/compact
    public Person1(String name) {
        this(name, 0);
    }

    public Person1() {
        this("Unknown", 0);
    }

    // Custom instance methods allowed
    public String greet() {
        return "Hello, my name is " + name + " and I am " + age + " years old.";
    }

    // Auto toString override optional
    @Override
    public String toString() {
        return name + " (" + age + " years old)";
    }
}
