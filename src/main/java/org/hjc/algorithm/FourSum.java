package org.hjc.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 *
 * @author 何金成
 * @date 2020/8/3 16:06
 */
public class FourSum {

    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum2(new int[]{1, 0, -1, 0, -2, 2}, 0));
        System.out.println(new FourSum().fourSum2(new int[]{-5, 5, 4, -3, 0, 0, 4, -2}, 4));
        System.out.println(new FourSum().fourSum2(new int[]{5, 5, 3, 5, 1, -5, 1, -2}, 4));
        System.out.println(new FourSum().fourSum2(new int[]{-1, 2, 2, -5, 0, -1, 4}, 3));
    }

    /*
    Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

    Note:

    The solution set must not contain duplicate quadruplets.

    Example:

    Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

    A solution set is:
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
    ]

    给定一个包含  n 个整数的数组  nums  和一个目标值  target，判断  nums  中是否存在四个元素 a，b，c  和 d  ，使得  a + b + c + d  的值与  target  相等？找出所有满足条件且不重复的四元组。

    注意：

    答案中不可以包含重复的四元组。

    示例：

    给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

    满足要求的四元组集合为：
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
    ]

    url:https://leetcode-cn.com/problems/4sum/
    */

    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Arrays.sort(nums);

        for (int left = 0; left < nums.length - 3; left++) {
            for (int right = nums.length - 1; right >= left + 3; right--) {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int sum = leftNum + rightNum;

                // 子序列内计算总和
                int subLeft = left + 1;
                int subRight = right - 1;
                while (subLeft < subRight) {
                    int subLeftNum = nums[subLeft];
                    int subRightNum = nums[subRight];
                    int subSum = subLeftNum + subRightNum;
                    int totalSum = sum + subSum;
                    if (totalSum == target) {
                        lists.add(Arrays.asList(leftNum, subLeftNum, subRightNum, rightNum));
                        do {
                            subLeft++;
                        } while (subLeft < subRight && nums[subLeft] == nums[subLeft - 1]);
                        do {
                            subRight--;
                        } while (subLeft < subRight && nums[subRight] == nums[subRight + 1]);
                    } else if (totalSum < target) {
                        do {
                            subLeft++;
                        } while (subLeft < subRight && nums[subLeft] == nums[subLeft - 1]);
                    } else {
                        do {
                            subRight--;
                        } while (subLeft < subRight && nums[subRight] == nums[subRight + 1]);
                    }
                }
                while (right - 1 > left && nums[right - 1] == nums[right]) {
                    right--;
                }
            }
            while (left + 1 < nums.length - 3 && nums[left + 1] == nums[left]) {
                left++;
            }
        }
        return lists;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        /*定义一个返回值*/
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        /*当数组为null或元素小于4个时，直接返回*/
        if (nums == null || nums.length < 4) {
            return result;
        }
        /*对数组进行从小到大排序*/
        Arrays.sort(nums);
        /*数组长度*/
        int length = nums.length;
        /*定义4个指针p1-p4  p1从0开始遍历，p2从p1+1开始遍历，留下p3和p4，p3指向p2+1，p4指向数组最大值*/
        for (int p1 = 0; p1 < length - 3; p1++) {
            /*当p1的值与前面的值相等时忽略*/
            if (p1 > 0 && nums[p1] == nums[p1 - 1]) {
                continue;
            }
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min1 = nums[p1] + nums[p1 + 1] + nums[p1 + 2] + nums[p1 + 3];
            if (min1 > target) {
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
            int max1 = nums[p1] + nums[length - 1] + nums[length - 2] + nums[length - 3];
            if (max1 < target) {
                continue;
            }
            /*第二层循环p2，初始值指向p1+1*/
            for (int p2 = p1 + 1; p2 < length - 2; p2++) {
                /*当p2的值与前面的值相等时忽略*/
                if (p2 > p1 + 1 && nums[p2] == nums[p2 - 1]) {
                    continue;
                }
                /*定义指针p3指向p2+1*/
                int p3 = p2 + 1;
                /*定义指针p4指向数组末尾*/
                int p4 = length - 1;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏，忽略*/
                int min = nums[p1] + nums[p2] + nums[p3] + nums[p3 + 1];
                if (min > target) {
                    continue;
                }
                /*获取当前最大值，如果最大值比目标值小，说明后面越来越小的值根本没戏，忽略*/
                int max = nums[p1] + nums[p2] + nums[p4] + nums[p4 - 1];
                if (max < target) {
                    continue;
                }
                /*开始p3指针和p4指针的表演，计算当前和，如果等于目标值，p3++并去重，p4--并去重，当当前和大于目标值时p4--，当当前和小于目标值时p3++*/
                while (p3 < p4) {
                    int curr = nums[p1] + nums[p2] + nums[p3] + nums[p4];
                    if (curr == target) {
                        result.add(Arrays.asList(nums[p1], nums[p2], nums[p3], nums[p4]));
                        p3++;
                        while (p3 < p4 && nums[p3] == nums[p3 - 1]) {
                            p3++;
                        }
                        p4--;
                        while (p3 < p4 && p2 < p4 && nums[p4] == nums[p4 + 1]) {
                            p4--;
                        }
                    } else if (curr > target) {
                        p4--;
                    } else {
                        p3++;
                    }
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum3(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //先排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 去除指针i可能的重复情况
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 去除j可能重复的情况
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    // 不满足条件或者重复的，继续遍历
                    if ((left != j + 1 && nums[left] == nums[left - 1]) || nums[i] + nums[j] + nums[left] + nums[right] < target) {
                        left++;
                    } else if ((right != nums.length - 1 && nums[right] == nums[right + 1]) || nums[i] + nums[j] + nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);

                        result.add(list);
                        // 满足条件的，进入下一次遍历
                        left++;
                        right--;
                    }
                }

            }
        }

        return result;
    }
}
