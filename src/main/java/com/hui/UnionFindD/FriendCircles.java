package com.hui.UnionFindD;

import com.hui.Array.Matrix;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/26 10:31
 *
 *547. Friend Circles
 * DescriptionHintsSubmissionsDiscussSolution
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 *
 */
public class FriendCircles {

    public int findCircleNum(int[][] M) {
        int N = M.length;
        int[] parents = new int[N];
        int[] rank = new int[N];

        int count = N;
        for (int i = 0; i < M.length; i++) {
            parents[i] = i;
        }

        // up triangle
        for (int i = 0; i < N -1; i++) {
            for (int j = i +1; j < N; j++) {
                if(M[i][j] == 1)
                {

                    int pi = findParent(parents, i);
                    int pj = findParent(parents, j);
                    if(pi == pj)continue;
                    if(rank[pi] > rank[pj])
                    {
                        parents[pj] = pi;
                    }else {
                        parents[pi] = pj;
                        if(rank[pi] == rank[pj])
                        {
                            rank[pj]++;
                        }
                    }
                    count--;

                }
            }
        }
        Matrix.print(parents);
        return count;
    }

    public int findCircleNum2(int[][] M) {
        int N = M.length;
        int[] parents = new int[N];

        int count = N;
        for (int i = 0; i < M.length; i++) {
            parents[i] = i;
        }

        // down triangle
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(M[i][j] == 1)
                {

                    int pi = findParent(parents, i);
                    int pj = findParent(parents, j);
                    if(pi != pj)
                    {
                        count--;
                        parents[pi] = pj;
                    }


                }
            }
        }
        Matrix.print(parents);
        return count;
    }

    private int findParent(int[] parents, int idx)
    {
        while (idx != parents[idx])
        {
            parents[idx] = parents[parents[idx]];
            idx = parents[idx];
        }
        return idx;
    }




    class UnionFind {
        private int count = 0;
        private int[] parent, rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        //https://algs4.cs.princeton.edu/15uf/UF.java.html
        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            //将小树作为大树的子树, rootQ is deeper
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            } else {
                if(rank[rootP] == rank[rootQ])
                {
                    rank[rootP]++;
                }
                parent[rootQ] = rootP;
            }
            count--;
        }

        public int count() {
            return count;
        }
    }

    public int findCircleNum3(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }


    public static void main(String[] args) {
        FriendCircles friendCircles = new FriendCircles();
        int[][] matrix = {{1,1,1},{1,1,1},{1,1,1}};
        int[][] matrix2 = {{ 1,1,0},
                            {1,1,0},
                            {0,0,1}};

        int[][] matrix3 = {{1,0,0,1},
                           {0,1,1,0},
                           {0,1,1,1},
                           {1,0,1,1}};
        int[][] matrix4 = {
                {1,1,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,1,1,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0,1,1,0,0,0,0},
                {0,0,0,1,0,1,0,0,0,0,1,0,0,0,0},
                {0,0,0,1,0,0,1,0,1,0,0,0,0,1,0},
                {1,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,1,1,0,0,0,0,1,0},
                {0,0,0,0,1,0,0,0,0,1,0,1,0,0,1},
                {0,0,0,0,1,1,0,0,0,0,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,1,0,1,0,0,0,0,1,0},
                {0,0,0,0,0,0,0,0,0,1,0,0,0,0,1}};
        System.out.println(friendCircles.findCircleNum(matrix));
        System.out.println(friendCircles.findCircleNum(matrix2));
        System.out.println(friendCircles.findCircleNum(matrix3));
        System.out.println(friendCircles.findCircleNum(matrix4));


        System.out.println("********************");
//        System.out.println(friendCircles.findCircleNum2(matrix));
//        System.out.println(friendCircles.findCircleNum2(matrix2));
        System.out.println(friendCircles.findCircleNum2(matrix3));
        System.out.println(friendCircles.findCircleNum2(matrix4));
    }
}
