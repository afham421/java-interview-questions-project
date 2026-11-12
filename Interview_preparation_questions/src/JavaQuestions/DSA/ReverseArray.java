package JavaQuestions.DSA;

import java.util.Arrays;

/**
 * Task: Reverse an array.
 * Example: [1,2,3,4,5] → [5,4,3,2,1]
 *
 * Ways:
 * 1) Extra array (your way)     — O(n) time, O(n) space
 * 2) In-place two pointers      — O(n) time, O(1) space  ← smarter
 */
public class ReverseArray {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("Original:     " + Arrays.toString(array));
        System.out.println("Extra array:  " + Arrays.toString(reverseWithExtraArray(array)));

        // copy first because in-place method changes the same array
        int[] copy = Arrays.copyOf(array, array.length);
        reverseInPlace(copy);
        System.out.println("In-place:     " + Arrays.toString(copy));
    }

    /**
     * Your way: create a new array and fill from the end
     * Time: O(n)  Space: O(n)
     *
     * Steps for [1,2,3,4]:
     * reverse = [_, _, _, _]
     * i=3 → reverse[0]=4
     * i=2 → reverse[1]=3
     * i=1 → reverse[2]=2
     * i=0 → reverse[3]=1
     * result [4,3,2,1]
     *
     * Meaning:
     * - i walks original from right → left
     * - index walks new array from left → right
     */
    public static int[] reverseWithExtraArray(int[] array) {
        int[] reverse = new int[array.length];
        int index = 0;

        for (int i = array.length - 1; i >= 0; i--) {
            reverse[index] = array[i]; // copy last remaining into next empty slot
            index++;
        }
        return reverse; // original array is unchanged
    }

    /**
     * Smart way: two pointers, swap in place
     * Time: O(n)  Space: O(1) extra
     *
     * Idea:
     * left starts at 0, right starts at last index
     * swap array[left] and array[right]
     * move left++, right--
     * stop when left >= right (middle reached)
     *
     * Dry-run [1,2,3,4,5]:
     * left=0,right=4 → swap 1 and 5 → [5,2,3,4,1]
     * left=1,right=3 → swap 2 and 4 → [5,4,3,2,1]
     * left=2,right=2 → stop (center 3 stays)
     * result [5,4,3,2,1]
     *
     * Why smarter?
     * No second array needed → constant extra memory.
     */
    public static void reverseInPlace(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }
}
