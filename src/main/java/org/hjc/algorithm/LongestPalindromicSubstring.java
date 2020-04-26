package org.hjc.algorithm;

/**
 * 最长回文子串
 *
 * @author 何金成
 * @date 2020/4/24 22:09
 */
public class LongestPalindromicSubstring {

    /*
    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
    Example 2:

    Input: "cbbd"
    Output: "bb"

    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

    示例 1：

    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：

    输入: "cbbd"
    输出: "bb"

    url:https://leetcode-cn.com/problems/longest-palindromic-substring/
    */

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("上海自来水来自海上"));
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome("aaabaaaa"));
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
//        System.out.println(new LongestPalindromicSubstring().longestPalindrome(""));
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return s;
        }
        int maxFindStringStartIndex = 0;
        int maxFindStringEndIndex = 0;
        // 分别从字符串的两端向中间缩小范围遍历
        for (int i = 0; i < len; i++) {
            char forwardChar = s.charAt(i);
            int maxLen = maxFindStringEndIndex - maxFindStringStartIndex + 1;
            if (len - i <= maxLen) {
                // 剩余字符还没有最大回文串字符长，就不用了继续遍历了
                break;
            }
            for (int j = len - 1; j > i; j--) {
                char behindChar = s.charAt(j);
                int sLen = j - i + 1;
                if (sLen <= maxLen) {
                    // 剩余字符还没有最大回文串字符长，就不用了继续遍历了
                    break;
                }
                if (forwardChar == behindChar) {
                    // 首尾相同，判断是否为回文串
                    if (!isPalindrome(s, i, j, sLen % 2 != 0)) {
                        continue;
                    }
                    maxFindStringStartIndex = i;
                    maxFindStringEndIndex = j;
                    break;
                }
            }
        }
        return s.substring(maxFindStringStartIndex, maxFindStringEndIndex + 1);
    }

    private boolean isPalindrome(String s, int startIndex, int endIndex, boolean isLenOdd) {
        int midIndex = (startIndex + endIndex) / 2;
        for (int index = startIndex; isLenOdd ? index < midIndex : index <= midIndex; index++) {
            char forwardChar = s.charAt(index);
            int behindIndex = endIndex - (index - startIndex);
            char behindChar = s.charAt(behindIndex);
            if (forwardChar != behindChar) {
                return false;
            }
        }
        return true;
    }

}
