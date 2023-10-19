package org.hjc.algorithm.searchPath.entry;

/**
 * 包含地图所需的所有输入数据
 *
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/18 0:46
 **/
public class AStarMapInfo {
    // 二维数组的地图
    public String[][] maps;
    // 地图的宽
    public int width;
    // 地图的高
    public int height;
    // 起始结点
    public AStarNode start;
    // 最终结点
    public AStarNode end;

    public AStarMapInfo(String[][] maps, int width, int height, AStarNode start, AStarNode end) {
        this.maps = maps;
        this.width = width;
        this.height = height;
        this.start = start;
        this.end = end;
    }
}
