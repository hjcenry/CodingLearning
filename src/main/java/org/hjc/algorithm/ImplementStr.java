package org.hjc.algorithm;

/**
 * 实现 strStr()
 *
 * @author 何金成
 * @date 2021/8/4 17:06
 */
public class ImplementStr {

    public static void main(String[] args) {
        System.out.println(new ImplementStr().strStr("hello", "ll"));
        System.out.println(new ImplementStr().strStr("aaaaa", "bba"));
        System.out.println(new ImplementStr().strStr("", ""));
    }

    /*
    Implement strStr().

    Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
    
    Clarification:
    
    What should we return when needle is an empty string? This is a great question to ask during an interview.
    
    For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's  strstr() and Java's  indexOf().
    
      
    
    Example 1:
    
    Input: haystack = "hello", needle = "ll"
    Output: 2
    Example 2:
    
    Input: haystack = "aaaaa", needle = "bba"
    Output: -1
    Example 3:
    
    Input: haystack = "", needle = ""
    Output: 0
      
    
    Constraints:
    
    0 <= haystack.length, needle.length <= 5 * 104
    haystack and  needle consist of only lower-case English characters.
    
    实现  strStr()  函数。

    给你两个字符串  haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回   -1 。

      

    说明：

    当  needle  是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

    对于本题而言，当  needle  是空字符串时我们应当返回 0 。这与 C 语言的  strstr()  以及 Java 的  indexOf()  定义相符。

      

    示例 1：

    输入：haystack = "hello", needle = "ll"
    输出：2
    示例 2：

    输入：haystack = "aaaaa", needle = "bba"
    输出：-1
    示例 3：

    输入：haystack = "", needle = ""
    输出：0
      

    提示：

    0 <= haystack.length, needle.length <= 5 * 104
    haystack 和 needle 仅由小写英文字符组成
    */

    public int strStr(String haystack, String needle) {
        return 0;
    }
}
