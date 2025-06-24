package JavaQuestions.sorting_In_Java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class sorting_In_Java {

    public static void main(String[] args) {
        // creating array of integers
//        int[] array = new int[6]; // first way
//        array[0] =10; // and so on
        int[] array = {10, 5, 8, 3, 9, 1};
        for (Integer a : array) {
            System.out.print(a + " ");
        }
        System.out.println();
        // first way
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        // 2nd way
        int[] arra = {11, 2, 12, 23, 3};
// Convert the int array to a List<Integer> using streams
        List<Integer> list = Arrays.stream(arra) // Create an IntStream
                .boxed()       // Convert IntStream to Stream<Integer>
                .sorted()
//                .collect(Collectors.toList()); // Collect to List<Integer>
                .toList(); // we can use direct toList() // this return immutable list so we don't use collections sort method'
//        Collections.sort(list); // this is used with              .collect(Collectors.toList()); // Collect to List<Integer>
        System.out.println(list);

        // 3rd way
        int[] array1 = {11, 2, 12, 23, 3};
        // Convert int[] to Integer[]
        Integer[] integerArray = Arrays.stream(array1) // Convert to stream
                .boxed()       // Box each int to Integer
                .toArray(Integer[]::new); // Convert to Integer[]

        // Convert Integer[] to List<Integer>
        List<Integer> list1 = Arrays.asList(integerArray);

        Collections.sort(list1);
        System.out.println(list1);


    }
}
