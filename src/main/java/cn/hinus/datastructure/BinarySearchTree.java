package cn.hinus.datastructure;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * Created by hinus on 2017/1/11.
 */


public class BinarySearchTree<T extends Comparable<T>> {
    public static void main(String args[]) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(3);
        bst.insert(2);
        bst.insert(1);
        bst.insert(5);
        bst.insert(4);
        bst.insert(6);

        Iterator<Integer> it = bst.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }

    public Node<T> root;

    public boolean insert(T i) {
        if (root == null) {
            root = new Node(i);
            return true;
        }

        Node<T> current = root;
        while (true) {
            if (i.compareTo(current.data) < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    Node<T> n = new Node(i);
                    n.parent = current;
                    current.left = n;
                    break;
                }
            } else {
                if (current.right != null)
                    current = current.right;
                else {
                    Node<T> n = new Node(i);
                    n.parent = current;
                    current.right = n;
                    break;
                }
            }
        }
        return true;
    }

    public boolean contains(T i) {
        if (root == null)
            return false;
        Node<T> current = root;
        while (true) {
            if (i.compareTo(current.data) < 0) {
                if (current.left != null) {
                    current = current.left;
                } else {
                    return false;
                }
            } else if (i.compareTo(current.data) > 0) {
                if (current.right != null) {
                    current = current.right;
                } else {
                    return false;
                }
            } else
                return true;
        }
    }

    public void midOrder(Node n) {
        if (n.left != null)
            midOrder(n.left);
        System.out.println(n.data);
        if (n.right != null)
            midOrder(n.right);
    }

    public void postOrder(Node n) {
        if (n.left != null)
            postOrder(n.left);
        if (n.right != null)
            postOrder(n.right);
        System.out.println(n.data);
    }

    public void midOrderWithoutRecurs() {
        if (root == null)
            return;

        Stack<Node<T>> s = new Stack<>(64);
        Node<T> current;

        s.push(root);

        while (! s.isEmpty()) {
            current = s.getTop();
            if (current.state == 0) {
                if (current.left != null)
                    s.push(current.left);
                current.state = 1;
            }
            else if (current.state == 1) {
                System.out.println(current.data);
                current.state = 2;
            }
            else if (current.state == 2) {
                if (current.right != null)
                    s.push(current.right);
                current.state = 3;
            }
            else if (current.state == 3) {
                s.pop();
                current.state = 0;
            }
        }
    }

    public void preOrder(Node<T> n) {
        System.out.println(n.data);

        if (n.left != null)
            preOrder(n.left);
        if (n.right != null)
            preOrder(n.right);
    }

    public void visitByLayer() {
        if (root == null)
            return;

        ArrayDeque<Node<T>> q = new ArrayDeque<>();
        q.addLast(root);

        while (!q.isEmpty()) {
            Node<T> n = q.removeLast();
            System.out.println(n.data);
            if (n.left != null)
                q.push(n.left);

            if (n.right != null)
                q.push(n.right);
        }
    }

    public Node<T> successor(Node<T> n) {
        if (n == null)
            return null;

        Node<T> p;
        if (n.right != null) {
            p = n.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        else {
            p = n.parent;

            while (p != null && p.left != n) {
                n = p;
                p = n.parent;
            }
            return p;
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    public Node<T> firstElement() {
        if (root == null)
            return null;

        Node<T> p = root;
        while (p.left != null)
            p = p.left;

        return p;
    }

    public void remove(Node<T> n) {
        Node<T> p = n.parent;
        Node<T> next, child;

        // 叶子结点，直接删除即可。要考虑待删除结点是root的情况。
        if (n.left == null && n.right == null) {
            if (n == root) {
                root = null;
                return;
            }

            if (n == p.left)
                p.left = null;
            else if (n == p.right)
                p.right = null;
        }
        // 内部结点，把它的后继的值拷进来，然后递归删除它的后继。
        else if (n.left != null && n.right != null) {
            next = successor(n);
            n.data = next.data;
            remove(next);
        }
        // 只有一个孩子的结点，把它的孩子交给它的父结点即可。
        else {
            if (n.left != null)
                child = n.left;
            else
                child = n.right;

            if (n == root) {
                child.parent = null;
                root = child;
                return;
            }

            if (n == p.left) {
                child.parent = p;
                p.left = child;
            }
            else {
                child.parent = p;
                p.right = child;
            }
        }
    }

    private class Itr implements Iterator<T> {
        private Node<T> last;

        public Itr() {
            last = firstElement();
        }

        public boolean hasNext() {
            return last != null;
        }

        public T next() {
            Node<T> r = last;
            last = successor(last);
            return r.data;
        }
    }
}
