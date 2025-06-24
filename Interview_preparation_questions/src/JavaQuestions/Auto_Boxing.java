package JavaQuestions;

import java.util.ArrayList;
import java.util.List;

public class Auto_Boxing {

    public static void main(String[] args) {
// Auto_Boxing is a concept
        List<Integer> numbers = new ArrayList<Integer>();
        int num = 5;
        // Autoboxing happens here
        numbers.add(num); // int is automatically converted to Integer
        numbers.add(10);
        // Unboxing: converting Integer to int
        int num1 = numbers.get(1); // Unboxing happens here //integerList.get(0) returns an Integer object.
//        Java automatically converts this Integer object back to the primitive int type (unboxing) when you assign it to the int num variable.
        // Print the primitive int
        System.out.println(num1);

        Integer obj = null;
        // Unboxing a null reference
        try {
            int num2 = obj; // This line will throw a NullPointerException
        } catch (NullPointerException e) {//In this code, the attempt to unbox obj (which is null) will cause a NullPointerException, highlighting the need for caution when using autoboxing and unboxing.
            System.out.println("Caught NullPointerException: " + e.getMessage());
        }
//        Autoboxing is a feature in Java that automatically converts a primitive type into its corresponding wrapper class object when necessary. Conversely,
//        unboxing is the process of converting an object of a wrapper type back to its corresponding primitive type.

//        Primitive Types and Their Wrapper Classes:
//        int → Integer
//        char → Character
//        double → Double
//        boolean → Boolean
//        byte → Byte
//        short → Short
//        long → Long
//        float → Float
//        How Autoboxing Works:
//        Autoboxing allows you to write cleaner code by automatically handling the conversion between primitive types and their wrapper objects.
//        This is useful when working with collections, generics, or APIs that expect objects rather than primitives.




    }



}
