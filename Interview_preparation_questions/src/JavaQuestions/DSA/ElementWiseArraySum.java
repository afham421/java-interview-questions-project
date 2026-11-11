package JavaQuestions.DSA;

import com.sun.tools.javac.Main;

import java.util.Arrays;

/**
 * Element-wise sum of two arrays (different lengths allowed).
 * Missing positions are treated as 0.
 * <p>
 * Example:
 * A = [1, 2, 3]   (nA=3)
 * B = [4, 5]      (nB=2)
 * Sum = [5, 7, 3] (length = max = 3)
 * <p>
 * Bugs fixed from old Main.java:
 * 1) nSum was nA+nB → should be max(nA, nB)
 * 2) arrB[i] crashed when nA > nB
 * 3) leftover elements of the longer array were ignored
 */
public class ElementWiseArraySum {

    public static void main(String[] args) {
        int[] arrA = {1, 2, 3};
        int[] arrB = {4, 5};
        int nA = arrA.length;
        int nB = arrB.length;

        Result result = addArraysBrute(nA, arrA, nB, arrB);
        Result result1 = addArraysSmart(nA, arrA, nB, arrB);

        System.out.println(result.length); // 3
        System.out.println(Arrays.toString(result.sum)); // [5, 7, 3]
        System.out.println(result1.length); // 3
        System.out.println(Arrays.toString(result1.sum)); // [5, 7, 3]
    }

    static class Result {
        int length;
        int[] sum;

        Result(int length, int[] sum) {
            this.length = length;
            this.sum = sum;
        }
    }


    /**
     * Clear way: for each index, add arrA[i] and arrB[i] if they exist, else 0.
     * Time: O(max(nA, nB))
     */
    static Result addArraysBrute(int nA, int[] arrA, int nB, int[] arrB) {
        int length = Math.max(nA, nB);
        int[] sum = new int[length];

        for (int i = 0; i < length; i++) {
            int a = (i < nA) ? arrA[i] : 0;
            int b = (i < nB) ? arrB[i] : 0;
            sum[i] = a + b;
        }
        return new Result(length, sum);
    }

    /**
     * Smart way: start from the longer array, then add the shorter on top.
     * Avoids checking both bounds on every index.
     * Time: O(max)  Space: O(max)
     */
    static Result addArraysSmart(int nA, int[] arrA, int nB, int[] arrB) {
        int[] longer = nA >= nB ? arrA : arrB;
        int[] shorter = nA >= nB ? arrB : arrA;

        int[] sum = Arrays.copyOf(longer, longer.length); // copy longer as base
        for (int i = 0; i < shorter.length; i++) {
            sum[i] += shorter[i];
        }
        return new Result(sum.length, sum);
    }
}
