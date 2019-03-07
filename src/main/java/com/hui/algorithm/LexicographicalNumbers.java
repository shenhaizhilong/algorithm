package com.hui.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 19:14
 */
public class LexicographicalNumbers {


    /**
     * 386. Lexicographical Numbers
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an integer n, return 1 - n in lexicographical order.
     *
     * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
     *
     * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        DigitTree digitTree = new DigitTree();
        for (int i = 1; i <=n; i++) {
            digitTree.insert(Integer.toString(i));
        }
        List<Integer> results = new ArrayList<>(n);
        digitTree.dfs(results);
        return results;
    }

    private static class DigitTree{
        DigitTreeNode root;
        public DigitTree()
        {
            root = new DigitTreeNode();

        }

        public void insert(String number)
        {
            DigitTreeNode curr = root;
            for (int i = 0; i < number.length(); i++) {
                int idx = number.charAt(i) - '0';
                if(curr.children[idx] == null)
                {
                    curr.children[idx] = new DigitTreeNode();
                }
                curr = curr.children[idx];
            }
        }

        public void dfs( List<Integer> list)
        {
            dfs(root, 0, list);
        }

        private void dfs(DigitTreeNode curr, int currentNumber, List<Integer> list)
        {
            if(curr == null)return;
            if(currentNumber != 0)
                list.add(currentNumber);
            for (int i = 0; i < curr.children.length; i++) {
                if(curr.children[i] != null)
                    dfs(curr.children[i], currentNumber*10 + i, list);
            }
        }
    }

    private static class DigitTreeNode{
        DigitTreeNode[] children = new DigitTreeNode[10];
    }


    public List<Integer> lexicalOrder2(int n) {
        List<Integer> results = new ArrayList<>(n);
        for (int i = 1; i <=9; i++) {
            dfs2(i,n, results);
        }
        return results;
    }

    public void dfs2(int curr, int n, List<Integer> res){
       if (curr > n)return;
        res.add(curr);
        for(int i=0;i<10;++i){
            if(10*curr+i>n)
                return;

            // visit next layer
            dfs2(10*curr+i, n, res);
        }

    }

    public static void main(String[] args) {


        LexicographicalNumbers lexicographicalNumbers = new LexicographicalNumbers();
        List<Integer> list = lexicographicalNumbers.lexicalOrder(13);
        System.out.println(list);
        list = lexicographicalNumbers.lexicalOrder2(13);
        System.out.println(list);
    }



}
