package JavaQuestions.method_and_constructor_referance;

/**
 * Constructor Reference
 * Syntax: ClassName::new
 *
 * Meaning:
 * Instead of writing a lambda/anonymous class that only does `new Person(...)`,
 * you can point directly to the constructor.
 *
 * Equivalent forms:
 *   () -> new Person()           →  Person::new
 *   name -> new Person(name)     →  Person::new
 *
 * Java picks the matching constructor from the functional interface method signature.
 *
 * When to use:
 * - You need to pass object-creation logic to a functional interface / stream / factory
 * - Lambda would only call a constructor (no extra logic)
 */
public class ConstructorReference {

    public static void main(String[] args) {

        // ---------- 1) Old way: anonymous class ----------
        // Step A: create an implementation of Provider
        // Step B: getPerson() manually returns new Person()
        Provider provider1 = new Provider() {
            @Override
            public Person getPerson() {
                return new Person(); // no-arg constructor
            }
        };
        Person person1 = provider1.getPerson();
        System.out.println("Anonymous: " + person1.displayName()); // John Doe

        // ---------- 2) Constructor reference (no-arg) ----------
        // Provider.getPerson() takes NO args and returns Person
        // So Person::new binds to Person()
        // Same as: Provider provider = () -> new Person();
        Provider provider = Person::new;
        Person person = provider.getPerson();
        System.out.println("Ctor ref:  " + person.displayName()); // John Doe

        // ---------- 3) Constructor reference (with arg) ----------
        // PersonFactory.create(String) takes 1 String and returns Person
        // So Person::new binds to Person(String name)
        // Same as: PersonFactory f = name -> new Person(name);
        PersonFactory personFactory = Person::new;
        Person personWithName = personFactory.create("Alice");
        System.out.println("Ctor+arg:  " + personWithName); // Person{name='Alice'}

        // ---------- 4) Lambda vs constructor reference ----------
        PersonFactory lambdaWay = name -> new Person(name);
        PersonFactory refWay = Person::new; // cleaner, same behavior
        System.out.println("Lambda:    " + lambdaWay.create("Bob"));
        System.out.println("Reference: " + refWay.create("Bob"));
    }
}

/** Functional interface: no-arg factory → matches Person() */
@FunctionalInterface
interface Provider {
    Person getPerson();
}

/** Functional interface: String → Person → matches Person(String) */
@FunctionalInterface
interface PersonFactory {
    Person create(String name);
}

class Person {
    private final String name;

    public Person() {
        this.name = "John Doe";
    }

    public Person(String name) {
        this.name = name;
    }

    String displayName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}
