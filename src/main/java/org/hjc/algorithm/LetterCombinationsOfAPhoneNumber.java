package org.hjc.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 电话号码的字母组合
 *
 * @author 何金成
 * @date 2020/6/30 16:01
 */
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new LetterCombinationsOfAPhoneNumber().letterCombinations("23")));
    }

    /*
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



    Example:

    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    Note:

    Although the above answer is in lexicographical order, your answer could be in any order you want.

    给定一个仅包含数字  2-9  的字符串，返回所有它能表示的字母组合。

    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



    示例:

    输入："23"
    输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    说明:
    尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

    url:https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     */

    Map<Integer, char[]> numCharMap = new HashMap<Integer, char[]>(8);

    public LetterCombinationsOfAPhoneNumber() {
        numCharMap.put(2, new char[]{'a', 'b', 'c'});
        numCharMap.put(3, new char[]{'d', 'e', 'f'});
        numCharMap.put(4, new char[]{'g', 'h', 'i'});
        numCharMap.put(5, new char[]{'j', 'k', 'l'});
        numCharMap.put(6, new char[]{'m', 'n', 'o'});
        numCharMap.put(7, new char[]{'p', 'q', 'r', 's'});
        numCharMap.put(8, new char[]{'t', 'u', 'v'});
        numCharMap.put(9, new char[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<String>();
        getCombinationString(list, new StringBuilder(), 0, digits);
        return list;
    }

    private void getCombinationString(List<String> list, StringBuilder stringBuilder, int n, String digits) {
        if (n >= digits.length()) {
            return;
        }
        int digit = Integer.parseInt(String.valueOf(digits.charAt(n)));
        char[] numChars = numCharMap.get(digit);
        for (char numChar : numChars) {
            StringBuilder subBuilder = new StringBuilder(stringBuilder);
            subBuilder.append(numChar);
            if (n == digits.length() - 1) {
                // 最后一个数字了
                list.add(subBuilder.toString());
            } else {
                getCombinationString(list, subBuilder, n + 1, digits);
            }
        }
    }
}
