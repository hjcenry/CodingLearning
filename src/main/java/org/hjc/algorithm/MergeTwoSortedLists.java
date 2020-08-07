package org.hjc.algorithm;

import org.hjc.algorithm.common.ListNode;

/**
 * 合并两个有序链表
 *
 * @author 何金成
 * @date 2020/8/5 10:42
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
//        ListNode head = new MergeTwoSortedLists().mergeTwoLists(new ListNode(1), null);
        ListNode head = new MergeTwoSortedLists().mergeTwoLists(
                new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(4)))
        );
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    /*
    Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.

    Example:

    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4

    将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

     

    示例：

    输入：1->2->4, 1->3->4
    输出：1->1->2->3->4->4

    url:https://leetcode-cn.com/problems/merge-two-sorted-lists/
    */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode(0);
        ListNode preNode = headNode;
        while (l1 != null || l2 != null) {
            // l2 不为空，l1为空或者l2比l1小，则插入l2
            if (l2 != null && (l1 == null || l1.val > l2.val)) {
                preNode.next = l2;
//                preNode.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                // 反之插入l1
                preNode.next = l1;
//                preNode.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            // 遍历下一节点
            preNode = preNode.next;
        }
        return headNode.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            // 终止条件，直到两个链表都空
            return l2;
        }
        if (l2 == null) {
            // 终止条件，直到两个链表都空
            return l1;
        }
        if (l1.val <= l2.val) {
            // 递归调用
            l1.next = this.mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = this.mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

}
