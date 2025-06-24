package JavaQuestions.method_and_constructor_referance;

import java.util.ArrayList;
import java.util.List;

public class MethodReference {
//    In Java, method references and constructor references are special kinds of lambda expressions used to refer to methods or constructors without actually invoking them.
//    They are more concise and readable than using traditional lambda expressions. Both are part of the Java Stream API and functional programming enhancements introduced in Java 8.
// Traditional lambda expression
//Function<String, Integer> lambda = str -> Integer.parseInt(str);
// Method reference //Syntax: ClassName::methodName
//    Function<String, Integer> methodRef = Integer::parseInt;

    public static void main(String[] args) {
        //we can implement functional interface method
        TaskInterface taskInterface = new TaskInterface() {
            @Override
            public void performTask() {
                System.out.println("Performing task...");
            }
        };
        taskInterface.performTask();

        //static method reference  // double colon :: use for refer only static methods  // it is used in the place of anonymous class or lambda expression
        TaskInterface doStuff = Stuff::doStuff; // here we refer not calling static method that's why we dont write .doStuff() we write only doStuff because we pass only reference
        doStuff.performTask();// here we pass reference so it takes body of doStuff in performTask()

        //object can refer non-static method
        Stuff stuff = new Stuff();
        TaskInterface doStuff2 = stuff::doStuff2;
        doStuff2.performTask(); // here we pass object reference so it takes the body of doStuff2 in performTask()

// for example
        List<Integer> list = List.of(1, 2, 3,54,532,34);
        list.stream().forEach(e-> System.out.println(e));
        //we can do the same with method reference without using lambda by only refer the method
        list.stream().forEach(System.out::println); // the main purpose is the code reusability //, and it is an alternative of lambda expression





    }


}
@FunctionalInterface
 interface TaskInterface {
    void performTask();
}

 class Stuff {
    public static void doStuff() {
        System.out.println("I am doing stuff");
    }

    public void doStuff2( ){
        System.out.println("its non static method");
    }
}
