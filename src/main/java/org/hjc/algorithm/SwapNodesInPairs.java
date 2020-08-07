package org.hjc.algorithm;

import org.hjc.algorithm.common.ListNode;

/**
 * 两两交换链表中的节点
 *
 * @author 何金成
 * @date 2020/8/7 16:35
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode l1 = ListNode.createNode(1);
        l1.createNext(2).createNext(3).createNext(4);
        ListNode node = new SwapNodesInPairs().swapPairs(l1);
        System.out.print(node);
    }

    /*
    Given a linked list, swap every two adjacent nodes and return its head.

    You may not modify the values in the list's nodes, only nodes itself may be changed.

    Example:

    Given 1->2->3->4, you should return the list as 2->1->4->3.

    给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

    示例:

    给定 1->2->3->4, 你应该返回 2->1->4->3.

    url:https://leetcode-cn.com/problems/swap-nodes-in-pairs/
     */

    public ListNode swapPairs(ListNode head) {
        ListNode node = new ListNode(0);
        node.next = head;
        swapPairs0(node, head);
        return node.next;
    }

    /**
     * 递归交换节点
     *
     * @param preNode     上一节点
     * @param currentNode 当前节点
     */
    private void swapPairs0(ListNode preNode, ListNode currentNode) {
        if (currentNode == null || currentNode.next == null) {
            return;
        }
        ListNode nextNode = currentNode.next;

        // 交换顺序三步走
        // 交换前：preNode - currentNode - nextNode
        currentNode.next = nextNode.next;
        nextNode.next = currentNode;
        if (preNode != null) {
            preNode.next = nextNode;
        }
        // 交换后：preNode - nextNode - currentNode

        swapPairs0(currentNode, currentNode.next);
    }

}
