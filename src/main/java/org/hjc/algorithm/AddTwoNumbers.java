package org.hjc.algorithm;

/**
 * 两数相加
 *
 * @author 何金成
 * @date 2020/4/22 17:34
 */
public class AddTwoNumbers {

    /*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example:

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.

    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    示例：

    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807

    url:https://leetcode-cn.com/problems/add-two-numbers/
    */

    public static void main(String[] args) {
        ListNode l1 = ListNode.createNode(2);
        l1.createNext(4).createNext(3);

        ListNode l2 = ListNode.createNode(5);
        l2.createNext(6).createNext(4);

        ListNode newList = addTwoNumbers(l1, l2);
        System.out.println(newList);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode finalNode = null;
        ListNode headNode = null;
        ListNode incrNode = new ListNode(0);
        ListNode restNode = null;
        while (l1 != null || l2 != null) {
            // 当前节点数值，加上进位数值
            int num = incrNode.val;
            if (l1 != null) {
                if (l2 == null) {
                    // 一个链表加完了，直接把后面的链表接上，退出循环
                    restNode = finalNode.next = l1;
                    break;
                }
                // 累加链表1节点数值
                num = num + l1.val;
            } else {
                // 一个链表加完了，直接把后面的链表接上，退出循环
                restNode = finalNode.next = l2;
                break;
            }
            // 累加链表2节点数值
            num = num + l2.val;
            // 记录是否进位
            incrNode.val = num >= 10 ? 1 : 0;
            // 获取当前节点数值
            num = num % 10;
            if (finalNode == null) {
                finalNode = new ListNode(num);
            } else {
                finalNode.next = new ListNode(num);
                finalNode = finalNode.next;
            }
            // 记录头节点，用于返回值
            if (headNode == null) {
                headNode = finalNode;
            }
            // 遍历下一个节点
            l1 = l1.next;
            l2 = l2.next;
        }
        if (incrNode.val == 1) {
            restNode = restNode == null ? new ListNode(0) : restNode;
            finalNode.next = addTwoNumbers(incrNode, restNode);
        }
        return headNode;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        static ListNode createNode(int num) {
            return new ListNode(num);
        }

        ListNode createNext(int next) {
            this.next = new ListNode(next);
            return this.next;
        }

        @Override
        public String toString() {
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
}
