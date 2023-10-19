package org.hjc.algorithm.searchPath;

import org.hjc.algorithm.searchPath.entry.*;

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

    protected static final String[][] EMPTY_MAP = new String[][]{
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
            new String[]{_, _, _, _, _, _, _, _, _, _, _, _, _, _, _},
    };

//    protected static final String[][] MAP = EMPTY_MAP;
    protected  static final String[][] MAP = new String[][]{
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
        long startTime = System.currentTimeMillis();

        int loop = 1;
        // 广度优先搜索
        for (int i = 0; i < loop; i++) {
            searchPathUseBFS(start, end);
        }
        long bfsCostTime = System.currentTimeMillis() - startTime;
        // 深度优先搜索
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            searchPathUseDFS(start, end);
        }
        long dfsCostTime = System.currentTimeMillis() - startTime;
        // A星搜索
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            searchPathUseAStar(start, end);
        }
        long aStarCostTime = System.currentTimeMillis() - startTime;

        System.out.println("寻路次数:" + loop);
        System.out.println("BFS耗时:" + bfsCostTime);
        System.out.println("DFS耗时:" + dfsCostTime);
        System.out.println("A星耗时:" + aStarCostTime);

    }

    public void searchPathUseAStar(Vector2 startPoint, Vector2 endPoint) {
        String[][] aStarMap = cloneArray(MAP);
        AStarMapInfo info = new AStarMapInfo(aStarMap, width, height, new AStarNode(startPoint.x, startPoint.y), new AStarNode(endPoint.x, endPoint.y));
        AStarSearchPath aStarSearchPath = new AStarSearchPath();
        aStarSearchPath.start(info);

        printMap(aStarMap);
    }

    public void searchPathUseDFS(Vector2 startPoint, Vector2 endPoint) throws Exception {
        String[][] dfsMap = cloneArray(MAP);
        DFSMapInfo info = new DFSMapInfo(dfsMap, width, height, startPoint, endPoint);
        DFSSearchPath DFSSearchPath = new DFSSearchPath();
        DFSSearchPath.start(info);

        printMap(dfsMap);
    }

    public void searchPathUseBFS(Vector2 startPoint, Vector2 endPoint) throws Exception {
        String[][] bfsMap = cloneArray(MAP);
        BFSMapInfo info = new BFSMapInfo(bfsMap, width, height, new BFSNode(startPoint), new BFSNode(endPoint));
        BFSSearchPath bFSSearchPath = new BFSSearchPath();
        bFSSearchPath.start(info);

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
