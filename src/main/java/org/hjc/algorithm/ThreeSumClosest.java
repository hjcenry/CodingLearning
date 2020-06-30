package org.hjc.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最接近的三数之和
 *
 * @author 何金成
 * @date 2020/6/29 21:20
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
//        System.out.println(new ThreeSumClosest().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(new ThreeSumClosest().threeSumClosest(new int[]{1, 1, 1, 0}, 100));
    }

    /*
    Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

     

    Example 1:

    Input: nums = [-1,2,1,-4], target = 1
    Output: 2
    Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     

    Constraints:

    3 <= nums.length <= 10^3
    -10^3 <= nums[i] <= 10^3
    -10^4 <= target <= 10^4

    给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

     

    示例：

    输入：nums = [-1,2,1,-4], target = 1
    输出：2
    解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     

    提示：

    3 <= nums.length <= 10^3
    -10^3 <= nums[i] <= 10^3
    -10^4 <= target <= 10^4

    url:https://leetcode-cn.com/problems/3sum-closest/
    */

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        // 最接近的和
        int closedSum = -1;
        // 最接近的差值
        int closedDiff = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重
            }
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == target) {
                    return sum;
                }
                int diff = sum - target;
                int absDiff = Math.abs(diff);
                if (closedDiff == -1 || absDiff < closedDiff) {
                    closedDiff = absDiff;
                    closedSum = sum;
                }
                if (diff > 0) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return closedSum;
    }
}
