package JavaQuestions.functional_programing_vs_object_oriented_programing;

import java.util.Arrays;
import java.util.List;

public class FP_vs_OOP {

    public static void main(String[] args) {
        List<Integer> speeds = Arrays.asList(10, 20, 30, 40);
//🔄 “Immutable” Doesn’t Mean “No Change” — It Means “No Mutation of Original Data”
//        In functional programming, immutability means you don’t modify the original data, you create and return a new value instead.
        speeds.stream()
                .map(speed -> speed * 2) // Creates new values, doesn't change the original list
                .forEach(System.out::println);

        System.out.println(speeds); // Still prints: [10, 20, 30, 40]

    }
}
