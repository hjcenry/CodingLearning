//给定一个单链表 L 的头节点 head ，单链表 L 表示为： 
//
// 
//L0 → L1 → … → Ln - 1 → Ln
// 
//
// 请将其重新排列后变为： 
//
// 
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → … 
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [1,2,3,4]
//输出：[1,4,2,3] 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3] 
//
// 
//
// 提示： 
//
// 
// 链表的长度范围为 [1, 5 * 10⁴] 
// 1 <= node.val <= 1000 
// 
//
// Related Topics 栈 递归 链表 双指针 👍 1402 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
     public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

//    public class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode() {
//        }
//
//        ListNode(int val) {
//            this.val = val;
//        }
//
//        ListNode(int val, ListNode next) {
//            this.val = val;
//            this.next = next;
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
