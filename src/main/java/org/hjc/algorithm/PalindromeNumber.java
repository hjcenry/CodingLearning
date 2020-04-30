package org.hjc.algorithm;

/**
 * 回文数
 *
 * @author 何金成
 * @date 2020/4/29 17:10
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome(121));
        System.out.println(new PalindromeNumber().isPalindrome(-121));
        System.out.println(new PalindromeNumber().isPalindrome(10));
        System.out.println(new PalindromeNumber().isPalindrome(157565751));
        System.out.println(new PalindromeNumber().isPalindrome(157565752));
        System.out.println(new PalindromeNumber().isPalindrome(Integer.MAX_VALUE));
    }

    /*
    Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

    Example 1:

    Input: 121
    Output: true
    Example 2:

    Input: -121
    Output: false
    Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
    Example 3:

    Input: 10
    Output: false
    Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
    Follow up:

    Could you solve it without converting the integer to a string?

    判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

    示例 1:

    输入: 121
    输出: true
    示例 2:

    输入: -121
    输出: false
    解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    示例 3:

    输入: 10
    输出: false
    解释: 从右向左读, 为 01 。因此它不是一个回文数。
    进阶:

    你能不将整数转为字符串来解决这个问题吗？

    url:https://leetcode-cn.com/problems/palindrome-number/
     */

    /**
     *
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int oldNum = x;
        int newNum = 0;
        while (x > 0) {
            int num = x % 10;
            // 考虑int溢出
            if (newNum > 214748364 || (newNum == 214748364 && num > 7)) {
                return false;
            }
            newNum = newNum * 10 + num;
            x = x / 10;
        }
        return newNum == oldNum;
    }
}
