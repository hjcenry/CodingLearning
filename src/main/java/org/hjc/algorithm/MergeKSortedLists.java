package org.hjc.algorithm;

import java.util.*;

/**
 * 合并K个排序链表
 *
 * @author 何金成
 * @date 2020/8/7 10:58
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode headNode = new MergeKSortedLists().mergeKLists(new ListNode[]{l1, l2, l3});
        while (headNode != null) {
            System.out.print(headNode.val + " ");
            headNode = headNode.next;
        }
    }

    /*
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

    Example:

    Input:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    Output: 1->1->2->3->4->4->5->6

    合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

    示例:

    输入:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    输出: 1->1->2->3->4->4->5->6

    url:https://leetcode-cn.com/problems/merge-k-sorted-lists/
    */

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode preNode = new ListNode(0);
        ListNode node = preNode;
        while (true) {
            int smallestNodeIndex = -1;
            for (int index = 0; index < lists.length; index++) {
                ListNode listNode = lists[index];
                if (listNode == null) {
                    continue;
                }
                if (smallestNodeIndex == -1 || lists[smallestNodeIndex].val > listNode.val) {
                    smallestNodeIndex = index;
                }
            }
            if (smallestNodeIndex == -1) {
                break;
            }
            node.next = lists[smallestNodeIndex];
            node = node.next;
            lists[smallestNodeIndex] = lists[smallestNodeIndex].next;
        }
        return preNode.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        int num = 0;//记录一共有多少个元素，以便确定创建数组的大小
        for (ListNode node : lists) {
            while (node != null) {
                num++;
                node = node.next;
            }
        }//O(nk)的复杂度，k为链表个数，n为假设每个链表长度都为n

        int[] list = new int[num];
        int i = 0;
        //装入数组 O(nk)
        for (ListNode node : lists) {
            while (node != null) {
                list[i] = node.val;
                node = node.next;
                i++;
            }
        }
        //排序 O(nklog(nk))
        Arrays.sort(list);
        if (list.length == 0) {
            return null;
        }
        ListNode ans = new ListNode(list[0]);
        ListNode head = ans;
        //装回链表O(nk)
        for (int k = 1; k < num; ++k) {
            ans.next = new ListNode(list[k]);
            ans = ans.next;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
