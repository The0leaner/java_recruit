package cn.hinus.datastructure;

import java.util.ArrayList;

/**
 * Created by hinus on 2017/1/11.
 */
public class Stack<T> {
    private ArrayList<T> list;

    public Stack(int size) {
        this.list = new ArrayList<T>(size);
    }

    public T getTop() {
        if (isEmpty())
            return null;

        return list.get(list.size() - 1);
    }

    public void push(T t) {
        this.list.add(t);
    }

    public T pop() {
        if (isEmpty())
            return null;

        return list.remove(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
