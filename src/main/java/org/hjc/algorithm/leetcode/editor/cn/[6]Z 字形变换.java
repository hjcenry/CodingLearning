//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下： 
//
// 
//P   A   H   N
//A P L S I I G
//Y   I   R 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// 
//string convert(string s, int numRows); 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
// 
//
//示例 2：
//
// 
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
// 
//
// 示例 3： 
//
// 
//输入：s = "A", numRows = 1
//输出："A"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 由英文字母（小写和大写）、',' 和 '.' 组成 
// 1 <= numRows <= 1000 
// 
//
// Related Topics 字符串 👍 2250 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        int len = s.length();
        // 新的字符数组
        char[] newStrChars = new char[len];
        int charIndex = 0;
        for (int row = 0; row < numRows; row++) {
            // 逐行读取
            for (int col = 0; col < len; col = col + numRows - 1) {
                // 读取列按照相同规律一段一段读取
                if (row != 0 && row != numRows - 1 && col > 0) {
                    // 非首尾两行先取中间的数:当前列的第一行的index减去行数（即从当前列的顶部往前推row个数，就是要取得的下标）
                    int midIndex = col * 2 - row;
                    if (midIndex >= len) {
                        break;
                    }
                    newStrChars[charIndex++] = s.charAt(midIndex);
                }
                // 取竖线列上的数，根据行数和列数获取元素下标
                int strIndex = 2 * col + row;
                if (strIndex >= len) {
                    break;
                }
                newStrChars[charIndex++] = s.charAt(strIndex);
            }
        }
        return String.copyValueOf(newStrChars, 0, len);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
