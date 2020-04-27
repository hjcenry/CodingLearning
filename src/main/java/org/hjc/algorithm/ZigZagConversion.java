package org.hjc.algorithm;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 寻找规律逐行读取
     * 分析每一行读取字符的规律，按照一定的数学规律来逐行逐个字符读取
     *
     * 分析Z字形字符转换时，行数与字符下标的关系
     *
     * 列数 0 1 2 3 4 5   6  7  8  9 10
     *
     *     0         10             20
     *     1       9 11          19 21
     *     2     8   12       18    22
     *     3   7     13    17
     *     4 6       14 16
     *     5         15
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        int len = s.length();
        // 新的字符数组
        char[] newStrChars = new char[len];

//        （这样做其实会稍微耗时一点，因为StringBuilder的每一次append都会根据长度来copy扩展char数组）
//        StringBuilder newStrBuilder = new StringBuilder(len);

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
//                    newStrBuilder.append(s.charAt(midIndex));
                }
                // 取竖线列上的数，根据行数和列数获取元素下标
                int strIndex = 2 * col + row;
                if (strIndex >= len) {
                    break;
                }
                newStrChars[charIndex++] = s.charAt(strIndex);
//                newStrBuilder.append(s.charAt(strIndex));
            }
        }
        return String.copyValueOf(newStrChars, 0, len);
//        return newStrBuilder.toString();
    }

    /**
     * 按Z字形阅读顺序按顺序读取
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            // 每一行用一个StringBuilder来存储当前行的字符
            rows.add(new StringBuilder());
        }
        // 通过flag标识来确定字符读取是正序还是倒序，并把字符追加到对应那行的StringBuilder中
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                // 调换读取顺序
                flag = -flag;
            }
            i += flag;
        }
        // 拼装所有行的字符结果
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
