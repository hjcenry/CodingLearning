package org.hjc.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * @author 何金成
 * @date 2020/4/23 10:48
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /*
    Given a string, find the length of the longest substring without repeating characters.

    Example 1:

    Input: "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
    Example 2:

    Input: "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
                 Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    示例 1:

    输入: "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    示例 2:

    输入: "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    示例 3:

    输入: "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
         请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

    url:https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "bbbbb";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "pwwkew";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "abcebcbb";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "cdd";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "abcb";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "dvdf";
        System.out.println(lengthOfLongestSubstring1(s));
        s = "au";
        System.out.println(lengthOfLongestSubstring1(s));
        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * 方法一：暴力法
     * 逐个检查所有的子字符串，看它是否不含有重复的字符
     * 执行时间长，占用内存高
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> subChars = new HashMap<Character, Integer>(chars.length);
        int maxSize = 0;
        int repeatIndex = -1;
        int i = 0;
        while (i < chars.length) {
            for (int j = i; j < chars.length; j++) {
                char c = chars[j];
                if (subChars.containsKey(c)) {
                    repeatIndex = subChars.get(c);
                    break;
                }
                subChars.put(c, j);
            }
            if (repeatIndex != -1) {
                // 找到重复元素，从第一个重复元素的下一个下标开始遍历
                i = repeatIndex + 1;
                repeatIndex = -1;
            } else {
                i++;
            }
            maxSize = Math.max(maxSize, subChars.size());
            subChars.clear();
            float middleSize = chars.length / 2f;
            if (maxSize >= middleSize && i >= middleSize) {
                // 最大子串长度超过一半了，可以不用继续遍历了
                break;
            }
        }
        return maxSize;
    }

    /**
     * 方法二：滑动窗口
     * 按区间进行字符串搜索，遇到重复字符，就从左区间开始删除，直到删除掉重复字符为止
     * 这道题主要用到思路是：滑动窗口
     * 什么是滑动窗口？
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
     * 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     * 时间复杂度：O(n)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<Character>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 方法三：优化的滑动窗口
     * 上述的方法最多需要执行 2n 个步骤。事实上，它可以被进一步优化为仅需要 n 个步骤。我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。 当我们找到重复的字符时，我们可以立即跳过该窗口。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(c, j + 1);
        }
        return ans;

//      Java（假设字符集为 ASCII 128）
//      以前的我们都没有对字符串 s 所使用的字符集进行假设。
//      当我们知道该字符集比较小的时侯，我们可以用一个整数数组作为直接访问表来替换 Map。
//
//        int n = s.length(), ans = 0;
//        int[] index = new int[128]; // current index of character
//        // try to extend the range [i, j]
//        for (int j = 0, i = 0; j < n; j++) {
//            i = Math.max(index[s.charAt(j)], i);
//            ans = Math.max(ans, j - i + 1);
//            index[s.charAt(j)] = j + 1;
//        }
//        return ans;
    }

}
