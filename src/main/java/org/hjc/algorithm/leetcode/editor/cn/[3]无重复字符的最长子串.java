package org.hjc.algorithm.leetcode.editor.cn;//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 9894 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        // 最大长度
        int maxLength = 0;
        // 左指针
        int left = 0;
        // 右指针
        int right = 0;
        // 子串队列
        Set<Character> subChars = new HashSet<>();

        while (left < len && right < len) {
            if (subChars.contains(s.charAt(right))) {
                // 子串重复，字符串从队首开始移除
                subChars.remove(s.charAt(left));
                // 移动左指针
                left++;
            } else {
                // 子串不重复，字符入队尾
                subChars.add(s.charAt(right));
                right++;
                // 记录最长不重复长度
                maxLength = Math.max(maxLength, right - left);
            }
        }
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
