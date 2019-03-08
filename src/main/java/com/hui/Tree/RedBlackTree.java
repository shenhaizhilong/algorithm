package com.hui.Tree;
import java.util.LinkedList;

/**
 * @author: shenhaizhilong
 * @date: 2018/7/18 14:00
 */
public class RedBlackTree {
    // Red-black mechanics

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    private int size = 0;
    // leaf node is black, NIL is a sentinel, we can use it to save space
    public static final RBTreeNode NIL = new RBTreeNode(BLACK);

    //root node
    private RBTreeNode root = NIL;


    public RBTreeNode searchTree(int k)
    {
        return searchTree(root, k);
    }
    /**
     * recursive search int k in binary tree, O(lg(n))
     *
     * @param treeNode
     * @param k
     * @return
     */
    private RBTreeNode searchTree(RBTreeNode treeNode, int k) {
        if (treeNode == null  || treeNode == NIL || treeNode.val == k) return treeNode;
        if (k < treeNode.val)
            return searchTree(treeNode.left, k);
        return searchTree(treeNode.right, k);
    }


    public RBTreeNode iterativeSearchTree( int k) {
        return iterativeSearchTree(root, k);
    }
    /**
     * iterative search int k in binary tree, O(lg(n))
     *
     * @param treeNode
     * @param k
     * @return
     */
    private RBTreeNode iterativeSearchTree(RBTreeNode treeNode, int k) {
        while (treeNode != NIL && treeNode.val != k) {
            treeNode = (k < treeNode.val) ? treeNode.left : treeNode.right;
        }

        return treeNode;
    }

    public RBTreeNode getMin()
    {
        return getMin(root);
    }

    /**
     * get min value in binary tree, O(lg(n))
     *
     * @param treeNode
     * @return
     */
    private RBTreeNode getMin(RBTreeNode treeNode) {
        while (treeNode != NIL && treeNode.left != NIL) {
            treeNode = treeNode.left;
        }
        return treeNode;
    }


    public RBTreeNode getMax()
    {
        return getMax(root);
    }
    /**
     * get max value in binary tree, O(lg(n))
     *
     * @param treeNode
     * @return
     */
    private RBTreeNode getMax(RBTreeNode treeNode) {
        while (treeNode != NIL && treeNode.right != NIL) {
            treeNode = treeNode.right;
        }
        return treeNode;
    }
    /**
     *  get successor for a tree x,
     *  if x.right is not null,
     *  then the successor should be min(x.right)
     *
     *  if x.right is null:
     *  if x == (x.parent).left
     *  then the successor should be x.parent
     *  if x == (x.parent).right, we should continue the loop until x == (x.parent).left
     *  O(lg(n))
     * @param x
     * @return
     */
    public RBTreeNode getSuccessor(RBTreeNode x)
    {
        if(x == null || x == NIL)return x;
        if(x.right != NIL)
        {
            return getMin(x.right);
        }

        RBTreeNode parent = x.parent;
        while (parent != NIL && x == parent.right)
        {
            x = parent;
            parent = parent.parent;
        }

        return parent;
    }

    /**
     *
     * https://blog.csdn.net/i_love_blog/article/details/60343787
     * 前驱节点
     * 1. 若一个节点有左子树，那么该节点的前驱节点是其左子树中val值最大的节点
     * 2. 若一个节点没有左子树，那么判断该节点和其父节点的关系
     *   2.1 若该节点是其父节点的右边孩子，那么该节点的前驱结点即为其父节点。
     *   2.2 若该节点是其父节点的左边孩子，那么需要沿着其父亲节点一直向树的顶端寻找，直到找到一个节点P，
     * P节点是其父节点Q的右边孩子（可参考例子2的前驱结点是1），
     * 那么Q就是该节点的前驱节点（按参考例子P结点为5，Q结点为1，P结点是Q结点的右边的孩子），所以Q1就是2结点的前驱节点
     * @param x
     * @return
     */
    public RBTreeNode getPredecessor(RBTreeNode x)
    {
        if(x == null || x == NIL)return NIL;
        if(x.left != NIL)
        {
            return getMax(x.left);
        }

        RBTreeNode parent = x.parent;
        while (parent != NIL && x == parent.left)
        {
            x =  parent;
            parent = parent.parent;
        }

        return parent;


    }


   public void printTreeInOrder()
   {
       printTreeInOrder(root);
   }

    /**
     * 算法导论 第十二章
     * 二叉搜索树
     * <p>
     * In order print tree, O(n)
     *
     * @param treeNode
     */
    private void printTreeInOrder(RBTreeNode treeNode) {
        if (treeNode != null && treeNode != NIL) {
            printTreeInOrder(treeNode.left);
            System.out.println(treeNode.val);
            printTreeInOrder(treeNode.right);
        }
    }



    public void printTreeLevelOrder()
    {

        printTreeLevelOrder(root);
    }


