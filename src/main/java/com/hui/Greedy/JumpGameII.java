package com.hui.Greedy;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/12 18:27
 */
public class JumpGameII {


    /**
     *45. Jump Game II
     * DescriptionHintsSubmissionsDiscussSolution
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * Example:
     *
     * Input: [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Note:
     *
     * You can assume that you can always reach the last index
     *
     *
     * The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd],
     * curFarthest is the farthest point that all points in [curBegin, curEnd] can reach. Once the current point reaches curEnd,
     * then trigger another jump,and set the new curEnd with curFarthest, then keep the above steps, as the following:
     * @param nums
     * @return
     */
    public int jump(int[] nums) {


        int curEnd = 0;
        int cufFarthest = 0;
        int jumpStep = 0;

        for (int i = 0; i <nums.length -1 ; i++) {
            cufFarthest = Math.max(cufFarthest, nums[i] + i);
            if(i == curEnd)
            {
                jumpStep++;
                curEnd = cufFarthest;
            }
            if(curEnd >= nums.length -1)break;

        }


        return jumpStep;
    }

    public static void main(String[] args) {

        JumpGameII jumpGameII = new JumpGameII();
        System.out.println(jumpGameII.jump(new int[]{2,100,1,1,4}));
        System.out.println(jumpGameII.jump(new int[]{1,2,3,1,1,1}));
        System.out.println(jumpGameII.jump(new int[]{2,3,1,0,4}));
    }
}
