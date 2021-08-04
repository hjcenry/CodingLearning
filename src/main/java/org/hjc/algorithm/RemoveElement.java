package org.hjc.algorithm;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

/**
 * 移除元素
 *
 * @author 何金成
 * @date 2021/8/4 16:52
 */
public class RemoveElement {
    public static void main(String[] args) {
        System.out.println(new RemoveElement().removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    /*
    Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The relative order of the elements may be changed.

    Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
    
    Return k after placing the final result in the first k slots of nums.
    
    Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
    
    Custom Judge:
    
    The judge will test your solution with the following code:
    
    int[] nums = [...]; // Input array
    int val = ...; // Value to remove
    int[] expectedNums = [...]; // The expected answer with correct length.
                                // It is sorted with no values equaling val.
    
    int k = removeElement(nums, val); // Calls your implementation
    
    assert k == expectedNums.length;
    sort(nums, 0, k); // Sort the first k elements of nums
    for (int i = 0; i < actualLength; i++) {
        assert nums[i] == expectedNums[i];
    }
    If all assertions pass, then your solution will be accepted.
    
      
    Example 1:
    
    Input: nums = [3,2,2,3], val = 3
    Output: 2, nums = [2,2,_,_]
    Explanation: Your function should return k = 2, with the first two elements of nums being 2.
    It does not matter what you leave beyond the returned k (hence they are underscores).
    Example 2:
    
    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]
    Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
    Note that the five elements can be returned in any order.
    It does not matter what you leave beyond the returned k (hence they are underscores).
      
    
    Constraints:
    
    0 <= nums.length <= 100
    0 <= nums[i] <= 50
    0 <= val <= 100
    
    给你一个数组 nums  和一个值 val，你需要 原地 移除所有数值等于  val  的元素，并返回移除后数组的新长度。

    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。


    说明:

    为什么返回数值是整数，但输出的答案是数组呢?

    请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

    你可以想象内部操作如下:

    // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
    int len = removeElement(nums, val);

    // 在函数里修改输入数组对于调用者是可见的。
    // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
    for (int i = 0; i < len; i++) {
          print(nums[i]);
    }
      

    示例 1：

    输入：nums = [3,2,2,3], val = 3
    输出：2, nums = [2,2]
    解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
    示例 2：

    输入：nums = [0,1,2,2,3,0,4,2], val = 2
    输出：5, nums = [0,1,4,0,3]
    解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
      

    提示：

    0 <= nums.length <= 100
    0 <= nums[i] <= 50
    0 <= val <= 100

    */

    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int nextIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (num == val) {
                // 移除元素
                if (nextIndex == -1) {
                    nextIndex = i;
                }
                len--;
            } else if (nextIndex > -1) {
                // 如果下个下标>-1，则把数字往这个坐标挪动，然后下标往后走
                nums[nextIndex] = num;
                nextIndex++;
            }
        }
        return len;
    }
}
