package cn.hinus.sort;

/**
 * Created by hinus on 2017/3/9.
 */
public class BubbleSorter implements Sorter {
    public void bubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean changed = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                    changed = true;
                }
            }

            if (!changed)
                break;
        }
    }
}
