package com.hui.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/27 23:31
 *
 * 863. All Nodes Distance K in Binary Tree
 * DescriptionHintsSubmissionsDiscussSolution
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 *
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 *
 *
 *
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 *
 * Note:
 *
 * The given tree is non-empty.
 * Each node in the tree has unique values 0 <= node.val <= 500.
 * The target node is a node in the tree.
 * 0 <= K <= 1000.
 *
 */
public class AllNodesDistanceKinBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        buildMap(null, root, map);

        List<Integer> bfs = new ArrayList<>();
        if(K >= map.size())return bfs;
        bfs.add(target.val);
        boolean[] visited = new boolean[501];
        for (int i = 0; i < K; i++) {
            List<Integer> neighbors = new ArrayList<>();
            for(int key:bfs)
            {
                visited[key] = true;
                List<Integer> temp = map.get(key);
                if(temp == null)continue;
                for(int n: temp)
                {
                    if(!visited[n])
                    {
                        neighbors.add(n);
                    }
                }
            }
            bfs = neighbors;
        }
        return bfs;
    }


    private void buildMap(TreeNode parent, TreeNode child, Map<Integer,List<Integer>> map)
    {
        if(parent != null && child != null)
        {
            List<Integer> list = map.getOrDefault(parent.val, new ArrayList<>());
            list.add(child.val);
            map.put(parent.val, list);

            List<Integer> list2 = map.getOrDefault(child.val, new ArrayList<>());
            list2.add(parent.val);
            map.put(child.val, list2);
        }

        if(child.left != null)
            buildMap(child, child.left, map);
        if(child.right != null)
            buildMap(child, child.right, map);
    }


    /**
     *
     * dfs  and graph and bfs method
     * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143729/Python-DFS-and-BFS
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < 501; i++) {
            map.add(new ArrayList<>());
        }
        buildMap(null, root, map);

        List<Integer> bfs = new ArrayList<>();
        if(K >= map.size())return bfs;
        bfs.add(target.val);
        boolean[] visited = new boolean[501];
        for (int i = 0; i < K; i++) {
            // find it's neighbors.
            List<Integer> neighbors = new ArrayList<>();
            for(int key:bfs)
            {
                visited[key] = true;
                List<Integer> temp = map.get(key);
                if(temp.isEmpty())continue;
                for(int n: temp)
                {
                    // don't repeat yourself.
                    if(!visited[n])
                    {
                        neighbors.add(n);
                    }
                }
            }
            bfs = neighbors;
        }
        return bfs;
    }


    /**
     * dfs method to traverse the tree and build graph.
     * @param parent
     * @param child
     * @param map
     */
    private void buildMap(TreeNode parent, TreeNode child, List<List<Integer>> map)
    {
        if(parent != null && child != null)
        {
            List<Integer> list = map.get(parent.val);
            list.add(child.val);
            List<Integer> list2 = map.get(child.val);
            list2.add(parent.val);

        }

        if(child.left != null)
            buildMap(child, child.left, map);
        if(child.right != null)
            buildMap(child, child.right, map);
    }


    /**
     *
     * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143798/1ms-beat-100-simple-Java-dfs-using-hashmap-with-explanation
     *
     * As we know, if the distance from a node to target node is k, the distance from its child to the
     * target node is k + 1 unless the child node is closer to the target node which means the target node is in it's subtree.
     *
     * To avoid this situation, we need to travel the tree first to find the path from root to target, to:
     *
     * store the value of distance in hashamp from the all nodes in that path to target
     * Then we can easily use dfs to travel the whole tree. Every time when we meet a treenode which has already stored in map,
     * use the stored value in hashmap instead of the value from its parent node.
     *
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK3(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        find(root, target, map);
        dfs(root, K, map.get(root), map, ans);
        return ans;
    }
    private int find(TreeNode root, TreeNode target, Map<TreeNode, Integer> map)
    {
        if(root == null)return -1;
        if(root == target)
        {
            map.put(root, 0);
            return 0;
        }
        int leftDistance = find(root.left, target, map);
        if(leftDistance >= 0)
        {
            map.put(root, leftDistance +1);
            return leftDistance +1;
        }

        int rightDistance = find(root.right, target, map);
        if(rightDistance >= 0)
        {
            map.put(root, rightDistance +1);
            return rightDistance +1;
        }

        return -1;
    }

    private void dfs(TreeNode root, int K,int distance, Map<TreeNode,Integer> map, List<Integer> ans)
    {
        if(root == null)return;
        if(map.containsKey(root))distance = map.get(root);
        if(distance == K)ans.add(root.val);
        if(!map.containsKey(root) && distance > K)return;
        dfs(root.left, K, distance +1, map, ans);
        dfs(root.right, K, distance +1, map, ans);

    }


    /**
     * since Each node in the tree has unique values 0 <= node.val <= 500. so  we can use int array replace hashMap
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK4(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        int[] map = new int[501];
        find(root, target, map);
        dfs(root, K, map[root.val] -1, map, ans);
        return ans;
    }
    private int find(TreeNode root, TreeNode target, int[] map)
    {
        if(root == null)return -1;
        if(root == target)
        {
            map[root.val] = 1;
            return 0;
        }
        int leftDistance = find(root.left, target, map);
        if(leftDistance >= 0)
        {
            map[root.val] = leftDistance +2;
            return leftDistance +1;
        }

        int rightDistance = find(root.right, target, map);
        if(rightDistance >= 0)
        {
            map[root.val] = rightDistance +2;
            return rightDistance +1;
        }

        return -1;
    }

    private void dfs(TreeNode root, int K,int distance, int[] map, List<Integer> ans)
    {
        if(root == null)return;
        // in path from root to the target.
        if(map[root.val] > 0)distance = map[root.val] -1;
        if(distance == K)ans.add(root.val);

        // not in path and distance bigger than K
        if(!(map[root.val] > 0) && distance > K)return;
        dfs(root.left, K, distance +1, map, ans);
        dfs(root.right, K, distance +1, map, ans);

    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

        TreeNode root =  new TreeNode(3);
        TreeNode five =  new TreeNode(5);
        TreeNode one  =  new TreeNode(1);
        TreeNode six  =  new TreeNode(6);
        TreeNode two  =  new TreeNode(2);
        TreeNode seven = new TreeNode(7);
        TreeNode four =  new TreeNode(4);
        TreeNode zero =  new TreeNode(0);
        TreeNode eight = new TreeNode(8);
        root.left = five;
        root.right = one;
        five.left = six;
        five.right = two;
        two.left = seven;
        two.right = four;
        one.left = zero;
        one.right = eight;

        TreeNode root2 = new TreeNode(1);

        AllNodesDistanceKinBinaryTree allNodesDistanceKinBinaryTree = new AllNodesDistanceKinBinaryTree();


        System.out.println(allNodesDistanceKinBinaryTree.distanceK(root, five, 2));
        System.out.println(allNodesDistanceKinBinaryTree.distanceK(root2, root2, 3));
        System.out.println("******************");

        System.out.println(allNodesDistanceKinBinaryTree.distanceK2(root, five, 2));
        System.out.println(allNodesDistanceKinBinaryTree.distanceK2(root2, root2, 3));


        System.out.println("******************");

        System.out.println(allNodesDistanceKinBinaryTree.distanceK3(root, five, 2));
        System.out.println(allNodesDistanceKinBinaryTree.distanceK3(root2, root2, 3));

        System.out.println("******************");

        System.out.println(allNodesDistanceKinBinaryTree.distanceK4(root, five, 2));
        System.out.println(allNodesDistanceKinBinaryTree.distanceK4(root2, root2, 3));
    }
}
