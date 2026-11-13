package JavaQuestions.DSA;

/**
 * Task: Count whitespace, uppercase, lowercase, and other characters in a string.
 *
 * Example: "Hi Java 123!"
 * spaces=1, upper=2 (H,J), lower=4 (i,a,v,a), other=5 ( ,1,2,3,! wait space counted separate)
 * Actually: "Hi Java 123!"
 * H i [space] J a v a [space] 1 2 3 !
 * spaces=2, upper=2, lower=4, other=5 → total 13
 *
 * Ways:
 * 1) ASCII range checks (your custom logic)
 * 2) Character.isXxx methods (smarter/readable, handles more unicode cases)
 */
public class CountCharCategories {

    public static void main(String[] args) {
        String s = "Hi Java 123!";
        System.out.println("Input: " + s);

        int[] ascii = countByAscii(s);
        int[] builtin = countByCharacterApi(s);

        print("ASCII way", ascii);
        print("Character API way", builtin);
    }

    /**
     * Your way: compare using ASCII codes / char ranges
     * Time: O(n)  Space: O(1)
     *
     * ASCII reminders:
     * space ' ' = 32
     * 'A'..'Z'  = 65..90
     * 'a'..'z'  = 97..122
     *
     * Steps for "Ab 1":
     * i=0 'A' → upper (65..90)
     * i=1 'b' → lower (97..122)
     * i=2 ' ' → space (32)
     * i=3 '1' → other
     * → spaces=1, upper=1, lower=1, other=1
     *
     * Note: c == 32 only counts normal space, not tab/newline.
     */
    public static int[] countByAscii(String s) {
        int spaces = 0, upper = 0, lower = 0, other = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ' ') {                 // same as c == 32
                spaces++;
            } else if (c >= 'A' && c <= 'Z') { // same as 65..90
                upper++;
            } else if (c >= 'a' && c <= 'z') { // same as 97..122
                lower++;
            } else {
                other++; // digits, punctuation, etc.
            }
        }
        return new int[]{spaces, upper, lower, other};
    }

    /**
     * Smart / safer way: use Java Character helpers
     * Time: O(n)  Space: O(1)
     *
     * Why smarter?
     * - more readable
     * - isWhitespace covers space, tab, newline, etc.
     * - better for real text (not only basic English ASCII)
     *
     * Same loop idea: visit each char once and classify.
     */
    public static int[] countByCharacterApi(String s) {
        int spaces = 0, upper = 0, lower = 0, other = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isWhitespace(c)) {
                spaces++;
            } else if (Character.isUpperCase(c)) {
                upper++;
            } else if (Character.isLowerCase(c)) {
                lower++;
            } else {
                other++;
            }
        }
        return new int[]{spaces, upper, lower, other};
    }

    private static void print(String title, int[] counts) {
        System.out.println(title + ":");
        System.out.println("  spaces    = " + counts[0]);
        System.out.println("  uppercase = " + counts[1]);
        System.out.println("  lowercase = " + counts[2]);
        System.out.println("  other     = " + counts[3]);
    }
}
