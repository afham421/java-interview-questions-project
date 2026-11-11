package JavaQuestions.DSA;

/**
 * Armstrong number:
 * sum of each digit raised to (number of digits) equals the number itself.
 *
 * Examples:
 * 153 → 1^3 + 5^3 + 3^3 = 153  ✅ (3 digits)
 * 9474 → 9^4 + 4^4 + 7^4 + 4^4 = 9474 ✅ (4 digits)
 * 123 → not Armstrong
 */
public class ArmstrongNumber {

    public static void main(String[] args) {
        int number = 153;

        System.out.println(number + (isArmstrong(number) ? " is Armstrong." : " is NOT Armstrong."));
        System.out.println(number + (isArmstrongNoMathPow(number) ? " is Armstrong (int power)." : " is NOT Armstrong."));
        System.out.println("371 → " + isArmstrongNoMathPow(371));
        System.out.println("123 → " + isArmstrongNoMathPow(123));
    }

    /**
     * General way (any digit length)
     * Uses Math.pow (returns double) — OK for small cases, but float precision can bite
     * Time: O(d) where d = number of digits
     *
     * Step meaning (example number = 153):
     * 1) original = 153          → keep a copy to compare at the end
     * 2) digits = 3              → 153 has 3 digits, so power = 3
     * 3) sum = 0                 → will collect digit^digits
     *
     * Loop extracts digits from right to left:
     *   pass1: digit=153%10=3, sum+=3^3=27, number=153/10=15
     *   pass2: digit=15%10=5,  sum+=5^3=125 → 152, number=15/10=1
     *   pass3: digit=1%10=1,   sum+=1^3=1   → 153, number=1/10=0  stop
     *
     * 4) return sum == original → 153 == 153 → true
     *
     * % 10  → last digit
     * / 10  → remove last digit
     */
    public static boolean isArmstrong(int number) {
        int original = number;           // 1) save original
        int digits = countDigits(number); // 2) how many digits? → power
        int sum = 0;                     // 3) running sum of digit^digits

        while (number > 0) {             // 4) process each digit
            int digit = number % 10;     //    get last digit
            sum += (int) Math.pow(digit, digits); // add digit^digits
            number /= 10;                //    remove last digit
        }
        return sum == original;          // 5) Armstrong if equal
    }

    /**
     * Smarter / safer way: integer power (no Math.pow double)
     * Also counts digits mathematically (no String.valueOf)
     * Time: O(d^2) worst with simple power, still fine for int digits
     * Space: O(1)
     */
    public static boolean isArmstrongNoMathPow(int number) {
        if (number < 0) return false;
        int original = number;
        int digits = countDigits(number);
        int sum = 0;

        while (number > 0) {
            int digit = number % 10;
            sum += power(digit, digits); // integer multiply, exact
            number /= 10;
        }
        return sum == original;
    }

    /** Count digits without converting to String — O(d) */
    private static int countDigits(int number) {
        if (number == 0) return 1;
        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    /** integer base^exp */
    private static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * Classic 3-digit-only version (often asked in beginners interviews)
     * 153 = 1^3 + 5^3 + 3^3
     */
    public static boolean isArmstrong3Digit(int number) {
        if (number < 100 || number > 999) return false;
        int original = number;
        int sum = 0;
        while (number > 0) {
            int d = number % 10;
            sum += d * d * d;
            number /= 10;
        }
        return sum == original;
    }
}
