package JavaQuestions.DSA;

import java.util.Arrays;

/**
 * Product of Array Except Self
 * For each index i, result[i] = product of all elements except arr[i]
 * Example: [1, 2, 3, 4] → [24, 12, 8, 6]
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};

        System.out.println(Arrays.toString(productBruteForce(arr))); // [24, 12, 8, 6]
        System.out.println(Arrays.toString(productSmart(arr)));      // [24, 12, 8, 6]
    }

    /**
     * Brute force: for each i, multiply all j != i
     * Time: O(n²)  Space: O(n)
     */
    public static int[] productBruteForce(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int mul = 1;
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    mul *= arr[j];
                }
            }
            result[i] = mul;
        }
        return result;
    }

    /**
     * Smart way: prefix * suffix
     * result[i] = (product of left of i) * (product of right of i)
     * Time: O(n)  Space: O(n) for result (no division needed)
     *
     * Pass 1 → fill left products into result
     * Pass 2 → multiply by right products while scanning from end
     */
    public static int[] productSmart(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        // left products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * arr[i - 1];
        }
        // After pass 1, result[i] = product of everything LEFT of i
        // arr:    [1, 2, 3, 4]
        // result: [1, 1, 2, 6]
        //          ^  ^  ^  ^
        //          |  |  |  +-- 1*2*3
        //          |  |  +----- 1*2
        //          |  +-------- 1
        //          +----------- (nothing on left)

        // Pass 2: walk RIGHT → LEFT.
        // `right` = running product of everything to the RIGHT of current i
        // At each i: result[i] already has LEFT product → multiply by RIGHT product
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= right; // LEFT * RIGHT
            right *= arr[i];    // include arr[i] for the next index on the left
        }
        // Step-by-step for [1,2,3,4]:
        // i=3: result[3]=6*1=6,   right=1*4=4
        // i=2: result[2]=2*4=8,   right=4*3=12
        // i=1: result[1]=1*12=12, right=12*2=24
        // i=0: result[0]=1*24=24, right=24*1=24
        // final result: [24, 12, 8, 6]
        return result;
    }
}
