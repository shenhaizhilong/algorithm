package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/10/16 15:44
 */
public class H_IndexII {


    /**
     *275. H-Index II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
     *
     * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
     *
     * Example:
     *
     * Input: citations = [0,1,3,5,6]
     * Output: 3
     * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
     *              received 0, 1, 3, 5, 6 citations respectively.
     *              Since the researcher has 3 papers with at least 3 citations each and the remaining
     *              two with no more than 3 citations each, her h-index is 3.
     * Note:
     *
     * If there are several possible values for h, the maximum one is taken as the h-index.
     *
     * Follow up:
     *
     * This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
     * Could you solve it in logarithmic time complexity?
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int max = 0;
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, Math.min(len - i, citations[i]));
        }
        return max;
    }


    /**
     *
     * https://leetcode.com/problems/h-index-ii/discuss/71063/Standard-binary-search
     *Here I'm writing down my understanding based on Jerry's post to help those who are still confused about "why binary search".
     *
     * Before we dive into binary search, let's understand the original solution from wiki first.
     *
     * We have a descending-sorted array, citations[] and base-1 index of them.
     *
     * citations[i] 25, 8, 5, 3, 3
     * index[i]:     1, 2, 3, 4, 5
     * It's hard to think it over with just the concept "index". Let's change to another meaning.
     *
     * The index of citations[i] simply means the number of papers whose citations equal or larger than citations[i]
     * i.e. There are index[i] papers which have been cited at least citations[i] times.
     *
     * With this definition in mind, we can go further.
     *
     * For a certain i, say it's corresponding to index[i] = x and citations[i] = n. Then we have x papers which have citations number >= n.
     *
     * If n >= x, we will have x papers which have citations number >= n >= x.
     *
     * See now x will be a candidate for h-index.
     *
     * How to find the max candidate? Find from right to left until we first come across some n >= x, i.e. citations[i] >= index[i].
     *
     * This explains thinking detail of original solution. Now let's focus on binary search.
     *
     * What does binary search do? To find an element that meets some equality constraints. In this problem, we are searching citations[i] >= index[i] (conversion from 0-base index to 1-base see Jerry's post). If we are using standard binary search(wiki version) we have the same as @dong-wang-1694 's post:
     *
     * int left=0, len = citations.size(), right= len-1,  mid;
     *         while(left<=right)
     *         {
     *             mid=(left+right)>>1;
     *             if(citations[mid] == (len-mid)) return citations[mid];
     *             else if(citations[mid] > (len-mid)) right = mid - 1;
     *             else left = mid + 1;
     *         }
     *         return len - (right+1);
     * An example problem:
     *
     * citations[i]: 0, 1, 3, 5, 7
     * base-0 index: 0, 1, 2, 3, 4
     * base-1 index: 5, 4, 3, 2, 1
     * It's easy to understand case:citations[mid] == (len-mid) directly returns the result.
     * Then what if instead of 0, 1, 3, 5, 7 , we have 0, 1, 4, 5, 7 ?
     *
     * The algorithm will jump out op while loop. We know for binary search, if it cannot find the target, pointers left and right will be right besides the location which should be the target.
     *
     *      left
     *       v
     * 0, 1, 4, 5, 7
     *    ^
     *  right
     * Let's imagine there is a virtual candidate of h-index which is 3.5, and insert it into original array.
     *
     * citations[i]: 0, 1, (3.5), 4, 5, 7
     * base-1 index: 5, 4, (3.5), 3, 2, 1
     * base-0 index: 0, 1,        2, 3, 4
     * Thus from left to right to find the first i so that citations[i] >= base-1 index[i], we have left pointer pointing the result. And its 1-based index , i.e. len - left = len - (right+1) is the final answer.
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        int len = citations.length;
        int left = 0;
        int right = len-1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int h = len - mid;

            if (h < citations[mid]) {
                right = mid - 1;
            } else if (h > citations[mid]){
                left = mid + 1;
            }else {
                return h;
            }
        }
        return len-right-1;
    }

}
