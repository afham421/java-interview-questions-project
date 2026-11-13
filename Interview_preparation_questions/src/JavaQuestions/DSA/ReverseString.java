package JavaQuestions.DSA;

/**
 * Task: Reverse a string.
 * Example: "Hello" → "olleH"
 *
 * Ways:
 * 1) Loop + String +=          — easy, but slow O(n²) due to immutable String
 * 2) StringBuilder append/reverse — practical O(n)
 * 3) Two pointers on char[]    — interview favorite, O(n) time O(n) or in-place on array
 * 4) Recursion                 — good for learning recursion, O(n) time O(n) stack
 */
public class ReverseString {

    public static void main(String[] args) {
        String str = "Hello World!";
        System.out.println("Original:              " + str);
        System.out.println("Loop += :              " + reverseWithPlus(str));
        System.out.println("StringBuilder.reverse: " + reverseWithStringBuilder(str));
        System.out.println("Two pointers:          " + reverseTwoPointers(str));
        System.out.println("Recursion:             " + reverseRecursive(str));
    }

    /**
     * Way 1: walk from end → start, build new string with +=
     * Time: O(n²) roughly (each += may copy whole string)
     * Space: O(n)
     *
     * Steps for "ABC":
     * i=2 → reversed = "" + 'C' = "C"
     * i=1 → reversed = "C" + 'B' = "CB"
     * i=0 → reversed = "CB" + 'A' = "CBA"
     *
     * Works, but String is immutable → avoid += in loops for interviews/production.
     */
    public static String reverseWithPlus(String str) {
        String reversed = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }

    /**
     * Way 2: StringBuilder (built-in reverse)
     * Time: O(n)  Space: O(n)
     * Best practical one-liner style.
     */
    public static String reverseWithStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * Way 3 (smart interview way): two pointers on char array
     * Time: O(n)  Space: O(n) for the array (same idea as ReverseArray)
     *
     * Steps for "HELLO":
     * chars = [H,E,L,L,O]
     * left=0,right=4 → swap H↔O → [O,E,L,L,H]
     * left=1,right=3 → swap E↔L → [O,L,L,E,H]
     * left=2,right=2 → stop
     * result "OLLEH"
     */
    public static String reverseTwoPointers(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    /**
     * Way 4: recursion
     * Time: O(n)  Space: O(n) call stack
     *
     * Idea:
     * reverse("ABC") = reverse("BC") + 'A'
     * reverse("BC")  = reverse("C")  + 'B'
     * reverse("C")   = reverse("")   + 'C'
     * reverse("")    = ""   ← base case
     *
     * Then unwind:
     * "" + 'C' = "C"
     * "C" + 'B' = "CB"
     * "CB" + 'A' = "CBA"
     *
     * Meaning of: reverse(str.substring(1)) + str.charAt(0)
     * - take rest of string without first char
     * - reverse that rest
     * - put first char at the end
     */
    public static String reverseRecursive(String str) {
        if (str.isEmpty()) { // base case
            return str;
        }
        return reverseRecursive(str.substring(1)) + str.charAt(0);
    }
}
