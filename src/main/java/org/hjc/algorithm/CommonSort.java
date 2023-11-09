package org.hjc.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        int[] arr = new int[]{4, 6, 7, 2, 9, 8, 3, 5};

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
        mergeSort(arr6, 0, arr6.length - 1);
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
        // 希尔排序
        int[] arr8 = arr.clone();
        start = System.currentTimeMillis();
        shellSort(arr8);
        end = System.currentTimeMillis();
        System.out.println("希尔排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr8)));
        // 基数排序
        int[] arr9 = arr.clone();
        start = System.currentTimeMillis();
        radixSort(arr9);
        end = System.currentTimeMillis();
        System.out.println("基数排序：耗时:" + (end - start));
        System.out.println(JSON.toJSONString(Collections.singletonList(arr9)));
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
        // 从第二个数开始遍历
        for (int i = 1; i < arr.length; i++) {
            // 插入的数
            int insertNum = arr[i];
            // 从后往前寻找插入点
            int insertIndex = 0;
            for (insertIndex = i - 1; insertIndex >= 0; insertIndex--) {
                if (arr[insertIndex] < insertNum) {
                    // 找到比自己还小的数说明自己就插入到这里
                    break;
                }
                // 把当前位置往后挪
                arr[insertIndex + 1] = arr[insertIndex];
            }
            // 最后把要插入的数查到对应位置
            arr[insertIndex + 1] = insertNum;
        }
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public void shellSort(int[] arr) {
        // gap 为步长，每次减为原来的一半。
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 对每一组都执行直接插入排序
            for (int i = 0; i < gap; i++) {
                // 对本组数据执行直接插入排序
                for (int j = i + gap; j < arr.length; j += gap) {
                    // 如果 a[j] < a[j-gap]，则寻找 a[j] 位置，并将后面数据的位置都后移
                    if (arr[j] < arr[j - gap]) {
                        int k;
                        int temp = arr[j];
                        for (k = j - gap; k >= 0 && arr[k] > temp; k -= gap) {
                            arr[k + gap] = arr[k];
                        }
                        arr[k + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 遍历数据
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 从当前位置往后遍历，找到最小数的下标
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 最小数的下标不是第一个数，那么就把最小下标和第一个数做交换
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * 堆排序
     *
     * @param arr
     */
    public void heapSort(int[] arr) {
        // 开始位置是最后一个非叶子结点，即最后一个结点的父结点
        int start = (arr.length - 1) / 2;
        // 调整为大顶堆
        for (int i = start; i >= 0; i--) {
            maxHeap(arr, arr.length, i);
        }
        // 先把数组中第 0 个位置的数和堆中最后一个数交换位置，再把前面的处理为大顶堆
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeap(arr, i, 0);
        }
    }

    /**
     * 转化为大顶堆
     *
     * @param arr   待转化的数组
     * @param size  待调整的区间长度
     * @param index 结点下标
     */
    public void maxHeap(int[] arr, int size, int index) {
        // 左子结点
        int leftNode = 2 * index + 1;
        // 右子结点
        int rightNode = 2 * index + 2;
        int max = index;
        // 和两个子结点分别对比，找出最大的结点
        if (leftNode < size && arr[leftNode] > arr[max]) {
            max = leftNode;
        }
        if (rightNode < size && arr[rightNode] > arr[max]) {
            max = rightNode;
        }
        // 交换位置
        if (max != index) {
            int temp = arr[index];
            arr[index] = arr[max];
            arr[max] = temp;
            // 因为交换位置后有可能使子树不满足大顶堆条件，所以要对子树进行调整
            maxHeap(arr, size, max);
        }
    }

    /**
     * 合并数组
     */
    public void merge(int[] arr, int low, int middle, int high) {
        // 用于存储归并后的临时数组
        int[] temp = new int[high - low + 1];
        // 记录第一个数组中需要遍历的下标
        int i = low;
        // 记录第二个数组中需要遍历的下标
        int j = middle + 1;
        // 记录在临时数组中存放的下标
        int index = 0;
        // 遍历两个数组，取出小的数字，放入临时数组中
        while (i <= middle && j <= high) {
            // 第一个数组的数据更小
            if (arr[i] <= arr[j]) {
                // 把更小的数据放入临时数组中
                temp[index] = arr[i];
                // 下标向后移动一位
                i++;
            } else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }
        // 处理剩余未比较的数据
        while (i <= middle) {
            temp[index] = arr[i];
            i++;
            index++;
        }
        while (j <= high) {
            temp[index] = arr[j];
            j++;
            index++;
        }
        // 把临时数组中的数据重新放入原数组
        for (int k = 0; k < temp.length; k++) {
            arr[k + low] = temp[k];
        }
    }

    /**
     * 归并排序
     */
    public void mergeSort(int[] arr, int low, int high) {
        int middle = (high + low) / 2;
        if (low < high) {
            // 处理左边数组
            mergeSort(arr, low, middle);
            // 处理右边数组
            mergeSort(arr, middle + 1, high);
            // 归并
            merge(arr, low, middle, high);
        }
    }

    /**
     * 基数排序
     *
     * @param arr
     */
    public void radixSort(int[] arr) {
        // 存放数组中的最大数字
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        // 计算最大数字是几位数
        int maxLength = (max + "").length();
        // 用于临时存储数据（0-9）
        int[][] temp = new int[10][arr.length];
        // 用于记录在 temp 中相应的下标存放数字的数量（0-9）
        int[] counts = new int[10];
        // 根据最大长度的数决定比较次数（从低位往高位以此比较）
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 每一个数字分别计算余数
            for (int value : arr) {
                // 计算余数
                int remainder = value / n % 10;
                // 把当前遍历的数据放到指定的数组中
                temp[remainder][counts[remainder]] = value;
                // 记录数量
                counts[remainder]++;
            }
            // 记录取的元素需要放的位置
            int index = 0;
            // 把数字取出来
            for (int k = 0; k < counts.length; k++) {
                // 记录数量的数组中当前余数记录的数量不为 0
                if (counts[k] != 0) {
                    // 循环取出元素
                    for (int l = 0; l < counts[k]; l++) {
                        arr[index] = temp[k][l];
                        // 记录下一个位置
                        index++;
                    }
                    // 把数量置空
                    counts[k] = 0;
                }
            }
        }
    }

    /**
     * 桶排序
     *
     * @param arr
     */
    public void bucketSort(int[] arr) {
        int n = arr.length;
        int mn = arr[0], mx = arr[0];
        // 找出数组中的最大最小值
        for (int i = 1; i < n; i++) {
            mn = Math.min(mn, arr[i]);
            mx = Math.max(mx, arr[i]);
        }
        // 每个桶存储数的范围大小，使得数尽量均匀地分布在各个桶中，保证最少存储一个
        int size = (mx - mn) / n + 1;
        // 桶的个数，保证桶的个数至少为1
        int cnt = (mx - mn) / size + 1;
        // 声明cnt个桶
        List<Integer>[] buckets = new List[cnt];
        for (int i = 0; i < cnt; i++) {
            buckets[i] = new ArrayList<>();
        }
        // 扫描一遍数组，将数放进桶里
        for (int k : arr) {
            int idx = (k - mn) / size;
            buckets[idx].add(k);
        }
        // 对各个桶中的数进行排序，这里用库函数快速排序
        for (int i = 0; i < cnt; i++) {
            // 默认是按从小打到排序
            // 这里可以用任何的方式排序，这里直接用List的sort方法
            buckets[i].sort(null);
        }
        // 依次将各个桶中的数据放入返回数组中
        int index = 0;
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }
}
