package twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shengfei
 * # LeetCode #1, Easy
 */
public class TwoSum {

    /**
     * 时间复杂度O(n), 空间复杂度O(n)
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            } else {
                map.put(num, i);
            }
        }
        throw new IllegalArgumentException("No solution");
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(TwoSum.twoSum(new int[]{1, -1, 0}, 0)));
        System.out.println(Arrays.toString(TwoSum.twoSum(new int[]{1, 1, 0}, 0)));
        System.out.println(Arrays.toString(TwoSum.twoSum(new int[]{0}, 0)));
    }

}