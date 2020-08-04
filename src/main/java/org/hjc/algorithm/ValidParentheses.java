package org.hjc.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 *
 * @author 何金成
 * @date 2020/8/4 11:39
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("[])"));
//        System.out.println(new ValidParentheses().isValid("()"));
//        System.out.println(new ValidParentheses().isValid("()[]{}"));
//        System.out.println(new ValidParentheses().isValid("(]"));
//        System.out.println(new ValidParentheses().isValid("([)]"));
//        System.out.println(new ValidParentheses().isValid("{[]}"));
    }

    /*
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Note that an empty string is also considered valid.

    Example 1:

    Input: "()"
    Output: true
    Example 2:

    Input: "()[]{}"
    Output: true
    Example 3:

    Input: "(]"
    Output: false
    Example 4:

    Input: "([)]"
    Output: false
    Example 5:

    Input: "{[]}"
    Output: true

    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

    有效字符串需满足：

    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    注意空字符串可被认为是有效字符串。

    示例 1:

    输入: "()"
    输出: true
    示例 2:

    输入: "()[]{}"
    输出: true
    示例 3:

    输入: "(]"
    输出: false
    示例 4:

    输入: "([)]"
    输出: false
    示例 5:

    输入: "{[]}"
    输出: true

    url:https://leetcode-cn.com/problems/valid-parentheses/
    */

    Map<Character, Character> bracketMap = new HashMap<Character, Character>();

    public ValidParentheses() {
        bracketMap.put('(', ')');
        bracketMap.put('{', '}');
        bracketMap.put('[', ']');
    }

    public boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        boolean hashPushed = false;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // 是左括号，入栈
            if (bracketMap.containsKey(c)) {
                stack.push(c);
                hashPushed = true;
                continue;
            }
            // 没有左括号，跳过
            if (stack.size() == 0) {
                return false;
            }
            char leftChar = stack.get(stack.size() - 1);
            // 是和当前左括号匹配的右括号，出栈
            if (bracketMap.get(leftChar) == c) {
                stack.pop();
            } else {
                // 不匹配，无效
                return false;
            }
        }
        // 入过栈并全部出栈则验证通过
        return hashPushed && stack.size() == 0;
    }
}