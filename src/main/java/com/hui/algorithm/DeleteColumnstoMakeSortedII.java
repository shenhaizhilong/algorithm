package com.hui.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: shenhaizhilong
 * @date: 2018/12/13 23:13
 *
 *
 * 955. Delete Columns to Make Sorted II
 * DescriptionHintsSubmissionsDiscussSolution
 * We are given an array A of N lowercase letter strings, all of the same length.
 *
 * Now, we may choose any set of deletion indices, and for each string, we delete all the characters in those indices.
 *
 * For example, if we have an array A = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3}, then the final array after deletions is ["bef","vyz"].
 *
 * Suppose we chose a set of deletion indices D such that after deletions, the final array has its elements in lexicographic order (A[0] <= A[1] <= A[2] ... <= A[A.length - 1]).
 *
 * Return the minimum possible value of D.length.
 *
 *
 *
 * Example 1:
 *
 * Input: ["ca","bb","ac"]
 * Output: 1
 * Explanation:
 * After deleting the first column, A = ["a", "b", "c"].
 * Now A is in lexicographic order (ie. A[0] <= A[1] <= A[2]).
 * We require at least 1 deletion since initially A was not in lexicographic order, so the answer is 1.
 * Example 2:
 *
 * Input: ["xc","yb","za"]
 * Output: 0
 * Explanation:
 * A is already in lexicographic order, so we don't need to delete anything.
 * Note that the rows of A are not necessarily in lexicographic order:
 * ie. it is NOT necessarily true that (A[0][0] <= A[0][1] <= ...)
 * Example 3:
 *
 * Input: ["zyx","wvu","tsr"]
 * Output: 3
 * Explanation:
 * We have to delete every column.
 *
 *
 */
public class DeleteColumnstoMakeSortedII {

    public int minDeletionSize(String[] A) {
        int ans = 0;
        int N = A.length;
        int M = A[0].length();
        boolean[] sorted = new boolean[N];  // if A[k] < A[k +1], then set A[k] = true;
        int j = 1;
        for (int i = 0; i < M; i++) {
            for (j = 1; j < N; j++) {
                if(!sorted[j -1] && A[j -1].charAt(i) > A[j].charAt(i))
                {
                    ans++;
                    break;
                }
            }

            if (j < N) continue; // delete column i
            int count = 0;
            for (int k = 0; k < N -1; k++) {
                if(A[k].charAt(i) < A[k +1].charAt(i)){
                    sorted[k] = true;
                    count++;
                }
            }
            // all sorted
            if(count == N -1)
                break;

        }


        return ans;

    }


    public static void main(String[] args) {
        DeleteColumnstoMakeSortedII deleteColumnstoMakeSortedII = new DeleteColumnstoMakeSortedII();
        System.out.println(deleteColumnstoMakeSortedII.minDeletionSize(new String[]{
                "xga",
                "xfb",
                "yfa"}));

        System.out.println(-5 & 0x01);
        Map<Integer,Integer> counter = new HashMap<>();
        counter.merge(1,1,Integer::sum);
        counter.merge(1,1,Integer::sum);
        System.out.println(counter.get(1));
    }


}
