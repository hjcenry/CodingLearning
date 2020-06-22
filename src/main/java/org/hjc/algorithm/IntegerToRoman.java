package org.hjc.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 整数转罗马数字
 *
 * @author 何金成
 * @date 2020/6/20 17:01
 */
public class IntegerToRoman {

    public static void main(String[] args) {
//        System.out.println(new IntegerToRoman().intToRoman(1));
        System.out.println(new IntegerToRoman().intToRoman(3));
//        System.out.println(new IntegerToRoman().intToRoman(4));
//        System.out.println(new IntegerToRoman().intToRoman(5));
//        System.out.println(new IntegerToRoman().intToRoman(9));
//        System.out.println(new IntegerToRoman().intToRoman(10));
//        System.out.println(new IntegerToRoman().intToRoman(30));
//        System.out.println(new IntegerToRoman().intToRoman(40));
//        System.out.println(new IntegerToRoman().intToRoman(50));
//        System.out.println(new IntegerToRoman().intToRoman(90));
//        System.out.println(new IntegerToRoman().intToRoman(100));
//        System.out.println(new IntegerToRoman().intToRoman(300));
//        System.out.println(new IntegerToRoman().intToRoman(400));
//        System.out.println(new IntegerToRoman().intToRoman(500));
//        System.out.println(new IntegerToRoman().intToRoman(900));
//        System.out.println(new IntegerToRoman().intToRoman(1000));
//        System.out.println(new IntegerToRoman().intToRoman(3200));
        System.out.println(new IntegerToRoman().intToRoman(58));
    }

    /*
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9. 
    X can be placed before L (50) and C (100) to make 40 and 90. 
    C can be placed before D (500) and M (1000) to make 400 and 900.
    Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

    Example 1:

    Input: 3
    Output: "III"
    Example 2:

    Input: 4
    Output: "IV"
    Example 3:

    Input: 9
    Output: "IX"
    Example 4:

    Input: 58
    Output: "LVIII"
    Explanation: L = 50, V = 5, III = 3.
    Example 5:

    Input: 1994
    Output: "MCMXCIV"
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

    罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

    字符          数值
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

    通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

    I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
    X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
    C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
    给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

    示例 1:

    输入: 3
    输出: "III"
    示例 2:

    输入: 4
    输出: "IV"
    示例 3:

    输入: 9
    输出: "IX"
    示例 4:

    输入: 58
    输出: "LVIII"
    解释: L = 50, V = 5, III = 3.
    示例 5:

    输入: 1994
    输出: "MCMXCIV"
    解释: M = 1000, CM = 900, XC = 90, IV = 4.

    url:https://leetcode-cn.com/problems/integer-to-roman/
    */

    public String intToRoman(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        int digits = 1;
        while (num > 0) {
            int temp = num % 10;
            int offset = 0;
            if (temp == 4) {
                stringBuilder.insert(offset, Symbol.getChar(digits));
                stringBuilder.insert(offset + 1, Symbol.getChar(5 * digits));
            } else if (temp == 5) {
                stringBuilder.insert(offset, Symbol.getChar(5 * digits));
            } else if (temp == 9) {
                stringBuilder.insert(offset, Symbol.getChar(digits));
                stringBuilder.insert(offset + 1, Symbol.getChar(10 * digits));
            } else {
                if (temp > 5) {
                    stringBuilder.insert(offset++, Symbol.getChar(5 * digits));
                    temp = temp - 5;
                }
                for (int i = 0; i < temp; i++) {
                    stringBuilder.insert(offset, Symbol.getChar(digits));
                }
            }
            num = num / 10;
            digits = digits * 10;
        }
        return stringBuilder.toString();
    }

    enum Symbol {
        // 1
        ONE('I', 1),
        // 5
        FIVE('V', 'I', 5),
        // 10
        TEN('X', 'I', 10),
        // 50
        FIFTY('L', 'X', 50),
        // 100
        HUNDRED('C', 'X', 100),
        // 500
        FIVE_HUNDRED('D', 'C', 500),
        // 1000
        THOUSAND('M', 'C', 1000),
        ;

        char symbol;
        char oneSymbol;
        int num;

        Symbol(char symbol, int num) {
            this.symbol = symbol;
            this.num = num;
        }

        Symbol(char symbol, char oneSymbol, int num) {
            this.symbol = symbol;
            this.oneSymbol = oneSymbol;
            this.num = num;
        }

        static Map<Integer, Character> charMap = new HashMap(7);

        static {
            for (Symbol symbol : Symbol.values()) {
                charMap.put(symbol.num, symbol.symbol);
            }
        }

        static char getChar(int num) {
            return charMap.get(num);
        }
    }
}
