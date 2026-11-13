package JavaQuestions.DSA;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Task: Find the first non-repeating character/element.
 *
 * String example: "aabbcde" → 'c' (a,b repeat; c is first unique)
 * Array example:  {1,8,1,8,6,9} → 6
 *
 * Bug in old code:
 * - looped frequencies (`for (int r : count)`) and did `result = r`
 * - so it stored count value 1, NOT the actual number 6
 * - also must scan original order, not the count array order
 */
public class FirstNonRepeatingCharacter {

    public static void main(String[] args) {
        System.out.println("String brute:  " + firstNonRepeatingBrute("aabbcde")); // c
        System.out.println("String smart:  " + firstNonRepeatingMap("aabbcde"));   // c

        int[] arr = {6, 8, 7, 8, 6, 9};
        System.out.println("Array smart:   " + firstNonRepeatingElement(arr));     // 6
    }

    /**
     * Brute force for string
     * Time: O(n²)  Space: O(1)
     *
     * Steps for "aabbcde":
     * For each char, count how many times it appears in whole string.
     * 'a' appears 2 → skip
     * 'b' appears 2 → skip
     * 'c' appears 1 → return 'c'
     */
    public static Character firstNonRepeatingBrute(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == c) count++;
            }
            if (count == 1) return c;
        }
        return null; // none
    }

    /**
     * Smart way: HashMap frequency, then scan string in order
     * Time: O(n)  Space: O(k) unique chars
     *
     * Steps for "aabbcde":
     * 1) count map: a=2, b=2, c=1, d=1, e=1
     * 2) walk string left→right:
     *    a → freq 2
     *    a → freq 2
     *    b → freq 2
     *    b → freq 2
     *    c → freq 1 → return 'c'   (first unique in order)
     *
     * Why second pass on original string?
     * Because "first" means earliest index, not map iteration order.
     */
    public static Character firstNonRepeatingMap(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq.get(s.charAt(i)) == 1) {
                return s.charAt(i);
            }
        }
        return null;
    }

    /**
     * Same idea for int array
     * Example: {1,8,1,8,6,9}
     *
     * count: 1→2, 8→2, 6→1, 9→1
     * scan array: 1(no), 8(no), 1(no), 8(no), 6(yes) → 6
     *
     * Old wrong logic returned 1 because it saved frequency, not value.
     */
    public static Integer firstNonRepeatingElement(int[] arr) {
        Map<Integer, Integer> freq = new LinkedHashMap<>(); // optional; order not required if we rescan arr
        for (int x : arr) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        for (int x : arr) { // IMPORTANT: scan original array for "first"
            if (freq.get(x) == 1) {
                return x;
            }
        }
        return null;
    }
}
