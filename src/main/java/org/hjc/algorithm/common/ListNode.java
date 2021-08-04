package org.hjc.algorithm.common;

import org.hjc.algorithm.AddTwoNumbers;

/**
 * 节点类
 *
 * @author 何金成
 * @date 2020/8/7 16:43
 */
public class ListNode {

    public static final boolean ONLY_PRINT_VAL = false;

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode createNode(int num) {
        return new ListNode(num);
    }

    public ListNode createNext(int next) {
        this.next = new ListNode(next);
        return this.next;
    }

    @Override
    public String toString() {
        if (ONLY_PRINT_VAL) {
            return val + "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(val);
        ListNode next = this.next;
        while (next != null) {
            builder.append(",");
            builder.append(next.val);
            next = next.next;
        }
        builder.append("]");
        return builder.toString();
    }

}
