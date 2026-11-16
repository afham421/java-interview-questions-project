package JavaQuestions.DSA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Task: Given nums and target, return indices of TWO numbers that add up to target.
 * Assume exactly one solution; you may not use the same element twice.
 *
 * Example: nums = [2, 7, 11, 15], target = 9 → [0, 1]  (because 2+7=9)
 *
 * LeetCode: 1. Two Sum
 * Day 3 practice: brute O(n²), then HashMap O(n)
 *
 * Ways:
 * 1) Brute nested loops     — O(n²) time, O(1) space
 * 2) HashMap one pass       — O(n) time, O(n) space  ← interview favorite
 *
 * Pattern:
 * “Need complement fast?” → HashMap (value → index)
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println("nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("Brute:   " + Arrays.toString(twoSumBrute(nums, target)));   // [0, 1]
        System.out.println("HashMap: " + Arrays.toString(twoSumHashMap(nums, target))); // [0, 1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println();
        System.out.println("nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Brute:   " + Arrays.toString(twoSumBrute(nums2, target2)));   // [1, 2]
        System.out.println("HashMap: " + Arrays.toString(twoSumHashMap(nums2, target2))); // [1, 2]
    }

    /**
     * Way 1: Brute force (start here)
     * Time: O(n²)  Space: O(1)
     *
     * Idea:
     * Try every pair (i, j) with j > i.
     * If nums[i] + nums[j] == target → return {i, j}.
     *
     * Dry-run [2, 7, 11, 15], target 9:
     * i=0 (2):
     *   j=1 → 2+7=9  → found [0, 1]
     *
     * Why slow?
     * Nested loops check almost every pair.
     *
     * Repeated work?
     * For each i we scan the rest looking for (target - nums[i]).
     * That lookup is what HashMap makes O(1).
     */
    public static int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // no solution (problem usually guarantees one)
    }

    /**
     * Way 2: HashMap (smart / usual interview answer)
     * Time: O(n)  Space: O(n)
     *
     * Idea:
     * For current value x, we need complement = target - x.
     * Keep a map of already-seen numbers → their index.
     * If complement is already in the map → we found the pair.
     *
     * Dry-run [2, 7, 11, 15], target 9:
     * i=0, x=2, need=7  → map empty → put 2→0   map={2:0}
     * i=1, x=7, need=2  → map has 2 at index 0 → return [0, 1]
     *
     * Dry-run [3, 2, 4], target 6:
     * i=0, x=3, need=3  → not seen yet → put 3→0
     * i=1, x=2, need=4  → no → put 2→1
     * i=2, x=4, need=2  → map has 2 at index 1 → return [1, 2]
     *
     * Why not put before checking?
     * So we never use the same index twice (e.g. target=6, nums=[3,3] is fine with two 3s;
     * but [3] alone must not pair with itself).
     *
     * Pattern (Day 3):
     * Brute checks every pair → HashMap remembers what we already saw.
     */
    public static int[] twoSumHashMap(int[] nums, int target) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (valueToIndex.containsKey(need)) {
                return new int[]{valueToIndex.get(need), i};
            }
            valueToIndex.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
