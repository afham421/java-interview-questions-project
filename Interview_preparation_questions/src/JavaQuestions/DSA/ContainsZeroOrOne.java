package JavaQuestions.DSA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Task: Check if an array contains 0 or 1 (or both).
 * Return true if at least one element is 0 or 1, else false.
 *
 * Example:
 * [50, 77, 12, 54, -11] → false
 * [50, 77, 12, 54, -1, 1, -11] → true  (because of 1)
 */
public class ContainsZeroOrOne {

    public static void main(String[] args) {
        int[] arrayNums = {50, 77, 12, 54, -1, 1, -11};
        System.out.println("Original Array: " + Arrays.toString(arrayNums));
        System.out.println("Loop way:   " + containsZeroOrOne(arrayNums));
        System.out.println("Stream way: " + containsZeroOrOneStream(arrayNums));
        System.out.println("Set way:    " + containsZeroOrOneSet(arrayNums));
    }

    /**
     * Best / normal way for this problem: one linear scan
     * Time: O(n)  Space: O(1)
     *
     * Step-by-step for arr = [50, 77, 12, 54, -1, 1, -11]:
     * 1) Start looping each number
     * 2) number=50 → not 0 and not 1 → continue
     * 3) number=77 → continue
     * 4) number=12 → continue
     * 5) number=54 → continue
     * 6) number=-1 → continue  (note: -1 is NOT 1)
     * 7) number=1  → matches → return true immediately (early exit)
     *    (remaining -11 is never checked — good)
     *
     * If no 0/1 found till end → return false
     *
     * Why this is already "smart enough":
     * For an unsorted array, you MUST look at elements in worst case → O(n) is optimal.
     */
    public static boolean containsZeroOrOne(int[] numbers) {
        for (int number : numbers) {          // 1) visit each element
            if (number == 0 || number == 1) { // 2) check target values
                return true;                  // 3) found → stop early
            }
        }
        return false;                         // 4) never found
    }

    /**
     * Alternate style: Java Stream anyMatch
     * Same idea as loop, just shorter syntax
     * Time: O(n)  Space: O(1)
     */
    public static boolean containsZeroOrOneStream(int[] numbers) {
        return Arrays.stream(numbers).anyMatch(n -> n == 0 || n == 1);
        // anyMatch stops early when first match is found (like return true)
    }

    /**
     * Alternate: put array in HashSet, then check contains(0) or contains(1)
     * Time: O(n) to build set + O(1) lookups
     * Space: O(n)  ← worse space than simple loop
     *
     * Useful pattern when you check MANY values repeatedly.
     * For only checking 0 and 1 once, loop is better.
     */
    public static boolean containsZeroOrOneSet(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for (int number : numbers) {
            set.add(number);
        }
        return set.contains(0) || set.contains(1);
    }
}
