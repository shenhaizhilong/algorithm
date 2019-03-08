package com.hui.Greedy;

/**
 * @author: shenhaizhilong
 * @date: 2018/9/7 10:48
 */
public class ContainerWithMostWater {


    /**
     *
     * 11. Container With Most Water
     * DescriptionHintsSubmissionsDiscussSolution
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
     *
     * Note: You may not slant the container and n is at least 2.
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < height.length -1; i++) {
            for (int j = i + 1; j < height.length ; j++) {
                if(i != j)
                {
                    maxArea = Math.max(maxArea, (j -i)*Math.min(height[i],height[j]));
                }
            }
        }

        return maxArea;
    }

    public int maxArea2(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length -1;
        while (left < right)
        {
            maxArea = Math.max(maxArea, (right - left)*Math.min(height[left], height[right]));
            if(height[left] < height[right])
            {
                left++;
            }else {
                right--;
            }
        }

        return maxArea;
    }



    public static void main(String[] args) {

        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        System.out.println(containerWithMostWater.maxArea2(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(containerWithMostWater.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        System.out.println(containerWithMostWater.maxArea2(new int[]{1,2,3,1,1,1,1}));
        System.out.println(containerWithMostWater.maxArea(new int[]{1,2,3,1,1,1,1}));
        System.out.println(containerWithMostWater.maxArea2(new int[]{1,2,3,4,5,6}));
        System.out.println(containerWithMostWater.maxArea(new int[]{1,2,3,4,5,6}));
    }
}
