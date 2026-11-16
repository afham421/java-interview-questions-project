package JavaQuestions.DSA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Task: Find common elements between two arrays.
 * Example:
 * a = [1, 2, 5, 5, 8, 9, 7, 10]
 * b = [1, 0, 6, 15, 6, 4, 7, 10]
 * commons = 1, 7, 10
 *
 * Ways:
 * 1) Brute nested loops     — O(n*m)
 * 2) HashSet lookup         — O(n+m)  ← smart
 * 3) Sort + two pointers    — O(n log n + m log m)
 */
public class FindCommonElementBetweenTwoArrays {

    public static void main(String[] args) {
        int[] array1 = {1, 2, 5, 5, 8, 9, 7, 10};
        int[] array2 = {1, 0, 6, 15, 6, 4, 7, 10};

        System.out.println("Array1: " + Arrays.toString(array1));
        System.out.println("Array2: " + Arrays.toString(array2));

        System.out.println("Brute:          " + findCommonBrute(array1, array2));
        System.out.println("HashSet smart:  " + findCommonHashSet(array1, array2));
        System.out.println("Sort+2pointer:  " + findCommonSortedTwoPointers(array1, array2));
    }

    /**
     * Brute force (your idea)
     * Time: O(n*m)  Space: O(k) for result
     *
     * Steps:
     * for each x in array1:
     *   for each y in array2:
     *     if x == y → common
     *
     * Dry-run tiny:
     * a=[1,7] b=[1,7,10]
     * 1 compared with 1,7,10 → match 1
     * 7 compared with 1,7,10 → match 7
     *
     * Note:
     * If duplicates exist in both arrays, nested loops can print same number many times.
     * Use a Set for unique commons.
     */
    public static List<Integer> findCommonBrute(int[] a, int[] b) {
        Set<Integer> commons = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    commons.add(a[i]); // unique
                }
            }
        }
        return new ArrayList<>(commons);
    }

    /**
     * Smart way: HashSet
     * Time: O(n+m)  Space: O(n)
     *
     * Steps:
     * 1) put all array1 values into a Set (fast contains)
     * 2) walk array2; if set contains value → common
     * 3) store in result Set to keep unique
     *
     * Dry-run:
     * set1 = {1,2,5,8,9,7,10}
     * scan array2:
     * 1 → yes
     * 0 → no
     * 6 → no
     * 15 → no
     * 6 → no
     * 4 → no
     * 7 → yes
     * 10 → yes
     * result = [1,7,10]
     *
     * Why smart?
     * Avoids nested loop; contains is average O(1).
     */
    public static List<Integer> findCommonHashSet(int[] a, int[] b) {
        Set<Integer> setA = new HashSet<>();
        for (int x : a) {
            setA.add(x);
        }

        Set<Integer> commons = new HashSet<>();
        for (int y : b) {
            if (setA.contains(y)) {
                commons.add(y);
            }
        }
        return new ArrayList<>(commons);
    }

    /**
     * Alternative smart: sort both, then two pointers
     * Time: O(n log n + m log m)  Space: O(1) extra besides result/copies
     *
     * Steps:
     * 1) sort both arrays
     * 2) i at start of a, j at start of b
     * 3) if equal → common, i++, j++
     *    if a[i] < b[j] → i++
     *    else j++
     *
     * Good when you already need sorted data / want less hash memory.
     */
    public static List<Integer> findCommonSortedTwoPointers(int[] a, int[] b) {
        int[] x = Arrays.copyOf(a, a.length);
        int[] y = Arrays.copyOf(b, b.length);
        Arrays.sort(x);
        Arrays.sort(y);

        List<Integer> commons = new ArrayList<>();
        int i = 0, j = 0;
        while (i < x.length && j < y.length) {
            if (x[i] == y[j]) {
                // add unique only
                if (commons.isEmpty() || commons.get(commons.size() - 1) != x[i]) {
                    commons.add(x[i]);
                }
                i++;
                j++;
            } else if (x[i] < y[j]) {
                i++;
            } else {
                j++;
            }
        }
        return commons;
    }
}
