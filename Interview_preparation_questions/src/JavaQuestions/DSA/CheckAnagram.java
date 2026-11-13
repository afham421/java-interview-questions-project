package JavaQuestions.DSA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Task: Check if two strings are anagrams.
 * Anagram = same characters with same frequencies (order can differ).
 *
 * Examples:
 * "listen" and "silent" → true
 * "hello" and "world" → false
 * "anagram" and "nagaram" → true
 *
 * Ways:
 * 1) Brute/smart-enough: sort both, compare        — O(n log n)
 * 2) Count array (26 letters)                      — O(n)     ← smart for a-z
 * 3) HashMap frequency                             — O(n)     ← smart for general chars
 */
public class CheckAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagramBySort("listen", "silent"));     // true
        System.out.println(isAnagramByCount("anagram", "nagaram"));   // true
        System.out.println(isAnagramByHashMap("hello", "world"));     // false
        System.out.println(isAnagramByCount("Listen", "Silent"));     // true (ignore case)
    }

    /**
     * Way 1: Sort both strings, then equals
     * Time: O(n log n)  Space: O(n) for char arrays
     *
     * Steps for "listen" / "silent":
     * 1) convert to char arrays
     * 2) sort → "eilnst" and "eilnst"
     * 3) arrays equal? → true
     *
     * Why it works:
     * If frequencies match, sorted versions become identical.
     */
    public static boolean isAnagramBySort(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        if (a.length() != b.length()) return false; // different length → impossible

        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        return Arrays.equals(arrA, arrB);
    }

    /**
     * Way 2 (smart for a-z): int[26] frequency count
     * Time: O(n)  Space: O(1) (fixed 26)
     *
     * Think of count[26] as 26 boxes, one for each letter:
     * index: 0=a, 1=b, 2=c, ... 25=z
     *
     * Why `c - 'a'`?
     * 'a' - 'a' = 0
     * 'b' - 'a' = 1
     * 'c' - 'a' = 2
     * ...
     * So letter becomes array index.
     *
     * Process (same index i for both strings):
     * - take letter from a → ++ (add one)
     * - take letter from b → -- (remove one)
     *
     * If both strings use the same letters same times,
     * every ++ is cancelled by a -- → all zeros → anagram.
     *
     * Tiny dry-run a="ab", b="ba":
     * start: [0,0,0,...]
     * i=0: a++ → count[a]=1 ; b-- → count[b]=-1
     *       now a=1, b=-1
     * i=1: b++ → count[b]=0  ; a-- → count[a]=0
     *       now a=0, b=0 → true
     *
     * Fail dry-run a="ab", b="ac":
     * i=0: a++ , a-- → a=0
     * i=1: b++ (=1), c-- (=-1) → not all zero → false
     *
     * Full dry-run a="listen", b="silent":
     * letters that matter: e i l n s t
     *
     * i | listen(++) | silent(--) | counts after step
     * --|------------|------------|------------------
     * 0 | l++ (l=1)  | s-- (s=-1) | l=1, s=-1
     * 1 | i++ (i=1)  | i-- (i=0)  | l=1, s=-1, i=0
     * 2 | s++ (s=0)  | l-- (l=0)  | l=0, s=0, i=0
     * 3 | t++ (t=1)  | e-- (e=-1) | t=1, e=-1
     * 4 | e++ (e=0)  | n-- (n=-1) | t=1, n=-1
     * 5 | n++ (n=0)  | t-- (t=0)  | all 0
     *
     * Final check: every box is 0 → true (anagram)
     * Because both strings use e,i,l,n,s,t exactly once each.
     */
    public static boolean isAnagramByCount(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        if (a.length() != b.length()) return false;

        int[] count = new int[26]; // boxes for a..z

        for (int i = 0; i < a.length(); i++) {
            // How count[a.charAt(i) - 'a']++ works:
            // count is 26 slots: index 0=a, 1=b, ... 11=l, ... 25=z (all start at 0)
            // Example a="listen", i=0:
            //   1) a.charAt(0) → 'l'
            //   2) 'l' - 'a' → 11  (find slot index)
            //   3) count[11]++ → count[11] = count[11] + 1
            // It does NOT insert the character; it only increases that letter's number.
            count[a.charAt(i) - 'a']++; // add letter from first string
            count[b.charAt(i) - 'a']--; // remove letter from second string (same idea with --)
        }

        for (int c : count) {
            if (c != 0) return false; // leftover means frequencies differ
        }
        return true;
    }

    /**
     * Way 3 (smart general): HashMap frequency
     * Time: O(n)  Space: O(k) unique chars
     *
     * Same idea as count array, but works beyond a-z (unicode/symbols too).
     *
     * Steps:
     * 1) count chars of first string in map
     * 2) for second string, decrease counts
     * 3) if any key would go below 0 / missing → false
     * 4) all zero → true
     */
    public static boolean isAnagramByHashMap(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        if (a.length() != b.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : a.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : b.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) < 0) return false;
        }
        return true;
    }
}
