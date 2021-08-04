package org.hjc.algorithm;

import java.util.Calendar;
import java.util.Date;

/**
 * 正则表达式匹配
 *
 * @author 何金成
 * @date 2020/5/27 20:30
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        RegularExpressionMatching clz = new RegularExpressionMatching();
        System.out.println(clz.isMatch2("aaa", "a*a"));
//        System.out.println(clz.isMatch("aaa", "aaaa"));
//        System.out.println(clz.isMatch("aa", "a"));
//        System.out.println(clz.isMatch("aa", "a*"));
//        System.out.println(clz.isMatch("ab", ".*"));
//        System.out.println(clz.isMatch("aab", "c*a*b"));
//        System.out.println(clz.isMatch("mississippi", "mis*is*p*."));
//        System.out.println(clz.isMatch("mississippi", "mis*is*ip*."));
    }

    /*
    Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.
    The matching should cover the entire input string (not partial).

    Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.
    Example 1:

    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:

    Input:
    s = "aa"
    p = "a*"
    Output: true
    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
    Example 3:

    Input:
    s = "ab"
    p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".
    Example 4:

    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
    Example 5:

    Input:
    s = "mississippi"
    p = "mis*is*p*."
    Output: false

    给你一个字符串  s  和一个字符规律  p，请你来实现一个支持 '.'  和  '*'  的正则表达式匹配。

    '.' 匹配任意单个字符
    '*' 匹配零个或多个前面的那一个元素
    所谓匹配，是要涵盖  整个  字符串  s的，而不是部分字符串。

    说明:

    s  可能为空，且只包含从  a-z  的小写字母。
    p  可能为空，且只包含从  a-z  的小写字母，以及字符  .  和  *。
    示例 1:

    输入:
    s = "aa"
    p = "a"
    输出: false
    解释: "a" 无法匹配 "aa" 整个字符串。
    示例 2:

    输入:
    s = "aa"
    p = "a*"
    输出: true
    解释:  因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
    示例  3:

    输入:
    s = "ab"
    p = ".*"
    输出: true
    解释:  ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
    示例 4:

    输入:
    s = "aab"
    p = "c*a*b"
    输出: true
    解释:  因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
    示例 5:

    输入:
    s = "mississippi"
    p = "mis*is*p*."
    输出: false

    url:https://leetcode-cn.com/problems/regular-expression-matching/
     */

    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 这儿实际上隐藏了一个对于 s字符串存在，但是p字符串为空的情况，因为一定会为false，并且这个是dp的默认值，因此省去
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                    // 此处是匹配一个的情况，是匹配多个的特殊情况（匹配多个的表达式：dp[i - 1][j]）
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    // 此处是匹配多个和匹配0个的表达式
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

//    /**
//     * 已放弃，暴力解法，始终不能涵盖到所有的case，学习下别人的最优解法
//     * @param s
//     * @param p
//     * @return
//     */
//    public boolean isMatch(String s, String p) {
//        int sLen = s.length();
//        int pLen = p.length();
//
//        int tailIndex = pLen;
//        for (int index = pLen - 1; index >= 0; index--) {
//            if (p.charAt(index) != '*') {
//                tailIndex = index;
//                break;
//            }
//        }
//
//        for (int i = 0; i < sLen; i++) {
//            int pPointer = 0;
//            boolean grouped = false;
//            for (int j = i; j < sLen; j++) {
//                char sChar = s.charAt(j);
//                if (pPointer >= pLen) {
//                    return false;
//                }
//                char pChar = p.charAt(pPointer);
//                if (!grouped) {
//                    // 按*组成一组进行匹配
//                    grouped = pPointer < pLen - 1 && p.charAt(pPointer + 1) == '*';
//                }
////                int pStartPointer = pPointer;
//                if ((pPointer = doMatch(sChar, pChar, p, pLen, pPointer, grouped)) == -1) {
//                    return false;
//                } else {
//                    // 按组匹配切指针发生跳跃，则可以往后走 || 普通匹配且指针不在最后一位
////                    if ((grouped && pPointer != pStartPointer) || (!grouped && j < sLen - 1)) {
////                        pPointer++;
////                    }
//                    if (!grouped && j < sLen - 1) {
//                        pPointer++;
//                    }
//                }
//            }
//            if (pPointer >= tailIndex) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private int doMatch(char sChar, char pChar, String p, int pLen, int pPointer, boolean grouped) {
//        if (!grouped) {
//            // 按*组成一组进行匹配
//            grouped = pPointer < pLen - 1 && p.charAt(pPointer + 1) == '*';
//        }
//        if (sChar == pChar || pChar == '.') {
//            return pPointer;
//        } else {
//            if (grouped) {
//                if (pPointer + 2 >= pLen) {
//                    return -1;
//                }
//                pPointer = pPointer + 2;
//                // 递归调用判断
//                return doMatch(sChar, p.charAt(pPointer), p, pLen, pPointer, grouped);
//            }
//        }
//        return -1;
//    }
}