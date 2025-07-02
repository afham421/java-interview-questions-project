package JavaQuestions;

import java.util.ArrayList;

public class Var_Implementation {

    //    In Java, var is a feature introduced in Java 10 that allows for local variable type inference. When you declare a variable using var,
//    the compiler automatically infers the variable's type based on the value it's initialized with. This feature can make your code more concise and readable,
//    especially in situations where the type is evident(clear) from the context.
//
//    Key Points About var:
//    Local Variable Type Inference: var can only be used for local variables, such as those inside a method, for loop, or a block.
//    It cannot be used for class fields, method parameters, or return types.
//    Mandatory Initialization: Variables declared with var must be initialized at the time of declaration, so the compiler can infer the type.
//    Static and Dynamic Typing: Despite using var, Java remains statically typed. The type of the variable is determined at compile-time and does not change.
//    var name = "ffdf"; // here you can't use var because it is instance variable and var is for local variables

    public static void main(String[] args) {
        // var is for local variables only
        var message = "Hello, World!"; // Inferred as String
        var number = 10;               // Inferred as int
        var list = new ArrayList<>();
//        list.add("dfdf"); // Inferred as ArrayList<String>
        list.add(13); // Inferred as ArrayList<Integer>
        System.out.println(message);
        System.out.println(number);
        System.out.println(list);
    }
    // This is not allowed. `var` cannot be used in method signatures.
//    Public var countElements(var items) {
//        return items.size();
//    }
}
