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
//    Lower-Bounded Wildcard: <? super T>
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

