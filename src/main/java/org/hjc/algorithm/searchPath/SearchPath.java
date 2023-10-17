package org.hjc.algorithm.searchPath;

import org.hjc.algorithm.searchPath.aStar.MapInfo;
import org.hjc.algorithm.searchPath.aStar.Node;

import java.util.*;

/**
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/18 0:34
 **/
public class SearchPath {

    // 空余格子
    protected static final String _ = "_";
    // 阻碍物格子
    protected static final String X = "口";

    protected static final String[][] MAP = new String[][]{
            new String[]{X, _, _, _, X, _, _, _, _, _, _, _, X, _, _},
            new String[]{_, _, X, X, X, X, _, _, X, X, X, X, X, X, _},
            new String[]{_, _, X, _, _, X, X, _, X, _, _, _, X, _, _},
            new String[]{_, X, X, X, _, _, X, _, X, X, _, _, X, X, _},
            new String[]{_, _, X, _, _, X, X, _, _, X, _, X, X, _, _},
            new String[]{_, _, X, _, X, _, _, _, _, X, _, _, _, _, X},
            new String[]{_, X, X, _, X, X, X, X, _, X, _, X, X, _, _},
            new String[]{_, X, _, _, X, _, _, _, _, X, _, X, X, X, _},
            new String[]{_, X, X, _, _, _, X, X, X, X, _, X, X, _, _},
            new String[]{_, _, _, _, X, _, _, _, _, _, _, _, X, X, _},
            new String[]{_, _, X, _, X, _, X, X, X, X, X, _, _, X, _},
            new String[]{_, X, X, X, X, X, X, X, _, _, X, _, X, X, _},
            new String[]{_, _, _, X, _, _, _, _, _, X, X, _, X, _, _},
            new String[]{_, X, X, X, X, X, X, X, _, X, X, _, X, X, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
    };

    protected static final String PASSED = "0";

    protected static final int width = MAP[0].length;
    protected static final int height = MAP.length;

    public static void main(String[] args) throws Exception {
        SearchPath searchPath = new SearchPath();
        searchPath.printMap(MAP);
        searchPath.printLine();
        searchPath.run();
    }

    public void printLine() {
        System.out.println("===================================================================");
    }

    public void run() throws Exception {
        // 起点
        Vector2 start = new Vector2(5, 14);
        // 终点
        Vector2 end = new Vector2(13, 0);
        // 广度优先搜索
        // searchPathUseBFS(start, end);
        // A星搜索
        searchPathUseAStar(start, end);
    }

    public void searchPathUseAStar(Vector2 startPoint, Vector2 endPoint) {
        String[][] aStarMap = cloneArray(MAP);
        MapInfo info = new MapInfo(aStarMap, width, height, new Node(startPoint.x, startPoint.y), new Node(endPoint.x, endPoint.y));
        AStarSearchPath aStarSearchPath = new AStarSearchPath();
        aStarSearchPath.start(info);

        printMap(aStarMap);
    }

    public void searchPathUseBFS(Vector2 startPoint, Vector2 endPoint) throws Exception {
        BFSSearchPath bfsSearchPath = new BFSSearchPath();

        String[][] bfsMap = cloneArray(MAP);
        List<List<Vector2>> allPathList = new ArrayList<>();
        List<Vector2> pathList = new ArrayList<>();
        pathList.add(startPoint);
        bfsSearchPath.doSearchPathUseBFS(startPoint, endPoint, bfsMap, pathList, allPathList);

        System.out.println("最短路径长度:" + bfsSearchPath.minBFSPathCount);
        System.out.println("总共找到路径数:" + allPathList.size());
        for (int i = 0; i < allPathList.size(); i++) {
            System.out.println("路径[" + (i + 1) + "]长度:" + allPathList.get(i).size());
        }
        if (bfsSearchPath.minBFSPathList != null) {
            bfsSearchPath.minBFSPathList.forEach(path -> bfsMap[path.y][path.x] = PASSED);
        }

        printMap(bfsMap);
    }

    public void printMap(String[][] map) {
        for (String[] rows : map) {
            for (String col : rows) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    private String[][] cloneArray(String[][] origin) {
        String[][] dest = new String[origin.length][origin[0].length];
        for (int rowIndex = 0; rowIndex < origin.length; rowIndex++) {
            String[] row = origin[rowIndex];
            String[] destRow = Arrays.copyOf(row, row.length);
            dest[rowIndex] = destRow;
        }
        return dest;
    }

}
