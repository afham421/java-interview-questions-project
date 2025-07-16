package JavaQuestions.method_and_constructor_referance;

public class ConstructorReference {
    //    Reference to a Constructor
//    Syntax: ClassName::new
    // Traditional lambda expression
//    Supplier<List<String>> lambda = () -> new ArrayList<>();
    // Constructor reference
//    Supplier<List<String>> constructorRef = ArrayList::new;

    public static void main(String[] args) {
//        When to Use Method and Constructor References
//        When you need to pass a method or constructor as an argument to a higher-order function.
//        When your lambda expression simply calls an existing method or constructor without additional logic.

        // No-argument constructor reference // basically it is an alternative if lambda expression
        Provider provider1 = new Provider() {
            @Override
            public Person getPerson() {
                return new Person();
            }
        };
        Person person1 = provider1.getPerson();
        System.out.println(person1.displayName());
        //2nd way
        Provider provider = Person::new;
        Person person = provider.getPerson(); // Creates a new Person using the no-arg constructor
        System.out.println(person.displayName());
        // Constructor reference with an argument
        // Use constructor reference to bind to our custom functional interface
        PersonFactory personFactory = Person::new;
        // Create a new Person using the constructor reference
        Person personWithName = personFactory.create("Alice");
        System.out.println(personWithName.toString());

    }
}
//✅ Types of Method References
//| Type                                        | Syntax                      | Equivalent Lambda                         |
//| ------------------------------------------- | --------------------------- | ----------------------------------------- |
//| 1. **Static method**                        | `ClassName::staticMethod`   | `(args) -> ClassName.staticMethod(args)`  |
//| 2. **Instance method of an object**         | `object::instanceMethod`    | `(args) -> object.instanceMethod(args)`   |
//| 3. **Instance method of a class (unbound)** | `ClassName::instanceMethod` | `(obj, args) -> obj.instanceMethod(args)` |
//| 4. **Constructor reference**                | `ClassName::new`            | `() -> new ClassName()`                   |
//
//📘 Examples
//1️⃣ Static Method Reference

//class Util {
//    public static void print(String msg) {
//        System.out.println(msg);
//    }
//}
//
//Consumer<String> printer = Util::print;  // instead of msg -> Util.print(msg)
//printer.accept("Hello");  // Output: Hello

//2️⃣ Instance Method of an Object

//List<String> list = Arrays.asList("a", "b", "c");
//list.forEach(System.out::println);  // instead of s -> System.out.println(s)

//3️⃣ Instance Method of a Class (Unbound)

//Function<String, Integer> strLength = String::length;  // str -> str.length()
//System.out.println(strLength.apply("Java")); // Output: 4

//4️⃣ Constructor Reference

//Supplier<List<String>> listSupplier = ArrayList::new;  // () -> new ArrayList<>()
//List<String> list = listSupplier.get();  // creates a new ArrayList

//✅ Benefits
//Cleaner, more concise than lambda expressions

//Improves readability

//Useful when the lambda just calls a method

//
//✅ Summary
//| You Use                  | When...                                                         |
//| ------------------------ | --------------------------------------------------------------- |
//| `Class::staticMethod`    | You want to refer to a static method                            |
//| `object::instanceMethod` | You already have an object and want to call its method          |
//| `Class::instanceMethod`  | You want to call an instance method on any object of that class |
//| `Class::new`             | You want to create a new object (constructor reference)         |

interface Provider {
    Person getPerson();

}

// Define a custom functional interface with a single abstract method that takes a String and returns a Person
@FunctionalInterface
interface PersonFactory {
    Person create(String name);
}


class Person {
    private String name;

    public Person() {
        this.name = "John Doe";
    }

    public Person(String name) {
        this.name = name;
    }

    String displayName() {
        return "i am a person";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}