package JavaQuestions.GenericsExamples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsExamples {

//    Generics in Java are a powerful feature that allows you to write flexible, reusable, and type-safe code. Introduced in Java 5, generics enable classes,
//    interfaces, and methods to operate on types specified by the client code while maintaining compile-time type safety.

//    KEY IDEA (easy memory):
//    Without generics:  List list = ...;  String s = (String) list.get(0);  // cast + runtime ClassCastException risk
//    With generics:     List<String> list = ...;  String s = list.get(0);   // compile-time type safety
//
//    Generics = one class/method, many types, checked at COMPILE time.
//
//    Forms:
//    1) Generic class:     class Box<T>
//    2) Generic interface: interface Pair<K,V>
//    3) Generic method:    public static <T> void printArray(T[] array)
//    4) Bounded type:      <T extends Number>
//    5) Wildcards:         <?>, <? extends T>, <? super T>
//
//    TYPE ERASURE (very common interview question):
//    Generics exist mainly at compile time. At runtime, type parameters are erased.
//    Box<String> and Box<Integer> become just Box after compilation (roughly).
//    That is why:
//    - you cannot use primitives: Box<int> ❌  -> use Box<Integer> ✅
//    - you cannot create new T() or new T[] easily
//    - List<String> and List<Integer> share the same runtime class: List
//
//    PECS: Producer Extends, Consumer Super
//    - reading FROM a collection (producer) -> <? extends T>
//    - writing INTO a collection (consumer) -> <? super T>

    public static void main(String[] args) {
        // diamond <> infers type from left side (Java 7+)
        Box<String> stringBox = new Box<>(); // assign String
        stringBox.setContent("Hello");
        String content = stringBox.getContent(); // no cast needed
        System.out.println("1) String box: " + content);

        Box<Integer> intBox = new Box<>(); // assign Integer (not int — generics need reference types)
        intBox.setContent(123);
        int value = intBox.getContent(); // auto-unboxing Integer -> int
        System.out.println("2) Integer box: " + value);


        String[] words = {"Hello", "World"};
        System.out.println("3) printArray(String[]):");
        printArray(words);
        Integer[] keys = {23, 45}; // cannot pass int[] — type parameter T must be a reference type
        System.out.println("4) printArray(Integer[]):");
        printArray(keys);


        System.out.println("5) bounded <T extends Number>:");
        addNumbers(5, 10); // Integers (subclasses of Number)


        // <? extends Number> = list of Number or any subtype (Integer, Double...)
        List<Integer> intList = Arrays.asList(1, 2, 3);
        System.out.println("6) upper bound <? extends Number> (READ):");
        printNumbers(intList);
        printNumbers(Arrays.asList(1.5, 2.5)); // Double also OK

        // <? super Integer> = list that can HOLD Integer (Integer, Number, Object)
        List<Number> numberList = new ArrayList<>();
        System.out.println("7) lower bound <? super Integer> (WRITE):");
        addIntegers(numberList);
        System.out.println(numberList);


        // generic interface + class
        Pair<String, Integer> p1 = new OrderedPair<>("One", 1);
        System.out.println("8) Pair: " + p1.getKey() + " -> " + p1.getValue());

        // RAW type (avoid) — no <Type>, loses type safety (legacy pre-Java 5 style)
        // Box raw = new Box(); raw.setContent(1); String bad = (String) raw.getContent(); // ClassCastException risk
    }

    // generic method: <T> before return type declares the type parameter
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println("   " + element);
        }
    }

    // bounded type parameter: only Number or its subclasses
    public static <T extends Number> void addNumbers(T num1, T num2) {
        System.out.println("   sum = " + (num1.doubleValue() + num2.doubleValue()));
    }

//    Wildcards = unknown type:
//    Unbounded:      <?>              any type
//    Upper-bounded:  <? extends T>    T or subclass  -> good for READING (producer)
//    Lower-bounded:  <? super T>      T or superclass -> good for WRITING (consumer)

    public static void printNumbers(List<? extends Number> list) {
        for (Number n : list) {
            System.out.println("   " + n);
        }
        // list.add(1); // ❌ cannot add — exact subtype unknown (could be List<Double>)
    }

    public static void addIntegers(List<? super Integer> list) {
        list.add(10); // ✅ can write Integer
        list.add(20);
        // Integer x = list.get(0); // ❌ not safe — may be Object/Number; read only as Object
        Object first = list.get(0);
        System.out.println("   first as Object: " + first);
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

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}


//✅ Upper-Bounded Wildcard: <? extends T>
//Meaning: Accepts any type that is T or a subclass of T.
//Used when you want to READ data from a generic structure.
//You cannot add elements (exact type unknown) — only reading is safe.
//
//printNumbers(List<Integer>) ✅
//printNumbers(List<Double>)  ✅
//list.add(5) ❌
//list.add(null) ✅ (only null)
//
//When: consume/read; items are at least type T.


//✅ Lower-Bounded Wildcard: <? super T>
//Meaning: Accepts any type that is T or a superclass of T.
//Used when you want to WRITE (add) data into a generic structure.
//You cannot safely read as T — only as Object.
//
//addIntegers(List<Object>) ✅
//addIntegers(List<Number>) ✅
//list.add(10) ✅ Integer
//list.add(2.5) ❌ Double not Integer
//
//When: produce/write T into the collection.


//🔁 PECS: Producer Extends, Consumer Super
//| Wildcard        | Usage    | Add Elements?        | Read Elements?           |
//| --------------- | -------- | -------------------- | ------------------------ |
//| <? extends T>   | Producer | No                   | Yes (as T)               |
//| <? super T>     | Consumer | Yes (T or subtype)   | Yes (only as Object)     |


// One-Line Summary
// Generics = compile-time type safety + reusable code (Box<T>, methods, wildcards)
// Erasure = type info mostly gone at runtime (no primitives as type args)
// PECS = extends to read, super to write
