package org.hjc.algorithm.searchPath;

import org.hjc.algorithm.searchPath.entry.AStarMapInfo;
import org.hjc.algorithm.searchPath.entry.AStarNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A星寻路
 *
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/17 20:02
 **/
public class AStarSearchPath {
    // 横竖移动代价
    public final static int DIRECT_VALUE = 10;
    // 斜移动代价
    public final static int OBLIQUE_VALUE = 14;
    // 优先队列(升序)
    Queue<AStarNode> openList = new PriorityQueue<>();
    List<AStarNode> closeList = new ArrayList<>();

    /**
     * 开始算法
     */
    public void start(AStarMapInfo AStarMapInfo) {
        if (AStarMapInfo == null) {
            return;
        }
        // clean
        openList.clear();
        closeList.clear();
        // 开始搜索
        openList.add(AStarMapInfo.start);
        moveNodes(AStarMapInfo);
    }

    /**
     * 移动当前结点
     */
    private void moveNodes(AStarMapInfo AStarMapInfo) {
        while (!openList.isEmpty()) {
            AStarNode current = openList.poll();
            closeList.add(current);
            if (isVector2InClose(AStarMapInfo.end.vector2)) {
                // 终点已在close列表，回溯路径
                drawPath(AStarMapInfo.maps, AStarMapInfo.end);
                break;
            }
            addNeighborNodeInOpen(AStarMapInfo, current);
        }
    }

    /**
     * 在二维数组中绘制路径
     */
    private void drawPath(String[][] maps, AStarNode end) {
        if (end == null || maps == null) {
            return;
        }
        System.out.println("A星总代价：" + end.G);
        int pathCount = 0;
        while (end != null) {
            Vector2 c = end.vector2;
            maps[c.y][c.x] = SearchPath.PASSED;
            end = end.parent;
            pathCount++;
        }
        System.out.println("A星路径长度：" + pathCount);
    }

    /**
     * 添加所有邻结点到open表
     */
    private void addNeighborNodeInOpen(AStarMapInfo AStarMapInfo, AStarNode current) {
        int x = current.vector2.x;
        int y = current.vector2.y;
        // 左
        addNeighborNodeInOpen(AStarMapInfo, current, x - 1, y, DIRECT_VALUE);
        // 上
        addNeighborNodeInOpen(AStarMapInfo, current, x, y - 1, DIRECT_VALUE);
        // 右
        addNeighborNodeInOpen(AStarMapInfo, current, x + 1, y, DIRECT_VALUE);
        // 下
        addNeighborNodeInOpen(AStarMapInfo, current, x, y + 1, DIRECT_VALUE);
//        以下是包含斜着的路径
        // 左上
//        addNeighborNodeInOpen(mapInfo, current, x - 1, y - 1, OBLIQUE_VALUE);
        // 右上
//        addNeighborNodeInOpen(mapInfo, current, x + 1, y - 1, OBLIQUE_VALUE);
        // 右下
//        addNeighborNodeInOpen(mapInfo, current, x + 1, y + 1, OBLIQUE_VALUE);
        // 左下
//        addNeighborNodeInOpen(mapInfo, current, x - 1, y + 1, OBLIQUE_VALUE);
    }

    /**
     * 添加一个邻结点到open表
     */
    private void addNeighborNodeInOpen(AStarMapInfo AStarMapInfo, AStarNode current, int x, int y, int value) {
        if (canAddNodeToOpen(AStarMapInfo, x, y)) {
            AStarNode end = AStarMapInfo.end;
            Vector2 vector2 = new Vector2(x, y);
            int G = current.G + value;
            // 计算邻结点的G值
            AStarNode child = findNodeInOpen(vector2);
            if (child == null) {
                int H = calcH(end.vector2, vector2);
                // 计算H值
                if (isEndNode(end.vector2, vector2)) {
                    child = end;
                    child.parent = current;
                    child.G = G;
                    child.H = H;
                } else {
                    child = new AStarNode(vector2, current, G, H);
                }
                openList.add(child);
            } else if (child.G > G) {
                child.G = G;
                child.parent = current;
                openList.add(child);
            }
        }
    }

    /**
     * 从Open列表中查找结点
     */
    private AStarNode findNodeInOpen(Vector2 vector2) {
        if (vector2 == null || openList.isEmpty()) {
            return null;
        }
        for (AStarNode AStarNode : openList) {
            if (AStarNode.vector2.equals(vector2)) {
                return AStarNode;
            }
        }
        return null;
    }


    /**
     * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
     */
    private int calcH(Vector2 end, Vector2 vector2) {
        return (Math.abs(end.x - vector2.x) + Math.abs(end.y - vector2.y)) * DIRECT_VALUE;
    }

    /**
     * 判断结点是否是最终结点
     */
    private boolean isEndNode(Vector2 end, Vector2 vector2) {
        return end.equals(vector2);
    }

    /**
     * 判断结点能否放入Open列表
     */
    private boolean canAddNodeToOpen(AStarMapInfo AStarMapInfo, int x, int y) {
        // 是否在地图中
        if (x < 0 || x >= AStarMapInfo.width || y < 0 || y >= AStarMapInfo.height) {
            return false;
        }
        // 判断是否是不可通过的结点
        if (AStarMapInfo.maps[y][x].equals(SearchPath.X)) {
            return false;
        }
        // 判断结点是否存在close表
        if (isVector2InClose(x, y)) {
            return false;
        }
        return true;
    }

    /**
     * 判断坐标是否在close表中
     */
    private boolean isVector2InClose(Vector2 coord) {
        return coord != null && isVector2InClose(coord.x, coord.y);
    }

    /**
     * 判断坐标是否在close表中
     */
    private boolean isVector2InClose(int x, int y) {
        if (closeList.isEmpty()) {
            return false;
        }
        for (AStarNode AStarNode : closeList) {
            if (AStarNode.vector2.x == x && AStarNode.vector2.y == y) {
                return true;
            }
        }
        return false;
    }
}
