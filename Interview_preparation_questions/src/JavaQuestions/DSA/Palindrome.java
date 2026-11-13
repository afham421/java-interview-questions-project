package JavaQuestions.DSA;

/**
 * Task: Check if a string is a palindrome (reads same forward and backward).
 * Examples:
 * "myym" → true
 * "1221" → true
 * "hello" → false
 * "A man, a plan, a canal: Panama" → true (if we ignore spaces/punctuation/case)
 *
 * Ways:
 * 1) Reverse whole string and compare  — O(n) time, O(n) space
 * 2) Two pointers                      — O(n) time, O(1) extra space  ← smarter
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println("myym  reverse-way : " + isPalindromeByReverse("myym"));
        System.out.println("1221  two-pointers: " + isPalindromeTwoPointers("1221"));
        System.out.println("hello two-pointers: " + isPalindromeTwoPointers("hello"));
        System.out.println("Phrase cleaned    : " +
                isPalindromeCleaned("A man, a plan, a canal: Panama"));
        System.out.println("Number 1221       : " + isPalindromeNumber(1221));
    }

    /**
     * Way 1: build reverse, then equals
     * Time: O(n)  Space: O(n)
     *
     * Steps for "myym":
     * 1) original = "myym"
     * 2) reverse by reading end → start:
     *    i=3 → "m"
     *    i=2 → "my"
     *    i=1 → "myy"
     *    i=0 → "myym"
     * 3) original.equals(reverse) → "myym".equals("myym") → true
     *
     * Meaning: palindrome = string equals its reverse.
     */
    public static boolean isPalindromeByReverse(String s) {
        StringBuilder reverse = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            reverse.append(s.charAt(i));
        }
        return s.equals(reverse.toString());
    }

    /**
     * Way 2 (smart): two pointers from both ends
     * Time: O(n)  Space: O(1)
     *
     * Idea:
     * left starts at first char, right at last char
     * if they differ → not palindrome
     * else move inward (left++, right--) until they meet
     *
     * Dry-run "1221":
     * left=0 ('1'), right=3 ('1') → equal
     * left=1 ('2'), right=2 ('2') → equal
     * left=2, right=1 → stop (crossed)
     * → true
     *
     * Dry-run "hello":
     * left='h', right='o' → not equal → false
     *
     * Why smarter?
     * No need to build a full reversed copy.
     */
    public static boolean isPalindromeTwoPointers(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Variant: ignore non-alphanumeric + case (common interview follow-up)
     * Clean first, then two pointers.
     *
     * "A man, a plan, a canal: Panama"
     * → cleaned "amanaplanacanalpanama" → true
     */
    public static boolean isPalindromeCleaned(String s) {
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return isPalindromeTwoPointers(cleaned);
    }

    /**
     * Number palindrome (optional)
     * 1221 → true, 123 → false
     * Convert to String then reuse two-pointer logic.
     */
    public static boolean isPalindromeNumber(int num) {
        return isPalindromeTwoPointers(String.valueOf(num));
    }
}
