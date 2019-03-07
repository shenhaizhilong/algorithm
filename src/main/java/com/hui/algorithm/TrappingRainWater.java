package com.hui.algorithm;

/**
 * @author: shenhaizhilong
 * @date: 2018/11/4 20:09
 *
 * 42. Trapping Rain Water
 * DescriptionHintsSubmissionsDiscussSolution
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 *
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        if(height == null || height.length < 1)return 0;
        int N = height.length;

        // max value from right side.
        int[] maxFromRight  = new int[N];
        int ans = 0;
        maxFromRight[N -1] = height[N-1];
        for (int i = N-2; i >=0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1], height[i]);
        }

        int maxFromLeft = height[0];
        for (int i = 1; i < N -1; i++) {
            maxFromLeft = Math.max(maxFromLeft, height[i]);
            ans += Math.min(maxFromLeft, maxFromRight[i]) - height[i];

        }
        return ans;

    }

    public int trap2(int[] height) {
        if(height == null || height.length < 2)return 0;
        int N = height.length;
        int left = 0;
        int right = N -1;
        int maxFromLeft = height[0];
        int maxFromRight = height[right];
        int ans = 0;
        while (left < right)
        {
            if(height[left] < height[right])
            {
                if(height[left] >= maxFromLeft)
                {
                    maxFromLeft = height[left];
                }else {
                    ans += maxFromLeft - height[left];
                }

                left++;
            }else {
                if(height[right] >= maxFromRight)
                {
                    maxFromRight = height[right];
                }else {
                    ans += maxFromRight - height[right];
                }
                right--;
            }
        }

        return ans;

    }

    public static void main(String[] args) {


        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trappingRainWater.trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
