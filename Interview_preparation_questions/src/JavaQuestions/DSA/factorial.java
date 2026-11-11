package JavaQuestions.DSA;

import java.math.BigInteger;

/**
 * Factorial: n! = 1 * 2 * 3 * ... * n
 * Example: 5! = 120
 *
 * Note: long overflows quickly (20! fits in long, 21! does not).
 * For large n (26, 50, …) use BigInteger.
 */
public class Factorial {

    public static void main(String[] args) {
        int num = 5;

        System.out.println("Important !!  factorial of zero is 1   : " + factorialLong(0));
        System.out.println("Iterative (long):       " + factorialLong(num));       // 120
        System.out.println("Recursive (long):       " + factorialRecursive(num));  // 120
        System.out.println("Iterative (BigInteger): " + factorialBig(num));        // 120
        System.out.println("50! = " + factorialBig(50)); // needs BigInteger
    }

    /**
     * Iterative with long — fine for small n (n <= 20)
     * Time: O(n)  Space: O(1)
     */
    public static long factorialLong(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        long result = 1L;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Recursive — classic interview version
     * Time: O(n)  Space: O(n) call stack
     * Same overflow limit as long (n <= 20)
     */
    public static long factorialRecursive(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        if (n <= 1) return 1;                 // base case
        return n * factorialRecursive(n - 1); // n * (n-1)!
    }

    /**
     * Iterative BigInteger — smart for large n (no overflow)
     * Time: O(n)  Space: O(digits of n!)
     */
    public static BigInteger factorialBig(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
