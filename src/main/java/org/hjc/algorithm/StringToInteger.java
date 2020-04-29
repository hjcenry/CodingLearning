package org.hjc.algorithm;

/**
 * 字符串转换整数 (atoi)
 *
 * @author 何金成
 * @date 2020/4/27 21:41
 */
public class StringToInteger {

    /*
    Implement atoi which converts a string to an integer.
    The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
    The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
    If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
    If no valid conversion could be performed, a zero value is returned.

    Note:

    Only the space character ' ' is considered as whitespace character.
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
    Example 1:

    Input: "42"
    Output: 42
    Example 2:

    Input: "   -42"
    Output: -42
    Explanation: The first non-whitespace character is '-', which is the minus sign.
                 Then take as many numerical digits as possible, which gets 42.
    Example 3:

    Input: "4193 with words"
    Output: 4193
    Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
    Example 4:

    Input: "words and 987"
    Output: 0
    Explanation: The first non-whitespace character is 'w', which is not a numerical
                 digit or a +/- sign. Therefore no valid conversion could be performed.
    Example 5:

    Input: "-91283472332"
    Output: -2147483648
    Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
                 Thefore INT_MIN (−231) is returned.

    请你来实现一个 atoi 函数，使其能将字符串转换成整数。
    首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
    如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
    假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
    该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
    注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
    在任何情况下，若函数不能进行有效的转换时，请返回 0 。

    提示：

    本题中的空白字符只包括空格字符 ' ' 。
    假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     

    示例 1:

    输入: "42"
    输出: 42
    示例 2:

    输入: "   -42"
    输出: -42
    解释: 第一个非空白字符为 '-', 它是一个负号。
         我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
    示例 3:

    输入: "4193 with words"
    输出: 4193
    解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
    示例 4:

    输入: "words and 987"
    输出: 0
    解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
         因此无法执行有效的转换。
    示例 5:

    输入: "-91283472332"
    输出: -2147483648
    解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
         因此返回 INT_MIN (−231) 。

    url:https://leetcode-cn.com/problems/string-to-integer-atoi/

    解题思路：
    这个问题其实没有考察算法的知识，模拟的是日常开发中对于原始数据的处理（例如「参数校验」等场景），如果面试中遇到类似的问题，应先仔细阅读题目文字说明和示例，有疑惑的地方和需要和面试官确认，在编码的时候需要耐心和细心地调试。

    其实很多时候，业务需求就是类似这样的问题，工作中如果遇到：

    1、有现成的工具和类库需尽量使用，因为它们是性能更优，且经过更严格测试，是相对可靠的；
    2、能抽取成工具类、工具方法的尽量抽取，以突出主干逻辑、方便以后代码复用；
    3、不得不写得比较繁琐、冗长的时候，需要写清楚注释、体现逻辑层次，以便上线以后排查问题和后续维护。

    在这里我罗列几个要点：

    根据示例 1，需要去掉前导空格；
    根据示例 2，需要判断第 1 个字符为 + 和 - 的情况，因此，可以设计一个变量 sign，初始化的时候为 1，如果遇到 - ，将 sign 修正为 -1；
    判断是否是数字，可以使用字符的 ASCII 码数值进行比较，即 0 <= c <= '9'；
    根据示例 3 和示例 4 ，在遇到第 1 个不是数字的字符的情况下，转换停止，退出循环；
    根据示例 5，如果转换以后的数字超过了 int 类型的范围，需要截取。这里不能将结果 res 变量设计为 long 类型，注意：由于输入的字符串转换以后也有可能超过 long 类型，因此需要在循环内部就判断是否越界，只要越界就退出循环，这样也可以减少不必要的计算；
    由于涉及下标访问，因此全程需要考虑数组下标是否越界的情况。
    特别注意：

    1、由于题目中说「环境只能保存 32 位整数」，因此这里在每一轮循环之前先要检查乘以 1010 以后是否溢出，具体细节请见编码。
    2、Java 、Python 和 C++ 字符串的设计都是不可变的，即使用 trim() 会产生新的变量，因此我们尽量不使用库函数，使用一个变量 index 去做遍历，这样遍历完成以后就得到转换以后的数值。
     */

    public static void main(String[] args) {
        System.out.println(new StringToInteger().myAtoi("42"));
        System.out.println(new StringToInteger().myAtoi("   -42"));
        System.out.println(new StringToInteger().myAtoi("4193 with words"));
        System.out.println(new StringToInteger().myAtoi("words and 987"));
        System.out.println(new StringToInteger().myAtoi("-91283472332"));
        System.out.println(new StringToInteger().myAtoi("-000000000000001"));
        System.out.println(new StringToInteger().myAtoi("   +0 123"));
        System.out.println(new StringToInteger().myAtoi("  -0012a42"));
        System.out.println(new StringToInteger().myAtoi("-2147483649"));
        System.out.println(new StringToInteger().myAtoi("-   234"));
    }

    public int myAtoi(String str) {
        int len = str.length();
        if (len == 0) {
            return 0;
        }
        int resultNumber = 0;
        boolean isValid = false;
        int flag = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            // 判断c是否为数字，0的字符ascii码是48,9的字符ascii码是57
            if (c >= '0' && c <= '9') {
                if (!isValid) {
                    flag = 1;
                    isValid = true;
                }
                // 字符减去0的ascii码，就是对应的数字
                int num = c - '0';
                //判断是否 大于 最大32位整数
                if (resultNumber * flag > 214748364 || (resultNumber * flag == 214748364 && num > 7)) {
                    return Integer.MAX_VALUE;
                }
                //判断是否 小于 最小32位整数
                if (resultNumber * flag < -214748364 || (resultNumber * flag == -214748364 && num * flag < -8)) {
                    return Integer.MIN_VALUE;
                }
                resultNumber = resultNumber * 10 + num;
            } else {
                if (isValid) {
                    return resultNumber * flag;
                }
                if (c == ' ') {
                    continue;
                }
                if (c == '+' || c == '-') {
                    flag = c == '+' ? 1 : -1;
                    isValid = true;
                    continue;
                }
                return 0;
            }
        }
        return resultNumber * flag;
    }
}
