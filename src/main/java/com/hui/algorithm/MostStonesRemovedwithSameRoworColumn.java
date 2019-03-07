package com.hui.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/1 11:49
 *
 *
 * 947. Most Stones Removed with Same Row or Column
 * DescriptionHintsSubmissionsDiscussSolution
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 *
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 *
 * What is the largest possible number of moves we can make?
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 1000
 * 0 <= stones[i][j] < 10000
 *
 */
public class MostStonesRemovedwithSameRoworColumn {

    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind(20000);
        for(int[] stone: stones)
        {
            unionFind.union(stone[0], stone[1] + 10000);
        }
        Set<Integer> seen = new HashSet<>();
        for(int[] stone: stones)
        {
            int p = unionFind.find(stone[0]);
            seen.add(p);
        }

        return stones.length - seen.size();
    }


    public static void main(String[] args) {

        MostStonesRemovedwithSameRoworColumn mostStonesRemovedwithSameRoworColumn = new MostStonesRemovedwithSameRoworColumn();
        System.out.println(mostStonesRemovedwithSameRoworColumn.removeStones(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}}));
    }
}
