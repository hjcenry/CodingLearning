package org.hjc.algorithm.searchPath.entry;

import org.hjc.algorithm.searchPath.Vector2;

/**
 * 路径结点
 *
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/18 0:45
 **/
public class AStarNode implements Comparable<AStarNode> {

    // 坐标
    public Vector2 vector2;
    // 父结点
    public AStarNode parent;
    // G：是个准确的值，是起点到当前结点的代价
    public int G;
    // H：是个估值，当前结点到目的结点的估计代价
    public int H;

    public AStarNode(int x, int y) {
        this.vector2 = new Vector2(x, y);
    }

    public AStarNode(Vector2 vector2, AStarNode parent, int g, int h) {
        this.vector2 = vector2;
        this.parent = parent;
        G = g;
        H = h;
    }

    @Override
    public int compareTo(AStarNode o) {
        if (o == null) {
            return -1;
        }
        if (G + H > o.G + o.H) {
            return 1;
        } else if (G + H < o.G + o.H) {
            return -1;
        }
        return 0;
    }
}
