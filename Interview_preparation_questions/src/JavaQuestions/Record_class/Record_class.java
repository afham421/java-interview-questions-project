package JavaQuestions.Record_class;

public class Record_class {
    // Records were introduced as a preview feature in Java 14 and became a standard feature in Java 16. They provide a new, compact way to declare classes
// that are primarily used to store data. By using records, you can avoid the boilerplate code typically associated with data classes,
// such as defining constructors, equals(), hashCode(), and toString() methods.
//Benefits of Using Records
// Reduced Boilerplate Code: Less code to write and maintain.
// Immutability by Default: Encourages good programming practices by making the data immutable.
// Automatic Method Generation: Simplifies the creation of data classes.
// Limitations
// No Inheritance: Records cannot extend other classes. They implicitly extend java.lang.Record.
// Immutable Fields: Once a record is created, its fields cannot be modified.
    public static void main(String[] args) {
        Point point = new Point(10, 20);

        // Accessing the fields
        System.out.println("X: " + point.x()); //x() are getters
        System.out.println("Y: " + point.y()); //there is no need for setters because record is immutable and final when you create a new record and pass the values then you don't update.'

        // Using the toString() method
        System.out.println("JavaQuestions.Record_class.Point: " + point);

        // Comparing records
        Point anotherPoint = new Point(10, 20);
        System.out.println("Points are equal: " + point.equals(anotherPoint));


        Person1 person1 = new Person1("joki", 21);
        System.out.println(person1.greet());

        // Using the canonical constructor
        Person1 person = new Person1("Alice", 30);
        System.out.println(person);

        // Using the overloaded constructor with name only
        Person1 person2 = new Person1("Bob");
        System.out.println(person2);

        // Using the default constructor
        Person1 person3 = new Person1();
        System.out.println(person3);

        // Attempting to create a person with invalid age
        try {
            Person1 person4 = new Person1("Charlie", -5); // This will throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception: " + e.getMessage());
        }
    }

}

record Point(int x, int y) {

}

//class fdf extends JavaQuestions.Record_class.Point{ // can't extend record class because it final
//}

record Person1(String name, int age) {

    // Canonical constructor (automatically generated, shown here for clarity)
//    public JavaQuestions.Record_class.Person1(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    // Custom constructor with validation logic
    public Person1 {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
    }

    // Additional custom constructor (overloaded)
    public Person1(String name) {
        this(name, 0); // Default age to 0 if not provided
    }

    // Custom constructor for another initialization method
    public Person1() {
        this("Unknown", 0); // Default name to "Unknown" and age to 0
    }

    // Custom method
    public String greet() {
        return "Hello, my name is " + name + " and I am " + age + " years old.";
    }

    // Overriding the toString() method
    @Override
    public String toString() {
        return name + " (" + age + " years old)";
    }
}
