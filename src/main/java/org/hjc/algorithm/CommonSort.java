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
        // 选择排序
        int[] arr4 = arr.clone();
        start = System.currentTimeMillis();
        selectSort(arr4);
        end = System.currentTimeMillis();
        System.out.println("选择排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr4)));
        // 堆排序
        int[] arr5 = arr.clone();
        start = System.currentTimeMillis();
        heapSort(arr5);
        end = System.currentTimeMillis();
        System.out.println("堆排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr5)));
        // 归并排序
        int[] arr6 = arr.clone();
        start = System.currentTimeMillis();
        mergeSort(arr6);
        end = System.currentTimeMillis();
        System.out.println("归并排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr6)));
        // 桶排序
        int[] arr7 = arr.clone();
        start = System.currentTimeMillis();
        bucketSort(arr7);
        end = System.currentTimeMillis();
        System.out.println("桶排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr7)));
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

        // 这一轮快排结束，交换相遇点与基准点，把基准数放在对应的位置上
        // 因为要把基准数和相遇点交换，由于if判断包含等于，那么从左往右就会找到比基准数小的数，从右往左就会找到比基准数大的数，会越过基准数
        // 因此当基准点小于相遇点时（基准点取第一位数），必须保证从先移动右指针，再移动左指针；当基准点大于相遇点时（基准点取最后一位位数），必须保证从先移动左指针，再移动右指针
        // 如果一定要随机基准数，有个简单的改进方法，就是随机一位数，与首位，或最后一位先做一下交换
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
        //
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public void selectSort(int[] arr) {

    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public void heapSort(int[] arr) {

    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public void mergeSort(int[] arr) {

    }

    /**
     * 桶排序
     *
     * @param arr
     */
    public void bucketSort(int[] arr) {

    }
}
