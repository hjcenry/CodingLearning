package org.hjc.algorithm;

import org.hjc.algorithm.common.ListNode;

/**
 * K 个一组翻转链表
 *
 * @author 何金成
 * @date 2020/8/7 17:18
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode l1 = ListNode.createNode(1);
        l1.createNext(2).createNext(3).createNext(4).createNext(5);
        System.out.println(new ReverseNodesInKGroup().reverseKGroup(l1, 2));
        ListNode l2 = ListNode.createNode(1);
        l2.createNext(2).createNext(3).createNext(4).createNext(5);
        System.out.println(new ReverseNodesInKGroup().reverseKGroup(l2, 3));
        ListNode l3 = ListNode.createNode(1);
        l3.createNext(2).createNext(3).createNext(4).createNext(5);
        System.out.println(new ReverseNodesInKGroup().reverseKGroup(l3, 1));
        ListNode l4 = ListNode.createNode(1);
        System.out.println(new ReverseNodesInKGroup().reverseKGroup(l4, 1));
        ListNode l5 = ListNode.createNode(1);
        l5.createNext(2);
        System.out.println(new ReverseNodesInKGroup().reverseKGroup(l5, 2));
    }

    /*
    Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

    Example:

    Given this linked list: 1->2->3->4->5

    For k = 2, you should return: 2->1->4->3->5

    For k = 3, you should return: 3->2->1->4->5

    Note:

    Only constant extra memory is allowed.
    You may not alter the values in the list's nodes, only nodes itself may be changed.

    给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

    k 是一个正整数，它的值小于或等于链表的长度。

    如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

     

    示例：

    给你这个链表：1->2->3->4->5

    当 k = 2 时，应当返回: 2->1->4->3->5

    当 k = 3 时，应当返回: 3->2->1->4->5

     

    说明：

    你的算法只能使用常数的额外空间。
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

    url:https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
    */

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode currentNode = head;
        ListNode lastNode = null;

        ListNode reverseHeadNode = null;
        ListNode firstNode = null;
        while (true) {
            ListNode[] listNodes = new ListNode[k];
            int count = 0;

            boolean isPositiveNode = false;
            // 从头到尾遍历，把节点全部放入数组
            while (count < k) {
                if (currentNode == null || (count < k - 1 && currentNode.next == null)) {
                    isPositiveNode = true;
                }
                listNodes[count] = currentNode;
                if (currentNode != null) {
                    currentNode = currentNode.next;
                }
                if (currentNode == null) {
                    break;
                }
                count++;
            }
            if (isPositiveNode) {
                if (lastNode == null) {
                    return head;
                }
                ListNode endNodes = lastNode.next = listNodes[0];
                for (int i = 1; i < listNodes.length; i++) {
                    ListNode endNode = listNodes[i];
                    if (endNode == null) {
                        break;
                    }
                    endNodes.next = endNode;
                    endNodes = endNodes.next;
                }
                return reverseHeadNode;
            }
            // 倒叙遍历节点数组，建立好链表关系
            for (int i = listNodes.length - 1; i >= 0; i--) {
                if (i == 0) {
                    break;
                }
                if (i == listNodes.length - 1) {
                    firstNode = listNodes[i];
                }
                if (reverseHeadNode == null) {
                    reverseHeadNode = listNodes[i];
                }
                // 把下个节点的指向置空，不然会循环引用
                listNodes[i - 1].next = null;
                listNodes[i].next = listNodes[i - 1];
            }
            if (reverseHeadNode == null) {
                return head;
            }
            // 把上一个lastNode指向当前firstNode
            if (lastNode != null) {
                lastNode.next = firstNode;
            }
            // 当前lastNode设置为最后一个节点
            lastNode = listNodes[0];
            if (lastNode == null) {
                break;
            }
        }
        return reverseHeadNode;
    }

}