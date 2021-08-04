package org.hjc.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 三数之和
 *
 * @author 何金成
 * @date 2020/6/28 17:31
 */
public class ThreeSum {

    public static void main(String[] args) {
//        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4})));
//        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{0, 0, 0, 0})));
//        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{1, 1, 1})));
//        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{3, 0, -2, -1, 1, 2})));
//        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{-2, 0, 0, 2, 2})));
//        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{-2, 0, 1, 1, 2})));
//        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{-2, -1, -1, 0, 2})));
        System.out.println(JSON.toJSONString(new ThreeSum().threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6})));
    }
    /*
    Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
    
    Note:
    
    The solution set must not contain duplicate triplets.
    
    Example:
    
    Given array nums = [-1, 0, 1, 2, -1, -4],
    
    A solution set is:
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
    
    给你一个包含 n 个整数的数组  nums，判断  nums  中是否存在三个元素 a，b，c ，使得  a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    
    注意：答案中不可以包含重复的三元组。
    
      
    
    示例：
    
    给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    给定数组 nums = [-4, -1, -1, 0, 1, 2]，

    满足要求的三元组集合为：
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums.length < 3) {
            return list;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重
            }
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == 0) {
                    list.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    while (low < high && nums[low] == nums[low + 1]) {
                        low++; // 去重
                    }
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--; // 去重
                    }
                    low++;
                    high--;
                } else if (sum < 0) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return list;
    }
}
