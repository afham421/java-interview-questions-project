package JavaQuestions;

import java.util.function.Function;

public class HigherOrderFunctions {
    public static void main(String[] args) {
        // Define two operations using lambda expressions
        Function<Integer, Integer> addTen = x -> x + 10;
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;

        // Use the higher-order function to apply these operations
        int result1 = applyOperation(5, addTen); // Adds 10 to 5
        int result2 = applyOperation(5, multiplyByTwo); // Multiplies 5 by 2

        System.out.println("Result of adding 10: " + result1); // Outputs: 15
        System.out.println("Result of multiplying by 2: " + result2); // Outputs: 10

        Danceable d = () -> {
            System.out.println("implementation by lambda expression");
        };
        // both implementations are same
        Danceable d2 = new Danceable() { // anonymous class have no name here we just store this implementation into d2
            @Override
            public void dance() {
                System.out.println("implementation by anonymous class expression");
            }
        };

        Sarkar sarkar = new Sarkar();
        sarkar.testing(d);// here we pass the implementation of the function
        sarkar.testing(d2);// here we pass the implementation of the function


    }


    // Higher-order function that takes a Function as an argument
    public static int applyOperation(int number, Function<Integer, Integer> operation) {
        return operation.apply(number);
    }
}

class Sarkar{
//    Java, especially starting with Java 8, introduced functional programming features such as lambda expressions, method references, and functional interfaces
//    that make it possible to write higher-order functions.
    public void testing(Danceable danceable) { // here we pass function as an argument that return function as a result  // Higher-order function
        danceable.dance();
    }

}
@FunctionalInterface  // interface that has only one abstract  method we can use this methods implementation by lambda
interface Danceable{
     void dance();// Single abstract method
}
