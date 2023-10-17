package org.hjc.algorithm.searchPath;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/18 0:34
 **/
public class BFSSearchPath {

    // 最短路径长度
    protected int minBFSPathCount = -1;
    // 最短路径
    protected List<Vector2> minBFSPathList = null;

    /**
     * 上下左右依次遍历路径点
     *
     * @param curPoint    当前点
     * @param endPoint    终点
     * @param map         地图
     * @param pathList    当前经过的路径
     * @param allPathList 所有的满足条件的路径
     */
    public void doSearchPathUseBFS(Vector2 curPoint, Vector2 endPoint, String[][] map, List<Vector2> pathList, List<List<Vector2>> allPathList) throws Exception {
        // 上遍历
        Vector2 up = curPoint.clonePoint();
        up.y = up.y - 1;
        if (up.y >= 0) {
            searchPathUseBFSEnd(endPoint, map, new ArrayList<>(pathList), up, allPathList);
        }

        // 下遍历
        Vector2 down = curPoint.clonePoint();
        down.y = down.y + 1;
        if (down.y <= SearchPath.height - 1) {
            searchPathUseBFSEnd(endPoint, map, new ArrayList<>(pathList), down, allPathList);
        }

        // 左遍历
        Vector2 left = curPoint.clonePoint();
        left.x = left.x - 1;
        if (left.x >= 0) {
            searchPathUseBFSEnd(endPoint, map, new ArrayList<>(pathList), left, allPathList);
        }

        // 右遍历
        Vector2 right = curPoint.clonePoint();
        right.x = right.x + 1;
        if (right.x <= SearchPath.width - 1) {
            searchPathUseBFSEnd(endPoint, map, new ArrayList<>(pathList), right, allPathList);
        }
    }

    /**
     * 寻找
     *
     * @param endPoint
     * @param map
     * @param pathList
     * @param next
     * @param allPathList
     * @throws Exception
     */
    public void searchPathUseBFSEnd(Vector2 endPoint, String[][] map, List<Vector2> pathList, Vector2 next, List<List<Vector2>> allPathList) throws Exception {
        if (pathList.contains(next)) {
            // 已经走过该点
            return;
        }
        if (endPoint.x == next.x && endPoint.y == next.y) {
            // 找到终点
            pathList.add(next);
            int pathCount = pathList.size();
            allPathList.add(pathList);
            if (minBFSPathCount == -1 || pathCount <= minBFSPathCount) {
                // 比之前的路径线路短
                minBFSPathCount = pathCount;
                minBFSPathList = pathList;
                return;
            }
            return;
        }
        if (!map[next.y][next.x].equals(SearchPath.X)) {
            // 若不是阻碍物
            pathList.add(next);
            doSearchPathUseBFS(next, endPoint, map, pathList, allPathList);
        }
    }
    //====================================================================================================================
    // 广度优先寻路算法end
    // 从起点出发，依次遍历上下左右四个格子，判断如果不是走过的点并且也不是障碍物，那么这条路径可以继续往前走，直到找到目标点或走不通为止
    //====================================================================================================================
}
