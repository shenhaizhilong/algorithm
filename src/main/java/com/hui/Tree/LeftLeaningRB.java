package com.hui.Tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Left leaning Red-Black trees <<Robert Sedgewick>>
 *
 *     http://www.cs.princeton.edu/~rs/talks/LLRB/LLRB.pdf
 *     http://www.cs.princeton.edu/~rs/talks/LLRB/RedBlack.pdf
 *     https://algs4.cs.princeton.edu/33balanced/RedBlackBST.java
 *     https://stackoverflow.com/questions/15455042/can-anyone-explain-the-deletion-of-left-lean-red-black-tree-clearly
 *     http://www.read.seas.harvard.edu/~kohler/notes/llrb.html
 *
 *     One way to view red-black BST algorithms is as maintaining the following invariant properties
 * under insertion and deletion:
 * 1.    the color of  incoming link either is black or red
 * 2.    root node is black
 * 3.    Nil node is black
 * 4.    No path from the root to the bottom contains two consecutive red links.
 * 5.	 The number of black links on every such path is the same.
 *
 *  Represent 2-3-4 tree as a Binary search Tree
 *  Use "internal" red edges for 3- and 4- nodes
 *  Require that 3-nodes be left-leaning
 *
 * @author: shenhaizhilong
 * @date: 2018/7/23 13:08
 */
public class LeftLeaningRB<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;


    private class Node{
        private Key key;
        private Value val;
        private Node left,right;

        // a color field that can be set to the value RED to
        //represent the color of the incoming  link
        private boolean color;

        Node(Key key, Value val)
        {
            this.key = key;
            this.val = val;
            this.color = RED;  // new node are always red
        }
    }

    public Value search(Key key) {
        if(key == null)throw new  IllegalArgumentException("key can't be null");
        return search(root, key);
    }


    private Value search(Node x, Key key)
    {
        while (x != null)
        {
            int cmp = key.compareTo(x.key);
            if(cmp == 0)return x.val;
            x = (cmp < 0)? x.left: x.right;
        }

        return null;
    }








    private boolean isRed(Node h)
    {
        return h == null ? BLACK: h.color;
    }

    public void insert(Key key, Value value)
    {
        if(key == null)throw new  IllegalArgumentException("key can't be null");
        if(value == null){
            delete(key);
            return;
        }
        root = insert(root, key, value);
        root.color = BLACK;
    }



    private Node insert(Node h, Key key, Value value)
    {

        //insert a new node at the bottom
        if(h == null)return new Node(key, value);




        int cmp = key.compareTo(h.key);
        if(cmp == 0)
        {
            h.val = value;
        }else if(cmp < 0){
            h.left = insert(h.left, key,value);
        }else {
            h.right = insert(h.right, key, value);
        }

        // enforce left leaning condition, fix right-leaning reds on the way up
        if(isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);

        //fix two reds in a row on the way up
        if(isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);

        // split a 4-node
        if(isRed(h.left) && isRed(h.right)) flipColor(h);
        return h;

    }




    public boolean isEmpty()
    {
        return root == null;
    }


    /**
     * find the min key's key
     * @return
     */
    public Key min()
    {
        Node m = min(root);
        return (m == null) ? null : m.key;
    }

    private Node min(Node x)
    {
        while (x != null && x.left != null)
        {
            x = x.left;
        }
        return x;
    }


    /**
     * find the max key's Key
     * @return
     */
    public Key max()
    {
        Node m = max(root);
        return (m == null)? null : m.key;
    }

    private Node max(Node x)
    {
        while (x != null && x.right != null)
        {
            x = x.right;
        }
        return x;
    }


    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else           return x.key;
    }

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else           return x;
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else           return x.key;
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else           return x;
    }


    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return search(key) != null;
    }


    public List<Key> levelOrder()
    {
        return levelOrder(root);
    }

    List<Key> list = new ArrayList<>();
    private List<Key> levelOrder(Node x)
    {
        if(x == null)return list;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(x);
        while (!queue.isEmpty())
        {
            Node xx = queue.pollFirst();
            list.add(xx.key);
            if(xx.left != null)
            {
                queue.add(xx.left);
            }
            if(xx.right != null)
            {
                queue.add(xx.right);
            }



        }

        return list;

    }

    /**
     *
     * delete the smallest key and value from the tree
     * @throws NoSuchElementException if the tree is empty
     */
    public void deleteMin()
    {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        //if both children of root are black set root to red
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if(!isEmpty())root.color = BLACK;
    }

    /**
     * delete the key value pair with the min key in tree h
     * @param h
     * @return
     */
    private Node deleteMin(Node h)
    {
        if(h.left == null)
            return null;
        if(!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return fixUp(h);
    }


    /**
     * Removes the largest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax()
    {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        //if both children of root are black set root to red
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if(!isEmpty())root.color = BLACK;
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h)
    {
        if(isRed(h.left))
            h = rotateRight(h);
        if(h.right == null)
            return null;
        if(!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right =  deleteMax(h.right);
        return fixUp(h);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if(!contains(key))return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if(!isEmpty())
        {
            root.color = BLACK;
        }

    }

    private Node delete(Node h, Key key)
    {
        int cmp = key.compareTo(h.key);
        if(cmp < 0)
        {
            if(!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }else {
            if(isRed(h.left))
                h = rotateRight(h);
            if(cmp == 0 && (h.right == null))
                return null;
            if(!isRed(h.right) && !isRed(h.right.left))
            {
                h = moveRedRight(h);
            }

            if(cmp == 0)
            {
                // replace current node with successor key, value
                h = min(h.right);
                // delete successor
                h.right = deleteMin(h.right);
            }else {
                h.right = delete(h.right, key);
            }
        }

        return fixUp(h);
    }


    private Node rotateLeft(Node h)
    {

        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }


    /**
     * A color flip obviously does not change the
     * number of black links on any path from the root to the bottom, but it may introduce two consecutive
     * red links higher in the tree, which must be corrected.
     *
     * key points:
     *   1. preserves prefect black -lin balance
     *   2. passes a RED link up the tree
     *   3. reduces problem to inserting (that link) into parent
     * @param h
     */
    private void flipColor(Node h)
    {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * restore red-black tree invariant
     * no right red nodes
     * no  successive red nodes
     * @param h
     * @return
     */
    private Node fixUp(Node h)
    {
        if(isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);

        if(isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);

        if(isRed(h.left) && isRed(h.right))
            flipColor(h);
        return h;
    }


    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h)
    {
        flipColor(h);
        if(isRed(h.right.left))
        {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColor(h);
        }
        return h;
    }


    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h)
    {
        flipColor(h);
        if(isRed(h.left.left))
        {
            h = rotateRight(h);
            flipColor(h);
        }
        return h;
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }


}
