package com.hui.algorithm;

import java.util.*;

/**
 * Introduction to Algorithms, Binary tree
 */

public class BinaryTree {

    private TreeNode root;
    private int size = 0;


    public static class TreeNode {

         int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode parent;

       public   TreeNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }
    }


    /**
     * https://leetcode-cn.com/submissions/detail/3689482/
     * <p>
     * 判断一颗二叉树是不是对称的，等价于判断其左右子树是不是镜像对称的
     * 判断镜对称像即判断对称的位置上的元素是不是相等
     * 两个节点A和B对称等价于:
     * 这两个节点上存储的值相等
     * 节点A的左子树节点和节点B的右子树上的节点是对称的
     * 节点A的右子树节点和节点A的左子树上的节点是对称的
     *
     * @author: shenhaizhilong
     * @date: 2018/7/2 22:25
     */
    public boolean isSymmetric(TreeNode treeNode) {

        if (treeNode == null)
            return true;

        return isTreeSymmetric(treeNode.left, treeNode.right);

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    /**
     *
     * 572. Subtree of Another Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
     *
     * Example 1:
     * Given tree s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * Given tree t:
     *    4
     *   / \
     *  1   2
     * Return true, because t has the same structure and node values with a subtree of s.
     * Example 2:
     * Given tree s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * Given tree t:
     *    4
     *   / \
     *  1   2
     * Return false.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null && t == null)
            return true;

        if(s == null || t == null)
            return false;

        if(s.val == t.val && isSameTree(s, t)){
            return true;
        }
        return isSubtree(s.left,t) || isSubtree(s.right,t);
    }




    /**
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/description/
     * 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     *   3
     *  / \
     * 9  20
     *   /  \
     *  15   7
     * 返回它的最大深度 3 。
     *
     * @param treeNode
     * @return
     */
    public int maxDepth(TreeNode treeNode) {
        if (treeNode == null) return 0;
        int leftDepth = maxDepth(treeNode.left);
        int rightDepth = maxDepth(treeNode.right);
        return leftDepth > rightDepth ? (leftDepth + 1) : (rightDepth + 1);
    }

    public int maxDepth2(TreeNode treeNode)
    {
        if(treeNode == null)return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 1;  // 记录队列里面每一层的元素个数
        int depth = 0;
        TreeNode current = treeNode;
        queue.add(current);
        while (!queue.isEmpty())
        {
            current = queue.pollFirst();
            count--;
            if(current.left != null)
            {
                queue.add(current.left);
            }
            if(current.right != null)
            {
                queue.add(current.right);
            }

            if(count == 0)
            {
                count = queue.size();
                depth++;  //最大深度就是层数
            }

        }
        return depth;

    }


    /**
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/description/
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最小深度  2.
     *
     * @param treeNode
     * @return
     */

    public int minDepth(TreeNode treeNode) {
        if (treeNode == null) return 0;
        int leftDepth = minDepth(treeNode.left);
        int rightDepth = minDepth(treeNode.right);

        //双子树
        if (treeNode.left != null && treeNode.right != null) {
            return leftDepth > rightDepth ? (rightDepth + 1) : (leftDepth + 1);
        }

        //单子树
        if (treeNode.left != null) {
            return leftDepth + 1;
        }


        return rightDepth + 1;
    }

    public int minDepth2(TreeNode treeNode) {
        if (treeNode == null) return 0;
        int leftDepth = minDepth(treeNode.left);
        int rightDepth = minDepth(treeNode.right);

        return (leftDepth == 0 || rightDepth == 0) ? (leftDepth + rightDepth + 1) : (Math.min(leftDepth, rightDepth) + 1);
    }

    // 递归
    public boolean isTreeSymmetric(TreeNode head1, TreeNode head2) {
        if (head1 == null && head2 == null)
            return true;
        if (head1 == null || head2 == null)
            return false;
        if (head1.val != head2.val)
            return false;
        return (isTreeSymmetric(head1.left, head2.right)) && (isTreeSymmetric(head1.right, head2.left));
    }


    // 循环
    public boolean isSymmetric2(TreeNode treeNode) {

        if (treeNode == null)
            return true;

        LinkedList<TreeNode> linkedList = new LinkedList<>();
        TreeNode head1 = treeNode.left;
        TreeNode head2 = treeNode.right;
        linkedList.add(head1);
        linkedList.add(head2);
        while (!linkedList.isEmpty()) {
            head1 = linkedList.poll();
            head2 = linkedList.poll();
            if (head1 == null && head2 == null) continue;
            if (head1 == null || head2 == null) return false;
            if (head1.val != head2.val) return false;
            linkedList.add(head1.left);
            linkedList.add(head2.right);
            linkedList.add(head1.right);
            linkedList.add(head2.left);
        }

        return true;

    }

    private List<Integer> list = new ArrayList<>();

    // 递归

    // https://en.wikipedia.org/wiki/Tree_traversal

    public List<Integer> preOrder()
    {
        list.clear();
        preOrder(root);
        return list;

    }
    private void preOrder(TreeNode treeNode) {

        if (treeNode != null) {
            list.add(treeNode.val);
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }

    }


    public List<Integer> iterativePreOrder()
    {
        return iterativePreOrder(root);
    }


    private List<Integer> iterativePreOrder(TreeNode treeNode)
    {
        list.clear();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(treeNode);
        while (!stack.isEmpty())
        {
            TreeNode current = stack.pop();
            list.add(current.val);

            if(current.right != null)
            {
                stack.push(current.right);
            }
            if(current.left != null)
            {
                stack.push(current.left);
            }


        }
        return list;
    }



    public List<Integer> inOrder()
    {
        list.clear();
        inOrder(root);
        return list;
    }
    /**
     * Introduction to Algorithms chapter 12
     * binary search tree
     * <p>
     * In order print tree, O(n)
     *
     * @param treeNode
     */
    private void inOrder(TreeNode treeNode) {
        if (treeNode != null) {
            inOrder(treeNode.left);
            list.add(treeNode.val);
            inOrder(treeNode.right);
        }
    }

    public List<Integer> iterativeInOrder()
    {
        return iterativeInOrder(root);
    }

    private List<Integer> iterativeInOrder(TreeNode treeNode)
    {
        list.clear();
        if(treeNode == null)return list;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || treeNode != null)
        {
            if(treeNode != null)
            {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }else {
                treeNode = stack.pop();
                list.add(treeNode.val);
                treeNode = treeNode.right;
            }
        }

        return list;
    }
    public List<Integer> levelOrder()
    {

         return levelOrder(root);
    }

    private List<Integer> levelOrder(TreeNode treeNode)
    {

        list.clear();
        if(treeNode == null) return list;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty())
        {
            TreeNode temp = queue.pollFirst();
            list.add(temp.val);
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }

        return list;
    }


    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/50/
     * 二叉树的层次遍历
     *
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     *
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     * @param treeNode
     * @return
     */
    private List<List<Integer>> levelOrder2(TreeNode treeNode)
    {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> leverResults = new ArrayList<>();
        if(treeNode == null) return results;
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 1;
        queue.add(treeNode);
        while (!queue.isEmpty())
        {
            TreeNode temp = queue.pollFirst();
            count--;
            leverResults.add(temp.val);

            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
            if(count == 0)
            {
                results.add(leverResults);
                count = queue.size();
                leverResults = new ArrayList<>();
            }
        }

        return results;
    }

    public List<Integer> postOrder()
    {
        list.clear();
        postOrder(root);
        return list;
    }

    private void postOrder(TreeNode treeNode)
    {
        if(treeNode == null)return;
        postOrder(treeNode.left);
        postOrder(treeNode.right);
        list.add(treeNode.val);
    }

    public List<Integer> iterativePostOrder()
    {

        return iterativePostOrder(root);
    }

    private List<Integer> iterativePostOrder(TreeNode treeNode)
    {
        list.clear();
        if(treeNode == null)return list;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode lastVisitedNode = null;
        while (!stack.isEmpty() || treeNode !=null)
        {
            if(treeNode != null)
            {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }else {
                TreeNode peekNode = stack.peek();
                if(peekNode.right != null && lastVisitedNode != peekNode.right)
                {
                    treeNode = peekNode.right;
                }else {
                    list.add(peekNode.val);
                    lastVisitedNode = stack.pop();
                }
            }
        }

        return list;
    }

    /**
     *
     *
     *
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     *
     * 例如，
     *
     * 给定二叉搜索树:
     *
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     *
     * 和值: 2
     * 你应该返回如下子树:
     *
     *       2
     *      / \
     *     1   3
     * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
     *
     * @param k
     * @return
     */
    public TreeNode searchTree(int k)
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
    private TreeNode searchTree(TreeNode treeNode, int k) {
        if (treeNode == null || treeNode.val == k) return treeNode;
        if (k < treeNode.val)
            return searchTree(treeNode.left, k);
        return searchTree(treeNode.right, k);
    }


    public TreeNode iterativeSearchTree( int k) {
        return iterativeSearchTree(root, k);
    }
    /**
     * iterative search int k in binary tree, O(lg(n))
     *
     * @param treeNode
     * @param k
     * @return
     */
    private TreeNode iterativeSearchTree(TreeNode treeNode, int k) {
        while (treeNode != null && treeNode.val != k) {
            treeNode = (k < treeNode.val) ? treeNode.left : treeNode.right;
        }

        return treeNode;
    }


    public TreeNode getMin()
    {
        return getMin(root);
    }

    /**
     * get min value in binary tree, O(lg(n))
     *
     * @param treeNode
     * @return
     */
    public TreeNode getMin(TreeNode treeNode) {
        while (treeNode != null && treeNode.left != null) {
            treeNode = treeNode.left;
        }
        return treeNode;
    }

    public TreeNode getMax()
    {
        return getMax(root);
    }

    /**
     * get max value in binary tree, O(lg(n))
     *
     * @param treeNode
     * @return
     */
    public TreeNode getMax(TreeNode treeNode) {
        while (treeNode != null && treeNode.right != null) {
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
    public TreeNode getSuccessor(TreeNode x)
    {
        if(x == null)return x;
        if(x.right != null)
        {
            return getMin(x.right);
        }

        TreeNode parent = x.parent;
        while (parent != null && x == parent.right)
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
     * P节点是其父节点Q的右边孩子，那么Q就是该节点的前驱节点
     * @param x
     * @return
     */
    public TreeNode getPredecessor(TreeNode x)
    {
        if(x == null)return x;
        if(x.left !=null)
        {
            return getMax(x.left);
        }

        TreeNode parent = x.parent;
        while (parent != null && x == parent.left)
        {
            x =  parent;
            parent = parent.parent;
        }

        return parent;


    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }


    /**
     *
     *
     * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/description/
     * 701. 二叉搜索树中的插入操作
     *
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
     *
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
     *
     * 例如,
     *
     * 给定二叉搜索树:
     *
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     *
     * 和 插入的值: 5
     * 你可以返回这个二叉搜索树:
     *
     *          4
     *        /   \
     *       2     7
     *      / \   /
     *     1   3 5
     * 或者这个树也是有效的:
     *
     *          5
     *        /   \
     *       2     7
     *      / \
     *     1   3
     *          \
     *           4
     * @param z
     */
    public void insert(int z)
    {
        root = insert(root, z);

        //update the size
        size++;
    }

    private TreeNode insert(TreeNode p, int z)
    {
        TreeNode zz = new TreeNode(z);
        if(p == null)return zz ;
        TreeNode x = p;
        TreeNode parent = null;
        while (x !=null)
        {
            parent = x;
            if(z < x.val)
            {
                x = x.left;
            }else {
                x = x.right;
            }
        }
        zz.parent = parent;
        if(parent == null)
        {
            root = zz;
        }
        else if (zz.val < parent.val)
        {
            parent.left = zz;
        }else {
            parent.right = zz;
        }

        return root;
    }

    private  void transplant(TreeNode original, TreeNode newnode)
    {
        if(original == null)
        {
            throw new IllegalArgumentException("original can't be null");
        }
        if(original.parent == null) // original node is root
        {
            root = newnode;
        }else if(original.parent.left == original)         //original is left child
        {
            original.parent.left = newnode;
        }else {                                           // original is right child
            original.parent.right = newnode;
        }

        if(newnode !=null)                             // restore newnode's parent
        {
            newnode.parent = original.parent;
        }


    }

    public void delete(int k)
    {
        TreeNode z =  iterativeSearchTree(k);
        delete(z);
    }

    /**
     *  delete node z in binary tree, O(lgn)
     * @param z
     */
    private void delete(TreeNode z)
    {
        if(z == null)
            return;

        if(z.left == null)  //if z.left is null, replace z with z.right
        {
            transplant(z, z.right);
        }else if(z.right == null)  // if z.right is null, replace z with z.left
        {
            transplant(z, z.left);
        }else {
            TreeNode y = getMin(z.right);       //z has left and right child, find z's successor y, y.left must be null
            if(y.parent != z)
            {
                transplant(y,y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;

        }

        //update the size
        size--;
    }

    /**
     *
     * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/description/
     *把二叉搜索树转换为累加树
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     *
     * 例如：
     *
     * 输入: 二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     *  二叉树的中序遍历结果是有小到大的，故求每个节点的值为比其大的节点值之和，那我们倒着
     *  中序遍历就好了。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root)
    {
        TreeNode current = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (!stack.isEmpty() || current != null)
        {
            if(current != null)
            {
                stack.push(current);
                current = current.right;
            }else {
                current = stack.pop();
                sum += current.val;
                current.val = sum;
                current = current.left;

            }
        }

        return root;

    }


    // 递归 https://leetcode-cn.com/problems/convert-bst-to-greater-tree/description/
    public TreeNode convertBST2(TreeNode root)
    {
        convert(root,0);
        return root;
    }

    // 先将右子树转换为累加树；并记录右子树的累加和sum;
    // 然后处理根节点，根节点的值 = 根节点值 + sum;
    // 然后转化左子树

    private int convert(TreeNode current, int sum)
    {
        if(current == null)return sum;
        sum = convert(current.right, sum);
        current.val += sum;
        sum = current.val;
        sum = convert(current.left, sum);
        return sum;
    }


    /**
     *
     * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
     *
     * 验证二叉搜索树，
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     *
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     * 示例 2:
     *
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     *
     *
     *
     * @param root
     * @return
     */

    public boolean isValidBST(TreeNode root) {
       List<Integer> list = inOrder();  //中序遍历后数据有序排列，故比较相邻的元素即可
        for (int i = 0; i < list.size() -1; i++) {
            if(list.get(i) >= list.get(i+1))return false;
        }
        return true;

    }

    // 中序遍历， 只需要比较前面那个节点与当前节点的值就行
    public boolean isValidBST2(TreeNode treeNode)
    {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode current = treeNode, pre = null;
        while (!stack.isEmpty() || current != null)
        {
            while (current != null)
            {
                stack.push(current);
                current = current.left;
            }

            TreeNode t = stack.pop();
            if(pre != null && t.val <= pre.val)return false;
            pre = t;
            current = t.right;

        }
        return true;
    }


    /**
     * https://www.jianshu.com/p/958e35186e6b
     * 我们可以设置上下bound，递归左右子树时，为它们设置最大值，最小值，并且不可以超过。
     *
     * 注意：下一层递归时，需要把本层的up 或是down继续传递下去。相当巧妙的算法。
     * @param treeNode
     * @return
     */
    public boolean isValidBST3(TreeNode treeNode)
    {
        if(treeNode == null)return true;
        return dfs(treeNode, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode treeNode, long min, long max)
    {
        if(treeNode == null)return true;
        if(treeNode.val <= min || treeNode.val >= max)return false;
        return dfs(treeNode.left, min, root.val) && dfs(treeNode.right, root.val, max);
    }


    /**
     *  将有序数组转换为二叉搜索树
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     *
     * 给定有序数组: [-10,-3,0,5,9],
     *
     * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
     *  采用分治法,选择中间元素创建 根节点，那么剩下的分为两个部分，可以看作是两个数组，
     *  这样剩下的在根节点左边的就是左子树，在根节点右边的为右子树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBST(nums, 0, nums.length -1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int start, int end)
    {
        if(start > end)return null;
        int middle = (start + end)>>>1;
        TreeNode current = new TreeNode(nums[middle]);  // 选取中间元素作为根
        current.left = sortedArrayToBST(nums, start, middle -1);  //
        current.right = sortedArrayToBST(nums, middle + 1, end); //
        return current;
    }


    /**
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
     *
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     *
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     *
     * Example 1:
     *
     * Input: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * Output: 1
     * Example 2:
     *
     * Input: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * Output: 3
     * Follow up:
     * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
     * @param treeNode
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode treeNode, int k)
    {
        // traverse in order
        List<Integer> myList = iterativeInOrder(treeNode);
        return myList.get(k -1);

    }


    //递归 中序遍历
    private  int count = 0;
    private  int kth = 0;
    public int kthSmallest2(TreeNode treeNode, int k)
    {
        count = k;
        helper(treeNode);
        return kth;

    }

    private void helper(TreeNode treeNode)
    {
        if(treeNode != null)
        {
            helper(treeNode.left);
            count--;
            if(count == 0)
            {
                kth = treeNode.val;
                return;
            }
            helper(treeNode.right);
        }
    }

    // method three
    private int cnt = 0;
    private TreeNode kthSmall = null;
    public int kthSmall3(TreeNode treeNode, int kth)
    {
        if(treeNode == null)return Integer.MAX_VALUE; // not found
        helper(treeNode, kth);
        return kthSmall == null ? Integer.MAX_VALUE: kthSmall.val;
    }

    private void helper(TreeNode treeNode, int kth)
    {
        if(treeNode == null || cnt >= kth)return;
        helper(treeNode.left, kth);
        cnt++;
        if(cnt == kth)
        {
            kthSmall = treeNode;
            return;
        }
        helper(treeNode.right, kth);
    }

    /**
     *
     *
     * https://leetcode.com/problems/invert-binary-tree/description/
     * Invert a binary tree.
     *
     * Example:
     *
     * Input:
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * Output:
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * Trivia:
     * This problem was inspired by this original tweet by Max Howell:
     *
     * Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so f*** off.
     * @param
     * @return
     */
    public TreeNode  invertTree()
    {
        return invertTree(root);
    }
    private TreeNode invertTree(TreeNode root) {

        if(root != null)
        {
            TreeNode right = invertTree(root.left);
            TreeNode left = invertTree(root.right);
            root.left = left;
            root.right = right;
        }
        return root;
    }


    /**
     *https://leetcode.com/problems/merge-two-binary-trees/description/
     *
     * 617. Merge Two Binary Trees
     * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
     *
     * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
     *
     * Example 1:
     * Input:
     * 	Tree 1                     Tree 2
     *           1                         2
     *          / \                       / \
     *         3   2                     1   3
     *        /                           \   \
     *       5                             4   7
     * Output:
     * Merged tree:
     * 	     3
     * 	    / \
     * 	   4   5
     * 	  / \   \
     * 	 5   4   7
     * Note: The merging process must start from the root nodes of both trees.
     * @param t1
     * @param t2
     * @return
     */

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if(t1 == null && t2 == null)return null;

        if(t1 != null && t2== null)
        {
            return t1;
        }
        if(t1 == null && t2 != null)
        {
            return t2;
        }

        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;

    }


    /**
     *
     *
     * https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
     * 783. Minimum Distance Between BST Nodes
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
     *
     * Example :
     *
     * Input: root = [4,2,6,1,3,null,null]
     * Output: 1
     * Explanation:
     * Note that root is a TreeNode object, not an array.
     *
     * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
     *
     *           4
     *         /   \
     *       2      6
     *      / \
     *     1   3
     *
     * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
     * Note:
     *
     * The size of the BST will be between 2 and 100.
     * The BST is always valid, each node's value is an integer, and each node's value is different.
     *
     * @return
     */
    public int minDiffInBST() {
       return inOrderVisist(root);
    }

    private int inOrderVisist(TreeNode root)
    {
        int min = Integer.MAX_VALUE;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode lastVisisted = null;
        while (!stack.isEmpty() || root != null)
        {
          if(root != null)
          {
              stack.push(root);
              root = root.left;
          }else {
              root = stack.pop();
              if(lastVisisted != null && root != lastVisisted)
              {
                  min = Math.min(root.val - lastVisisted.val, min);
                  if(min == 1)return 1;

              }
              lastVisisted = root;

              root = root.right;
          }

        }

        return min;
    }


    /**
     *
     * 530. Minimum Absolute Difference in BST
     * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
     * 与上面那题一样
     *
     *
     * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
     *
     * Example:
     *
     * Input:
     *
     *    1
     *     \
     *      3
     *     /
     *    2
     *
     * Output:
     * 1
     *
     * Explanation:
     * The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
     * Note: There are at least two nodes in this BST.
     *
     */
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;

    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null)
        {
            minDiff = Math.min(minDiff, root.val - prev.val);
            if(minDiff == 1)return;
        }
        prev = root;
        inorder(root.right);
    }


    public int getMinimumDifference(TreeNode root) {
        int min=Integer.MAX_VALUE;
        if(root==null) return min;
        if(root.left!=null) min=Math.min(min, root.val-findMaxValue(root.left));
        if(root.right!=null) min=Math.min(min, findMinValue(root.right)-root.val);
        return Math.min(min, Math.min(getMinimumDifference(root.left), getMinimumDifference(root.right)));
    }

    private int findMinValue(TreeNode root){
        while(root.left!=null)
            root=root.left;
        return root.val;
    }

    private int findMaxValue(TreeNode root){
        while(root.right!=null)
            root=root.right;
        return root.val;
    }


    /**
     *
     *
     * 872. Leaf-Similar Trees
     * DescriptionHintsSubmissionsDiscussSolution
     * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
     *
     *
     *
     * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
     *
     * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     *
     * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     *
     *
     *
     * Note:
     *
     * Both of the given trees will have between 1 and 100 nodes.
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        leafInOrder(root1, list1);
        List<Integer> list2 = new ArrayList<>();
        leafInOrder(root2, list2);
        if(list1.size() != list2.size())return false;
        for (int i = 0; i < list1.size(); i++) {
            if(!list1.get(i).equals(list2.get(i)))return false;
        }
        return true;
    }

    private void leafInOrder(TreeNode root1, List<Integer> leafList)
    {
        if(root1 == null)return;
        leafInOrder(root1.left, leafList);
        if(root1.left == null && root1.right == null)
        {
            leafList.add(root1.val);
        }
        leafInOrder(root1.right, leafList);
    }

    public boolean isBalanced(TreeNode root) {
        if(root != null)
        {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            if(Math.abs(leftHeight - rightHeight) <= 1){
                return isBalanced(root.left) && isBalanced(root.right);
            }
            return false;
        }

        return true;

    }


    /**
     *
     *
     * https://leetcode.com/problems/balanced-binary-tree/description/
     *
     * 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced.
     *
     * For this problem, a height-balanced binary tree is defined as:
     *
     * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * Example 1:
     *
     * Given the following tree [3,9,20,null,null,15,7]:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Return true.
     *
     * Example 2:
     *
     * Given the following tree [1,2,2,3,3,null,null,4,4]:
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * Return false.
     *
     */
    private boolean balacned = true;
    public int height(TreeNode root)
    {
        if(root == null)return 0;
        int left = height(root.left);
        int right = height(root.right);
        balacned = balacned && (Math.abs(left - right) <=1);
        return 1 + Math.max(left, right);
    }
    public boolean isBalanced2(TreeNode root)
    {
        if(root == null)return true;
        height(root);
        return balacned;
    }


    /**
     *
     * https://www.polarxiong.com/archives/LeetCode-563-binary-tree-tilt.html
     *
     *
     * 563. 二叉树的坡度
     * 题目描述提示帮助提交记录社区讨论阅读解答
     * 给定一个二叉树，计算整个树的坡度。
     *
     * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
     *
     * 整个树的坡度就是其所有节点的坡度之和。
     *
     * 示例:
     *
     * 输入:
     *          1
     *        /   \
     *       2     3
     * 输出: 1
     * 解释:
     * 结点的坡度 2 : 0
     * 结点的坡度 3 : 0
     * 结点的坡度 1 : |2-3| = 1
     * 树的坡度 : 0 + 0 + 1 = 1
     * 注意:
     *
     * 任何子树的结点的和不会超过32位整数的范围。
     * 坡度的值不会超过32位整数的范围。
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {

        sum(root);
        return tilt;
    }

    private int tilt;
    // 计算所有节点之和
    private int sum(TreeNode root)
    {
        if(root == null)return 0;
        int left = sum(root.left);     // 左子树之和
        int right = sum(root.right);   // 右子树之和
        tilt += Math.abs(left - right);  // 所有坡度之和
        return left + right + root.val;  // 所有节点之和
    }


    /**
     *
     *
     * 112. Path Sum
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \      \
     * 7    2      1
     * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int sum)
    {
        if(root == null)return false;
        if(root.left == null && root.right == null)return sum == root.val;
        else {
            return hasPathSum2(root.left, sum- root.val) || hasPathSum2(root.right, sum - root.val);
        }
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSumDfs(root, 0,sum);
    }

    private boolean hasPathSumDfs(TreeNode root, int sum, int target)
    {
        if(root == null)return false;
        sum += root.val;
        if(sum == target && root.left == null && root.right == null)return true;
        return hasPathSumDfs(root.left, sum, target) || hasPathSumDfs(root.right, sum, target);
    }


    public String tree2str() {
       return tree2str(root);
    }


    /**
     *https://leetcode.com/problems/construct-string-from-binary-tree/description/
     * 606. Construct String from Binary Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
     *
     * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
     *
     * Example 1:
     * Input: Binary tree: [1,2,3,4]
     *        1
     *      /   \
     *     2     3
     *    /
     *   4
     *
     * Output: "1(2(4))(3)"
     *
     * Explanation: Originallay it needs to be "1(2(4)())(3()())",
     * but you need to omit all the unnecessary empty parenthesis pairs.
     * And it will be "1(2(4))(3)".
     * Example 2:
     * Input: Binary tree: [1,2,3,null,4]
     *        1
     *      /   \
     *     2     3
     *      \
     *       4
     *
     * Output: "1(2()(4))(3)"
     *
     * Explanation: Almost the same as the first example,
     * except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        tree2StrHelper(t, sb);
        return sb.toString();
    }

    private void tree2StrHelper(TreeNode t, StringBuilder sb)
    {
        if(t == null)return;

        if(t.left != null && t.right == null)
        {
            sb.append(t.val);
            sb.append("(");
            tree2StrHelper(t.left, sb);
            sb.append(")");
        }else if(t.left == null && t.right == null)
        {
            sb.append(t.val);
        }else {
            sb.append(t.val);
            sb.append("(");
            tree2StrHelper(t.left, sb);
            sb.append(")");
            sb.append("(");
            tree2StrHelper(t.right, sb);
            sb.append(")");
        }
    }


    /**
     * 官网运行最快的代码
     * @param root
     * @return
     */
    public String tree2str2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) return sb.toString();
        build(root, sb);
        return sb.toString();
    }

    private void build(TreeNode root, StringBuilder sb){
        sb.append(root.val);
        if(root.left != null){
            sb.append("(");
            build(root.left, sb);
            sb.append(")");
        }
        if(root.right != null){
            if(root.left == null) sb.append("()");
            sb.append("(");
            build(root.right, sb);
            sb.append(")");
        }
    }

    /**
     *
     * 653. Two Sum IV - Input is a BST
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
     *
     * Example 1:
     * Input:
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * Target = 9
     *
     * Output: True
     * Example 2:
     * Input:
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * Target = 28
     *
     * Output: False
     *
     * @param rt
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode rt, int k) {
        if(rt == null)return false;
        return findTargetHelper(rt, rt, k);
    }

    public boolean findTarget(int k) {
       return findTarget(root, k);
    }
    private boolean findTargetHelper(TreeNode root, TreeNode current, int k)
    {
        if(current == null)return false;
        int diff = k - current.val;
        TreeNode t = searchTree(root, diff);
        if(t != null && t != current)return true;
        return findTargetHelper( root,current.left, k) || findTargetHelper(root, current.right, k);
    }

    /**
     * 官网题解
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget2(TreeNode root, int k)
    {
        HashSet<Integer> set = new HashSet<>();
        return find(root, k, set);
    }
    private boolean find(TreeNode root, int k, Set<Integer> set)
    {
        if(root == null)return false;
        if(set.contains(k - root.val))return true;
        set.add(root.val);
        return find(root.left,k, set) || find(root.right,k, set);
    }


    /**
     *
     * 404. Sum of Left Leaves
     * DescriptionHintsSubmissionsDiscussSolution
     * Find the sum of all left leaves in a given binary tree.
     *
     * Example:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null)return 0;
        int sum = 0;
        if(root.left != null)
        {
            if(root.left.left == null && root.left.right == null)
            {
                sum += root.left.val;
            }else {
                sum += sumOfLeftLeaves(root.left);
            }
        }
         sum += sumOfLeftLeaves(root.right);
        return   sum;
    }

    /**
     *
     * https://leetcode.com/problems/sum-of-left-leaves/discuss/88951/3-line-recursive-c++-solution-no-need-to-explain
     * mzchen
     * @param root
     * @param isLeft
     * @return
     */
    public int sumOfLeftLeaves2(TreeNode root, boolean isLeft)
    {
        if(root == null)return 0;
        if(root.left == null && root.right == null)return isLeft ? root.val:0;
        return sumOfLeftLeaves2(root.left, true) + sumOfLeftLeaves2(root.right, false);
    }

    public int[] findMode(TreeNode root) {
        Map<Integer,Integer> count = new HashMap<>();
        findModeHelper(root, count);
        Set<Map.Entry<Integer,Integer>> entries = count.entrySet();
        if(entries.size() == 0)return new int[0];
        Map.Entry<Integer,Integer> max =   Collections.max(entries, Map.Entry.comparingByValue());
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry :
                entries) {
            if(entry.getValue().equals(max.getValue()))
            {
                list.add(entry.getKey());
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;

    }

    private void findModeHelper(TreeNode root, Map<Integer,Integer> count)
    {
        if(root == null)return;
        count.put(root.val, count.getOrDefault(root.val,0) + 1);
        findModeHelper(root.left, count);
        findModeHelper(root.right, count);
    }


    /**
     *
     *
     * 637. Average of Levels in Binary Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
     * Example 1:
     * Input:
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Output: [3, 14.5, 11]
     * Explanation:
     * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
     * Note:
     * The range of node's value is in the range of 32-bit signed integer.
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        List<TreeNode> queue = new LinkedList<>();
        if(root == null)return res;
        queue.add(root);
        int count = 1;
        int levelCount = 1;
        double sum = 0;
        while (!queue.isEmpty())
        {
            TreeNode current = ((LinkedList<TreeNode>) queue).pollFirst();
            sum += current.val;
            count--;
            if(current.left != null)
            {
                queue.add(current.left);
            }
            if(current.right != null){
                queue.add(current.right);
            }
            if(count == 0)
            {
                double average = sum/levelCount;
                count = queue.size();
                levelCount = queue.size();
                res.add(average);
                sum = 0;
            }
        }

        return res;
    }

    public List<Double> averageOfLevels() {
        return averageOfLevels(root);
    }


    /**
     * 别人的解
     * @param root
     * @return
     */
    public List<Double> averageOfLevels2(TreeNode root) {
        List<Double> res = new ArrayList();
        ArrayList<double[]> list = new ArrayList();
        dfs(root, list, 0);
        for (int i = 0; i < list.size(); i ++) {
            double[] cur = list.get(i);
            res.add(cur[0] / cur[1]);
        }
        return res;
    }
    private void dfs(TreeNode root, ArrayList<double[]> list, int depth) {
        if (root == null) return;
        if (depth == list.size())
            list.add(new double[]{0, 0});
        double[] cur = list.get(depth);
        cur[0] += root.val;
        cur[1] ++;
        dfs(root.left, list, depth + 1);
        dfs(root.right, list, depth + 1);
    }

    /**
     *
     *
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
     * 107. Binary Tree Level Order Traversal II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its bottom-up level order traversal as:
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results =new LinkedList<>();
        List<Integer> leverResults = new ArrayList<>();
        if(root == null) return results;
        LinkedList<TreeNode> queue = new LinkedList<>();
        int count = 1;
        queue.add(root);
        while (!queue.isEmpty())
        {
            TreeNode temp = queue.pollFirst();
            count--;
            leverResults.add(temp.val);

            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
            if(count == 0)
            {
                ((LinkedList<List<Integer>>) results).addFirst(leverResults);
                count = queue.size();
                leverResults = new ArrayList<>();
            }
        }

        return results;
    }


    /**
     *
     *
     * https://leetcode.com/problems/longest-univalue-path/description/
     * 687. Longest Univalue Path
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
     *
     * Note: The length of path between two nodes is represented by the number of edges between them.
     *
     * Example 1:
     *
     * Input:
     *
     *               5
     *              / \
     *             4   5
     *            / \   \
     *           1   1   5
     * Output:
     *
     * 2
     * Example 2:
     *
     * Input:
     *
     *               1
     *              / \
     *             4   5
     *            / \   \
     *           4   4   5
     * Output:
     *
     * 2
     * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
     *
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        consecutivePath(root);
        return MaxLen;
    }

    private  int MaxLen = 0;
    private int consecutivePath(TreeNode root)
    {
        if(root == null)return 0 ;

        int left = consecutivePath(root.left);
        int right = consecutivePath(root.right);

        int currentLeft = 0;
        int currentRight = 0;
        if(root.left != null && root.val == root.left.val )
        {
            currentLeft += left + 1;
        }

        if(root.right != null && root.val == root.right.val )
        {
            currentRight += right +1;

        }

        MaxLen = Math.max(MaxLen, currentLeft + currentRight);
        return Math.max(currentLeft, currentRight);
    }


    int len;
    public  int longestUnivaluePath2(TreeNode root)
    {
        if(root == null)return 0;
        len = 0;
        getLen(root, root.val);
        return len;
    }

    //得到与root 值一样的最大路径长度
    private int getLen(TreeNode root, int val)
    {
        if(root == null)return 0;
        int left = getLen(root.left, root.val);
        int right = getLen(root.right, root.val);
        len = Math.max(len, left + right);
        if(val == root.val)return Math.max(left, right) + 1;
        return 0;
    }


    /**
     *
     * 257. Binary Tree Paths
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, return all root-to-leaf paths.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Input:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * Output: ["1->2->5", "1->3"]
     *
     * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfsPaths(root, "", res);
        return res;
    }

    private void dfsPaths(TreeNode root, String path, List<String> res)
    {
        if(root == null)return;
        if(root.left == null && root.right == null){
            res.add(path +root.val);
            return;
        }
        if(root.left != null)
        {
            dfsPaths(root.left,  path + root.val +"->", res);
        }

        if(root.right != null)
        {
            dfsPaths(root.right, path + root.val +"->", res);
        }
    }


    /**
     *
     * 113. Path Sum II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given the below binary tree and sum = 22,
     *
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \    / \
     * 7    2  5   1
     * Return:
     *
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     * @param
     * @param sum
     * @return
     */

    public List<List<Integer>> pathSum( int sum) {
        return pathSum(root, sum);
    }


    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer>  currentPath = new ArrayList<>();
        dfsPathSum(root, res,  sum, currentPath);

        return res;

    }

    private void dfsPathSum(TreeNode root, List<List<Integer>> res, int sum, List<Integer> currentPath)
    {
        if(root == null)return;
        currentPath.add(root.val);
        if(root.left == null && root.right == null && root.val == sum)
        {
            res.add(new ArrayList<>(currentPath));
            currentPath.remove(currentPath.size() -1);
            return;
        }else {
                dfsPathSum(root.left, res, sum - root.val, currentPath);
                dfsPathSum(root.right, res, sum- root.val, currentPath);

        }

        currentPath.remove(currentPath.size() -1);
    }


    /**
     *
     * 437. Path Sum III
     * DescriptionHintsSubmissionsDiscussSolution
     * You are given a binary tree in which each node contains an integer value.
     *
     * Find the number of paths that sum to a given value.
     *
     * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
     *
     * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     *
     * Example:
     *
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *
     * Return 3. The paths that sum to 8 are:
     *
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3. -3 -> 11
     *
     *
     * T(n) = O(n) + 2T(n/2). According to Master Theorem, T(n) = O(nlgn).
     * @param root
     * @param sum
     * @return
     */
    public int pathSumiii(TreeNode root, int sum) {

        if(root == null)return 0;
        return pathSumFrom(root, sum) + pathSumiii(root.left, sum) + pathSumiii(root.right, sum);

    }
    private int pathSumFrom(TreeNode root, int target)
    {
        if(root == null)return 0;
        //                4
        //               /
        //              -3
        //              /
        //             3
        //            /
        //           4
        //
        //

        return ((root.val == target)?1:0) + pathSumFrom(root.left, target - root.val) + pathSumFrom(root.right, target - root.val);

    }


    /**
     *
     * 669. Trim a Binary Search Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
     *
     * Example 1:
     * Input:
     *     1
     *    / \
     *   0   2
     *
     *   L = 1
     *   R = 2
     *
     * Output:
     *     1
     *       \
     *        2
     * Example 2:
     * Input:
     *     3
     *    / \
     *   0   4
     *    \
     *     2
     *    /
     *   1
     *
     *   L = 1
     *   R = 3
     *
     * Output:
     *       3
     *      /
     *    2
     *   /
     *  1
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
            if(root == null)return root;
            if(root.val < L)return trimBST(root.right, L,R);
            if(root.val > R)return trimBST(root.left, L,R);
            root.left = trimBST(root.left, L,R);
            root.right = trimBST(root.right, L,R);
            return root;
    }


    public int findBottomLeftValue() {
        return findBottomLeftValue(root);
    }

    public int findBottomLeftValue(TreeNode root) {

        maxDepthBottomLeft = Integer.MIN_VALUE;
        bottomLeftValue = root.val;
        findMaxDepthBottomLeft(root, 0);
        return bottomLeftValue;
    }


    private int bottomLeftValue;
    private int maxDepthBottomLeft;
    private void findMaxDepthBottomLeft(TreeNode root, int height)
    {

        if(root == null)return;
        height++;
        findMaxDepthBottomLeft(root.left, height);
        findMaxDepthBottomLeft(root.right,  height);

        if( height > maxDepthBottomLeft )
        {
            maxDepthBottomLeft = height;
            bottomLeftValue = root.val;
        }


    }

    /**
     *
     * 别人的解法
     * https://leetcode.com/problems/find-bottom-left-tree-value/discuss/98802/Simple-Java-Solution-beats-100.0!
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue2(TreeNode root) {
        return findBottomLeftValue2(root, 1, new int[]{0,0});
    }
    public int findBottomLeftValue2(TreeNode root, int depth, int[] res) {
        if (res[1]<depth) {res[0]=root.val;res[1]=depth;}
        if (root.left!=null) findBottomLeftValue2(root.left, depth+1, res);
        if (root.right!=null) findBottomLeftValue2(root.right, depth+1, res);
        return res[0];
    }


    /**
     *
     *https://leetcode.com/problems/find-bottom-left-tree-value/discuss/98779/Right-to-Left-BFS-(Python-+-Java)
     * Doing BFS right-to-left means we can simply return the last node's value and don't
     * have to keep track of the first node in the current row or even care about rows at all.
     * Inspired by @fallcreek's solution (not published) which uses two nested loops to go row by row
     * but already had the right-to-left idea making it easier. I just took that further.
     * @param root
     * @return
     */
    public int findLeftMostNode3(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null)
                queue.add(root.right);
            if (root.left != null)
                queue.add(root.left);
        }
        return root.val;
    }


    /**
     *
     *
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     *
     * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
     *
     *         _______6______
     *        /              \
     *     ___2__          ___8__
     *    /      \        /      \
     *    0      _4       7       9
     *          /  \
     *          3   5
     * Example 1:
     *
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * Output: 6
     * Explanation: The LCA of nodes 2 and 8 is 6.
     * Example 2:
     *
     * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * Output: 2
     * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
     *              according to the LCA definition.
     * Note:
     *
     * All of the nodes' values will be unique.
     * p and q are different and both values will exist in the BST.
     *
     * beats 100%
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val)
        {
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        return lowestCommonAncestorHelper(root,p,q);

    }
    public TreeNode lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {


        if(root.val > p.val && root.val < q.val)
        {
            return root;
        }else if(root.val < p.val)
        {
            return lowestCommonAncestorHelper(root.right, p,q);
        }else if(root.val > q.val)
        {
            return lowestCommonAncestorHelper(root.left,p,q);
        }

        return root;


    }



    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q)
    {

        if (root.val > Math.max(p.val,q.val))
        {
            return lowestCommonAncestor2(root.left,p,q);
        }
        else if (root.val < Math.min(p.val,q.val))
        {
            return lowestCommonAncestor2(root.right,p,q);
        }
        else
        {
            return root;
        }

    }




    /**
     *
     *
     *https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/discuss/64963/3-lines-with-O(1)-space-1-Liners-Alternatives
     *StefanPochmann
     *
     * 不过,乘法会溢出,那么结果就很难预料
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        return (root.val - p.val) * (root.val - q.val) < 1 ? root :
                lowestCommonAncestor3(p.val < root.val ? root.left : root.right, p, q);
    }


    /**
     * Just walk down from the whole tree's root as long as both p and q are in the same subtree (meaning their values are both smaller or both larger than root's). This walks straight from the root to the LCA, not looking at the rest of the tree, so it's pretty much as fast as it gets. A few ways to do it:
     *
     * Iterative, O(1) space
     *StefanPochmann
     *
     * 不过,乘法会溢出,那么结果就很难预料
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0)
            root = p.val < root.val ? root.left : root.right;
        return root;
    }


    /**
     *
     *236. Lowest Common Ancestor of a Binary Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     *
     * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     *         _______3______
     *        /              \
     *     ___5__          ___1__
     *    /      \        /      \
     *    6      _2       0       8
     *          /  \
     *          7   4
     * Example 1:
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * Output: 3
     * Explanation: The LCA of of nodes 5 and 1 is 3.
     * Example 2:
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * Output: 5
     * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
     *              according to the LCA definition.
     * Note:
     *
     * All of the nodes' values will be unique.
     * p and q are different and both values will exist in the binary tree.
     *
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225/4-lines-C++JavaPythonRuby
     *
     *
     * If the current (sub)tree contains both p and q, then the function result is their LCA.
     * If only one of them is in that subtree, then the result is that one of them.
     * If neither are in that subtree, the result is null/None/nil.
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor5(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || p == root || q == root)
            return root;
        TreeNode left = lowestCommonAncestor5(root.left, p,q);
        TreeNode right = lowestCommonAncestor5(root.right, p, q);
        if(left != null && right != null)
            return root;
        return (left != null) ? left: right;
    }





    /**
     *
     * 543. Diameter of Binary Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     *
     * Example:
     * Given a binary tree
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     *
     * Note: The length of path between two nodes is represented by the number of edges between them.
     *
     * Seen this question in a real interview before?
     *
     *
     * beats 99.5%
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {

        int[] res = new int[1];
        res[0] = 1;
        heightOfTree(root, res);
        return res[0] -1;
    }

    private int heightOfTree(TreeNode root, int[] res)
    {
        if(root == null)return 0;
        //左子树的高，也为左子树高路径上的节点数
        int left = heightOfTree(root.left, res);
        //右子树的高，也为右子树高路径上的节点数
        int right = heightOfTree(root.right, res);
        res[0] = Math.max(res[0], left + right + 1);
        return Math.max(left,right) + 1;

    }



    public List<List<Integer>> zigzagLevelOrder() {
        return zigzagLevelOrder(root);
    }
    /**
     *103. Binary Tree Zigzag Level Order Traversal
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its zigzag level order traversal as:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> levelRes = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if(root != null)
        {
            deque.add(root);
        }
        int count = 1;
        boolean isReverse = false;

        while (!deque.isEmpty())
        {
            TreeNode temp = deque.pollFirst();
            count--;
            if(!isReverse)
            {
                levelRes.add(temp.val);
            }else {
                levelRes.add(0,temp.val);
            }

            if(temp.left != null)
            {
                deque.addLast(temp.left);
            }

            if(temp.right != null)
            {
                deque.addLast(temp.right);
            }

            if(count == 0)
            {
                lists.add(levelRes);
                count = deque.size();
                levelRes = new ArrayList<>();
                isReverse = !isReverse;
            }
        }

        return lists;
    }


    /**
     *
     * 109. Convert Sorted List to Binary Search Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * Example:
     *
     * Given the sorted linked list: [-10,-3,0,5,9],
     *
     * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
     *
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     *
     * @param head
     * @return
     */

    private ListNode curr;
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null)return null;
        curr = head;
        int size = 0;
        while (head != null)
        {
            size++;
            head = head.next;
        }

        return inOrderHelperSortedListToBST(0,size -1);

    }

    private TreeNode inOrderHelperSortedListToBST(int low, int high)
    {
        if(low > high)return null;

        int middle = (low + high)>>>1;
        TreeNode left = inOrderHelperSortedListToBST(low, middle-1);
        TreeNode tempRoot = new TreeNode(curr.val);
        tempRoot.left = left;
        curr = curr.next;
        TreeNode right = inOrderHelperSortedListToBST(middle +1, high);
        tempRoot.right = right;
        return tempRoot;

    }


    /**
     *
     * https://articles.leetcode.com/construct-binary-tree-from-inorder-and-preorder-postorder-traversal/
     * // beats 95%
     *105. Construct Binary Tree from Preorder and Inorder Traversal
     * DescriptionHintsSubmissionsDiscussSolution
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param preorder
     * @param inorder
     * @return
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer,Integer> mapIndex = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            mapIndex.put(inorder[i],i);
        }
        return buildFromPreorderInorder(preorder,0,inorder,0,inorder.length -1, mapIndex);
    }

    // id - inStart is the left sub tree's size, end - id is the right sub tree's size
    private TreeNode buildFromPreorderInorder(int[] preorder,int preId, int[] inorder, int inStart, int inEnd, Map<Integer,Integer> map )
    {
       if(inStart > inEnd)return null;
        int val = preorder[preId];
        int id = map.get(val);  // the divider's index
        TreeNode root = new TreeNode(val);
        root.left = buildFromPreorderInorder(preorder,preId +1, inorder, inStart, id -1, map);
        root.right = buildFromPreorderInorder(preorder,preId + id - inStart +1 , inorder, id +1, inEnd, map);

        return root;
    }


    /**
     *
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * DescriptionHintsSubmissionsDiscussSolution
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     */
    int preStart = 0;
    int inStart = 0;
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, Long.MAX_VALUE);
    }

    private TreeNode helper(int[] preorder, int[] inorder, long parent) {
        if (inStart >= inorder.length || inorder[inStart] == parent) {
            inStart++;
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart++]);
        root.left = helper(preorder, inorder, root.val);
        root.right = helper(preorder, inorder, parent);
        return root;
    }


    /**
     *
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     * DescriptionHintsSubmissionsDiscussSolution
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * inorder = [9,3,15,20,7]
     * postorder = [9,15,7,20,3]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree3(int[] inorder, int[] postorder) {

        HashMap<Integer,Integer> mapIndex = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            mapIndex.put(inorder[i],i);
        }
        return buildFromPostorderInorder(postorder,postorder.length -1,0,inorder.length -1, mapIndex);
    }


    // id - inStart is the left sub tree's size, end - id is the right sub tree's size
    private TreeNode buildFromPostorderInorder(int[] postorder,int postId,  int inStart, int inEnd, Map<Integer,Integer> map )
    {
        if(inStart > inEnd)return null;
        int val = postorder[postId];
        int id = map.get(val);  // the divider's index
        TreeNode root = new TreeNode(val);
        root.left = buildFromPostorderInorder(postorder,postId  - (inEnd - id +1),  inStart, id -1, map);
        root.right = buildFromPostorderInorder(postorder,postId -1 , id +1, inEnd, map);

        return root;
    }


    /**
     *
     * 889. Construct Binary Tree from Preorder and Postorder Traversal
     * DescriptionHintsSubmissionsDiscussSolution
     * Return any binary tree that matches the given preorder and postorder traversals.
     *
     * Values in the traversals pre and post are distinct positive integers.
     *
     *
     *
     * Example 1:
     *
     * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
     * Output: [1,2,3,4,5,6,7]
     *
     *
     * Note:
     *
     * 1 <= pre.length == post.length <= 30
     * pre[] and post[] are both permutations of 1, 2, ..., pre.length.
     * It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
     *
     *
     *
     *
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161268/C++JavaPython-One-Pass-Real-O(N)?page=1
     * We will preorder generate TreeNodes, push them to stack and postorder pop them out.
     *
     * Loop on pre array and construct node one by one.
     * stack save the current path of tree.
     * node = new TreeNode(pre[i]), if not left child, add node to the left. otherwise add it to the right.
     * If we meet a same value in the pre and post, it means we complete the construction for current subtree. We pop it from stack.
     *
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(new TreeNode(pre[0]));
        int j = 0;
        for (int i = 1; i < pre.length; i++) {
            TreeNode newNode = new TreeNode(pre[i]);

            // finished this subtree
            while (queue.peekLast().val == post[j])
            {
                queue.pollLast();
                j++;
            }

            TreeNode curr = queue.getLast();
            if(curr.left == null)
            {
                curr.left = newNode;
            }else {
                curr.right = newNode;
            }

            queue.offer(newNode);


        }

        return queue.peekFirst();
    }


    /**
     *
     * 199. Binary Tree Right Side View
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     *
     * Example:
     *
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     * Explanation:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        rightSideViewHelper(root,0,list);
        return list;
    }


    private void rightSideViewHelper(TreeNode root, int depth, List<Integer> list )
    {
        if(root == null)return;
        if(list.size() <= depth)
        {
            list.add(root.val);
        }else {
            list.set(depth,root.val);
        }

        rightSideViewHelper(root.left, depth+1, list);
        rightSideViewHelper(root.right, depth +1, list);
    }


    private void rightSideViewHelper2(TreeNode root, int depth, List<Integer> list )
    {
        if(root == null)return;
        if(list.size() <= depth)
        {
            list.add(root.val);
        }
        rightSideViewHelper2(root.right, depth +1, list);
        rightSideViewHelper2(root.left, depth+1, list);

    }

    // every layer(depth), we just chose one node's value.
    private void rightSideViewHelper3(TreeNode root, int depth, List<Integer> list )
    {
        if(root == null)return;
        if(list.size() == depth)
        {
            list.add(root.val);
        }
        rightSideViewHelper3(root.right, depth +1, list);
        rightSideViewHelper3(root.left, depth+1, list);

    }


    /**
     *
     * 450. Delete Node in a BST
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
     *
     * Basically, the deletion can be divided into two stages:
     *
     * Search for a node to remove.
     * If the node is found, delete the node.
     * Note: Time complexity should be O(height of tree).
     *
     * Example:
     *
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * Given key to delete is 3. So we find the node with value 3 and delete it.
     *
     * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * Another valid answer is [5,2,6,null,4,null,7].
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     *
     * @param key
     * @return
     */
    public TreeNode deleteNode(int key)
    {
        return deleteNode(root,key);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
       if(root == null)return null;
       int cmp = root.val - key;
       if(cmp > 0)
       {
           root.left = deleteNode(root.left, key);// key on the left, delete it from left

       }else if( cmp < 0)
       {
           root.right = deleteNode(root.right,key); // key on the right, delete it from right.
       }else {
           if(root.right == null) return root.left;
           if(root.left == null) return root.right;
           TreeNode t = root;
           root = getMin(t.right);
           root.right = deleteMin(t.right);
           root.left = t.left;

       }
       return root;


    }

   private TreeNode deleteMin(TreeNode curr)
   {
       if(curr.left == null)return curr.right;
       curr.left = deleteMin(curr.left);
       return curr;
   }


    /**
     *
     * 124. Binary Tree Maximum Path Sum
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a non-empty binary tree, find the maximum path sum.
     *
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
     *
     * Example 1:
     *
     * Input: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * Output: 6
     * Example 2:
     *
     * Input: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * Output: 42
     *
     *
     * 很多人的解答是错误的，因为没有考虑溢出
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if(root == null)return 0;
        maxPathSumHelper(root);
        return maxPSum;
    }

    int maxPSum = Integer.MIN_VALUE;
    private int maxPathSumHelper(TreeNode root)
    {
        if(root == null)return 0;
        int left = maxPathSumHelper(root.left);
        int right = maxPathSumHelper(root.right);

        int k = root.val + left + right;
        int m = Math.max(Math.max(root.val, left + root.val),right + root.val);
        maxPSum = k > maxPSum ? k: maxPSum;
        maxPSum = m > maxPSum ? m: maxPSum;
        return m;
    }


    /**
     *
     * 129. Sum Root to Leaf Numbers
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     *
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     *
     * Find the total sum of all root-to-leaf numbers.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Input: [1,2,3]
     *     1
     *    / \
     *   2   3
     * Output: 25
     * Explanation:
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Therefore, sum = 12 + 13 = 25.
     * Example 2:
     *
     * Input: [4,9,0,5,1]
     *     4
     *    / \
     *   9   0
     *  / \
     * 5   1
     * Output: 1026
     * Explanation:
     * The root-to-leaf path 4->9->5 represents the number 495.
     * The root-to-leaf path 4->9->1 represents the number 491.
     * The root-to-leaf path 4->0 represents the number 40.
     * Therefore, sum = 495 + 491 + 40 = 1026.
     *
     *
     * 0ms beats 100%
     * @param root
     * @return
     */

    public int sumNumbers(TreeNode root) {
        sumN = 0;
        dfsSumNumbers(root, 0);
        return sumN;
    }
    private int  sumN;
    private void dfsSumNumbers(TreeNode root, int currentSum)
    {
        if(root == null)return;
        currentSum = currentSum*10 + root.val;
        if(root.left == null && root.right == null)
        {
            sumN += currentSum;
            return;
        }

        dfsSumNumbers(root.left, currentSum);
        dfsSumNumbers(root.right, currentSum);

    }


    /**
     * 99. Recover Binary Search Tree
     * DescriptionHintsSubmissionsDiscussSolution
     * Two elements of a binary search tree (BST) are swapped by mistake.
     *
     * Recover the tree without changing its structure.
     *
     * Example 1:
     *
     * Input: [1,3,null,null,2]
     *
     *    1
     *   /
     *  3
     *   \
     *    2
     *
     * Output: [3,1,null,null,2]
     *
     *    3
     *   /
     *  1
     *   \
     *    2
     * Example 2:
     *
     * Input: [3,1,4,null,null,2]
     *
     *   3
     *  / \
     * 1   4
     *    /
     *   2
     *
     * Output: [2,1,4,null,null,3]
     *
     *   2
     *  / \
     * 1   4
     *    /
     *   3
     * Follow up:
     *
     * A solution using O(n) space is pretty straight forward.
     * Could you devise a constant space solution?
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {

        recoverTreeHelper(root);
        if(first != null)
        {
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }
    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = null;
    int nodeCount = 0;
    private void  recoverTreeHelper(TreeNode root)
    {
        if(root == null)return;
        recoverTreeHelper(root.left);
        if(pre != null &&  pre.val > root.val)
        {
            if(first == null)
                first = pre;
            second = root;
            nodeCount++;
            if(nodeCount >=2)return;
        }
        pre = root;
        recoverTreeHelper(root.right);
    }


    /**
     *
     * 222. Count Complete Tree Nodes
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a complete binary tree, count the number of nodes.
     *
     * Note:
     *
     * Definition of a complete binary tree from Wikipedia:
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     *
     * Example:
     *
     * Input:
     *     1
     *    / \
     *   2   3
     *  / \  /
     * 4  5 6
     *
     * Output: 6
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null)return 0;
        int heightLeft = 0, heightRight = 0;
        TreeNode left = root;
        TreeNode right = root;
        while (left != null){heightLeft++; left = left.left;} // get height of left subtree
        while (right != null){heightRight++; right = right.right;} // get the height of right subtree
        if(heightLeft == heightRight)return (1<<heightLeft) -1;  // full tree
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int countNodes2(TreeNode root) {
        if(root == null)return 0;
        int height = 0;
        TreeNode left = root;
        TreeNode right = root;
        while (right != null){
            height++;
            right = right.right;
            left = left.left;
        }
        if(left == null)return (1<<height) -1;   // full tree
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    /**
     *515. Find Largest Value in Each Tree Row
     * DescriptionHintsSubmissionsDiscussSolution
     * You need to find the largest value in each row of a binary tree.
     *
     * Example:
     * Input:
     *
     *           1
     *          / \
     *         3   2
     *        / \   \
     *       5   3   9
     *
     * Output: [1, 3, 9]
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {

        if(root ==  null)return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        int count = 1;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty())
        {
            TreeNode temp = queue.pollFirst();
            count--;
            max = max > temp.val ? max: temp.val;
            if(temp.left != null)queue.addLast(temp.left);
            if(temp.right != null)queue.addLast(temp.right);
            if(count == 0)
            {
                count = queue.size();
                list.add(max);
                max = Integer.MIN_VALUE;
            }
        }

        return list;
    }


    // 二叉树给出根节点和目标节点，找出从根节点到目标节点的路径

    public List<Integer> findPath(TreeNode root, TreeNode target)
    {
        List<Integer> list = new ArrayList<>();
        findPathHelper(root, target, list);
        return list;

    }

    private boolean findPathHelper(TreeNode root, TreeNode target, List<Integer> list)
    {
        if(root == null)return false;
        list.add(root.val);
        if(root == target)return true;
        boolean hasFound = false;
        if(!hasFound && root.left != null)
        {
            hasFound = findPathHelper(root.left, target, list);
        }

        if(!hasFound && root.right != null)
        {
            hasFound = findPathHelper(root.right, target, list);
        }

        // backtracking
        if(!hasFound)
        {
            list.remove(list.size() -1);
        }

        return hasFound;

    }

    public List<Integer> findPath2(TreeNode root, TreeNode target)
    {
        List<Integer> list = new ArrayList<>();
        findPathHelper(root, target, list);
        return list;

    }

    private void findPathHelper(TreeNode root, TreeNode target, List<Integer> list, boolean[] found)
    {
        if(root == null);
        if(root == target)
        {
            found[0] = true;
            list.add(0, root.val);
            return;
        }
        if(!found[0] && root.left != null)
        {
            findPathHelper(root.left, target, list, found);
        }

        if(!found[0] && root.right != null)
        {
            findPathHelper(root.right, target, list, found);
        }
        if(found[0])
        {
            list.add(root.val);
        }

    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(12);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        root.left = node2;
        root.right = node1;
        node2.left = node4;
        node2.right = node3;
        node4.left = node6;
        node4.right = node5;

        BinaryTree binaryTree = new BinaryTree();
        List<Integer> list = binaryTree.findPath(root, node5);
        System.out.println(list);

        list = binaryTree.findPath2(root, node6);
        System.out.println(list);

    }
}
