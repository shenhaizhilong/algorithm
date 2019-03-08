package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/20 17:29
 *
 * 对一个“01”串进行一次μ变换被定义为：将其中的"0"变成"10"，"1"变成"01"，初始串为"1"，求经过N(N <= 1000)次μ变换后的串中有多少对"00"（有没有人会纠结会不会出现"000"的情况？这个请放心，由于问题的特殊性，不会出现"000"的情况）。图一 -1-7表示经过小于4次变换时串的情况。
 *
 * 如果纯模拟的话，每次μ变换串的长度都会加倍，所以时间和空间复杂度都是O(2^n)，对于n为1000的情况，完全不可能计算出来。仔细观察这个树形结构，可以发现要出现"00"，一定是"10"和"01"相邻产生的。为了将问题简化，我们不妨设A = "10", B = "01"，构造出的树形递推图如图一 -1-8所示，如果要出现"00"，一定是AB（"1001"）。
 *        令FA[i]为A经过i次μ变换后"00"的数量，FA[0] = 0;FB[i]为B经过i次μ变换后"00"的数量，FB[0] = 0。
 *        从图中观察得出，以A为根的树，它的左子树的最右端点一定是B，也就是说无论经过多少次变换，两棵子树的交界处都不可能产生AB，所以FA[i] = FB[i-1] + FA[i-1]（直接累加两棵子树的"00"的数量）；而以B为根的树，它的左子树的右端点一定是A，而右子树的左端点呈BABABA...交替排布，所以隔代产生一次AB，于是FB[i] = FA[i-1] + FB[i-1] + (i mod 2) 。最后要求的答案就是FB[N-1]，递推求解。
 *
 *
 */
public class UTransfer {

    public int count(int n)
    {
        int[] FA = new int[n +1];
        int[] FB = new int[n +1];
        for (int i = 1; i <= n; i++) {
            FA[i] = FA[i -1] + FB[i -1];
            FB[i] = FA[i -1] + FB[i -1] + (i % 2);
        }
        return FB[n];
    }

    public static void main(String[] args) {
        UTransfer uTransfer = new UTransfer();
        for (int i = 1; i < 10; i++) {
            System.out.println(uTransfer.count(i));
        }

    }
}
