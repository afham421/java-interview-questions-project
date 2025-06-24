package JavaQuestions;

import java.util.Optional;

public class Optional_Learn {
    public static void main(String[] args) { // use optional with getter not instance variable of class because instance variable are private
        // Optional class in Java 8 provides a way to handle null values // it is used normally when your method return some value
        // but it can be null so you return optional value
        // It is used to represent a value that might be absent or not available
//        Optional is a powerful utility in Java for handling values that may be absent, reducing the risk of NullPointerException and making code more expressive
//        and maintainable. By using methods like map, flatMap, and orElse, you can handle optional values in a more functional and elegant manner.

//        String s = null;// it will throw NullPointerException so we handle this
        String s =  "Hi, its me";// it will throw NullPointerException so we handle this
        if (s == null){
            System.out.println("its null value");
        }else {
            System.out.println(s.length());
        }

        // we can do same thing by optional
        Optional<String> s1 = Optional.ofNullable(s);
        System.out.println(s1.isPresent());// if null then return false
//        System.out.println(s1.get()); // if s1 is null it throws an exception
        System.out.println(s1.orElse("its value is null"));// if value is present then return else return custom value
        System.out.println(s1.orElseGet(() -> "Generated Value")); // Output: Generated Value if  null



//        1. Optional.of()
//        Creates an Optional containing a non-null value. If the value is null, it throws a NullPointerException.
        Optional<String> optionalString = Optional.of("Hello, World!");
//        Optional<String> optionalString1 = Optional.of(null);
        // Access the value using get() method
        System.out.println(optionalString);// it will return Optional[Hello, World!]
        System.out.println(optionalString.get());//and this Hello, World!
//        System.out.println(optionalString1);//If the value is null, it throws a NullPointerException.
//2. Optional.ofNullable()
//Creates an Optional that may or may not contain a non-null value. If the value is null, it returns an empty Optional.
        Optional<String> optionalNonNull = Optional.ofNullable("Hello, Optional!");
        Optional<String> optionalNull = Optional.ofNullable(null);
        System.out.println(optionalNonNull);  // Output: Optional[Hello, Optional!]
        System.out.println(optionalNull);     // Output: Optional.empty
//        4. isPresent()
//        Returns true if there is a value present; otherwise, returns false.
        Optional<String> optional = Optional.of("Java");
        System.out.println(optional.isPresent());  // Output: true

        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.isPresent());  // Output: false
//        5. ifPresent(Consumer<? super T> consumer)
//        If a value is present, it performs the given action with the value; otherwise, does nothing.
        Optional<String> optional1 = Optional.of("Functional Programming");
        optional1.ifPresent(value -> System.out.println("Value: " + value));
// Output: Value: Functional Programming

//        8. orElseThrow(Supplier<? extends X> exceptionSupplier)
//        Returns the contained value if present; otherwise, throws an exception provided by the exception-supplying function.
        Optional<String> optional2 = Optional.empty();
        try {
            String result = optional2.orElseThrow(() -> new IllegalArgumentException("Value is absent"));
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());  // Output: Value is absent
        }
//        9. map(Function<? super T, ? extends U> mapper)
//        If a value is present, applies the provided mapping function to it, and if the result is non-null, returns an Optional describing the result.
        Optional<String> optional3 = Optional.of("Java");
        Optional<Integer> length = optional3.map(String::length);
        System.out.println(length);  // Output: Optional[4]
//        10. flatMap(Function<? super T, Optional<U>> mapper)
//        Similar to map, but the provided mapping function must return an Optional.
        Optional<String> optional4 = Optional.of("Hello");
        Optional<String> upperCase = optional4.flatMap(w -> Optional.of(w.toUpperCase()));   // in this optional is necessary to pass by lambda
        System.out.println(upperCase);  // Output: Optional[HELLO]

//        11. filter(Predicate<? super T> predicate)
//        If a value is present, returns an Optional describing the value if it matches the given predicate; otherwise, returns an empty Optional.
        Optional<String> optional5 = Optional.of("Hello, World!");
        Optional<String> filtered = optional5.filter(r -> r.contains("World"));
        System.out.println(filtered);  // Output: Optional[Hello, World!]

        Optional<String> filteredEmpty = optional5.filter(r -> r.contains("Java"));
        System.out.println(filteredEmpty);  // Output: Optional.empty

        // Check if the Optional object is present
        if (optionalString.isPresent()) {
            System.out.println("Optional value is present.");
        } else {
            System.out.println("Optional value is not present.");
        }

        // If the Optional object is present, use the value
        // Otherwise, print a default message
        String defaultValue = optionalString.orElse("Default value");
        System.out.println(defaultValue);

        // If the Optional object is present, use the value
        // Otherwise, print a custom message
        String customValue = optionalString.orElse("Default value");



    }
}
