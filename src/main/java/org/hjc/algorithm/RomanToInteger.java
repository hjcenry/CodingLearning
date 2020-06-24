package org.hjc.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 *
 * @author 何金成
 * @date 2020/6/22 11:34
 */
public class RomanToInteger {

    public static void main(String[] args) {
        System.out.println(new RomanToInteger().romanToInt("III"));
        System.out.println(new RomanToInteger().romanToInt("IV"));
        System.out.println(new RomanToInteger().romanToInt("IX"));
        System.out.println(new RomanToInteger().romanToInt("LVIII"));
        System.out.println(new RomanToInteger().romanToInt("MCMXCIV"));
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
    Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

    Example 1:

    Input: "III"
    Output: 3
    Example 2:

    Input: "IV"
    Output: 4
    Example 3:

    Input: "IX"
    Output: 9
    Example 4:

    Input: "LVIII"
    Output: 58
    Explanation: L = 50, V= 5, III = 3.
    Example 5:

    Input: "MCMXCIV"
    Output: 1994
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

    罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

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
    给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

    示例 1:

    输入: "III"
    输出: 3
    示例 2:

    输入: "IV"
    输出: 4
    示例 3:

    输入: "IX"
    输出: 9
    示例 4:

    输入: "LVIII"
    输出: 58
    解释: L = 50, V= 5, III = 3.
    示例 5:

    输入: "MCMXCIV"
    输出: 1994
    解释: M = 1000, CM = 900, XC = 90, IV = 4.

    url:https://leetcode-cn.com/problems/roman-to-integer/
    */

    public int romanToInt(String s) {
        int sLen = s.length();
        int num = 0;

        int pointer = 0;
        while (pointer < sLen) {
            Symbol symbol = Symbol.getChar(s.charAt(pointer));
            if (pointer == sLen - 1) {
                return num + symbol.num;
            }
            Symbol nextSymbol = Symbol.getChar(s.charAt(pointer + 1));

            if (symbol.isOneSymbol()) {
                // 是当前进制的1
                if (nextSymbol.symbol != symbol.symbol && nextSymbol.num > symbol.num) {
                    // 下一位不是1，得到4或者9
                    num = num + (nextSymbol.num - symbol.num);
                    pointer = pointer + 2;
                } else {
                    num = num + symbol.num;
                    pointer++;
                    while (pointer < sLen && nextSymbol.symbol == symbol.symbol) {
                        num = num + nextSymbol.num;
                        if (pointer + 1 < sLen) {
                            nextSymbol = Symbol.getChar(s.charAt(pointer + 1));
                        }
                        pointer++;
                    }
                }
            } else {
                if (!nextSymbol.isOneSymbol()) {
                    num = num + symbol.num;
                    pointer++;
                } else {
                    num = num + symbol.num;
                    pointer++;
                    while (pointer < sLen && nextSymbol.symbol == symbol.oneSymbol) {
                        num = num + nextSymbol.num;
                        if (pointer + 1 < sLen) {
                            nextSymbol = Symbol.getChar(s.charAt(pointer + 1));
                        }
                        pointer++;
                    }
                }
            }
        }
        return num;
    }

    enum Symbol {
        // 1
        ONE('I', 1, true),
        // 5
        FIVE('V', 'I', 5),
        // 10
        TEN('X', 'I', 10, true),
        // 50
        FIFTY('L', 'X', 50),
        // 100
        HUNDRED('C', 'X', 100, true),
        // 500
        FIVE_HUNDRED('D', 'C', 500),
        // 1000
        THOUSAND('M', 'C', 1000, true),
        ;

        char symbol = 0;
        char oneSymbol = 0;
        int num;
        boolean isOne = false;

        boolean isOneSymbol() {
            return isOne;
        }

        Symbol(char symbol, int num) {
            this.symbol = symbol;
            this.num = num;
        }

        Symbol(char symbol, char oneSymbol, int num) {
            this.symbol = symbol;
            this.oneSymbol = oneSymbol;
            this.num = num;
        }

        Symbol(char symbol, int num, boolean isOne) {
            this.symbol = symbol;
            this.num = num;
            this.isOne = isOne;
        }

        Symbol(char symbol, char oneSymbol, int num, boolean isOne) {
            this.symbol = symbol;
            this.oneSymbol = oneSymbol;
            this.num = num;
            this.isOne = isOne;
        }

        static Map<Character, Symbol> charMap = new HashMap(7);

        static {
            for (Symbol symbol : Symbol.values()) {
                charMap.put(symbol.symbol, symbol);
            }
        }

        static Symbol getChar(char c) {
            return charMap.get(c);
        }
    }
}
