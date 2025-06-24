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
interface Provider{
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
    String displayName(){
        return "i am a person";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}