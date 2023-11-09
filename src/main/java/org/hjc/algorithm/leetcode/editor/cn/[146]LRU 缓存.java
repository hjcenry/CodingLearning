//
// 请你设计并实现一个满足 
// LRU (最近最少使用) 缓存 约束的数据结构。
// 
//
// 
// 实现 
// LRUCache 类：
// 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
// 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 3001 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    private final Map<Integer, Entry> map;

    private final Entry head;
    private Entry tail;

    private int capacity;

    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(this.capacity);
        tail = head = new Entry();
        head.next = tail;
    }

    public int get(int key) {
        Entry entry = map.get(key);
        if (entry == null) {
            return -1;
        }
        this.updateRecentUseEntry(entry);
        return entry.value;
    }

    public void put(int key, int value) {
        Entry entry = map.get(key);
        if (entry != null) {
            entry.value = value;
            this.updateRecentUseEntry(entry);
            return;
        }
        if (size >= capacity && head.next != null) {
            if (head.next == tail) {
                tail = head;
            }
            // LRU移除
            int removeKey = head.next.key;
            map.remove(removeKey);

            if (head.next.next != null) {
                head.next.next.pre = head;
            }
            head.next = head.next.next;
            size--;
        }

        entry = new Entry();
        entry.key = key;
        entry.value = value;
        map.put(key, entry);
        size++;
        this.updateRecentUseEntry(entry);
    }

    private void updateRecentUseEntry(Entry entry) {
        if (entry == tail) {
            // 就在链表尾部，不用处理
            return;
        }
        // 移除链表
        if (entry.pre != null) {
            entry.pre.next = entry.next;
            entry.next.pre = entry.pre;
        }
        // 插入链表尾部
        entry.pre = tail;
        entry.next = null;
        tail.next = entry;

        tail = entry;
    }

    private void printList() {
        Entry entry = head;
        System.out.print("list:");
        while (entry != null) {
            System.out.print(" " + entry.value + "[" + (entry.pre == null ? -1 : entry.pre.value) + "/" + (entry.next == null ? -1 : entry.next.value) + "]");
            entry = entry.next;
        }
        System.out.println();
    }

    static class Entry {
        int key;
        int value = -1;
        Entry pre;
        Entry next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
