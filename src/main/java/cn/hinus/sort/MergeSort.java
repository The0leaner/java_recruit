package cn.hinus.sort;

import static java.lang.Integer.min;

/**
 * Created by hinus on 2017/3/22.
 */
public class MergeSort implements Sorter {
    private int[] array2;
    public void sort(int[] arr) {
        array2 = new int[arr.length];

        mergeSort2(arr, 0, arr.length - 1);
    }

    public void mergeSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            mergeSort(arr, begin, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, begin, mid, end);
        }
    }

    public void mergeSort2(int[] arr, int begin, int end) {
        int step = 2, i;
        while (step <= arr.length) {
            i = 0;
            while (step + i <= arr.length) {
                merge(arr, i, i + step / 2 - 1, i + step - 1);
                i += step;
            }
            if (i < arr.length) {
                merge(arr, i, min(i + step / 2 - 1, arr.length - 1), arr.length - 1);
            }
            step *= 2;
        }
        merge(arr, 0, step / 2 - 1, arr.length - 1);
    }

    public void merge(int[] array1, int low, int mid, int high) {
        int i = low, j = mid + 1, k = low;

        while (i <= mid && j <= high) {
            if (array1[i] <= array1[j])
                array2[k++] = array1[i++];
            else
                array2[k++] = array1[j++];
        }

        while (i <= mid)
            array2[k++] = array1[i++];

        while (j <= high)
            array2[k++] = array1[j++];

        for (i = low; i <= high; i++) {
            array1[i] = array2[i];
        }
    }
}
