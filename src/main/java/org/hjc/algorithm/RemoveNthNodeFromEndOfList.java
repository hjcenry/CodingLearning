package org.hjc.algorithm;

import org.hjc.algorithm.common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除链表的倒数第N个节点
 *
 * @author 何金成
 * @date 2020/8/3 18:14
 */
public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode node = new RemoveNthNodeFromEndOfList().removeNthFromEnd(l1, 2);
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    /*
    Given a linked list, remove the n-th node from the end of list and return its head.

    Example:

    Given linked list: 1->2->3->4->5, and n = 2.

    After removing the second node from the end, the linked list becomes 1->2->3->5.
    Note:

    Given n will always be valid.

    Follow up:

    Could you do this in one pass?

    给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

    示例：

    给定一个链表: 1->2->3->4->5, 和 n = 2.

    当删除了倒数第二个节点后，链表变为 1->2->3->5.
    说明：

    给定的 n 保证是有效的。

    进阶：

    你能尝试使用一趟扫描实现吗？

    url:https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
    */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> nodeIndex = new HashMap<Integer, ListNode>();
        ListNode node = head;
        int index = 0;
        while (node != null) {
            index++;
            nodeIndex.put(index, node);
            node = node.next;
        }
        int removeIndex = index - (n - 1);
        if (removeIndex <= 0) {
            return head;
        }
        ListNode removeNode = nodeIndex.get(removeIndex);
        if (removeIndex == 1) {
            return removeNode.next;
        }
        ListNode preNode = nodeIndex.get(removeIndex - 1);
        preNode.next = removeIndex == index ? null : removeNode.next;
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;
        while (n != 0) {
            start = start.next;
            n--;
        }
        while (start.next != null) {
            start = start.next;
            end = end.next;
        }
        end.next = end.next.next;
        return pre.next;
    }
}

