//给定一个包含大写字母和小写字母的字符串
// s ，返回 通过这些字母构造成的 最长的回文串 。 
//
// 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入:s = "abccccdd"
//输出:7
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
// 
//
// 示例 2: 
//
// 
//输入:s = "a"
//输出:1
// 
//
// 示例 3： 
//
// 
//输入:s = "aaaaaccc"
//输出:7 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 2000 
// s 只由小写 和/或 大写英文字母组成 
// 
//
// Related Topics 贪心 哈希表 字符串 👍 569 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindrome(String s) {
        char[] charsArr = s.toCharArray();
        // 统计数量
        Map<Character, Integer> charMap = new HashMap<>(charsArr.length);
        for (char c : charsArr) {
            int counter = charMap.getOrDefault(c, 0);
            counter++;
            charMap.put(c, counter);
        }

        if (charMap.size() == 1) {
            return s.length();
        }

        int ans = 0;
        int flag = 0;
        for (Integer counter : charMap.values()) {
            int num = counter % 2;
            if (num != 0) {
                flag = 1;
            }
            ans += (counter - num);
        }
        return ans + flag;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
