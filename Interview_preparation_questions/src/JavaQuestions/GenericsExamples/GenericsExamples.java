package JavaQuestions.GenericsExamples;

import java.util.Arrays;
import java.util.List;

public class GenericsExamples {

//    Generics in Java are a powerful feature that allows you to write flexible, reusable, and type-safe code. Introduced in Java 5, generics enable classes,
//    interfaces, and methods to operate on types specified by the client code while maintaining compile-time type safety.

    public static void main(String[] args) {
        Box<String> stringBox = new Box<String>();// here we assign the string value
        stringBox.setContent("Hello");
        String content = stringBox.getContent();
        System.out.println(content);

        Box<Integer> intBox = new Box<Integer>(); //here we assign int value
        intBox.setContent(123);
        int value = intBox.getContent();
        System.out.println(value);


        String[] words = {"Hello", "World"};
        printArray(words);
        Integer[] keys = {23,45}; //  here we can't pas int[] because generics wants wrapper class
        printArray(keys);


        addNumbers(5, 10); // Both are Integers, which are subclasses of Number we can restrict here only numbers



        // Usage
        List<Integer> intList = Arrays.asList(1, 2, 3);
        printNumbers(intList);
//    <? extends Number> means the method accepts a list of any type that extends Number.




// we can use in interface and classes  look his interface and classes are below
        Pair<String, Integer> p1 = new OrderedPair<>("One", 1);


    }
    // Example of a generic method that takes an array of any type and prints its elements
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
    //You can restrict the types that can be used with generics by specifying bounds:
    public static  <T extends Number> void addNumbers(T num1, T num2) {
        System.out.println(num1.doubleValue() + num2.doubleValue());
    }

//    Wildcards are used to handle unknown types:
//
//    Unbounded Wildcard: <?>
//    Upper-Bounded Wildcard: <? extends T>
//    Lower-Bounded Wildcard: <? super T>       //////read below
//    Example of an upper-bounded wildcard:

    public static void printNumbers(List<? extends Number> list) {
        for (Number n : list) {
            System.out.println(n);
        }
    }

}
 class Box<T> {
    private T content;

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

 interface Pair<K, V> {
    K getKey();
    V getValue();
}

 class OrderedPair<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }
}


//✅ Upper-Bounded Wildcard: <? extends T>
//Meaning: Accepts any type that is T or a subclass of T.
//
//It is used when you want to read data from a generic structure.
//
//You cannot add elements, because the exact type is unknown — only reading is safe.
//
//📦 Example Use Case: Reading a List of Numbers
//java
//Copy
//Edit
//public class WildcardExample {
//    public static void printNumbers(List<? extends Number> list) {
//        for (Number num : list) {
//            System.out.println(num);
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Integer> intList = Arrays.asList(1, 2, 3);
//        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
//
//        printNumbers(intList);     // ✅ Allowed (Integer extends Number)
//        printNumbers(doubleList);  // ✅ Allowed (Double extends Number)
//    }
//}
//⚠️ Important Notes:
//You can read elements as type Number.
//
//You cannot add elements to the list — because the exact subtype is unknown.
//
//java
//Copy
//Edit
//list.add(5);       // ❌ Compilation error
//list.add(null);    // ✅ Allowed
//📌 When to use <? extends T>?
//When you need to consume (read) data from a collection.
//
//You don’t need to write/add data to it.
//
//You want to ensure the items are at least of type T.







//✅ Lower-Bounded Wildcard: <? super T>
//Meaning: Accepts any type that is T or a superclass of T.
//
//It is used when you want to write (add) data into a generic structure.
//
//You cannot safely read specific types, except Object.
//
//📦 Example Use Case: Adding Integers to a List
//java
//Copy
//Edit
//public class LowerBoundExample {
//    public static void addNumbers(List<? super Integer> list) {
//        list.add(10);      // ✅ Allowed
//        list.add(20);      // ✅ Allowed
//        // list.add(2.5);  // ❌ Not allowed, 2.5 is Double
//
//        // Reading elements - only as Object
//        for (Object obj : list) {
//            System.out.println(obj);
//        }
//    }
//
//    public static void main(String[] args) {
//        List<Object> objList = new ArrayList<>();
//        List<Number> numList = new ArrayList<>();
//
//        addNumbers(objList);  // ✅ Object is a super of Integer
//        addNumbers(numList);  // ✅ Number is a super of Integer
//    }
//}
//⚠️ Key Rules:
//You can add Integer or its subclasses into the list.
//
//You can only read as Object — not as Integer or Number.
//
//🔁 Summary: PECS Principle
//PECS: Producer Extends, Consumer Super
//
//| Wildcard        | Usage    | Add Elements?        | Read Elements?           |
//| --------------- | -------- | -------------------- | ------------------------ |
//| `<? extends T>` | Producer | ❌ No                 | ✅ Yes (as `T`)           |
//| `<? super T>`   | Consumer | ✅ Yes (T or subtype) | ✅ Yes (only as `Object`) |
