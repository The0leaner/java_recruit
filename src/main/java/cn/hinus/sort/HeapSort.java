package cn.hinus.sort;

import cn.hinus.datastructure.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by hinus on 2017/3/17.
 */
public class HeapSort implements Sorter{
    public void psort(int[] arr) {
        Heap.buildUpHeap(arr);

        for (int i = arr.length - 1; i > 0; i--) {
            int t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;

            Heap.maxHeapify(arr, i, 0);
        }
    }

    public void sort(int[] arr) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
        }

        int i = 0;
        while (!q.isEmpty()) {
            arr[i++] = q.poll();
        }
    }

    public void sortStudent(Student[] s) {
        PriorityQueue<Student> q = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < s.length; i++) {
            q.add(s[i]);
        }

        int i = 0;
        while (!q.isEmpty()) {
            s[i++] = q.poll();
        }
    }
}

class Student implements Comparable<Student> {
    public int age;
    public String name;

    public int compareTo(Student o) {
        return this.age - o.age;
    }
}