package JavaQuestions.DSA;

/**
 * Even / Odd / Prime checks
 *
 * Even-Odd: O(1)
 * Prime brute: check divisors 2..n-1 → O(n)
 * Prime smart: check 2..√n → O(√n)
 * Prime smarter: skip even numbers after 2 → still O(√n) but fewer checks
 */
public class PrimeEvenOdd {

    public static void main(String[] args) {
        int number = 15;

        System.out.println(number + (isEven(number) ? " is Even." : " is Odd."));
        System.out.println(number + (isPrime(number) ? " is a Prime number." : " is not a Prime number."));

        System.out.println("Brute?      " + isPrimeBrute(number));
        System.out.println("Smart √n?   " + isPrime(number));
        System.out.println("Skip evens? " + isPrimeSkipEvens(number));
        System.out.println("6k±1?       " + isPrimeOptimized(number));
    }

    /** O(1) */
    public static boolean isEven(int number) {
        return number % 2 == 0;
        // alternative: return (number & 1) == 0; // bitwise, also O(1)
    }

    public static boolean isOdd(int number) {
        return !isEven(number);
    }

    /**
     * Brute force prime check
     * Trial division from 2 to n-1
     * Time: O(n)
     */
    public static boolean isPrimeBrute(int number) {
        if (number <= 1) return false;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    /**
     * Smart prime check (your approach)
     * If n has a factor > √n, the pair factor is already < √n
     * So checking to √n is enough
     * Time: O(√n)
     */
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    /**
     * Easier "optimized" version: skip even divisors
     * After checking 2, no need to test 4,6,8,... (even numbers can't divide an odd n usefully)
     * Loop: 3,5,7,9,... up to √n
     */
    public static boolean isPrimeSkipEvens(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false; // even > 2 → not prime

        for (int i = 3; (long) i * i <= number; i += 2) { // only odd i
            if (number % i == 0) return false;
        }
        return true;
    }

    /**
     * More optimized: 6k ± 1 trick
     *
     * Why?
     * Any integer is one of: 6k, 6k+1, 6k+2, 6k+3, 6k+4, 6k+5
     * - 6k, 6k+2, 6k+4 → divisible by 2
     * - 6k+3         → divisible by 3
     * So the ONLY forms that can still be prime (>3) are:
     *   6k+1 and 6k+5 (=6k-1)
     *
     * Example candidates: 5,7,11,13,17,19,23,25,...
     * In code: i=5, then check i and i+2 (5 and 7), then i+=6 → 11 and 13, etc.
     *
     * Dry-run number=29 (√29≈5.3, so i*i<=29 → i=5 only):
     * - not <=1
     * - not <=3
     * - 29%2 !=0, 29%3 !=0
     * - i=5: 29%5!=0, 29%7!=0
     * - next i=11, 11*11=121 > 29 → stop
     * → prime
     *
     * Dry-run number=25:
     * - i=5: 25%5==0 → NOT prime
     */
    public static boolean isPrimeOptimized(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true; // 2 and 3 are prime
        if (number % 2 == 0 || number % 3 == 0) return false; // divisible by 2 or 3

        // check only 6k±1 candidates as possible divisors
        for (int i = 5; (long) i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }
}
