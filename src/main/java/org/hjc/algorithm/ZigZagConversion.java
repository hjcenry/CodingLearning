package org.hjc.algorithm;

/**
 * Z 字形变换
 *
 * @author 何金成
 * @date 2020/4/26 17:37
 */
public class ZigZagConversion {

    /*
    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

    P   A   H   N
    A P L S I I G
    Y   I   R
    And then read line by line: "PAHNAPLSIIGYIR"

    Write the code that will take a string and make this conversion given a number of rows:

    string convert(string s, int numRows);
    Example 1:

    Input: s = "PAYPALISHIRING", numRows = 3
    Output: "PAHNAPLSIIGYIR"
    Example 2:

    Input: s = "PAYPALISHIRING", numRows = 4
    Output: "PINALSIGYAHRPI"
    Explanation:

    P     I    N
    A   L S  I G
    Y A   H R
    P     I

    将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
    比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

    L   C   I   R
    E T O E S I I G
    E   D   H   N
    之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
    请你实现这个将字符串进行指定行数变换的函数：

    string convert(string s, int numRows);
    示例 1:

    输入: s = "LEETCODEISHIRING", numRows = 3
    输出: "LCIRETOESIIGEDHN"
    示例 2:

    输入: s = "LEETCODEISHIRING", numRows = 4
    输出: "LDREOEIIECIHNTSG"
    解释:

    L     D     R
    E   O E   I I
    E C   I H   N
    T     S     G

    思路：

列数 0 1 2 3 4   5 6  7  8  9  10

    1          11             21
    2       10 12          20 22
    3     9    13       19    23
    4   8      14    18
    5 7        15 17
    6          16

    url:https://leetcode-cn.com/problems/zigzag-conversion/
     */

    public static void main(String[] args) {
        System.out.println(new ZigZagConversion().convert("LEETCODEISHIRING", 2));
//        System.out.println(new ZigZagConversion().convert("A", 1));

        /*
        P   A   H   N
        A P L S I I G
        Y   I   R
        */
//        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 3));

        /*
        P     I     N
        A   L S   I G
        Y A   H R
        P     I
        */
//        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 4));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int len = s.length();
        // 新的字符数组
        char[] newStrChars = new char[len];
        int charIndex = 0;
        // 总列数
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < len; col = col + numRows - 1) {
                // 根据列数获取元素下标
                int strIndex = 2 * col + row;

                if (row != 0 && row != numRows - 1 && col > 0) {
                    // 非首尾两行先取中间的数,当前列的第一行的index-行数
                    int midIndex = col * 2 - row;
                    if (midIndex >= len) {
                        break;
                    }
                    newStrChars[charIndex++] = s.charAt(midIndex);
                }
                // 取竖线列上的数
                if (strIndex >= len) {
                    break;
                }
                newStrChars[charIndex++] = s.charAt(strIndex);
            }
        }
        return String.copyValueOf(newStrChars, 0, len);
    }
}
