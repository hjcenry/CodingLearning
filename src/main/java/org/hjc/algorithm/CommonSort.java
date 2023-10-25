package org.hjc.algorithm;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/25 22:32
 **/
public class CommonSort {

    public static void main(String[] args) {
        CommonSort commonSort = new CommonSort();
        commonSort.run();
    }

    public void run() {
        // 随机数组
//        int arrSize = 10000;
//        int[] arr = new int[arrSize];
//        for (int i = 0; i < arrSize; i++) {
//            arr[i] = (int) Math.round(Math.random() * 1000);
//        }
        int[] arr = new int[]{5, 1, 3, 4, 6, 7, 8};

        System.out.println("原始数组：");
        System.out.println(JSON.toJSONString(Collections.singletonList(arr)));

        // 冒泡排序
        int[] arr1 = arr.clone();
        long start = System.currentTimeMillis();
        bubbleSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println("冒泡排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr1)));
        // 快速排序
        int[] arr2 = arr.clone();
        start = System.currentTimeMillis();
        quickSort(arr2, 0, arr2.length - 1);
        end = System.currentTimeMillis();
        System.out.println("快速排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr2)));
        // 插入排序
        int[] arr3 = arr.clone();
        start = System.currentTimeMillis();
        insertSort(arr3);
        end = System.currentTimeMillis();
        System.out.println("插入排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr3)));


    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        int tmp;
        // 冒泡优化：如果在某一次冒泡过程中，没有发生交换，则证明数组已经是有序的了
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    flag = false;
                    tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;
        int baseIndex = left;
        int base = arr[baseIndex];

        int tmp;
        while (left < right) {
            while (arr[right] >= base && left < right) {
                // 以base为基准，从右往左找到比基准小的数
                right--;
            }
            while (arr[left] <= base && left < right) {
                // 以base为基准，从左往右找到比基准大的数
                left++;
            }
            if (left < right) {
                // 左右还没相交，大小对换位置
                tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }

        // 这一轮快排结束，交换相遇点与基准点
        tmp = arr[baseIndex];
        arr[baseIndex] = arr[right];
        arr[right] = tmp;

        //数组“分两半”,再重复上面的操作
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public void insertSort(int[] arr) {

    }
}
