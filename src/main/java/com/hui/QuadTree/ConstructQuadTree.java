package com.hui.QuadTree;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/28 21:58
 *
 * 427. Construct Quad Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents the whole grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.
 *
 * Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.
 *
 * Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:
 *
 * Given the 8 x 8 grid below, we want to construct the corresponding quad tree:
 *
 *
 *
 * It can be divided according to the definition above:
 *
 *
 *
 *
 *
 * The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.
 *
 * For the non-leaf nodes, val can be arbitrary, so it is represented as *.
 *
 *
 *
 * Note:
 *
 * N is less than 1000 and guaranteened to be a power of 2.
 * If you want to know more about the quad tree, you can refer to its wiki.
 *
 */
public class ConstructQuadTree {

    public Node construct(int[][] grid) {
        return build(grid,0, 0, grid.length -1, grid[0].length -1);
    }

    private Node build(int[][] grid, int r1, int c1, int r2, int c2)
    {
        if(r1 > r2 || c1 > c2)return null;  // we need r1 == r2 and c1 == c2; for an example: grid = {{0,1},{1,0}}
        boolean isLeaf = true;
        int val = grid[r1][c1];
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if(val != grid[i][j])
                {
                    int rMid = (r1 + r2)/2;
                    int cMid = (c1 + c2)/2;
                    Node curr = new Node(false, false, null, null, null, null);
                    curr.topLeft = build(grid, r1, c1, rMid, cMid);
                    curr.topRight = build(grid, r1, cMid +1, rMid, c2);
                    curr.bottomLeft = build(grid, rMid +1, c1, r2, cMid);
                    curr.bottomRight = build(grid, rMid +1, cMid +1, r2, c2);
                    return curr;

                }
            }
        }
        return new Node( val == 1, isLeaf, null, null, null,null);



    }


    /**
     *558. Quad Tree Intersection
     * DescriptionHintsSubmissionsDiscussSolution
     * A quadtree is a tree data in which each internal node has exactly four children: topLeft, topRight, bottomLeft and bottomRight. Quad trees are often used to partition a two-dimensional space by recursively subdividing it into four quadrants or regions.
     *
     * We want to store True/False information in our quad tree. The quad tree is used to represent a N * N boolean grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same. Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.
     *
     * For example, below are two quad trees A and B:
     *
     * A:
     * +-------+-------+   T: true
     * |       |       |   F: false
     * |   T   |   T   |
     * |       |       |
     * +-------+-------+
     * |       |       |
     * |   F   |   F   |
     * |       |       |
     * +-------+-------+
     * topLeft: T
     * topRight: T
     * bottomLeft: F
     * bottomRight: F
     *
     * B:
     * +-------+---+---+
     * |       | F | F |
     * |   T   +---+---+
     * |       | T | T |
     * +-------+---+---+
     * |       |       |
     * |   T   |   F   |
     * |       |       |
     * +-------+-------+
     * topLeft: T
     * topRight:
     *      topLeft: F
     *      topRight: F
     *      bottomLeft: T
     *      bottomRight: T
     * bottomLeft: T
     * bottomRight: F
     *
     *
     * Your task is to implement a function that will take two quadtrees and return a quadtree that represents the logical OR (or union) of the two trees.
     *
     * A:                 B:                 C (A or B):
     * +-------+-------+  +-------+---+---+  +-------+-------+
     * |       |       |  |       | F | F |  |       |       |
     * |   T   |   T   |  |   T   +---+---+  |   T   |   T   |
     * |       |       |  |       | T | T |  |       |       |
     * +-------+-------+  +-------+---+---+  +-------+-------+
     * |       |       |  |       |       |  |       |       |
     * |   F   |   F   |  |   T   |   F   |  |   T   |   F   |
     * |       |       |  |       |       |  |       |       |
     * +-------+-------+  +-------+-------+  +-------+-------+
     * Note:
     *
     * Both A and B represent grids of size N * N.
     * N is guaranteed to be a power of 2.
     * If you want to know more about the quad tree, you can refer to its wiki.
     * The logic OR operation is defined as this: "A or B" is true if A is true, or if B is true, or if both A and B are true.
     *
     * @param quadTree1
     * @param quadTree2
     * @return
     */
    public Node intersect(Node quadTree1, Node quadTree2) {
        // either quardTree1 is leaf of quad tree2 is leaf
        if(quadTree1.isLeaf) return quadTree1.val ? quadTree1: quadTree2;
        if(quadTree2.isLeaf) return quadTree2.val ? quadTree2: quadTree1;

        // if 4 quad sub tree are same value, then re-combine 4 sub tree to 1 leaf
        quadTree1.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        quadTree1.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        quadTree1.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        quadTree1.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        if(canReCombine(quadTree1))
        {
            quadTree1.val = quadTree1.topLeft.val;
            quadTree1.isLeaf = true;
        }
        return quadTree1;


    }

    /**
     * check whether we can re combine the tree;
     * if all 4 sub tree are leaf and in the same state(val is the same value),
     * we can re-combine the 4 sub tree
     * @param quadTree
     * @return
     */
    private boolean canReCombine(Node quadTree)
    {
        if(quadTree.topLeft.isLeaf
                && quadTree.topRight.isLeaf
                && quadTree.bottomLeft.isLeaf
                && quadTree.bottomRight.isLeaf
                && quadTree.topLeft.val == quadTree.topRight.val
                && quadTree.topLeft.val == quadTree.bottomLeft.val
                && quadTree.bottomLeft.val == quadTree.bottomRight.val)return true;
        return false;
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }
}
