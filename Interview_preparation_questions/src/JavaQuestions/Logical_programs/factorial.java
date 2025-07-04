package JavaQuestions.Logical_programs;

public class factorial {


    public static void main(String[] args) {
        int num = 10;
        long factorialResult = 1l;
        for (int i = 1; i <= num; ++i) {
            factorialResult *= i;
        }
        System.out.println("Factorial: " + factorialResult);
    }


}
