package com.hui.Tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * https://en.wikipedia.org/wiki/Huffman_coding
 * 算法导论第16章 贪心算法
 * 贪婪的原因在于:每次都是选择两个频率最小的节点合并而没有进行全局的考虑
 * 定理：huffman  过程会生成一个最优前缀码
 * @author: shenhaizhilong
 * @date: 2018/7/26 15:22
 */
public class HuffmanTree {

    private PriorityQueue<HuffmanNode> heap = new PriorityQueue<>(Comparator.comparing(HuffmanNode::getFreq));
    private int[] charsFreq = new int[128];
    private HuffmanNode root = null;
    private static char NIL = 128;

    /**
     *   calculate every char's frequency in s
     * @param s
     */
    private void calcFreq(String s)
    {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int k = (int)c;
            if(k >=0 && k<=127)
            {
                charsFreq[c] +=1;
            }
        }
    }


    /**
     *  build a min binary heap from string s
     * @param s
     */
    public void buildHeap(String s)
    {
        calcFreq(s);
        for (int i = 0; i < charsFreq.length; i++) {
            HuffmanNode currentNode = new HuffmanNode((char)i, charsFreq[i]);
            heap.add(currentNode);
        }

    }

    public HuffmanNode buildHuffmanTree()
    {
        removeZeroFreq();
        if(heap.size() < 1)
        {
            throw new IllegalArgumentException("after remove the zero freq, heap's size less than 2");
        }

        HuffmanNode first;
        HuffmanNode second;
        HuffmanNode parent;
        root = heap.peek();
        while (heap.size() > 1)
        {
            first = heap.poll();
            second = heap.poll();
            parent = new HuffmanNode(first.freq + second.freq);
            if(first.freq == second.freq && first.val == NIL && second.val != NIL)
            {
                parent.left = second;
                parent.right = first;
            }else {
                parent.left = first;
                parent.right = second;
            }

            root = parent;
            heap.add(parent);
        }
        return root;

    }

    //remove the elements where it's count = 0;
    private void removeZeroFreq()
    {
        while (!heap.isEmpty())
        {
            HuffmanNode c = heap.peek();
            if(c.freq == 0)
            {
                heap.remove();
            }else {
                break;
            }
        }
    }

    public void printCode()
    {
        printCode(root,"");
    }

    private void printCode(HuffmanNode head, String s)
    {
        if(head == null)return;
        //base case
        if(head.left == null && head.right == null && (head.val != NIL))
        {
            System.out.println("c: " + head + " , code: " + s);
            return;
        }

        printCode(head.left, s +"0");
        printCode(head.right, s + "1");
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
    private void printTreeInOrder(HuffmanNode treeNode) {
        if (treeNode != null) {
            printTreeInOrder(treeNode.left);
            System.out.println(treeNode);
            printTreeInOrder(treeNode.right);
        }
    }

    public void printTreeLevelOrder()
    {

        printTreeLevelOrder(root);
    }


    private void printTreeLevelOrder(HuffmanNode treeNode)
    {

        if( treeNode == null)return;
        LinkedList<HuffmanNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty())
        {
            HuffmanNode temp = queue.pollFirst();
            System.out.println(temp);
            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }
    }

    public static class HuffmanNode implements Comparable<HuffmanNode>
    {
        private final char val;
        private final int freq;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(int count)
        {
            this.val = NIL ;
            this.freq = count;
        }

        public HuffmanNode(char val, int count)
        {
            this.val = val;
            this.freq = count;
        }

        public int getFreq() {
            return freq;
        }

        public char getVal() {
            return val;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            if(o == null)return this.val;
            return this.val - o.val;
        }

        @Override
        public String toString() {
            return "{val: " + val +" ," + "freq: " + freq + "}";
        }
    }
}
