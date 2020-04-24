package org.hjc.algorithm;

/**
 * 寻找两个有序数组的中位数
 *
 * @author 何金成
 * @date 2020/4/23 19:55
 */
public class MedianOfTwoSortedArrays {

    /*
    There are two sorted arrays nums1 and nums2 of size m and n respectively.
    Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
    You may assume nums1 and nums2 cannot be both empty.

    Example 1:

    nums1 = [1, 3]
    nums2 = [2]

    The median is 2.0
    Example 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    The median is (2 + 3)/2 = 2.5

    给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
    请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    你可以假设 nums1 和 nums2 不会同时为空。

    示例 1:

    nums1 = [1, 3]
    nums2 = [2]

    则中位数是 2.0
    示例 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    则中位数是 (2 + 3)/2 = 2.5

    url:https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     */

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
//        int[] nums1 = new int[]{1, 2};
//        int[] nums2 = new int[]{3, 4};
//        int[] nums1 = new int[]{3};
//        int[] nums2 = new int[]{-2, -1};
        double median = findMedianSortedArrays(nums1, nums2);
        System.out.println(median);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int len = n1 + n2;
        boolean isOdd = len % 2 != 0;

        // 第一个中位数下标+1
        int medianNumCount = getMedianNumCount(len, isOdd);

        // 位数计数
        int numCounter = 0;
        for (int i = 0, num1Counter = 0, num2Counter = 0, counterType; i < len; i++) {
            // 遍历两数组总长度
            // 找到本次从哪个数组取出的数（1-数组1,2-数组2）
            counterType = getCounterType(nums1, nums2, num1Counter, num2Counter);
            // 数组计数自增，若numCounter的下一位计数是中位数，则找到中位数，用数组的下一位去取出中位数
            if (counterType == 2) {
                // 数组2
                if (numCounter + 1 >= medianNumCount) {
                    return findMedian(nums1, nums2, isOdd, num1Counter, num2Counter + 1, nums2[num2Counter]);
                } else {
                    num2Counter++;
                }
            } else if (counterType == 1) {
                // 数组1
                if (numCounter + 1 >= medianNumCount) {
                    return findMedian(nums1, nums2, isOdd, num1Counter + 1, num2Counter, nums1[num1Counter]);
                } else {
                    num1Counter++;
                }
            }
            // 总计数自增
            numCounter++;
        }
        return 0d;
    }

    /**
     * 找到中位数
     *
     * @param nums1       数组1
     * @param nums2       数组2
     * @param isOdd       是否是奇数位数
     * @param num1Counter 数组1计数器
     * @param num2Counter 数组2计数器
     * @param medianNum   中位数
     * @return 找到的中位数
     */
    private static double findMedian(int[] nums1, int[] nums2, boolean isOdd, int num1Counter, int num2Counter, int medianNum) {
        // 找到了中位数
        if (isOdd) {
            // 奇数位数只有1位中位数，直接返回这个数
            return medianNum;
        } else {
            // 偶数位数有2位中位数，返回平均值，因此需要从数组中再取出一个数
            int medianNum2 = 0;
            if (num2Counter == nums2.length) {
                // 数组2已经遍历完了，直接取数组1的数
                medianNum2 = nums1[num1Counter];
            } else if (num1Counter == nums1.length) {
                // 数组1已经遍历完了，直接取数组2的数
                medianNum2 = nums2[num2Counter];
            } else {
                medianNum2 = Math.min(nums1[num1Counter], nums2[num2Counter]);
            }
            // 计算两个中位数的平均值
            return (medianNum + medianNum2) / 2d;
        }
    }

    /**
     * 返回取数的数组
     *
     * @param nums1       数组1
     * @param nums2       数组2
     * @param num1Counter 数组1下标
     * @param num2Counter 数组2下标
     * @return 1-数组1,2-数组2
     */
    private static int getCounterType(int[] nums1, int[] nums2, int num1Counter, int num2Counter) {
        int counterType;
        if (num2Counter == nums2.length) {
            // 数组2已经遍历完了，直接取数组1的数
            counterType = 1;
        } else if (num1Counter == nums1.length) {
            // 数组1已经遍历完了，直接取数组2的数
            counterType = 2;
        } else {
            // 谁的数小，谁排前面，取谁的数
            counterType = nums1[num1Counter] >= nums2[num2Counter] ? 2 : 1;
        }
        return counterType;
    }

    /**
     * 获取中位数在数组中的位置
     * 从开始1计数，不是从0开始计数
     *
     * @param len   总数组长度
     * @param isOdd 数组是否是奇数位数
     * @return 中位数位数
     */
    private static int getMedianNumCount(int len, boolean isOdd) {
        int medianNumCount;
        if (isOdd) {
            // 奇数，中位数在数组正中间
            medianNumCount = (len - 1) / 2 + 1;
        } else {
            // 偶数，中位数是数组中间两个数/2
            medianNumCount = len / 2;
        }
        return medianNumCount;
    }

}
