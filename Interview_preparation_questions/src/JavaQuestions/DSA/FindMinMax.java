package JavaQuestions.DSA;

import java.util.Arrays;

/**
 * Task: Find minimum and maximum in an array in one pass.
 * Example: [1,2,13,4,5,6,17,8,29,10] → min=1, max=29
 *
 * Ways:
 * 1) Linear scan (simple)     — O(n) time, ~2n comparisons
 * 2) Pair comparison (smart)  — O(n) time, ~3n/2 comparisons
 */
public class FindMinMax {

    public static void main(String[] args) {
        int[] arr = {1, 2, 13, 4, 5, 6, 17, 8, 29, 10};

        int[] simple = findMinMaxSimple(arr);
        int[] smart = findMinMaxPairs(arr);

        System.out.println("Array:  " + Arrays.toString(arr));
        System.out.println("Simple: min=" + simple[0] + ", max=" + simple[1]);
        System.out.println("Smart:  min=" + smart[0] + ", max=" + smart[1]);
    }

    /**
     * Simple one-pass way (best to learn first)
     * Time: O(n)  Space: O(1)
     *
     * Steps for arr = [1, 2, 13, 4, 5]:
     * 1) Assume first element is both min and max
     *    min=1, max=1
     * 2) i=0 value=1  → not < min, not > max
     * 3) i=1 value=2  → 2 > max → max=2
     * 4) i=2 value=13 → 13 > max → max=13
     * 5) i=3 value=4  → no change
     * 6) i=4 value=5  → no change
     * Result: min=1, max=13
     *
     * Why start with arr[0]?
     * Because we need an initial known value from the array.
     */
    public static int[] findMinMaxSimple(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int min = arr[0]; // step 1: initial min
        int max = arr[0]; // step 1: initial max

        for (int i = 0; i < arr.length; i++) { // step 2: visit each element
            if (arr[i] < min) {                 // step 3: smaller? update min
                min = arr[i];
            }
            if (arr[i] > max) {                 // step 4: larger? update max
                max = arr[i];
            }
        }
        return new int[]{min, max}; // [0]=min, [1]=max
    }

    /**
     * Smart way: process elements in pairs
     * Time: O(n)  Space: O(1)
     * Comparisons ≈ 3n/2 (better than ~2n in simple way)
     *
     * Idea:
     * - Take two numbers at a time
     * - Compare them with each other first (1 comparison)
     * - Then compare smaller with min, larger with max (2 more)
     * - Total 3 comparisons per 2 elements
     *
     * Dry-run [1, 2, 13, 4]:
     * start min=1, max=1 (first element), begin from index 1 if odd length handled
     * For even length version below:
     * Pair (1,2): 1<2 → candidateMin=1, candidateMax=2
     *   min=1, max=2
     * Pair (13,4): 4<13 → candidateMin=4, candidateMax=13
     *   4 > min? no; 13 > max → max=13
     * Result min=1, max=13
     */
    public static int[] findMinMaxPairs(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        int min, max;
        int i;

        // If odd length, start with first element, then process pairs
        if (arr.length % 2 == 0) {
            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                min = arr[1];
                max = arr[0];
            }
            i = 2;
        } else {
            min = max = arr[0];
            i = 1;
        }

        // Process remaining as pairs
        while (i < arr.length - 1) {
            int localMin, localMax;
            if (arr[i] < arr[i + 1]) {
                localMin = arr[i];
                localMax = arr[i + 1];
            } else {
                localMin = arr[i + 1];
                localMax = arr[i];
            }

            if (localMin < min) min = localMin;
            if (localMax > max) max = localMax;
            i += 2;
        }

        return new int[]{min, max};
    }
}
