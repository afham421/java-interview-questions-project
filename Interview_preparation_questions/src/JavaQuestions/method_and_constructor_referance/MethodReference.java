package JavaQuestions.method_and_constructor_referance;

import java.util.List;
import java.util.function.Function;

/**
 * Method Reference
 * Syntax: ClassName::methodName  OR  object::methodName
 *
 * Meaning:
 * A short form of a lambda that ONLY calls an existing method.
 * You pass a reference to the method — you do NOT call it yet (no ()).
 *
 * Examples:
 *   str -> Integer.parseInt(str)   →  Integer::parseInt
 *   e -> System.out.println(e)     →  System.out::println
 *
 * Also see: Method_and_Constructor_Reference_Notes
 */
public class MethodReference {

    public static void main(String[] args) {

        // ---------- 1) Old way: anonymous class ----------
        TaskInterface task1 = new TaskInterface() {
            @Override
            public void performTask() {
                System.out.println("Performing task...");
            }
        };
        task1.performTask();

        // ---------- 2) Static method reference ----------
        // TaskInterface.performTask() takes no args
        // Stuff.doStuff() is static and also takes no args → signatures match
        // Same as: TaskInterface t = () -> Stuff.doStuff();
        // Note: Stuff::doStuff  → reference (not calling)
        //       Stuff.doStuff() → actual call
        TaskInterface task2 = Stuff::doStuff;
        task2.performTask(); // now it runs Stuff.doStuff() body

        // ---------- 3) Instance method reference (bound to an object) ----------
        // Need an object because doStuff2 is non-static
        // Same as: TaskInterface t = () -> stuff.doStuff2();
        Stuff stuff = new Stuff();
        TaskInterface task3 = stuff::doStuff2;
        task3.performTask(); // runs stuff.doStuff2()

        // ---------- 4) Practical stream example ----------
        List<Integer> list = List.of(1, 2, 3, 54, 532, 34);

        // lambda
        list.forEach(e -> System.out.print(e));
        System.out.println();

        // method reference (cleaner, same behavior)
        list.forEach(System.out::print);
        System.out.println();

        // ---------- 5) Another common static reference ----------
        // Function<String,Integer> f = str -> Integer.parseInt(str);
        Function<String, Integer> parse = Integer::parseInt;
        System.out.println("Parsed: " + parse.apply("123")); // 123
    }
}

@FunctionalInterface
interface TaskInterface {
    void performTask();
}

class Stuff {
    public static void doStuff() {
        System.out.println("I am doing stuff (static)");
    }

    public void doStuff2() {
        System.out.println("I am doing stuff (instance)");
    }
}
