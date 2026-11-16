package JavaQuestions.DSA;

import java.util.HashSet;
import java.util.Set;

/**
 * Task: Array contains numbers from 1..n with ONE missing. Find the missing number.
 * Example: [4,3,8,1,5,2,6] → numbers 1..8, missing = 7
 *
 * Assumption: values are in range 1..n and exactly one number is missing.
 *
 * Ways:
 * 1) Sum formula           — O(n) time, O(1) space
 * 2) XOR                   — O(n) time, O(1) space (no overflow risk)
 * 3) HashSet               — O(n) time, O(n) space
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] array = {4, 3, 8, 1, 5, 2, 6};

        System.out.println("Sum way:  " + findMissingBySum(array));  // 7
        System.out.println("XOR way:  " + findMissingByXor(array));  // 7
        System.out.println("Set way:  " + findMissingBySet(array));  // 7
    }

    /**
     * Way 1 (your way): expected sum - actual sum
     * Time: O(n)  Space: O(1)
     *
     * Steps for [4,3,8,1,5,2,6]:
     * 1) array has 7 numbers → complete range size n = 7+1 = 8 (1..8)
     * 2) expected sum = n*(n+1)/2 = 8*9/2 = 36
     * 3) actual sum = 4+3+8+1+5+2+6 = 29
     * 4) missing = 36 - 29 = 7
     *
     * Why n = length + 1?
     * Because one value is missing from the full sequence.
     *
     * Caution: for very large n, n*(n+1) may overflow int → use long.
     */
    public static int findMissingBySum(int[] array) {
        int n = array.length + 1;
        long expected = (long) n * (n + 1) / 2;
        long actual = 0;
        for (int value : array) {
            actual += value;
        }
        return (int) (expected - actual);
    }

    /**
     * Way 2 (smart alternative): XOR
     * Time: O(n)  Space: O(1)
     *
     * Idea:
     * x ^ x = 0
     * x ^ 0 = x
     * XOR all numbers 1..n and XOR all array values.
     * Pairs cancel out; leftover is the missing number.
     *
     * Steps for [4,3,8,1,5,2,6] (n=8):
     * xorAll = 1^2^3^4^5^6^7^8
     * xorArr = 4^3^8^1^5^2^6
     * result = xorAll ^ xorArr → 7
     *
     * Why useful?
     * Avoids large sum overflow; still O(1) extra space.
     */
    public static int findMissingByXor(int[] array) {
        int n = array.length + 1;
        int xor = 0;

        for (int i = 1; i <= n; i++) {
            xor ^= i;          // XOR 1..n
        }
        for (int value : array) {
            xor ^= value;      // XOR array values
        }
        return xor;            // leftover = missing
    }

    /**
     * Way 3: HashSet
     * Time: O(n)  Space: O(n)
     *
     * Steps:
     * 1) put all array values in a set
     * 2) check which number from 1..n is not present
     */
    public static int findMissingBySet(int[] array) {
        int n = array.length + 1;
        Set<Integer> set = new HashSet<>();
        for (int value : array) {
            set.add(value);
        }
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1; // should not happen if input is valid
    }
}
