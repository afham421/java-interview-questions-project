package JavaQuestions.Logical_programs;

import java.math.BigInteger;

public class factorial {


    public static void main(String[] args) {
        int num = 5; // when we assign num = 26 0r 50; it gives negative values because the output is out of the storage value, long cannot store this big value, so we use BigInteger to store big value
//        long factorialResult = 1l;

        BigInteger factorialResult = new BigInteger("1");
        System.out.println(factorialResult);

        for (int i = 1; i <= num; ++i) {
//            factorialResult *= i;
            factorialResult = factorialResult.multiply(BigInteger.valueOf(i));
        }
        System.out.println("Factorial: " + factorialResult);
    }


}
