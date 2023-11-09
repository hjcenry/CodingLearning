//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 3323 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
//        int[] mem = new int[n+1];
//        return climbStairs(n, mem);
    }

//    public int climbStairs(int n, int[] mem) {
//        if (mem[n] > 0) {
//            return mem[n];
//        }
//        if (n == 1) {
//            mem[n] = 1;
//        } else if (n == 2) {
//            mem[n] = 2;
//        } else {
//            mem[n] = climbStairs(n - 1) + climbStairs(n - 2);
//        }
//        return mem[n];
//    }

}
//leetcode submit region end(Prohibit modification and deletion)
