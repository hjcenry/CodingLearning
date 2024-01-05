//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 7009 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        // 动态规划
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLength = 0;
        int maxBegin = 0;

        // 对dp进行赋值
        for (int right = 1; right < len; right++) {
            for (int left = 0; left < len; left++) {
                // 左右字符不相等，一定不是回文子串
                if (s.charAt(left) != s.charAt(right)) {
                    dp[left][right] = false;
                } else {
                    // 左右字符相等，继续判断
                    if (right - left < 3) {
                        // 若仅剩两个字符，一定是回文串
                        dp[left][right] = true;
                    } else {
                        // 两个字符以上的回文子串，则掐头去尾再判断是否是子串（回文子串特性）
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                }

                // 当前为回文子串
                if (dp[left][right]) {
                    int curLength = right - left + 1;
                    // 当前长度比最大长度长
                    if (curLength > maxLength) {
                        maxLength = curLength;
                        maxBegin = left;
                    }
                }
            }
        }
        return s.substring(maxBegin, maxBegin + maxLength);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
