package JavaQuestions;

public class StringExample {
    public static void main(String[] args) {
        // in string case when we change the value or update the value, it creates a new object and references it to the variable
//        Immutability: Strings in Java are immutable. Operations that seem to modify a string actually create a new String object.
//                Heap and Pool: The string literal "hello" is stored in the string pool, while new String("hello") creates a new object in the heap.
//                Memory Management: Old String objects remain in memory as long as there are references to them. Once no references exist, they become eligible for garbage collection.
        String s1 = "hello";  // Literal, stored in the String Pool
        String s2 = "hello";  // Reuses the same instance from the String Pool // means one object address is referred to both s1 and s2
//string constant pool wala string constant pool main new object bnata chage keny py or heep wala heep main new object
        String s3 = new String("hello"); // Creates a new String object in the heap //when we update the String then it create new object in heep and refer it

        System.out.println(s1 == s2); // true, both refer to the same instance in the pool
        System.out.println(s1 == s3); // false, s3 is a new object in the heap
        System.out.println(s1.intern() == s3.intern()); // true, both refer to the same pooled instance
//        Intern Method: You can manually add a string to the pool using the intern() method. If the string is already in the pool, intern() returns the reference to the pooled string.
//        Otherwise, it adds the string to the pool and returns the reference.


        String originalString = "Hello";
        System.out.println("Original String: " + originalString);
        originalString = "world";
        System.out.println("Original String update: " + originalString);

//        Explanation:
//        Strings in Java are immutable, meaning the actual "Hello" and "world" objects never change.But originalString is just a reference variable.
//        So when you write:
//                originalString = "world";
//        you're not mutating the string — you're just pointing originalString to a new string object ("world"), and the old one ("Hello") becomes unreferenced (and eligible for GC if unused elsewhere).


        // Attempt to modify the string by concatenating a new value
        String modifiedString = originalString.concat(" World");
        System.out.println("Modified String: " + modifiedString);

        // Check if the original string is changed
        System.out.println("Is Original String changed? " + (originalString == modifiedString));
        System.out.println("Original String after modification attempt: " + originalString);


    }
}
