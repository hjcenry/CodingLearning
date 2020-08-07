package org.hjc.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 括号生成
 *
 * @author 何金成
 * @date 2020/8/5 15:57
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(new GenerateParentheses().generateParenthesis(3)));
    }

    /*
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    For example, given n = 3, a solution set is:

    [
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ]

    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

    示例：

    输入：n = 3
    输出：[
           "((()))",
           "(()())",
           "(())()",
           "()(())",
           "()()()"
         ]

    url:https://leetcode-cn.com/problems/generate-parentheses/
    */

    public List<String> generateParenthesis(int n) {
        List<String> parentheses = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        generateParenthesis(parentheses, stringBuilder, 0, 0, n);
        return parentheses;
    }

    /**
     * 递归拼接括号
     *
     * @param parentheses      有效括号列表
     * @param stringBuilder    字符串
     * @param currentLeftCount 当前左括号数量
     * @param allLeftCount     总共左括号数量
     * @param n                需要的数量
     */
    private void generateParenthesis(List<String> parentheses, StringBuilder stringBuilder, int currentLeftCount, int allLeftCount, int n) {
        if (stringBuilder.length() >= n * 2) {
            parentheses.add(stringBuilder.toString());
            return;
        }
        if (allLeftCount < n) {
            generateParenthesis(parentheses, new StringBuilder(stringBuilder).append('('), currentLeftCount + 1, allLeftCount + 1, n);
        }
        if (currentLeftCount > 0) {
            // 有左括号时，添加右括号才是有效的
            currentLeftCount--;
            generateParenthesis(parentheses, new StringBuilder(stringBuilder).append(')'), currentLeftCount, allLeftCount, n);
        }
    }
}
