package JavaQuestions.Logical_programs;

import java.util.Arrays;

public class Check_Zero_one {

    public static void main(String[] args)
    {
        int[] array_nums = {50, 77, 12, 54, -1, 1,-11};
        System.out.println("Original Array: "+ Arrays.toString(array_nums));
        System.out.println("Result: "+test(array_nums));
    }
    public static boolean test(int[] numbers) {
        for (int number : numbers) {
            if (number == 0 || number == 1) {
                return true;
            }
        }
        return false;
    }
}
