package JavaQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableObjects {
    public static void main(String[] args) {
        // Step 1: Create a mutable list of hobbies
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");
        hobbies.add("Swimming");

        // Step 2: Create an ImmutablePerson object
        ImmutablePerson person = new ImmutablePerson("John Doe", 30, hobbies);

        // Step 3: Attempt to modify the original list
        hobbies.add("Gaming");

        // Step 4: Try to modify the hobbies list through the getter
        List<String> retrievedHobbies = person.getHobbies();
        try {
            retrievedHobbies.add("Traveling");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify the hobbies list! Immutability preserved.");
        }

        // Step 5: Print details to verify immutability
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Hobbies: " + person.getHobbies());

        // Step 6: Assert that the original object is unchanged
        System.out.println("Original hobbies list (outside class): " + hobbies);
        System.out.println("Hobbies list in ImmutablePerson object: " + person.getHobbies());
    }

}

//Declare the class as final:
final class ImmutablePerson {

    //    Make all fields private and final:
//    private ensures that fields cannot be accessed directly from outside the class.
//    final ensures that the fields can only be assigned once, typically within the constructor.
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // Create a defensive copy of the mutable list
        this.hobbies = new ArrayList<>(hobbies);
    }

    //    Do not provide any setter methods:
//    Setters allow fields to be modified after object creation, which is against immutability.
    // Create getter methods to access the fields:
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
//    For mutable fields, return defensive copies:
//    If your class contains fields that reference mutable objects (e.g., Date, List, or custom objects), return a new object instead of the original reference.

    public List<String> getHobbies() {
        // Return an unmodifiable view of the list to prevent modification
        return Collections.unmodifiableList(hobbies);
    }



}
