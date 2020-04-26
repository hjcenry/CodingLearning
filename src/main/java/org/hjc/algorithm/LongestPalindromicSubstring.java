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

    /**
     * 暴力解法
     * 时间复杂度O(n3)，很费时
     *
     * @param s
     * @return
     */
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

    /**
     * 中心扩散法
     * 从每一个位置出发，向两边扩散即可。遇到不是回文的时候结束
     * 时间复杂度O(n2)
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * 动态规划解法
     * 中心扩散的方法，其实做了很多重复计算。动态规划就是为了减少重复计算的问题。动态规划听起来很高大上。其实说白了就是空间换时间，将计算结果暂存起来，避免重复计算。作用和工程中用 redis 做缓存有异曲同工之妙。
     * 我们用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。试想如果 dp[l][r]=true，我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符
     * 时间复杂度O(n2)
     *
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        //最长回文串的起点
        int maxStart = 0;
        //最长回文串的终点
        int maxEnd = 0;
        //最长回文串的长度
        int maxLen = 1;

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 3 || dp[l + 1][r - 1])) {
                    // 如果首尾字符相当 && （首尾中间字符串是回文串 || 首尾字符数只有3个以下）
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

}
