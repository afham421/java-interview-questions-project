package JavaQuestions.DSA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Task A (frequency): Count how many times each value appears.
 * Task B (majority):  Find the element that appears more than n/2 times.
 *                     (LeetCode 169 — majority is guaranteed to exist in classic version)
 *
 * Example: [3, 2, 3] → frequency {3:2, 2:1}, majority = 3  (2 > 3/2)
 *          [2, 2, 1, 1, 1, 2, 2] → majority = 2
 *
 * Day 3 practice: Majority / frequency of elements
 *
 * Ways:
 * 1) Brute: for each value, rescan array to count     — O(n²)
 * 2) HashMap frequency count                          — O(n)  ← Day 3 pattern
 * 3) (Bonus) Boyer-Moore voting for majority only     — O(n) time, O(1) space
 *
 * Pattern:
 * “Need to count / find most frequent?” → HashMap
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};

        System.out.println("nums = " + Arrays.toString(nums));
        System.out.println("Freq brute:   " + frequencyBrute(nums));
        System.out.println("Freq HashMap: " + frequencyHashMap(nums));
        System.out.println("Majority brute:   " + majorityBrute(nums));   // 3
        System.out.println("Majority HashMap: " + majorityHashMap(nums)); // 3
        System.out.println("Majority Boyer:   " + majorityBoyerMoore(nums)); // 3

        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println();
        System.out.println("nums = " + Arrays.toString(nums2));
        System.out.println("Freq HashMap: " + frequencyHashMap(nums2));
        System.out.println("Majority HashMap: " + majorityHashMap(nums2)); // 2
        System.out.println("Majority Boyer:   " + majorityBoyerMoore(nums2)); // 2
    }

    /**
     * Way 1a: Frequency — brute
     * Time: O(n²)  Space: O(k) for result map (k = unique values)
     *
     * Idea:
     * For each index i, if we have not counted this value yet,
     * scan whole array and count how many times it appears.
     *
     * Dry-run [3, 2, 3]:
     * i=0 value 3 → scan → appears 2 times → {3:2}
     * i=1 value 2 → not counted → scan → appears 1 time → {3:2, 2:1}
     * i=2 value 3 → already in map → skip
     *
     * Repeated work?
     * Same value gets rescanned from scratch → HashMap fixes this.
     */
    public static Map<Integer, Integer> frequencyBrute(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (freq.containsKey(nums[i])) {
                continue; // already counted this value
            }
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            freq.put(nums[i], count);
        }
        return freq;
    }

    /**
     * Way 2a: Frequency — HashMap (smart)
     * Time: O(n)  Space: O(k)
     *
     * Idea:
     * One pass: for each value, freq[value] = freq.getOrDefault(value, 0) + 1
     *
     * Dry-run [3, 2, 3]:
     * 3 → {3:1}
     * 2 → {3:1, 2:1}
     * 3 → {3:2, 2:1}
     */
    public static Map<Integer, Integer> frequencyHashMap(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int value : nums) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }
        return freq;
    }

    /**
     * Way 1b: Majority — brute (using nested count)
     * Time: O(n²)  Space: O(1)
     *
     * Majority = value that appears more than n/2 times.
     *
     * Dry-run [3, 2, 3], n=3, need > 1:
     * count of 3 = 2 → majority
     */
    public static int majorityBrute(int[] nums) {
        int need = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > need) {
                return nums[i];
            }
        }
        return -1; // should not happen if majority is guaranteed
    }

    /**
     * Way 2b: Majority — HashMap frequency, then find count > n/2
     * Time: O(n)  Space: O(k)
     *
     * Steps:
     * 1) build frequency map
     * 2) walk entries; return key whose count > n/2
     */
    public static int majorityHashMap(int[] nums) {
        Map<Integer, Integer> freq = frequencyHashMap(nums);
        int need = nums.length / 2;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > need) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * Way 3 (bonus interview): Boyer-Moore Voting
     * Time: O(n)  Space: O(1)
     *
     * Idea (only for majority > n/2):
     * Keep a candidate and a count.
     * Same as candidate → count++
     * Different → count--
     * When count hits 0 → pick new candidate.
     * At the end, candidate is the majority (if one is guaranteed).
     *
     * Dry-run [2, 2, 1, 1, 1, 2, 2]:
     * 2 → cand=2, count=1
     * 2 → count=2
     * 1 → count=1
     * 1 → count=0
     * 1 → cand=1, count=1
     * 2 → count=0
     * 2 → cand=2, count=1  → majority 2
     *
     * Learn HashMap first (Day 3). Remember Boyer-Moore as O(1) space upgrade.
     */
    public static int majorityBoyerMoore(int[] nums) {
        int candidate = 0;
        int count = 0;
        for (int value : nums) {
            if (count == 0) {
                candidate = value;
            }
            count += (value == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
