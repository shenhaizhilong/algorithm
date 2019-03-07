package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/4 22:38
 *
 * 754. Reach a Number
 * DescriptionHintsSubmissionsDiscussSolution
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 *
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 *
 * Return the minimum number of steps required to reach the destination.
 *
 * Example 1:
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second step we step from 1 to 3.
 * Example 2:
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the first move we step from 0 to 1.
 * On the second move we step  from 1 to -1.
 * On the third move we step from -1 to 2.
 * Note:
 * target will be a non-zero integer in the range [-10^9, 10^9].
 *
 *
 *
 * We can always take abs(target), since the axis is symmetric.
 *
 * First of all we keep adding sum=1+2+..+n>=target, solve this quadratic equation gives the smallest n such that sum>=target.
 *
 * If 1+2+..+n==target, return n.
 *
 * Now we must minus res=sum-target. If res is even, we can flip one number x in [1,n] to be -x.
 *
 * Otherwise if res is odd, and n+1 is odd, we can first add n+1, then res is even. Next flip an x to be -x.
 *
 * If res is odd and n+1 is even, we add n+1 then subtract n+2, res becomes even, then flip an x.
 *
 *
 */
public class ReachaNumber {

    public int reachNumber(int target) {
        target = Math.abs(target);
        int k =(int) Math.ceil((-1 + Math.sqrt(1 + 8.0*target))/2);
        int sum = k*(k + 1)/2;
        int diff = sum - target;
        if((diff & 1) == 0)return k;
        int update = ((k + 1) & 1) == 1 ? 1: 2;
        return k + update;
    }

    public static void main(String[] args) {
        ReachaNumber reachaNumber = new ReachaNumber();
        for (int i = 1; i < 20; i++) {
            System.out.println(i + ", " + reachaNumber.reachNumber(i));
        }
    }
}