    private void printTreeLevelOrder(RBTreeNode treeNode)
    {

        if(treeNode == NIL || treeNode == null)return;
        LinkedList<RBTreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty())
        {
            RBTreeNode temp = queue.pollFirst();
            System.out.println(temp.val);
            if(temp.left != NIL)
                queue.add(temp.left);
            if(temp.right != NIL)
                queue.add(temp.right);
        }
    }


    //we assume that x.right is not NIL and root's parent is NIL
    private void leftRotate(RBTreeNode x)
    {
        if(x == NIL || x.right == NIL)return;
        RBTreeNode y = x.right;  // set y
        x.right = y.left;      // turn y's left subtree into x's right subtree

        if (y.left != NIL)
        {
            y.left.parent = x;
        }

        y.parent = x.parent;    //link x's parent to y

        if(x.parent == NIL)    // x is root
        {
            root = y;
        }else if (x == x.parent.left){   //x is left child
            x.parent.left = y;
        }else {                           // x is right child
            x.parent.right = y;
        }

        y.left = x;   // put x on y's left
        x.parent = y;


    }

    //we assume that y.left is not NIL and root's parent is NIL
    private void rightRotate(RBTreeNode y)
    {
        if(y == NIL || y.left == NIL)return;
        RBTreeNode x = y.left;
        y.left = x.right;
        if(x.right != NIL)
        {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if(y.parent == NIL)           // y is root
        {
            root = x;
        }else if(y == y.parent.left)  // y is left child
        {
            y.parent.left = x;
        }else {                       // y is right child
            y.parent.right = x;
        }

        x.right = y;               // put y on x's right
        y.parent = x;             // set y's parent to x


    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return root == NIL;
    }

    public void insert(int key)
    {
        RBTreeNode z = new RBTreeNode(key);
        insert(z);

    }

    private void insert(RBTreeNode z)
    {
        RBTreeNode parent = NIL;
        RBTreeNode x = root;

        //find z's location and parent
        while (x != NIL)
        {
            parent = x;
            if(z.val < x.val)
            {
                x = x.left;
            }else {
                x = x.right;
            }

        }

        z.parent = parent; // if root is null, then we set z.parent = NIL
        if(parent == NIL)
        {
            root = z;
        }else if(z.val < parent.val)
        {
            parent.left = z;
        }else {
            parent.right = z;
        }

        z.left = NIL;
        z.right = NIL;
        z.color = RED; // set it color to red
        insertFixUp(z);

        size++;
    }

    private void insertFixUp(RBTreeNode z)
    {
        RBTreeNode y;
        while (z.parent.color == RED)   //z.parent.color is red, z can't be root node
        {
            if(z.parent == z.parent.parent.left)
            {
                y = z.parent.parent.right;  // uncle node
                if(y.color == RED)
                {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                }else
                {
                    if(z == z.parent.right)
                    {
                        z = z.parent;
                        leftRotate(z);
                    }

                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);

                }


            }else {
                y = z.parent.parent.left; // uncle node
                if(y.color == RED)
                {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                }else if(z == z.parent.left)
                {
                    z = z.parent;
                    rightRotate(z);
                }

                z.parent.color = BLACK;
                z.parent.parent.color = RED;
                leftRotate(z.parent.parent);
            }
        }
        root.color = BLACK;

    }

    /**
     *  we use newnode replace original node, and fix the relationship with it's parent.
     * @param original
     * @param newNode
     */
    private void transplant(RBTreeNode original, RBTreeNode newNode)
    {
        if(original == null)
            throw new IllegalArgumentException("original can't be null");
        if(original.parent == NIL)
        {
            root = newNode;
        }else if(original == original.parent.left)
        {
            original.parent.left = newNode;
        }else {
            original.parent.right = newNode;
        }
        newNode.parent = original.parent;

    }

    public void delete(int k)
    {
        RBTreeNode z = iterativeSearchTree(root, k);
        if(z == null)
            return;
        delete(z);
        size--;
    }

    private void delete(RBTreeNode z)
    {
        RBTreeNode y = z;
        RBTreeNode x;
        boolean originalColor = y.color;

        if(z.left == NIL)  // left child is NIL, we use z.right child replace z
        {
            x = z.right;
            transplant(z, z.right);
        }else if(z.right == NIL)   // right child is NIL, we use z.left child replace z
        {
            x = z.left;
            transplant(z, z.left);
        }else {
            y = getMin(z.right);
            originalColor = y.color;
            x = y.right;
            if(y.parent != z)
            {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if(originalColor == BLACK)
        {
            RBDeleteFixUp(x);
        }
    }

    private void RBDeleteFixUp(RBTreeNode x)
    {
        RBTreeNode w;
        while (x != root && x.color == BLACK)
        {
            if(x == x.parent.left)
            {
                w = x.parent.left;
                if(w.color == RED)
                {
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }

                if(w.left.color == BLACK && w.right.color == BLACK)
                {
                    w.color = RED;
                    x = x.parent;
                }else{
                    if(w.right.color == BLACK)
                    {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }

                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            }else {
                w = x.parent.right;
                if(w.color == RED)
                {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }

                if(w.right.color == BLACK && w.left.color == BLACK)
                {
                    w.color = RED;
                    x = x.parent;
                }else{
                    if(w.left.color == BLACK)
                    {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }

                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
    }
    public static class RBTreeNode
    {
         int val;
        RBTreeNode left;
        RBTreeNode right;
        RBTreeNode parent;
         //color of black node is true, color of red node is false
         boolean color = RED;

         public RBTreeNode(int val)
         {
             this.val = val;
         }

         public RBTreeNode (boolean color)
         {
             this.color = color;
         }
        public RBTreeNode(int val, boolean color)
        {
            this.val = val;
            this.color = color;
        }
    }
}
