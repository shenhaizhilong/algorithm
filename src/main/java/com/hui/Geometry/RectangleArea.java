package com.hui.Geometry;

/**
 *
 *
 * 223. Rectangle Area
 * DescriptionHintsSubmissionsDiscussSolution
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 *
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 *
 * Rectangle Area
 *
 * Example:
 *
 * Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
 * Output: 45
 * Note:
 *
 * Assume that the total area is never beyond the maximum possible value of int.
 *
 * @author: shenhaizhilong
 * @date: 2018/9/18 11:23
 */
public class RectangleArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            int[] rec1 = {A,B,C,D};
            int[] rec2 = {E,F,G,H};
            int area1 = caleArea(A,B,C,D);
            int area2 = caleArea(E,F,G,H);
            return area1 + area2 - getIntersectionArea(rec1,rec2);

    }


    private int caleArea(int leftx,int lefty, int rightx, int righty)
    {
        return (rightx - leftx)*(righty - lefty);
    }

    private int getIntersectionArea(int[] rec1, int[] rec2)
    {
        if(!isRectangleOverlap(rec1, rec2))return 0;
        int leftx, lefty,  rightx,  righty;
        leftx = Math.max(rec1[0],rec2[0]);
        lefty = Math.max(rec1[1], rec2[1]);
        rightx = Math.min(rec2[2], rec1[2]);
        righty = Math.min(rec2[3], rec1[3]);
        return caleArea(leftx,lefty, rightx, righty);

    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }


    public static void main(String[] args) {
        RectangleArea rectangleArea = new RectangleArea();
        System.out.println(rectangleArea.computeArea(-3,0,3,4,0,-1,9,2));
    }

}
