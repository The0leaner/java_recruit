package cn.hinus.sort;

/**
 * Created by hinus on 2017/3/10.
 */
public class InsertionSorter implements Sorter {
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int pivot = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (pivot < arr[j]) {
                    arr[j+1] = arr[j];
                }
                else {
                    break;
                }
            }
            arr[j+1] = pivot;
        }
    }
}
