package cn.hinus.sort;

import java.util.PriorityQueue;

/**
 * Created by hinus on 2017/3/15.
 */
public class ChooseSort implements  Sorter {
    public void sort(int[] arr) {
        int pivot, pos = -1;

        for (int i = 0; i < arr.length; i++) {
            pivot = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                if (pivot > arr[j]) {
                    pivot = arr[j];
                    pos = j;
                }
            }
            arr[pos] = arr[i];
            arr[i] = pivot;
        }
    }
}
