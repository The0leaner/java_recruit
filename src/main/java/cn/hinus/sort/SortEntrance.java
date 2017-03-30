package cn.hinus.sort;

import java.util.Arrays;

/**
 * Created by hinus on 2017/3/9.
 */
public class SortEntrance {
    public static void main(String args[]) {
        int[] myArray = {2, 4, 3, 7, 5, 8, 6, 1};
        //Sorter s = new BubbleSorter();
        //Sorter s = new ChooseSort();
        //Sorter s = new HeapSort();
        //Sorter s = new QuickSort();
        Sorter s = new MergeSort();
        //Sorter s = new InsertionSorter();
        s.sort(myArray);
        Arrays.stream(myArray).forEach(System.out::println);
    }
}
