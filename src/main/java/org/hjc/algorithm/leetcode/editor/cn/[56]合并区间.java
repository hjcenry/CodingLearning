//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics 数组 排序 👍 2168 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        //1、先根据区间开始位置的大小，将 intervals 排序
        Arrays.sort(intervals , Comparator.comparingInt(nums -> nums[0]));

        //2、创建一个新的二维数组，用于保存合并后的区间
        int[][] res = new int[intervals.length][2];
        int index = 0;

        //3、开始合并区间
        for (int i = 0; i < intervals.length; i++) {
            //若是第一个区间，或者当前区间的起始位置 > 结果数组res中最后区间的终止位置（注意，结果数组res中最后区间的下标是 index-1）
            //则不需要将当前区间合并到上一个区间，那么当前区间可以赋值给ret，作为一个新的“合并基底”，将后面满足条件的区间向这个基底合并
            if (i == 0 || intervals[i][0] > res[index - 1][1]) {
                res[index++] = intervals[i];//同时将index+1
            } else
            //否则，需要将当前区间合并到结果数组res的最后区间（即res中下标为index-1的区间），此时index不需要变化
            //此时需要改变 ret[index][1]，即ret[index] 数组的右边界可能扩展
            {
                res[index - 1][1] = Math.max(res[index - 1][1], intervals[i][1]);
            }
        }
        //res数组中实际上只有 index 个数组有效，其他都是 (0 , 0)，我们原先设置res长度为 intervals.length，需要将有效部分复制返回！
        return Arrays.copyOf(res, index);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
