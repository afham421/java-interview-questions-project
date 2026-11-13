package JavaQuestions.DSA;

import java.util.Set;

/**
 * Task: Count vowels in a string (a, e, i, o, u — both cases).
 * Example: "Javaeiou" → a,a,e,i,o,u → 6
 *
 * Ways:
 * 1) Loop + if conditions
 * 2) Loop + vowel set lookup (cleaner)
 * 3) Stream filter count
 *
 * Note: anyMatch only checks "is there at least one vowel?" — it does NOT count.
 */
public class CountVowels {

    public static void main(String[] args) {
        String s = "Javaeiou";
        System.out.println("Input: " + s);
        System.out.println("If-way count:   " + countVowelsIf(s));   // 6
        System.out.println("Set-way count:  " + countVowelsSet(s));  // 6
        System.out.println("Stream count:   " + countVowelsStream(s)); // 6
        System.out.println("Has vowel?:     " + hasVowel(s)); // true
    }

    /**
     * Way 1: visit each char, check with if
     * Time: O(n)  Space: O(1)
     *
     * Steps for "Javaeiou":
     * J → no
     * a → count=1
     * v → no
     * a → count=2
     * e → 3
     * i → 4
     * o → 5
     * u → 6
     * return 6
     *
     * toLowerCase on char so 'A' and 'a' both count.
     */
    public static int countVowelsIf(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    /**
     * Way 2 (smart/clean): put vowels in a Set, check contains
     * Time: O(n)  Space: O(1) for fixed vowel set
     *
     * Same loop, but membership test is cleaner:
     * if (vowels.contains(c)) count++;
     */
    public static int countVowelsSet(String s) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (vowels.contains(c)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Way 3: stream style
     * Time: O(n)  Space: O(1) extra (stream pipeline)
     */
    public static int countVowelsStream(String s) {
        return (int) s.chars()
                .map(Character::toLowerCase)
                .filter(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                .count();
    }

    /**
     * Related (NOT counting): is there at least one vowel?
     * anyMatch stops at first vowel.
     */
    public static boolean hasVowel(String s) {
        return s.chars()
                .map(Character::toLowerCase)
                .anyMatch(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
}
