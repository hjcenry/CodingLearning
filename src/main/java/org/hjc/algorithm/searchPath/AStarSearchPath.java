package org.hjc.algorithm.searchPath;

import org.hjc.algorithm.searchPath.aStar.MapInfo;
import org.hjc.algorithm.searchPath.aStar.Node;

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
    Queue<Node> openList = new PriorityQueue<>();
    List<Node> closeList = new ArrayList<>();

    /**
     * 开始算法
     */
    public void start(MapInfo mapInfo) {
        if (mapInfo == null) {
            return;
        }
        // clean
        openList.clear();
        closeList.clear();
        // 开始搜索
        openList.add(mapInfo.start);
        moveNodes(mapInfo);
    }

    /**
     * 移动当前结点
     */
    private void moveNodes(MapInfo mapInfo) {
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closeList.add(current);
            if (isVector2InClose(mapInfo.end.vector2)) {
                // 终点已在close列表，回溯路径
                drawPath(mapInfo.maps, mapInfo.end);
                break;
            }
            addNeighborNodeInOpen(mapInfo, current);
        }
    }

    /**
     * 在二维数组中绘制路径
     */
    private void drawPath(String[][] maps, Node end) {
        if (end == null || maps == null) {
            return;
        }
        System.out.println("总代价：" + end.G);
        while (end != null) {
            Vector2 c = end.vector2;
            maps[c.y][c.x] = SearchPath.PASSED;
            end = end.parent;
        }
    }

    /**
     * 添加所有邻结点到open表
     */
    private void addNeighborNodeInOpen(MapInfo mapInfo, Node current) {
        int x = current.vector2.x;
        int y = current.vector2.y;
        // 左
        addNeighborNodeInOpen(mapInfo, current, x - 1, y, DIRECT_VALUE);
        // 上
        addNeighborNodeInOpen(mapInfo, current, x, y - 1, DIRECT_VALUE);
        // 右
        addNeighborNodeInOpen(mapInfo, current, x + 1, y, DIRECT_VALUE);
        // 下
        addNeighborNodeInOpen(mapInfo, current, x, y + 1, DIRECT_VALUE);
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
    private void addNeighborNodeInOpen(MapInfo mapInfo, Node current, int x, int y, int value) {
        if (canAddNodeToOpen(mapInfo, x, y)) {
            Node end = mapInfo.end;
            Vector2 vector2 = new Vector2(x, y);
            int G = current.G + value;
            // 计算邻结点的G值
            Node child = findNodeInOpen(vector2);
            if (child == null) {
                int H = calcH(end.vector2, vector2);
                // 计算H值
                if (isEndNode(end.vector2, vector2)) {
                    child = end;
                    child.parent = current;
                    child.G = G;
                    child.H = H;
                } else {
                    child = new Node(vector2, current, G, H);
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
    private Node findNodeInOpen(Vector2 vector2) {
        if (vector2 == null || openList.isEmpty()) {
            return null;
        }
        for (Node node : openList) {
            if (node.vector2.equals(vector2)) {
                return node;
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
    private boolean canAddNodeToOpen(MapInfo mapInfo, int x, int y) {
        // 是否在地图中
        if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.height) {
            return false;
        }
        // 判断是否是不可通过的结点
        if (mapInfo.maps[y][x].equals(SearchPath.X)) {
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
        for (Node node : closeList) {
            if (node.vector2.x == x && node.vector2.y == y) {
                return true;
            }
        }
        return false;
    }
}
