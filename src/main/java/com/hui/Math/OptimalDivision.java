package com.hui.Math;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/4 10:17
 */
public class OptimalDivision {

    /**
     * 553. Optimal Division
     * DescriptionHintsSubmissionsDiscussSolution
     * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 2 / 3 / 4.
     *
     * However, you can add any number of parenthesis at any position to change the priority of operations. You should find out how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your expression should NOT contain redundant parenthesis.
     *
     * Example:
     * Input: [1000,100,10,2]
     * Output: "1000/(100/10/2)"
     * Explanation:
     * 1000/(100/10/2) = 1000/((100/10)/2) = 200
     * However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
     * since they don't influence the operation priority. So you should return "1000/(100/10/2)".
     *
     * Other cases:
     * 1000/(100/10)/2 = 50
     * 1000/(100/(10/2)) = 50
     * 1000/100/10/2 = 0.5
     * 1000/100/(10/2) = 2
     * Note:
     *
     * The length of the input array is [1, 10].
     * Elements in the given array will be in range [2, 1000].
     * There is only one optimal division for each test case.
     *
     *
     * 要让值最大，那么b/c/d/e 的组合应该最小
     * b 不变cde 的组合应该最大，最大显然是所有数相乘
     * 而且
     *  b/c/d/e... = b / ( cde... )
     *
     * 所以结果就是 a/(b/c/d/e...)
     *
     * @param nums
     * @return
     */
    public String optimalDivision(int[] nums) {
        if (nums.length == 1)
            return String.valueOf(nums[0]);
        if (nums.length == 2)
            return nums[0] + "/" +nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append('/');
            res.append(nums[i]);
        }
        res.append(')');
        return res.toString();
    }

    public static void main(String[] args) {
        OptimalDivision optimalDivision = new OptimalDivision();
        System.out.println(optimalDivision.optimalDivision(new int[]{1000,100,10,5,2}));
    }
}
