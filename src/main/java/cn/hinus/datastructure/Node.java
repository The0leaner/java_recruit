package cn.hinus.datastructure;

/**
 * Created by hinus on 2017/1/13.
 */
public class Node<T> {
    public T data;
    public Node left;
    public Node right;
    public Node parent;
    public int state;

    public Node(T d) {
        this.data = d;
    }
}
