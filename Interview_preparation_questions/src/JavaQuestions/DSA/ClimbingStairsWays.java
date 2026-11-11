package JavaQuestions.DSA;

import java.util.Arrays;

/**
 * Task: Count ways to climb `n` stairs if you can take 1 or 2 steps at a time.
 *
 * Example n=3:
 * 1+1+1
 * 1+2
 * 2+1
 * → 3 ways
 *
 * Key idea:
 * To reach stair n, last move was either:
 * - 1 step from (n-1), or
 * - 2 steps from (n-2)
 * So ways(n) = ways(n-1) + ways(n-2)   ← Fibonacci pattern
 *
 * Base:
 * ways(1) = 1
 * ways(2) = 2
 */
public class ClimbingStairsWays {

    public static void main(String[] args) {
        int n = 5;

        System.out.println("Naive recursive ways(" + n + ") = " + countWaysNaive(n));
        System.out.println("Memo ways(" + n + ")            = " + countWaysMemo(n));
        System.out.println("DP ways(" + n + ")              = " + countWaysDp(n));
        System.out.println("Optimized ways(" + n + ")       = " + countWaysOptimized(n));
        // old style link: fib(n+1) also gives ways(n) with fib(0)=0,fib(1)=1
        System.out.println("Via fib(n+1)                    = " + fibNaive(n + 1));
    }

    /**
     * Brute recursion
     * Time: O(2^n)  Space: O(n) stack
     *
     * This is NOT a for-loop. Each call waits for TWO smaller calls, then adds them.
     *
     * Full call tree for countWaysNaive(4):
     *
     *                     ways(5)
     *                    /       \
     *               ways(4)       ways(3)
     *              /      \       /     \
     *         ways(3)   ways(2) ways(2) ways(1)
     *         /    \       |      |       |
     *    ways(2) ways(1)   2      2       1
     *       |       |
     *       2       1
     * call ways(4)
     *   needs ways(3) and ways(2)
     *
     *   call ways(3)
     *     needs ways(2) and ways(1)
     *       call ways(2) → base → return 2
     *       call ways(1) → base → return 1
     *     ways(3) = 2 + 1 = 3  → return 3
     *
     *   call ways(2) → base → return 2
     *
     * ways(4) = 3 + 2 = 5 → return 5
     *
     * Order of finished returns:
     * 1) ways(2)=2
     * 2) ways(1)=1
     * 3) ways(3)=3
     * 4) ways(2)=2   (computed AGAIN)
     * 5) ways(4)=5
     *
     * That repeat of ways(2) is why naive is slow.
     */
    public static int countWaysNaive(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1; // only [1]
        if (n == 2) return 2; // [1,1] and [2]
        return countWaysNaive(n - 1) + countWaysNaive(n - 2);
    }

    /**
     * Smart top-down DP (memoization)
     * Time: O(n)  Space: O(n)
     *
     * Steps:
     * 1) Create memo[n+1], fill with -1 (unknown)
     * 2) Recurse like naive
     * 3) If memo[k] already known → reuse
     * 4) Else compute, store memo[k], return
     */
    public static int countWaysMemo(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return memoHelper(n, memo);
    }

    private static int memoHelper(int n, int[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (memo[n] != -1) return memo[n]; // already computed
        memo[n] = memoHelper(n - 1, memo) + memoHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Smart bottom-up DP
     * Time: O(n)  Space: O(n)
     *
     * Dry-run n=5:
     * dp[1]=1
     * dp[2]=2
     * dp[3]=dp[2]+dp[1]=2+1=3
     * dp[4]=3+2=5
     * dp[5]=5+3=8
     * return 8
     *
     * Meaning of dp[i]: number of ways to reach stair i
     */
    public static int countWaysDp(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // 1-step from i-1, or 2-step from i-2
        }
        return dp[n];
    }

    /**
     * Smartest space: only keep last 2 answers
     * Time: O(n)  Space: O(1)
     *
     * Steps for n=5:
     * a=ways1=1, b=ways2=2
     * i=3: next=2+1=3, a=2, b=3
     * i=4: next=3+2=5, a=3, b=5
     * i=5: next=5+3=8, a=5, b=8
     * return 8
     */
    public static int countWaysOptimized(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int a = 1; // ways(i-2)
        int b = 2; // ways(i-1)
        for (int i = 3; i <= n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    /** Same naive fib used in old CountStep (for comparison) */
    private static int fibNaive(int n) {
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }
}
