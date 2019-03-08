package com.hui.BackTracking;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/12 16:11
 *
 *
 *
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        return backTracking(nums,0);
    }

    private boolean backTracking(int[] nums, int position)
    {
        if(position >= nums.length -1)
        {
            return true;
        }
        int maxJump = position + nums[position];
        for (int nextPosition = position +1; nextPosition <= maxJump; nextPosition++) {
            if(backTracking(nums, nextPosition))
            {
                return true;
            }
        }

        return false;
    }






    public boolean canJump2(int[] nums) {

        int[] memo = new int[nums.length];

        // good position set it to 1, bad postion set it to -1, unknown set it to 0
        memo[memo.length -1] = 1;
        return backTrackingMemo(nums,0,  memo);
    }



    private boolean backTrackingMemo( int[] nums,int position, int[] memo)
    {
        if(memo[position] != 0)
        {
            return memo[position] == 1; // good position
        }

        int maxJump = Math.min(position + nums[position], nums.length -1);
        for (int nextPosition = position + 1; nextPosition <= maxJump; nextPosition++) {
            if(backTrackingMemo(nums, nextPosition, memo))
            {
                return true;
            }
        }

        memo[position] = -1;  // bad position
        return false;
    }


    /**
     *
     * Top-down to bottom-up conversion is done by eliminating recursion.
     * In practice, this achieves better performance as we no longer have the method stack overhead and
     * might even benefit from some caching. More importantly, this step opens up possibilities for future optimization.
     * The recursion is usually eliminated by trying to reverse the order of the steps from the top-down approach.
     *
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {

        int[] memo = new int[nums.length];

        // good position set it to 1, bad postion set it to -1, unknown set it to 0
        memo[memo.length -1] = 1;
        for (int i = nums.length -2; i >=0 ; i--) {
            int maxJump = Math.min(i + nums[i], nums.length -1);
            for (int j = i +1; j <= maxJump; j++) {
                if(memo[j] == 1)
                {
                    memo[i] = 1;
                    break;
                }
            }
        }

        return memo[0] == 1;

    }


    /**
     *
     * https://leetcode.com/problems/jump-game/solution/
     * Once we have our code in the bottom-up state, we can make one final, important observation. From a given position, when we try to see if we can jump to a GOOD position, we only ever use one - the first one (see the break statement). In other words, the left-most one. If we keep track of this left-most GOOD position as a separate variable, we can avoid searching for it in the array. Not only that, but we can stop using the array altogether.
     *
     * Iterating right-to-left, for each position we check if there is a potential jump that reaches a GOOD index (currPosition + nums[currPosition] >= leftmostGoodIndex). If we can reach a GOOD index, then our position is itself GOOD. Also, this new GOOD position will be the new leftmost GOOD index. Iteration continues until the beginning of the array. If first position is a GOOD index then we can reach the last index from the first position.
     *
     * To illustrate this scenario, we will use the diagram below, for input array nums = [9, 4, 2, 1, 0, 2, 0]. We write G for GOOD, B for BAD and U for UNKNOWN. Let's assume we have iterated all the way to position 0 and we need to decide if index 0 is GOOD. Since index 1 was determined to be GOOD, it is enough to jump there and then be sure we can eventually reach index 6. It does not matter that nums[0] is big enough to jump all the way to the last index.
     * All we need is one way.
     * @param nums
     * @return
     */
    public boolean canJump4(int[] nums) {

        int leftMostGood  = nums.length -1;
        for (int i = nums.length -2; i >=0 ; i--) {
            int maxJump = i + nums[i];
            if(maxJump >= leftMostGood)
            {
                leftMostGood = i;
            }
        }

        return leftMostGood == 0;

    }

    public static void main(String[] args) {

        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(new int[]{3,2,1,0,4}));
        System.out.println(jumpGame.canJump(new int[]{2,3,1,1,4}));

        System.out.println(jumpGame.canJump2(new int[]{3,2,1,0,4}));
        System.out.println(jumpGame.canJump2(new int[]{2,3,1,1,4}));
        System.out.println(jumpGame.canJump3(new int[]{3,2,1,0,4}));
        System.out.println(jumpGame.canJump3(new int[]{2,3,1,1,4}));
        System.out.println(jumpGame.canJump4(new int[]{3,2,1,0,4}));
        System.out.println(jumpGame.canJump4(new int[]{2,3,1,1,4}));
    }
}
