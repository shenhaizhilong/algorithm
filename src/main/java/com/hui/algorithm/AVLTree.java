package com.hui.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/22 19:41
 */
public class AVLTree<T extends Comparable<? super T>> {

    private AVLTreeNode<T> root;
    private int size = 0;


    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return root == null;
    }


    /**
     *
     * T1, T2 and T3 are subtrees of the tree
     * rooted with y (on the left side) or x (on
     * the right side)
     *      y                                x
     *     / \     Right Rotation          /  \
     *    x   T3   – – – – – – – >  T1   y
     *   / \       < - - - - - - -            / \
     *  T1  T2     Left Rotation            T2  T3
     * Keys in both of the above trees follow the
     * following order
     *  keys(T1) < key(x) < keys(T2) < key(y) < keys(T3)
     * So BST property is not violated anywhere.
     *
     * @param x
     * @return
     */
    //we assume that x.right is not NIL and root's parent is NIL
    private AVLTreeNode<T> leftRotate(AVLTreeNode<T> x)
    {
        if( x == null ||  x.right == null)return x;
        AVLTreeNode y = x.right;  // set y
        x.right = y.left;      // turn y's left subtree into x's right subtree
        y.left = x;   // put x on y's left

        updateHeight(x);
        updateHeight(y);
        return y;

    }

    private AVLTreeNode<T> leftRightRotate(AVLTreeNode<T> N)
    {
        if(N == null || N.left == null)return N;
        N.left = leftRotate(N.left);
        return rightRotate(N);

    }
    //we assume that y.left is not NIL and root's parent is NIL
    private AVLTreeNode<T> rightRotate(AVLTreeNode<T> y)
    {
        if(y == null || y.left == null)return y;
        AVLTreeNode x = y.left;
        y.left = x.right;
        x.right = y;               // put y on x's right
        updateHeight(y);
        updateHeight(x);
        return x;

    }

    private AVLTreeNode<T> rightLeftRotate(AVLTreeNode<T> N)
    {
        if(N == null || N.right == null)return N;
        N.right = rightRotate(N.right);
        return leftRotate(N);
    }
    /**
     * update height of k
     * @param k
     */
    private void updateHeight(AVLTreeNode<T> k)
    {
        if(k != null)
            k.height = max(getHeight(k.left), getHeight(k.right)) +1;
    }

    public int getHeight(AVLTreeNode<T> node)
    {
        return node == null ? 0: node.height;
    }

    /**
     *  get balance factor of AVLTreeNode z
     * @param z
     * @return
     */
    private int getHeightDiff(AVLTreeNode<T> z)
    {
        return (z == null) ? 0: getHeight(z.left) - getHeight(z.right);
    }

    private AVLTreeNode<T> rebalance(AVLTreeNode<T> z)
    {
        if(z == null)return null;
        int diff = getHeightDiff(z);
        if(diff > 1)
        {
            if(getHeightDiff(z.left) > 0)
            {
               z = rightRotate(z);
            }else {
                z =    leftRightRotate(z);
            }
        }else if(diff < -1)
        {
            if(getHeightDiff(z.right) < 0)
            {
               z = leftRotate(z);
            }else {
               z = rightLeftRotate(z);
            }
        }

        return z;
    }

    private int max(int a, int b)
    {
        return (a> b) ? a: b;
    }

    public void insert(T z)
    {
        root = insert(root, z);
        size++;
    }

    private AVLTreeNode<T> insert(AVLTreeNode<T> current, T z)
    {

        if(current == null)
        {
            return new AVLTreeNode<>(z);
        }
        int cmp = z.compareTo(current.val);
        if(cmp < 0)
        {
            current.left = insert(current.left, z);
        }else
        {
            current.right = insert(current.right,z);
        }
        current = rebalance(current);
        updateHeight(current);
        return current;

    }


    public T findMin()
    {
        AVLTreeNode<T> min = findMin(root);
        return (min == null) ? null : min.val;
    }

    private AVLTreeNode<T> findMin(AVLTreeNode<T> current)
    {
        while (current != null && current.left !=null)
        {
            current = current.left;
        }
        return current;
    }


    public T findMax()
    {
        AVLTreeNode<T> max = findMax(root);
        return (max == null) ? null: max.val;
    }
    private AVLTreeNode<T> findMax(AVLTreeNode<T> current)
    {
        while (current != null && current.right != null)
        {
            current = current.right;
        }
        return current;
    }

    public void remove(T x)
    {
        root = remove(x, root);
    }

    private AVLTreeNode<T> remove(T x, AVLTreeNode<T> current)
    {
        if(current == null)return null;
        int cmp = x.compareTo(current.val);
        if(cmp < 0)
        {
            current.left = remove(x, current.left);
        }else if(cmp > 0)
        {
            current.right = remove(x, current.right);
        }else if(current.left != null && current.right != null)
        {
            AVLTreeNode<T> min = findMin(current.right);
           current.val = min.val;
           current.right = remove(min.val, current.right);

        }else {
            current = (current.left == null)? current.right : current.left;
        }
        current =  rebalance(current);
        updateHeight(current);
        return current;
    }


    public void deleteMin()
    {
        if(isEmpty()) throw new NullPointerException("tree is null");
        root = deleteMin(root);
        size--;
    }


    private AVLTreeNode<T> deleteMin(AVLTreeNode<T> current)
    {
        if(current == null)return null;
        if(current.left == null)
        {
            return current.right;
        }

        current.left = deleteMin(current.left);
        current = rebalance(current);
        updateHeight(current);
        return current;
    }

    public void deleteMax()
    {
        if(isEmpty()) throw new NullPointerException("tree is null");
        root =  deleteMax(root);
        size--;
    }

    private AVLTreeNode<T> deleteMax(AVLTreeNode<T> current)
    {
        if(current == null)return null;
        if(current.right == null)
            return current.left;

        current.right = deleteMax(current.right);
        current = rebalance(current);
        updateHeight(current);
        return current;
    }

    public void levelOrder(List<AVLTreeNode<T>>  list)
    {

        levelOrder(root, list);
    }

    private List<AVLTreeNode<T>>  levelOrder(AVLTreeNode<T> treeNode, List<AVLTreeNode<T>> list)
    {

        list.clear();
        if(treeNode == null) return list;
        LinkedList<AVLTreeNode<T>> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty())
        {
            AVLTreeNode<T> temp = queue.pollFirst();
            list.add(temp);
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }

        return list;
    }


    public static class AVLTreeNode<T extends Comparable<? super T>> {

        private  T val;
        private int height;
        AVLTreeNode left;
        AVLTreeNode right;

        AVLTreeNode(T x) {
            val = x;
            height = 1;
        }

        @Override
        public String toString() {
            return "(v: " + this.val +",h: " + this.height +")";
        }
    }
}
