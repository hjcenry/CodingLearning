package org.hjc.algorithm;

/**
 * 最长公共前缀
 *
 * @author 何金成
 * @date 2020/6/28 17:06
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
//        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
//        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"aa", "a"}));
    }

    /*
    Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".

    Example 1:

    Input: ["flower","flow","flight"]
    Output: "fl"
    Example 2:

    Input: ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
    Note:

    All given inputs are in lowercase letters a-z.

    编写一个函数来查找字符串数组中的最长公共前缀。

    如果不存在公共前缀，返回空字符串 ""。

    示例 1:

    输入: ["flower","flow","flight"]
    输出: "fl"
    示例 2:

    输入: ["dog","racecar","car"]
    输出: ""
    解释: 输入不存在公共前缀。
    说明:

    所有输入只包含小写字母 a-z 。

    url:https://leetcode-cn.com/problems/longest-common-prefix/
     */

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int index = 0;
        boolean finish = false;
        while (!finish && index < strs[0].length()) {
            char c = strs[0].charAt(index);
            for (int n = 1; n < strs.length; n++) {
                if (strs[n].length() <= index) {
                    finish = true;
                    break;
                }
                if (strs[n].charAt(index) != c) {
                    if (index == 0) {
                        return "";
                    }
                    finish = true;
                    break;
                }
            }
            if (!finish) {
                index++;
            }
        }
        return strs[0].substring(0, index);
    }
}
