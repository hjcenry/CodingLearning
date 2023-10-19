package org.hjc.algorithm.searchPath;

import org.hjc.algorithm.searchPath.entry.BFSMapInfo;
import org.hjc.algorithm.searchPath.entry.BFSNode;

import java.util.*;

/**
 * 广度优先搜索
 *
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/18 0:34
 **/
public class BFSSearchPath {
    protected Map<Vector2, BFSNode> nodeList = new HashMap<>();
    protected int width;
    protected int height;

    public void start(BFSMapInfo info) {
        this.width = info.width;
        this.height = info.height;

        this.nodeList.put(info.start.getVector2(), info.start);
        while (!this.nodeList.isEmpty()) {
            // 子节点添加相邻节点
            List<BFSNode> addOpenNodeList = new ArrayList<>();
            List<BFSNode> closedNodeList = new ArrayList<>();
            for (Map.Entry<Vector2, BFSNode> entry : this.nodeList.entrySet()) {
                this.addNodes(info.maps, entry.getValue(), addOpenNodeList);
                closedNodeList.add(entry.getValue());
            }
            addOpenNodeList.forEach(node -> this.nodeList.put(node.getVector2(), node));
            closedNodeList.forEach(node -> this.nodeList.remove(node.getVector2()));

            if (nodeList.containsKey(info.end.getVector2())) {
                info.end.copyFrom(nodeList.get(info.end.getVector2()));
                break;
            }
        }
        if (info.end.getParent() == null) {
            System.out.println("没有搜索到可达路径");
            return;
        }
        int pathCount = 0;
        BFSNode iterNode = info.end;
        while (iterNode != null) {
            info.maps[iterNode.getVector2().y][iterNode.getVector2().x] = SearchPath.PASSED;
            iterNode = iterNode.getParent();
            pathCount++;
        }
        System.out.println("BFS路径长度：" + pathCount);
    }

    /**
     * 是否是障碍物
     *
     * @param map     地图
     * @param vector2 坐标点
     * @return
     */
    protected boolean isObstacle(String[][] map, Vector2 vector2) {
        return map[vector2.y][vector2.x].equals(SearchPath.X);
    }

    protected void addNodes(String[][] map, BFSNode node, List<BFSNode> addNodeList) {
        // 上遍历
        BFSNode up = node.createUpNode();
        if (up.getVector2().y >= 0 && !isObstacle(map, up.getVector2())) {
            addNodeList.add(up);
        }
        // 下遍历
        BFSNode down = node.createDownNode();
        if (down.getVector2().y <= this.height - 1 && !isObstacle(map, down.getVector2())) {
            addNodeList.add(down);
        }
        // 左遍历
        BFSNode left = node.createLeftNode();
        if (left.getVector2().x >= 0 && !isObstacle(map, left.getVector2())) {
            addNodeList.add(left);
        }
        // 右遍历
        BFSNode right = node.createRightNode();
        if (right.getVector2().x <= this.width - 1 && !isObstacle(map, right.getVector2())) {
            addNodeList.add(right);
        }
    }
}
