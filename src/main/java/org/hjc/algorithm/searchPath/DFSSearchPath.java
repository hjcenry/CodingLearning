package org.hjc.algorithm.searchPath;

import org.hjc.algorithm.searchPath.entry.AStarMapInfo;
import org.hjc.algorithm.searchPath.entry.BFSMapInfo;
import org.hjc.algorithm.searchPath.entry.DFSMapInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先搜索
 * <p>
 * 从起点出发，上下左右每条路进行搜索，直到走不通再找下一条
 *
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/18 0:34
 **/
public class DFSSearchPath {

    // 最短路径长度
    protected int minDFSPathCount = -1;
    // 最短路径
    protected List<Vector2> minDFSPathList = null;

    protected int width;
    protected int height;

    public void start(DFSMapInfo dfsMapInfo) throws Exception {
        this.width = dfsMapInfo.width;
        this.height = dfsMapInfo.height;

        List<List<Vector2>> allPathList = new ArrayList<>();
        List<Vector2> pathList = new ArrayList<>();
        pathList.add(dfsMapInfo.start);
        this.doSearchPathUseDFS(dfsMapInfo.start, dfsMapInfo.end, dfsMapInfo.maps, pathList, allPathList);

        System.out.println("最短路径长度:" + this.minDFSPathCount);
        System.out.println("总共找到路径数:" + allPathList.size());
        for (int i = 0; i < allPathList.size(); i++) {
            System.out.println("路径[" + (i + 1) + "]长度:" + allPathList.get(i).size());
        }
        if (this.minDFSPathList != null) {
            this.minDFSPathList.forEach(path -> dfsMapInfo.maps[path.y][path.x] = SearchPath.PASSED);
        }
    }

    /**
     * 上下左右依次遍历路径点
     *
     * @param curPoint    当前点
     * @param endPoint    终点
     * @param map         地图
     * @param pathList    当前经过的路径
     * @param allPathList 所有的满足条件的路径
     */
    public void doSearchPathUseDFS(Vector2 curPoint, Vector2 endPoint, String[][] map, List<Vector2> pathList, List<List<Vector2>> allPathList) throws Exception {
        // 上遍历
        Vector2 up = curPoint.createUp();
        if (up.y >= 0) {
            searchPathUseDFSEnd(endPoint, map, new ArrayList<>(pathList), up, allPathList);
        }
        // 下遍历
        Vector2 down = curPoint.createDown();
        down.y = down.y + 1;
        if (down.y <= this.height - 1) {
            searchPathUseDFSEnd(endPoint, map, new ArrayList<>(pathList), down, allPathList);
        }
        // 左遍历
        Vector2 left = curPoint.createLeft();
        left.x = left.x - 1;
        if (left.x >= 0) {
            searchPathUseDFSEnd(endPoint, map, new ArrayList<>(pathList), left, allPathList);
        }
        // 右遍历
        Vector2 right = curPoint.createRight();
        right.x = right.x + 1;
        if (right.x <= this.width - 1) {
            searchPathUseDFSEnd(endPoint, map, new ArrayList<>(pathList), right, allPathList);
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
    public boolean searchPathUseDFSEnd(Vector2 endPoint, String[][] map, List<Vector2> pathList, Vector2 next, List<List<Vector2>> allPathList) throws Exception {
        if (pathList.contains(next)) {
            // 已经走过该点
            return false;
        }
        if (endPoint.x == next.x && endPoint.y == next.y) {
            // 找到终点
            pathList.add(next);
            int pathCount = pathList.size();
            allPathList.add(pathList);
            if (minDFSPathCount == -1 || pathCount <= minDFSPathCount) {
                // 比之前的路径线路短
                minDFSPathCount = pathCount;
                minDFSPathList = pathList;
            }
            return true;
        }
        if (!map[next.y][next.x].equals(SearchPath.X)) {
            // 若不是阻碍物
            pathList.add(next);
            doSearchPathUseDFS(next, endPoint, map, pathList, allPathList);
        }
        return false;
    }
}
