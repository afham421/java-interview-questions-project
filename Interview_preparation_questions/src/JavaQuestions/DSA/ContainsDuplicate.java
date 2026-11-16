package JavaQuestions.DSA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Task: Return true if any value appears at least twice in the array; otherwise false.
 * Example: [1, 2, 3, 1] → true (1 repeats)
 *          [1, 2, 3, 4] → false (all unique)
 *
 * LeetCode: 217. Contains Duplicate
 * Name is fine — matches the problem. No rename needed.
 *
 * Ways:
 * 1) Brute nested loops     — O(n²) time, O(1) space
 * 2) Sort then scan         — O(n log n) time, O(1) or O(n) space
 * 3) HashSet                — O(n) time, O(n) space  ← interview favorite
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] withDup = {1, 2, 3, 1};
        int[] noDup = {1, 2, 3, 4};

        System.out.println("withDup = " + Arrays.toString(withDup));
        System.out.println("Brute:  " + containsDuplicateBrute(withDup));   // true
        System.out.println("Sort:   " + containsDuplicateSort(withDup));    // true
        System.out.println("Set:    " + containsDuplicateSet(withDup));     // true

        System.out.println();
        System.out.println("noDup = " + Arrays.toString(noDup));
        System.out.println("Brute:  " + containsDuplicateBrute(noDup));     // false
        System.out.println("Sort:   " + containsDuplicateSort(noDup));      // false
        System.out.println("Set:    " + containsDuplicateSet(noDup));       // false
    }

    /**
     * Way 1: Brute force (start here as a beginner)
     * Time: O(n²)  Space: O(1)
     *
     * Idea:
     * Compare every number with every number after it.
     * If any pair is equal → duplicate found.
     *
     * Steps for [1, 2, 3, 1]:
     * i=0 → value 1, compare with 2, 3, 1  → 1==1 → true
     *
     * Steps for [1, 2, 3, 4]:
     * every pair is different → false
     *
     * Why slow?
     * Nested loops: for n=1000 you do ~1,000,000 comparisons.
     */
    public static boolean containsDuplicateBrute(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Way 2: Sort, then check neighbors
     * Time: O(n log n)  Space: O(1) if in-place sort is ok (Arrays.sort on int[] is in-place)
     *
     * Idea:
     * After sorting, duplicates sit next to each other.
     * Walk once and compare each element with the previous one.
     *
     * Steps for [1, 2, 3, 1]:
     * 1) sort → [1, 1, 2, 3]
     * 2) scan: 1 then 1 → same → true
     *
     * Steps for [1, 2, 3, 4]:
     * 1) sort → [1, 2, 3, 4]
     * 2) neighbors never equal → false
     *
     * Note:
     * Sorting changes the array order. Copy first if you must keep original.
     */
    public static boolean containsDuplicateSort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length); // keep original safe for demo
        Arrays.sort(copy);

        for (int i = 1; i < copy.length; i++) {
            if (copy[i] == copy[i - 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Way 3: HashSet (smart / usual interview answer)
     * Time: O(n)  Space: O(n)
     *
     * Idea:
     * A Set stores unique values only.
     * While inserting, if add() returns false → value already seen → duplicate.
     *
     * Steps for [1, 2, 3, 1]:
     * seen = {}
     * 1 → not seen → add → {1}
     * 2 → not seen → add → {1,2}
     * 3 → not seen → add → {1,2,3}
     * 1 → already in set → return true
     *
     * Why fast?
     * HashSet contains/add is average O(1), so one pass is enough.
     *
     * Pattern (Day 3):
     * “Need fast ‘have I seen this before?’” → HashSet
     */
    public static boolean containsDuplicateSet(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int value : nums) {
            if (!seen.add(value)) {
                // add returns false when value was already present
                return true;
            }
        }
        return false;
    }
}
