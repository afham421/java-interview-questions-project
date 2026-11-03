package JavaQuestions.GenericsExamples;

import java.util.ArrayList;
import java.util.List;

public class GenericsConcepts {

//    WHY GENERICS? (this file's story)
//    Problem: we want ONE reusable container/list that works for many types,
//             but still stays TYPE SAFE at compile time.
//
//    3 approaches compared below:
//    1) Raw type     List list = new ArrayList();     -> flexible, NOT type safe (runtime risk)
//    2) Object       class Test { Object container; } -> flexible, NOT type safe (casts + runtime risk)
//    3) Generics     Container<T> / List<Integer>     -> flexible AND type safe (compile-time check)
//
//    KEY IDEA:
//    Generics move type errors from RUNTIME (ClassCastException)
//    to COMPILE TIME (red underline / won't compile).
//
//    Related file: GenericsExamples.java  -> wildcards, bounds, PECS, erasure details

    public static void main(String[] args) {

        // -------- 1) RAW TYPE (bad / legacy) --------
        List list = new ArrayList(); // raw type: can add ANY type — not type safe
        list.add(1);
        list.add("Hello"); // mixed types allowed at compile time
        // when another person assumes this list is only Integer, but it has String/boolean too
        // → error shows up at RUNTIME, not compile time — bad approach
        list.add(true);
        System.out.println("1) Raw List (mixed types allowed): " + list);

        // Dangerous cast example (do not uncomment — throws ClassCastException at runtime):
        // Integer onlyInt = (Integer) list.get(1); // get(1) is "Hello" → boom at runtime

        System.out.println("=======================");

        // -------- 2) GENERIC LIST (type safe) --------
        List<Integer> list1 = new ArrayList<>(); // diamond <> OK (Java 7+)
        // here we can add only Integers; other types = compile-time error
        list1.add(41);
//        list1.add("sss"); // compile-time error → type safety (raw list would fail only at runtime)
        list1.add(451);
        System.out.println("2) List<Integer> (only ints): " + list1);

        System.out.println("=======================");

        // -------- 3) Object as "generic" return type (flexible but unsafe) --------
        // Object is parent of all classes, so it can hold String, Integer, etc.
        // But caller must cast, and wrong cast → runtime exception
        Test test = new Test(45); // pass any Object
        System.out.println("3a) Test with Integer: " + test.getValue()); // 45
        test.container = "changed to String value"; // still compiles — no type lock
        System.out.println("3b) Same Test now String: " + test.getValue());
        // next person expects Integer, gets String → ClassCastException risk:
        // Integer n = (Integer) test.getValue(); // runtime fail

        Test test1 = new Test("water");
        System.out.println("3c) Another Test with String: " + test1.getValue());

        System.out.println("=======================");

        // -------- 4) GENERICS SOLUTION: Container<T> --------
        // Fix type once at creation → get/set stay consistent
        Container<String> container = new Container<>("This is a string");
        Container<Integer> containerInt = new Container<>(11); // String above, Integer here
//        containerInt.value = "dfef"; // compile-time error → type safety
        containerInt.value = 23;

        System.out.println("4a) String container type: " + container.value.getClass().getName());
        System.out.println("    value: " + container.value);
        container.performTask();

        System.out.println("4b) Integer container type: " + containerInt.value.getClass().getName());
        System.out.println("    value: " + containerInt.value);
        containerInt.performTask();

        // getValue() return type matches T — no cast needed
        String s = container.getValue();
        Integer i = containerInt.getValue();
        System.out.println("4c) No cast needed: " + s + " | " + i);
    }
}

class Container<T> {
    T value; // T is a placeholder; decided when you write Container<String> / Container<Integer>

    Container(T value) {
        this.value = value;
    }

    void performTask() {
        // NOTE: instanceof still works, but true generic style prefers NOT branching on type.
        // After type erasure, T is Object at runtime — instanceof checks the actual object.
        if (value instanceof String) {
            System.out.println("length of " + value + " is " + ((String) this.value).length());
        } else if (value instanceof Integer) {
            System.out.println("the integer value " + value);
        }
    }

    T getValue() {
        return value; // returns exact T (String or Integer), not Object
    }
}

class Test {
    Object container; // Object can take String, int (boxed), etc. — flexible but NOT type safe

    Test(Object container) {
        this.container = container;
    }

    Object getValue() {
        return this.container; // caller must cast → runtime risk
    }
}

// One-Line Summary
// Raw type / Object field = flexible but unsafe (runtime ClassCastException)
// Generics <T> = same flexibility + compile-time type safety (no cast needed)
