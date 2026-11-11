package JavaQuestions.DSA;

import java.util.Arrays;

/**
 * Fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, ...
 * F(0)=0, F(1)=1, F(n)=F(n-1)+F(n-2)
 *
 * Ways:
 * 1) Iterative series      — O(n) time, O(1) space  ← best for printing series
 * 2) Naive recursive nth   — O(2^n) time            ← slow, interview "brute"
 * 3) Memoization (smart)   — O(n) time, O(n) space  ← top-down DP
 * 4) Bottom-up DP (smart)  — O(n) time, O(n) or O(1) space
 */
public class Fibonacci {

    public static void main(String[] args) {
        int num = 11; // print first 10 Fibonacci numbers / compute F(9) style demos

        System.out.print("Iterative series: ");
        printFibonacciIterative(num); // 0 1 1 2 3 5 8 13 21 34

        System.out.println();
        System.out.println("Bottom-up O(1) F(10):  " + fibOptimized(10));   // 55
        System.out.println("Naive recursive F(10): " + fibNaive(10));       // 55
        System.out.println("Memoization F(10):     " + fibMemo(10));        // 55
        System.out.println("Bottom-up DP F(10):    " + fibDp(10));          // 55
    }

    /**
     * Iterative: print first `num` Fibonacci numbers
     * Time: O(n)  Space: O(1)
     */
    public static void printFibonacciIterative(int num) {
        int a = 0, b = 1;
        for (int i = 0; i < num; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }

    /**
     * Smartest space: only keep last 2 values
     * Time: O(n)  Space: O(1)
     */
    public static long fibOptimized(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    /**
     * Brute recursive: F(n) = F(n-1) + F(n-2)
     * Time: O(2^n)  Space: O(n) stack
     * Slow because same values are recomputed many times
     *
     * Meaning in plain words:
     * - If n is 0 or 1, answer is n itself (base case — stop recursion)
     * - Otherwise answer = fib(n-1) + fib(n-2)
     *   i.e. "go solve smaller problems, then add those answers"
     *
     * Dry-run fibNaive(4):
     *
     *                    fib(4)
     *                   /      \
     *               fib(3)      fib(2)
     *              /     \      /    \
     *          fib(2)  fib(1) fib(1) fib(0)
     *          /    \
     *      fib(1) fib(0)
     *
     * Base returns: fib(1)=1, fib(0)=0
     * So fib(2)=1+0=1
     *    fib(3)=1+1=2
     *    fib(4)=2+1=3
     *
     * Notice fib(2) is calculated more than once → wasted work (why memo helps)
     */
    public static long fibNaive(int n) {
        if (n <= 1) return n; // base: fib(0)=0, fib(1)=1
        return fibNaive(n - 1) + fibNaive(n - 2); // wait for both smaller answers, then add
    }

    /**
     * Smart top-down DP (Memoization): same recursion as naive, but SAVE answers
     * Time: O(n)  Space: O(n)
     *
     * Idea:
     * - Keep an array memo[] as a notebook
     * - memo[i] = -1 means "not calculated yet"
     * - Once fib(i) is calculated, store it in memo[i]
     * - Next time someone asks fib(i), return memo[i] instantly (no recompute)
     *
     * Dry-run fibMemo(4):
     * memo starts: [-1, -1, -1, -1, -1]
     *
     * fib(4) needs fib(3) and fib(2)
     *   fib(3) needs fib(2) and fib(1)
     *     fib(2) needs fib(1)+fib(0) = 1+0 = 1  → save memo[2]=1
     *     fib(1)=1
     *   fib(3)=1+1=2 → save memo[3]=2
     *   fib(2) asked again → already memo[2]=1 (NO second tree)
     * fib(4)=2+1=3 → save memo[4]=3
     *
     * Each n from 0..4 computed at most once → O(n)
     */
    public static long fibMemo(int n) {
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1); // -1 = unknown
        return fibMemoHelper(n, memo);
    }

    private static long fibMemoHelper(int n, long[] memo) {
        if (n <= 1) return n;                 // base
        if (memo[n] != -1) return memo[n];    // already known → reuse
        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Smart bottom-up DP: build answers from small → big (no recursion)
     * Time: O(n)  Space: O(n)
     *
     * Idea:
     * - Make dp[] where dp[i] means Fibonacci(i)
     * - You already know dp[0]=0, dp[1]=1
     * - For i=2..n: dp[i] = dp[i-1] + dp[i-2]
     * - Finally return dp[n]
     *
     * Dry-run fibDp(5):
     * start: dp[0]=0, dp[1]=1
     * i=2: dp[2]=dp[1]+dp[0]=1+0=1   → [0,1,1]
     * i=3: dp[3]=1+1=2               → [0,1,1,2]
     * i=4: dp[4]=2+1=3               → [0,1,1,2,3]
     * i=5: dp[5]=3+2=5               → [0,1,1,2,3,5]
     * return 5
     *
     * Difference vs Memo:
     * - Memo  = top-down (start from n, go down, cache on the way)
     * - DP    = bottom-up (start from 0,1, fill up to n)
     * Same O(n) idea: compute each value once.
     */
    public static long fibDp(int n) {
        if (n <= 1) return n;
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // use already known smaller answers
        }
        return dp[n];
    }


}
