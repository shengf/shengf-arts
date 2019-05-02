package threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shengfei
 * # Leetcode #15, Medium
 */
public class ThreeSum {


    public static void main(String[] args) {
        List<List<Integer>> resultList = ThreeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(resultList);
        resultList = ThreeSum.threeSum(new int[]{-4, -1, -1, 0, 1, 2});
        System.out.println(resultList);
        resultList = ThreeSum.threeSum(new int[]{-4, -1, -1, -1, 0, 1, 2});
        System.out.println(resultList);
        resultList = ThreeSum.threeSum(new int[]{-4, -1, -1, 0, 1, 1, 2});
        System.out.println(resultList);
        resultList = ThreeSum.threeSum(new int[]{-4, -1, -1});
        System.out.println(resultList);

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            return null;
        }

        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if ((i == 0) || (i > 0 && nums[i] != nums[i -1])) {
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> itemList = new ArrayList<>();
                        itemList.add(nums[i]);
                        itemList.add(nums[j]);
                        itemList.add(nums[k]);
                        resultList.add(itemList);
                        while (j < k && nums[j+1] == nums[j]) {
                            j++;
                        }
                        while (j < k && nums[k-1] == nums[k]) {
                            k--;
                        }
                        j++;
                        k--;
                    } else if (nums[i] + nums[j] + nums[k] > 0) {
                        k--;
                    } else {
                        j++;
                    }

                }
            }
        }
        return resultList;
    }

    /**
     * 时间复杂度O(n^2), 空间复杂度O(1)
     */
    public static List<List<Integer>> threeSum3(int[] nums) {
        if (nums == null) {
            return null;
        }

        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        Set<List<Integer>> resultSet = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> itemList = new ArrayList<>();
                    itemList.add(nums[i]);
                    itemList.add(nums[j]);
                    itemList.add(nums[k]);
                    resultSet.add(itemList);
                    j++;
                    k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    j++;
                }

            }
        }
        List<List<Integer>> resultList = new ArrayList<>(resultSet);
        return resultList;
    }

    /**
     * 时间复杂度O(n^2), 空间复杂度O(n)
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null) {
            return null;
        }

        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        Set<List<Integer>> resultSet = new HashSet<>();
        Set<Integer> set;

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            set = new HashSet<>();
            int left = 0 - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (set.contains(left - nums[j])) {
                    List<Integer> itemList = new ArrayList<>();
                    itemList.add(nums[i]);
                    itemList.add(left - nums[j]);
                    itemList.add(nums[j]);
                    resultSet.add(itemList);
                }
                set.add(nums[j]);
            }

        }
        List<List<Integer>> resultList = new ArrayList<>(resultSet);
        return resultList;
    }

}
